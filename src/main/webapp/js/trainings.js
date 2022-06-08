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
    let innerHTML = "<table><tr>" +
        "<th style='width: 100px;'>Training id</th>" +
        "<th style='width: 100px;'>Pulse</th>" +
        "<th style='width: 100px;'>Speed</th>" +
        "<th style='width: 350px;'>Start time</th>" +
        "<th style='width: 350px;'>End time</th>" +
        "<th style='width: 200px;'></th></tr>";
    for (const element of list) {
        let trainingObject = JSON.parse(element);
        let trainingMap = new Map(Object.entries(trainingObject));
        innerHTML += '<tr>' +
            '<th>' + trainingMap.get("id") + '</th>' +
            '<th>' + trainingMap.get("pulse") + '</th>' +
            '<th>' + trainingMap.get("speed") + '</th>' +
            '<th>' + trainingMap.get("startDateTime") + '</th>' +
            '<th>' + trainingMap.get("endDateTime") + '</th>' +
            '<th><button style="height: 40px; width: 300px; background-color: rgba(235,255,55,0.6); color: #6b461d; border-style: dotted; border-width: 2px; font-size: 18px; " onclick=\'openTeamTrainingResults("' + trainingMap.get("id") + '")\'>Get team results</button></th>' +
            '</tr>';
    }
    innerHTML+="</table>";
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