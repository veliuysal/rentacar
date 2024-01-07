document.addEventListener('DOMContentLoaded', function () {
    // Sayfa yüklendiğinde tüm arabaları getir
    updateTable();

    // Form submit olayı
    document.getElementById('addCarBtn').addEventListener('click', function () {
        // Form verilerini al
        var name = document.getElementById('name').value;

        // Fetch API ile veriyi backend'e gönder
        fetch('http://localhost:8080/api/car', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name: name }),
        })
            .then(response => response.json())
            .then(() => {
                // Formu temizle
                document.getElementById('name').value = '';

                // Tabloyu güncelle
                updateTable();
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    });
});

function updateTable() {
    fetch('http://localhost:8080/api/brand/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            var tableBody = document.getElementById('brandTable').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = '';

            data.forEach(function (brand) {
                var row = "<tr>" +
                    "<td>" + brand.id + "</td>" +
                    "<td>" + brand.name + "</td>" +
                    "<td>" +
                    "<button type='button' class='btn btn-info' data-toggle='modal' data-target='#editModal' onclick='openEditModal(" + brand.id + ")'>Edit</button> | " +
                    "<button type='button' class='btn btn-danger' onclick='deleteBrand(" + brand.id + ")'>Delete</button>" +
                    "</td>" +
                    "</tr>";

                tableBody.innerHTML += row;
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}
