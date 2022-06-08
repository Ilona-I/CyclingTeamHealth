let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function getChat() {
    const url = "http://localhost:8080/CyclingTeamHealth_war/chat?chatId="+localStorage.getItem("chatId");
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
    let chat = document.getElementById("chat");
    let list = dataMap.get("messages")
    let innerHTML = "";
    for (const element of list) {
        let message = JSON.parse(element);
        let messageMap = new Map(Object.entries(message));
        innerHTML += '<div style="background-color: white; margin-top: 20px;"><div style="margin-left: 5%;  width: 90%;"><div style="height: 5px;"></div>' +
            '<p>' + messageMap.get("sender") +  '</p>'+
            ' <span style="font-style: italic; font-size: small; float: right;">  ' + messageMap.get("dateTime").slice(0, 10) + '</span><hr style="margin-top: -10px">' +
            '<p>' +messageMap.get("text") + '</p>' +
            '</div><hr style="margin-top: -1px"></div><hr/>';
    }
    chat.innerHTML = innerHTML;
}

document.addEventListener("DOMContentLoaded", function(){
    document.getElementById("chatName").innerText='Chat: '+localStorage.getItem("chatName");
    getChat();
});

function sendMessage() {
    let text = document.getElementById("text").value;
    const url = "http://localhost:8080/CyclingTeamHealth_war/chat";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, false);
    xmlHttp.onreadystatechange = handleStateChange;
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
    let params = 'text=' + text +
        '&chatId=' + localStorage.getItem("chatId") +
        '&sender=' + localStorage.getItem("login");
    xmlHttp.send(params);
    getChat();
}