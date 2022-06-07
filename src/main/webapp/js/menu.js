document.addEventListener("DOMContentLoaded", function() {
    if (localStorage.getItem("login") == null) {
        document.getElementById("userPresent1").setAttribute("hidden", "");
        document.getElementById("userPresent2").setAttribute("hidden", "");
        document.getElementById("userPresent3").setAttribute("hidden", "");
        document.getElementById("userPresent4").setAttribute("hidden", "");
    } else {
        document.getElementById("noUser1").setAttribute("hidden", "");
        document.getElementById("noUser2").setAttribute("hidden", "");
    }
    if (localStorage.getItem("login") == null || localStorage.getItem("role") != "admin") {
        document.getElementById("admin").setAttribute("hidden", "");
    }
    if (localStorage.getItem("login") == null || localStorage.getItem("role") ==="admin") {
        document.getElementById("teamMember").setAttribute("hidden", "");
    }
});

function logOut(){
    localStorage.removeItem("login");
    localStorage.removeItem("firstName");
    localStorage.removeItem("lastName");
    localStorage.removeItem("email");
    localStorage.removeItem("birthDate");
    localStorage.removeItem("role");
    localStorage.removeItem("height");
    localStorage.removeItem("weight");
    localStorage.removeItem("gender");
    localStorage.removeItem("status");
    document.location="http://localhost:8080/CyclingTeamHealth_war/logIn.jsp";
}