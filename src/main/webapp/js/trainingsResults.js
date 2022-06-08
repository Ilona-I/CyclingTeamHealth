let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function getTrainingsResults() {
    let url;
    if (localStorage.getItem("trainingId") == null) {
        url = "http://localhost:8080/CyclingTeamHealth_war/trainings/team/results?teamId=" + localStorage.getItem("teamId");
        document.getElementById("title").innerText = 'All trainings results';
    } else {
        url = "http://localhost:8080/CyclingTeamHealth_war/training/team/results?id=" + localStorage.getItem("trainingId");
        document.getElementById("title").innerText = 'Trainings results. Training id: '+ localStorage.getItem("trainingId");
    }
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
    let trainingsResults = document.getElementById("trainingsResults");
    let list = dataMap.get("results");
    let innerHTML = "<table><tr>" +
        "<th style='width: 200px;'>Training id</th>" +
        "<th style='width: 200px;'>Login</th>" +
        "<th style='width: 200px;'>Pulse</th>" +
        "<th style='width: 200px;'>Speed</th>" +
        "</tr>";
    for (const element of list) {
        let trainingObject = JSON.parse(element);
        let trainingMap = new Map(Object.entries(trainingObject));
        innerHTML += '<tr>' +
            '<th>' + trainingMap.get("trainingId") + '</th>' +
            '<th>' + trainingMap.get("login") + '</th>' +
            '<th>' + trainingMap.get("pulse") + '</th>' +
            '<th>' + trainingMap.get("speed") + '</th>' +
            '</tr>';
    }
    innerHTML += "</table>";
    trainingsResults.innerHTML = innerHTML;
}

document.addEventListener("DOMContentLoaded", function() {
    getTrainingsResults();
});