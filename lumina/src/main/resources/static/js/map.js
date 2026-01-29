const map = L.map('map').setView([42.6977, 23.3219], 12);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
}).addTo(map);

async function loadClubMarkers(genre = '', artist = '') {
    map.eachLayer((layer) => {
        if (layer instanceof L.marker) map.removeLayer(layer);
    });

    try {
        const response = await fetch(`/api/clubs/search?genre=${genre}&artist=${artist}`);
        const clubs = await response.json();

        clubs.forEach(club => {
            const marker = L.marker([club.latitude, club.longitude]).addTo(map);
            let statusColor = club.status === 'FULL' ? 'red' : (club.status === 'ALMOST_FULL' ? 'orange' : 'green');

            marker.bindPopup(`
                <div style="text-align: center;">
                    <h3 style="margin:0;">${club.name}</h3>
                    <p>${club.artist}</p>
                    <p style="color: ${statusColor}; font-weight: bold;">Статус: ${club.status}</p>
<!--                    <small>${club.genre}</small>-->
                </div>
            `);
        });
    } catch (e) { console.error(e); }
}

document.addEventListener('DOMContentLoaded', loadClubMarkers);