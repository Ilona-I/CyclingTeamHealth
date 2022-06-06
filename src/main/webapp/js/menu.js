document.addEventListener("DOMContentLoaded", function() {
    if (localStorage.getItem("login") == null) {
        document.getElementById("userPresent").innerText='';
    } else {
        document.getElementById("userLogin").innerText=localStorage.getItem("login")+'  |  '+localStorage.getItem("role");
        document.getElementById("noUser").innerText='';
    }
    if (localStorage.getItem("login") == null || localStorage.getItem("role") != "admin") {
        document.getElementById("admin").innerText='';
    }
    if (localStorage.getItem("login") == null || localStorage.getItem("role") ==="admin") {
        document.getElementById("teamMember").innerText='';
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