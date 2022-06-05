let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function getTeamUsers() {
    let teamId = localStorage.getItem("teamId");
    if(teamId==null){
        teamId=0;
    }
    const url = "http://localhost:8080/CyclingTeamHealth_war/team/users?teamId="+teamId;
    createXMLHttpRequest();
    xmlHttp.open("GET", url, false);
    let user = '{"login":"' + localStorage.getItem("login") +
        '", "firstName":"' + localStorage.getItem("firstName") +
        '", "lastName":"' + localStorage.getItem("lastName") +
        '", "email":"' + localStorage.getItem("email") +
        '", "birthDate":"' + localStorage.getItem("birthDate") +
        '", "height":"' + localStorage.getItem("height") +
        '", "weight":"' + localStorage.getItem("weight") +
        '", "role":"' + localStorage.getItem("role") +
        '", "gender":"' + localStorage.getItem("gender") +
        '", "status":"' + localStorage.getItem("status") +
        '"}';
    xmlHttp.onreadystatechange = handleStateChange;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
}

function handleStateChange() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            jsonToHTML(xmlHttp.responseText);
        } else {
            document.location = "error.jsp";
        }
    }
}

function jsonToHTML(jsonString) {
    let jsonObject = JSON.parse(jsonString);
    let dataMap = new Map(Object.entries(jsonObject));
    let users = document.getElementById("users");
    let list = dataMap.get("teamMembers");
    let innerHTML = "";
    let role = localStorage.getItem("role");
    for (const element of list) {
        let usersObject = JSON.parse(element);
        let usersMap = new Map(Object.entries(usersObject));
        innerHTML += '<div><h6>' + usersMap.get("login") + '   |   ' + usersMap.get("firstName") + '   |   ' + usersMap.get("lastName")+'</h6>';
        if (role != null && role == 'admin') {
            innerHTML += '<div>' +
                '<button onClick=\'changeUserStatus("' +
                usersMap.get("login") +'", "' +
                usersMap.get("firstName") +'", "' +
                usersMap.get("lastName") +'", "' +
                usersMap.get("email") +'", "' +
                usersMap.get("status") +
                '")\'>' + usersMap.get("status") + '</button></div>';
        }
        if(localStorage.getItem("login")!=usersMap.get("login")){
            innerHTML += '<button onclick=\'openChat("'+usersMap.get("login")+'")\'>Open chat</button>';
        }
        innerHTML += '</div><hr/>';
    }
    users.innerHTML = innerHTML;
}

document.addEventListener("DOMContentLoaded", function(){
    getTeamUsers();
});

function openChat(receiver){
    const url = "http://localhost:8080/CyclingTeamHealth_war/chat?receiver="+receiver;
    createXMLHttpRequest();
    let user = '{"login":"' + localStorage.getItem("login") +
        '", "firstName":"' + localStorage.getItem("firstName") +
        '", "lastName":"' + localStorage.getItem("lastName") +
        '", "email":"' + localStorage.getItem("email") +
        '", "birthDate":"' + localStorage.getItem("birthDate") +
        '", "height":"' + localStorage.getItem("height") +
        '", "weight":"' + localStorage.getItem("weight") +
        '", "role":"' + localStorage.getItem("role") +
        '", "gender":"' + localStorage.getItem("gender") +
        '", "status":"' + localStorage.getItem("status") +
        '"}';
    xmlHttp.open("GET", url, false);
    xmlHttp.onreadystatechange = handleStateChangeGet;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();

}
function handleStateChangeGet() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            let jsonObject = JSON.parse(xmlHttp.responseText);
            let chatMap = new Map(Object.entries(jsonObject));
            localStorage.setItem("chatId", chatMap.get("id"));
            localStorage.setItem("chatName", chatMap.get("name"));
            document.location = "http://localhost:8080/CyclingTeamHealth_war/chat.jsp";
        } else {
            document.location = "error.jsp";
        }
    }
}

function changeUserStatus(login, firstName, lastName, email, status){
    if (status == 'active') {
        status = 'blocked';
    } else {
        status = 'active';
    }
    const url = "http://localhost:8080/CyclingTeamHealth_war/user";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, false);
    xmlHttp.onreadystatechange = handleStateChangePost;
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let user = '{"login":"' + localStorage.getItem("login") +
        '", "firstName":"' + localStorage.getItem("firstName") +
        '", "lastName":"' + localStorage.getItem("lastName") +
        '", "email":"' + localStorage.getItem("email") +
        '", "birthDate":"' + localStorage.getItem("birthDate") +
        '", "height":"' + localStorage.getItem("height") +
        '", "weight":"' + localStorage.getItem("weight") +
        '", "role":"' + localStorage.getItem("role") +
        '", "gender":"' + localStorage.getItem("gender") +
        '", "status":"' + localStorage.getItem("status") +
        '"}';
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    let params =
        "&login=" + login +
        "&firstName=" + firstName +
        "&lastName=" + lastName +
        "&email=" + email +
        "&status=" + status;
    xmlHttp.send(params);
    getFeedbacks();
}

function handleStateChangePost() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            document.location = "http://localhost:8080/CyclingTeamHealth_war/users.jsp";
        } else {
            document.location = "http://localhost:8080/CyclingTeamHealth_war/error.jsp";
        }
    }
}