var map;
function initMap() {

    var ruaVergueiro = {
        lat : -23.587469,
        lng : -46.633480
    };

    var mapElement = document.getElementById("map");
    if (mapElement) {
        map = new google.maps.Map(mapElement, {
            center: ruaVergueiro,
            zoom: 13
        });

        for (index = 0; index < alunos.length; ++index) {
                var latitude = alunos[index].contato.coordinates[0];
                var longitude = alunos[index].contato.coordinates[1];
                var coordenadas = {
                        lat : latitude,
                        lng : longitude
                    };
                var marker = new google.maps.Marker({
                    position : coordenadas,
                    label: alunos[index].nome
                });
                marker.setMap(map);
            }
    }
}