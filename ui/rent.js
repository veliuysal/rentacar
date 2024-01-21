document.addEventListener("DOMContentLoaded", function () {
    addRentBtn.addEventListener("click", function () {
        // Form verilerini al
        var name = document.getElementById("name").value;
        // Fetch API ile veriyi backend'e gönder
        fetch("http://localhost:8080/api/brand", {
            method: "POST",
            headers: getDefaultHeaders(),
            body: JSON.stringify({ name: name }),
        })
            .then((response) => response.json())
            .then(() => {
                // Formu temizle
                document.getElementById("name").value = "";

                // Tabloyu güncelle
                updateTable();
            })
            .catch((error) => {
                console.error("Error:", error);
            });
    })
});