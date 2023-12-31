document.addEventListener('DOMContentLoaded', function () {
    // Sayfa yüklendiğinde tüm markaları getir
    updateTable();

    // Form submit olayı
    document.getElementById('addBrandBtn').addEventListener('click', function () {
        // Form verilerini al
        var name = document.getElementById('name').value;

        // Fetch API ile veriyi backend'e gönder
        fetch('http://localhost:8080/api/brand', {
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

function openEditModal(id) {
    // Modal'ı açmadan önce mevcut marka bilgilerini al
    fetch('http://localhost:8080/api/brand/' + id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(brand => {
            // Modal içindeki formu doldur
            document.getElementById('editBrandId').value = brand.id;
            document.getElementById('editBrandName').value = brand.name;

            // Modal'ı aç
            $('#editModal').modal('show');
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

// Düzenleme işlemi için fonksiyon
function editBrand() {
    var id = document.getElementById('editBrandId').value;
    var newName = document.getElementById('editBrandName').value;

    // Fetch API ile veriyi backend'e gönder
    fetch('http://localhost:8080/api/brand', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({ id: id, name: newName }),
    })
        .then(response => response.json())
        .then(() => {
            // Modal'ı kapat
            $('#editModal').modal('hide');

            // Tabloyu güncelle
            updateTable();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

// Silme fonksiyonu
function deleteBrand(id) {
    if (confirm('Are you sure you want to delete this brand?')) {
        // Fetch API ile veriyi backend'e gönder
        fetch('http://localhost:8080/api/brand/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then(response => {
                if (response.ok) {
                    // Başarılı ise tabloyu güncelle
                    updateTable();
                } else {
                    // Başarısız ise hata mesajını logla
                    console.error('Error:', response.status);
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }
}

    