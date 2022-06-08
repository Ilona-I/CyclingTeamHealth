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
    let myMap = new Map(Object.entries(jsonObject));
    const dataMap = new Map([...myMap.entries()].sort((a, b) => b[1] - a[1]));
    let teams = document.getElementById("teams");
    let innerHTML = "";
    for (const key of dataMap.keys()) {
        innerHTML += '<div id = "'+key+'" class="row " style="background-color: rgba(203,255,226,0.34);">' +
            '<div style="width: 47%; text-align: right; margin-top:10px;">'+
            '<p style="font-size: x-large; color: #353637">' +
            key +
            '</p></div>' +
            '<div style="width: 47%; margin-left:6%;text-align: left;  margin-top:10px;">' +
            '<p style="font-size: x-large; font-weight: bold">' +
            dataMap.get(key) +
            '</p></div>' +
            '</div>' +
            '<hr/>';
    }
    teams.innerHTML = innerHTML;
}



document.addEventListener("DOMContentLoaded", function(){
    getTeams();
});
