let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function getTeams() {
    const url = "http://localhost:8080/CyclingTeamHealth_war/teams";
    createXMLHttpRequest();
    xmlHttp.open("GET", url, true);
    xmlHttp.onreadystatechange = handleStateChange;
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    console.log("18");
    xmlHttp.send(null);
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
    let teams = document.getElementById("teams");
    let innerHTML = "";
    for (const key of dataMap.keys()) {
        innerHTML += '<div>' + '<h6>' + key + '</h6>' + '<p>' + dataMap.get(key) + '</p>' + '</div><hr/>'
    }
    teams.innerHTML = innerHTML;
}



document.addEventListener("DOMContentLoaded", function(){
    getTeams();
});
