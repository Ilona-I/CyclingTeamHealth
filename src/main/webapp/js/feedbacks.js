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
        if (feedbackMap.get("status") == "active" || localStorage.getItem("role") == "admin") {
            innerHTML += '<div style="width: 30%; float: left;">' +
                '<div style="margin-top: 40px; background-color: #82b295; width: 90%; margin-right: auto; margin-left: auto;">' +
                '<hr>' +
                '   <div class="row" style="width: 90%; margin-right: auto; margin-left: auto; margin-top: 10px; color: white;">' +
                '       <div style="width: 79%; font-style: italic; font-size: 17px;">' +
                '           <p>' + feedbackMap.get("login") +
                '           </p>' +
                '       </div>' +
                '       <div style="width: 21%; font-size: 14px;">' +
                '               <p>' + feedbackMap.get("dateTime").slice(0, 10) +
                '               </p>' +
                '       </div>' +
                '   </div>' +
                '   <div style="background-color:  #FEF4CE">' +
                '       <div style="width: 90%; margin-right: auto; margin-left: auto;">' +
                '           <div style="height: 5px;">' +
                '           </div>' +
                '           <div class="rating-mini" style="">' +
                '               <span ' + getRating(parseInt(feedbackMap.get("rating")), 0) + '></span>' +
                '               <span ' + getRating(parseInt(feedbackMap.get("rating")), 1) + '></span>' +
                '               <span ' + getRating(parseInt(feedbackMap.get("rating")), 2) + '></span>' +
                '               <span ' + getRating(parseInt(feedbackMap.get("rating")), 3) + '></span>' +
                '               <span ' + getRating(parseInt(feedbackMap.get("rating")), 4) + '></span>' +
                '          </div>' +
                '          <p style="color: #353637">' + feedbackMap.get("text") +
                '          </p>' +
                '       </div>';
            console.log(innerHTML);
            if (role != null && role == 'admin') {
                innerHTML += '<div class="row" style="margin-left: 10px;">' +
                    '<button style="height: 40px;  background-color: transparent; color: #220a02; border-style: dotted; border-width: 2px; " ' +
                    'onClick=\'changeFeedbackStatus("' + feedbackMap.get("id") + '", "' +
                    feedbackMap.get("login") + '", "' +
                    feedbackMap.get("dateTime") + '", "' +
                    feedbackMap.get("rating") + '", "' +
                    feedbackMap.get("text") + '", "' +
                    feedbackMap.get("status") + '")\'>' + feedbackMap.get("status") +
                    '</button>' +
                    '</div>';
            }
            innerHTML += '<div style="height: 10px; "></div>' +
                '</div>' +
                '</div>' +
                ' <hr style="width: 90%; margin-right: auto; margin-left: auto;">' +
                '</div>';
        }
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

function getRating(rating, number) {
    return rating > number ? 'class="active"' : ''
}
