let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function logIn() {
    const url = "http://localhost:8080/CyclingTeamHealth_war/logIn";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = handleStateChange;
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let params = "login="+login+"&password="+password;
    xmlHttp.send(params);
}

function handleStateChange() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            document.location = "http://localhost:8080/CyclingTeamHealth_war/profile.jsp";
            let user = b64DecodeUnicode(xmlHttp.getResponseHeader("Authorization"));
            saveUserInLocalStorage(user);
        }
        if(xmlHttp.status == 400){
            onError();
        }
    }
}

function onError(){
    jsonToHTML(xmlHttp.responseText);
}

function jsonToHTML(jsonString) {
    let jsonObject = JSON.parse(jsonString);
    let dataMap = new Map(Object.entries(jsonObject));
    let wrongLogin = document.getElementById("wrongLogin");
    let wrongPassword = document.getElementById("wrongPassword");
    let list = dataMap.get("validationErrors");
    if(list.some(e => e=="wrongLogin")){
        wrongLogin.innerText = "Wrong login";
    }
    if(list.some(e => e=="wrongPassword")) {
        wrongPassword.innerText = "Wrong password";
    }
}

function saveUserInLocalStorage(jsonString) {
    let jsonObject = JSON.parse(jsonString);
    let dataMap = new Map(Object.entries(jsonObject));
    for (const key of dataMap.keys()) {
        localStorage.setItem(key, dataMap.get(key));
    }
}

function b64DecodeUnicode(str) {
    return decodeURIComponent(atob(str).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
}
