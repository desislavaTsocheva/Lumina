const map = L.map('map').setView([42.6977, 23.3219], 12);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
}).addTo(map);

const icons = {
    FREE: new L.Icon({
        iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    }),
    ALMOST_FULL: new L.Icon({
        iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-orange.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    }),
    FULL: new L.Icon({
        iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    })
};

async function loadClubMarkers(genre = '', artist = '') {
    const genreValue = (typeof genre === 'string') ? genre : '';
    const artistValue = (typeof artist === 'string') ? artist : '';

    console.log(`Зареждане на клубове - Жанр: "${genreValue}", Артист: "${artistValue}"`);

    map.eachLayer((layer) => {
        if (layer instanceof L.Marker) {
            map.removeLayer(layer);
        }
    });

    try {
        const response = await fetch(`/api/clubs/search?genre=${encodeURIComponent(genreValue)}&artist=${encodeURIComponent(artistValue)}`);

        if (!response.ok) {
            throw new Error(`Сървърна грешка: ${response.status}`);
        }

        const clubs = await response.json();

        if (!Array.isArray(clubs)) {
            throw new Error("API-то не върна масив от данни.");
        }

        clubs.forEach(club => {
            if (club.latitude && club.longitude) {
                const clubIcon = icons[club.status] || icons.FREE;

                const marker = L.marker([club.latitude, club.longitude], { icon: clubIcon }).addTo(map);
                let statusTextColor = '#2ecc71';
                if (club.status === 'FULL') statusTextColor = '#e74c3c';
                if (club.status === 'ALMOST_FULL') statusTextColor = '#f39c12';

                marker.bindPopup(`
                    <div style="text-align: center; min-width: 170px; font-family: 'Arial', sans-serif;">
                        <h3 style="margin: 0 0 5px 0; color: #2c3e50;">${club.name}</h3>
                        <p style="margin: 5px 0; font-size: 14px;"> <b>Артист:</b> ${club.artist || 'Няма информация'}</p>
                        <p style="margin: 5px 0; font-size: 14px; color: ${statusTextColor}; font-weight: bold;">
                            Статус: ${club.status}
                        </p>
                        <hr style="border: 0; border-top: 1px solid #eee; margin: 10px 0;">
                        <a href="https://www.google.com/maps/dir/?api=1&destination=${club.latitude},${club.longitude}" 
                           target="_blank" 
                           style="display: inline-block; padding: 5px 10px; background: #3498db; color: white; border-radius: 4px; text-decoration: none; font-size: 12px;">
                            Упътване до клуба
                        </a>
                    </div>
                `);
            }
        });

    } catch (e) {
        console.error("Грешка при зареждане на маркерите:", e.message);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    loadClubMarkers();
});