document.addEventListener('DOMContentLoaded', function () {
    // Sayfa yüklendiğinde tüm markaları getir
    fillBrands('brands');

    // Form submit olayı
    document.getElementById('addModelBtn').addEventListener('click', function () {
        // Form verilerini al
        var name = document.getElementById('name').value;
        var brand = document.getElementById('brands').value;

        // Fetch API ile veriyi backend'e gönder
        fetch('http://localhost:8080/api/model', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + token,
            },
            body: JSON.stringify({ name: name, brandId: brand }),
        })
            .then(response => response.json())
            .then(() => {
                // Formu temizle
                document.getElementById('name').value = '';

                // Tabloyu güncelle
                updateTable(-1);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    });
});

function updateTable(brandID) {
    var url = brandID != -1 ? 'http://localhost:8080/api/model/brand/' + brandID : 'http://localhost:8080/api/model/all';
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + token,
        }
    })
        .then(response => response.json())
        .then(data => {
            var tableBody = document.getElementById('modelTable').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = '';

            data.forEach(function (model) {
                var row = "<tr>" +
                    "<td>" + model.id + "</td>" +
                    "<td>" + model.name + "</td>" +
                    "<td>" + model.brand.name +
                    "<button type='button' class='btn btn-info' data-toggle='modal' data-target='#editModal' onclick='openEditModal(" + model.id + ")'>Edit</button> | " +
                    "<button type='button' class='btn btn-danger' onclick='deleteModel(" + model.id + ")'>Delete</button>" +
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
    fetch('http://localhost:8080/api/model/' + id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + token,
        }
    })
        .then(response => response.json())
        .then(model => {
            // Modal içindeki formu doldur
            document.getElementById('editModelId').value = model.id;
            document.getElementById('editModelName').value = model.name;

            fillBrands('editBrands', model.brand.id)
            // Modal'ı aç
            $('#editModal').modal('show');
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

// Düzenleme işlemi için fonksiyon
function editModel() {
    var id = document.getElementById('editModelId').value;
    var newName = document.getElementById('editModelName').value;
    var newBrandId = document.getElementById('editBrands').value;

    // Fetch API ile veriyi backend'e gönder
    fetch('http://localhost:8080/api/model', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + token,
        },
        body: JSON.stringify({ id: id, name: newName, brandId: newBrandId }),
    })
        .then(response => response.json())
        .then(() => {
            // Modal'ı kapat
            $('#editModal').modal('hide');

            // Tabloyu güncelle
            updateTable(-1);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

// Silme fonksiyonu
function deleteModel(id) {
    if (confirm('Are you sure you want to delete this Model?')) {
        // Fetch API ile veriyi backend'e gönder
        fetch('http://localhost:8080/api/model/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + token,
            },
        })
            .then(response => {
                if (response.ok) {
                    // Başarılı ise tabloyu güncelle
                    updateTable(-1);
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

function fillBrands(selectBrands, selected) {
    fetch('http://localhost:8080/api/brand/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + token,
        }
    })
        .then(response => response.json())
        .then(data => {
            var brands = document.getElementById(selectBrands);
            brands.innerHTML = "<option value='-1'>Tümü</option>";

            data.forEach(function (brand) {
                var row = " <option value=" + brand.id + (selected == brand.id ? " selected " : "") + ">" + brand.name + "</option>";

                brands.innerHTML += row;
            });
            updateTable(-1);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function getSelectedOption() {
    var selectedBrand = document.getElementById('brands').value;
    updateTable(selectedBrand);
}

