document.addEventListener('DOMContentLoaded', function () {
    var jwtToken=localStorage.getItem('jwtToken');
    if(!jwtToken){
        window.location.href = "login.html";
    }
});
