document.addEventListener("DOMContentLoaded", function() {
    let profileHTML =
        '<div class="row div_sign_up_elements">' +
        '        <div class="div_sign_up_items">' +
        '           <h6>Login:</h6>' +
        '       </div>' +
        '       <p>' + localStorage.getItem("login") + '</p>' +
        '</div>' +
        '<div class="row div_sign_up_elements">' +
        '        <div class="div_sign_up_items">' +
        '           <h6>First name:</h6>' +
        '       </div>' +
        '       <input id="firstName" class="input_style_sign_up" type="text" value="' + localStorage.getItem("firstName") + '"/>' +
        '<p id="wrongFirstName"></p>' +
        '</div>' +
        '<div class="row div_sign_up_elements">' +
        '        <div class="div_sign_up_items">' +
        '           <h6>Last name:</h6>' +
        '        </div>' +
        '        <input id="lastName" class="input_style_sign_up" type="text" value="' + localStorage.getItem("lastName") + '"/>' +
        '<p id="wrongLastName"></p>' +
        '</div>' +
        '<div class="row div_sign_up_elements">' +
        '        <div class="div_sign_up_items">' +
        '           <h6>E-mail:</h6>' +
        '        </div>' +
        '        <input id="email" class="input_style_sign_up" type="text" value="' + localStorage.getItem("email") + '"/>' +
        '       <p id="wrongEmail"></p>' +
        '</div>' +
        '<div class="row div_sign_up_elements">' +
        '        <div class="div_sign_up_items">' +
        '           <h6>Birth date:</h6>' +
        '        </div>' +
        '        <input id="birthDate" class="input_style_sign_up" type="date" value="' + localStorage.getItem("birthDate") + '"/>' +
        '        <p id="wrongBirthDate"></p>' +
        '</div>' +
        '<div class="row div_sign_up_elements">' +
        '        <div class="div_sign_up_items">' +
        '           <h6>Height:</h6>' +
        '       </div>' +
        '       <input id="height" class="input_style_sign_up" type="number" min="0" value="' + localStorage.getItem("height") + '"/>' +
        '       <p id="wrongHeight"></p>' +
        '</div>' +
        '<div class="row div_sign_up_elements">' +
        '        <div class="div_sign_up_items">' +
        '           <h6>Weight:</h6>' +
        '       </div>' +
        '       <input id="weight" class="input_style_sign_up" type="number" min="0" value="' + localStorage.getItem("weight") + '"/>' +
        '       <p id="wrongWeight"></p>' +
        '</div>' +
        '<div class="row div_sign_up_elements">' +
        '        <div class="div_sign_up_items">' +
        '           <h6>Gender:</h6>' +
        '        </div>' +
        '        <p>' + localStorage.getItem("gender") + '</p>' +
        '</div>' +
        '<div class="row div_sign_up_elements" style="margin-top: -20px;">' +
        '        <div class="div_sign_up_items">' +
        '           <h6>Role:</h6>' +
        '        </div>' +
        '       <p>' + localStorage.getItem("role") + '</p>' +
        '</div>';
    document.getElementById("profile").innerHTML = profileHTML;
});

let xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function saveProfile() {
    const url = "http://localhost:8080/CyclingTeamHealth_war/user";
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
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let email = document.getElementById("email").value;
    let birthDate = document.getElementById("birthDate").value;
    let height = document.getElementById("height").value;
    let weight = document.getElementById("weight").value;
    let params = 'login=' + localStorage.getItem("login") +
        '&firstName=' + firstName +
        '&lastName=' + lastName +
        '&email=' + email +
        '&birthDate=' + birthDate +
        '&height=' + height +
        '&weight=' + weight +
        '&role=' + localStorage.getItem("role") +
        '&gender=' + localStorage.getItem("gender") +
        '&status=' + localStorage.getItem("status");
    xmlHttp.send(params);
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