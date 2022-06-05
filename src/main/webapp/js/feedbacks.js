let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function getFeedbacks() {
    const url = "http://localhost:8080/CyclingTeamHealth_war/feedbacks";
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
    let dataMap = new Map(Object.entries(jsonObject));
    let feedbacks = document.getElementById("feedbacks");
    let list = dataMap.get("feedbacks");
    let innerHTML = "";
    let role = localStorage.getItem("role");
    for (const element of list) {
        let feedbackObject = JSON.parse(element);
        let feedbackMap = new Map(Object.entries(feedbackObject));
        innerHTML += '<div><h6>' + feedbackMap.get("login") + '   |   ' + feedbackMap.get("dateTime") + '</h6>' +
            '<p>' + feedbackMap.get("text") + '</p>';
        if (role != null && role == 'admin') {
            innerHTML += '<div>' +
                '<button onClick=\'changeFeedbackStatus("' + feedbackMap.get("id") + '", "' +
                feedbackMap.get("login") + '", "' +
                feedbackMap.get("dateTime") + '", "' +
                feedbackMap.get("rating") + '", "' +
                feedbackMap.get("text") + '", "' +
                feedbackMap.get("status") + '")\'>' + feedbackMap.get("status") + '</button></div>';
        }
        innerHTML += '</div><hr/>';
    }
    feedbacks.innerHTML = innerHTML;
}

document.addEventListener("DOMContentLoaded", function(){
    getFeedbacks();
});

function changeFeedbackStatus(id, login, dateTime, rating, text, status) {
    if (status == 'active') {
        status = 'blocked';
    } else {
        status = 'active';
    }
    const url = "http://localhost:8080/CyclingTeamHealth_war/feedback/update";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, false);
    xmlHttp.onreadystatechange = handleStateChangePost;
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let params = "id=" + id +
        "&login=" + login +
        "&dateTime=" + dateTime +
        "&rating=" + rating +
        "&text=" + text +
        "&status=" + status;
    xmlHttp.send(params);
    getFeedbacks();
}

function handleStateChangePost() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            document.location = "http://localhost:8080/CyclingTeamHealth_war/feedbacks.jsp";
        } else {
            document.location = "http://localhost:8080/CyclingTeamHealth_war/error.jsp";
        }
    }
}

