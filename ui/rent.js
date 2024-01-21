document.addEventListener("DOMContentLoaded", function () {

    // Sayfa yüklendiğinde tüm kiralamaları getir
    updateTable();

    addRentBtn.addEventListener("click", function () {
        // Form verilerini al
        var carId = document.getElementById("cars").value;
        var price = document.getElementById("price").value;
        var startDate = document.getElementById("startDate").value;
        var endDate = document.getElementById("endDate").value;
        var kilometers = document.getElementById("kilometers").value;
        var fuelTank = document.getElementById("fuelTank").value;
        // Fetch API ile veriyi backend'e gönder
        fetch("http://localhost:8080/api/rent", {
            method: "POST",
            headers: getDefaultHeaders(),
            body: JSON.stringify({ carId, price, startDate, endDate, kilometers, fuelTank }),
        })
            .then((response) => response.json())
            .then(() => {
                // Formu temizle
                document.getElementById("rentForm").reset();

                // Tabloyu güncelle
                updateTable();
            })
            .catch((error) => {
                console.error("Error:", error);
            });
    })
});

function updateTable() {
    fetch('http://localhost:8080/api/rent/all', {
        method: 'GET',
        headers: getDefaultHeaders(),
    })
        .then(response => response.json())
        .then(data => {
            var tableBody = document.getElementById('rentTable').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = '';

            data.forEach(function (rent) {
                var row = "<tr>" +
                    "<td>" + rent.id + "</td>" +
                    "<td>" + rent.car.model.brand.name + rent.car.model.name + "</td>" +
                    "<td>" + rent.price + "</td>" +
                    "<td>" + rent.startDate + "</td>" +
                    "<td>" + rent.endDate + "</td>" +
                    "<td>" + rent.kilometers + "</td>" +
                    "<td>" + rent.fuelTank.value + "</td>" +
                    "</tr>";

                tableBody.innerHTML += row;
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function fillCars() {
    fetch('http://localhost:8080/api/car/all', {
        method: 'GET',
        headers: getDefaultHeaders(),
    })
        .then(response => response.json())
        .then(data => {
            var cars = document.getElementById("cars");
            cars.innerHTML = "<option value='-1'>Tümü</option>";

            data.forEach(function (car) {
                var row = " <option value=" + car.id + (selected == brand.id ? " selected " : "") + ">" + car.name + "</option>";

                cars.innerHTML += row;
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}


function fillFuelTanks() {
    fetch('http://localhost:8080/api/common/fuel-tanks', {
        method: 'GET',
        headers: getDefaultHeaders(),
    })
        .then(response => response.json())
        .then(data => {
            var fuelTanks = document.getElementById("fuelTanks");
            fuelTanks.innerHTML = "<option value='-1'>Tümü</option>";

            data.forEach(function (fuelTank) {
                var row = " <option value=" + fuelTank.ordinal + (selected == fuelTank.ordinal ? " selected " : "") + ">" + fuelTank.value + "</option>";

                fuelTanks.innerHTML += row;
            });
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}