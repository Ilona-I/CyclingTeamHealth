let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function signUp() {
    const url = "http://localhost:8080/CyclingTeamHealth_war/signUp";
    createXMLHttpRequest();
    xmlHttp.open("POST", url, false);
    xmlHttp.onreadystatechange = handleStateChange;
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let repeatedPassword = document.getElementById("repeatedPassword").value;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let email = document.getElementById("email").value;
    let birthDate = document.getElementById("birthDate").value;
    let height = document.getElementById("height").value;
    let weight = document.getElementById("weight").value;
    let role = radio("role");
    let gender = radio("gender");
    let teamType = radio("teamType");
    let teamId = document.getElementById("teamId").value;
    let teamName = document.getElementById("teamName").value;
    let params = 'login=' + login +
        '&password=' + password +
        '&repeatedPassword=' + repeatedPassword +
        '&firstName=' + firstName +
        '&lastName=' + lastName +
        '&email=' + email +
        '&teamType=' + teamType +
        '&birthDate=' + birthDate +
        '&height=' + height +
        '&weight=' + weight +
        '&teamId=' + teamId +
        '&teamName=' + teamName +
        '&role=' + role +
        '&gender=' + gender;
    xmlHttp.send(params);
}

function radio(radioName) {
    const radioButtons = document.querySelectorAll('input[name='+radioName+']');
    for (const radioButton of radioButtons) {
        if (radioButton.checked) {
            window.alert(radioButton.value);
            return radioButton.value;
            break;
        }
    }
}

function handleStateChange() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            let user = b64DecodeUnicode(xmlHttp.getResponseHeader("Authorization"));
            window.alert(user)
            saveUserInLocalStorage(user);
            document.location = "http://localhost:8080/CyclingTeamHealth_war/profile.jsp";
        }
        if (xmlHttp.status == 400) {
            onError();
        }
    }
}

function onError() {
    jsonToHTML(xmlHttp.responseText);
}

function jsonToHTML(jsonString) {
    let jsonObject = JSON.parse(jsonString);
    let dataMap = new Map(Object.entries(jsonObject));
    let wrongFirstName = document.getElementById("wrongFirstName").value;
    let wrongLastName = document.getElementById("wrongLastName").value;
    let wrongEmail = document.getElementById("wrongEmail").value;
    let wrongBirthDate = document.getElementById("wrongBirthDate").value;
    let wrongHeight = document.getElementById("wrongHeight").value;
    let wrongWeight = document.getElementById("wrongWeight").value;
    let list = dataMap.get("validationErrors");
    if (list.some(e => e == "wrongLogin")) {
        wrongFirstName.innerText = "Wrong login";
    }
    if (list.some(e => e == "wrongFirstName")) {
        wrongFirstName.innerText = "Wrong First Name";
    }
    if (list.some(e => e == " wrongLastName")) {
        wrongLastName.innerText = "Wrong Last Name";
    }
    if (list.some(e => e == "wrongEmail")) {
        wrongEmail.innerText = "Wrong Email";
    }
    if (list.some(e => e == "wrongBirthDate ")) {
        wrongBirthDate.innerText = "Wrong Birth Date ";
    }
    if (list.some(e => e == "wrongHeight")) {
        wrongHeight.innerText = "Wrong Height";
    }
    if (list.some(e => e == "wrongWeight")) {
        wrongWeight.innerText = "Wrong Weight";
    }
}

function saveUserInLocalStorage(jsonString) {
    let jsonObject = JSON.parse(jsonString);
    let dataMap = new Map(Object.entries(jsonObject));
    for (const key of dataMap.keys()) {
        localStorage.removeItem(key);
        localStorage.setItem(key, dataMap.get(key));
    }
}

function b64DecodeUnicode(str) {
    return decodeURIComponent(atob(str).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
}

function createTeam(){
    document.getElementById("enterTeamId").setAttribute("hidden", "");
    document.getElementById("createTeam").removeAttribute("hidden");
}

function enterTeamId(){
    document.getElementById("createTeam").setAttribute("hidden", "");
    document.getElementById("enterTeamId").removeAttribute("hidden");
}

function openCyclistRole(){
    closeNewTeam();
    document.getElementById("cyclistRole").removeAttribute("hidden");
}

function openNewTeam(){
    document.getElementById("newTeam").removeAttribute("disabled");
}

function closeNewTeam(){
    enterTeamId();
    document.getElementById("newTeam").removeAttribute("checked");
    document.getElementById("enterTeam").setAttribute("checked", "");
    document.getElementById("newTeam").setAttribute("disabled", "");
}