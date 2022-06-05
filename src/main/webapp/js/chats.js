let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function getChats() {
    const url = "http://localhost:8080/CyclingTeamHealth_war/chats";
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
    xmlHttp.onreadystatechange = handleStateChange;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Authorization", btoa(encodeURIComponent(user)));
    xmlHttp.send();
}

function handleStateChange() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            jsonToHTML(xmlHttp.responseText);
        } else {
            document.location = "error.jsp";
        }
    }
}

function jsonToHTML(jsonString) {
    let jsonObject = JSON.parse(jsonString);
    let dataMap = new Map(Object.entries(jsonObject));
    let chats = document.getElementById("chats");
    let innerHTML = "";
    for (const key of dataMap.keys()) {
        innerHTML += '<div>' +
            '<p>' + dataMap.get(key) + '</p>' +
            '<button onclick=\'openChat("' + key + '", "' + dataMap.get(key) + '")\'>Open</button>' +
            '</div><hr/>';
    }
    chats.innerHTML = innerHTML;
}

document.addEventListener("DOMContentLoaded", function(){
    getChats();
});

function openChat(id, name) {
    localStorage.setItem("chatId", id);
    localStorage.setItem("chatName", name);
    document.location = "http://localhost:8080/CyclingTeamHealth_war/chat.jsp";
}