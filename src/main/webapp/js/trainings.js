let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function getTrainings() {
    const url = "http://localhost:8080/CyclingTeamHealth_war/training/goals?teamId="+localStorage.getItem("teamId");
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
    let trainings = document.getElementById("trainings");
    let list = dataMap.get("trainingGoals");
    let innerHTML = "";
    for (const element of list) {
        let trainingObject = JSON.parse(element);
        let trainingMap = new Map(Object.entries(trainingObject));
        innerHTML += '<div>' +
            '<p>Training id: ' + trainingMap.get("id") + '</p>' +
            '<p>Pulse: ' + trainingMap.get("pulse") + '</p>' +
            '<p>Speed: ' + trainingMap.get("speed") + '</p>' +
            '<p>Start time: ' + trainingMap.get("startDateTime") + '</p>' +
            '<p>End time: ' + trainingMap.get("endDateTime") + '</p>' +
            '<button onclick=\'openTeamTrainingResults("' + dataMap.get("id") + '")\'>Get team results</button>' +
            '</div><hr/>';
    }
    trainings.innerHTML = innerHTML;
}

document.addEventListener("DOMContentLoaded", function(){
    getTrainings();
});

function openTeamTrainingResults(id) {
    localStorage.setItem("trainingId", id);
    document.location = "http://localhost:8080/CyclingTeamHealth_war/trainingsResults.jsp";
}

function openTeamTrainingsResults(){
    localStorage.removeItem("trainingId");
    document.location = "http://localhost:8080/CyclingTeamHealth_war/trainingsResults.jsp";
}