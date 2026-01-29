const map = L.map('map').setView([42.6977, 23.3219], 12);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
}).addTo(map);

async function loadClubMarkers(genre = '', artist = '') {

    const genreValue = (typeof genre === 'string') ? genre : '';
    const artistValue = (typeof artist === 'string') ? artist : '';

    console.log(`Зареждане на клубове с филтър - Жанр: "${genreValue}", Артист: "${artistValue}"`);

    map.eachLayer((layer) => {
        if (layer instanceof L.marker) {
            map.removeLayer(layer);
        }
    });

    try {
        const response = await fetch(`/api/clubs/search?genre=${encodeURIComponent(genreValue)}&artist=${encodeURIComponent(artistValue)}`);
        if (!response.ok) {
            throw new Error(`Сървърът върна грешка: ${response.status}. Провери дали API-то съществува.`);
        }

        const clubs = await response.json();

        if (!Array.isArray(clubs)) {
            throw new Error("Очакваният JSON не е масив. Провери контролера в Spring Boot.");
        }

        clubs.forEach(club => {
            if (club.latitude && club.longitude) {
                const marker = L.marker([club.latitude, club.longitude]).addTo(map);

                let statusColor = 'green';
                if (club.status === 'FULL') statusColor = 'red';
                else if (club.status === 'ALMOST_FULL') statusColor = 'orange';
                marker.bindPopup(`
                    <div style="text-align: center; min-width: 150px;">
                    <h3 style="margin:0; color: #333;">${club.name}</h3> 
                    <p style="margin: 5px 0;"><b>Артист:</b> ${club.artist}</p>
                    <p style="color: ${club.status === 'FREE' ? 'green' : 'red'}; font-weight: bold; margin: 0;">
                        Статус: ${club.status}</p>
                    <a href="https://www.google.com/maps/search/?api=1&query=${club.latitude},${club.longitude}" target="_blank">
                        Упътване до клуба </a>
                    </div>
                `);
            }
        });

    } catch (e) {
        console.error("Грешка при обработка на данните:", e.message);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    loadClubMarkers();
});