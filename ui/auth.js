function getDefaultHeaders() {
  var token = localStorage.getItem("jwtToken");
  var headers = {
    "Content-Type": "application/json",
    Authorization: "Bearer " + token,
  };
  return headers;
}

function redirectToLogin() {
  window.location.href = "login.html";
}

function request_no_body(_url, _method, callback) {
  fetch(_url, {
    method: _method,
    headers: getDefaultHeaders,
  })
    .then((response) => response.json())
    .then((data) => {
      callback(data);
    })
    .catch((error) => {
      console.error("Error:", error);
      redirectToLogin();
    });
}
