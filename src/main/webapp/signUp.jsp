<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/menu.css" type="text/css">
    <link rel="stylesheet" href="css/signUp.css" type="text/css">
    <title>Cyclist Health</title>
</head>
<body>
<div class="row div_menu center_content">
    <div class="div_title_style">
        <h5 class="h5_title_style">Відстеження <br>фізичного стану <br>велосипедистів</h5>
    </div>
    <div class="center_content div_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/index.jsp" class="a_menu_item">Home</a></div>
    <div class="center_content div_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/feedbacks.jsp" class="a_menu_item">Feedbacks</a></div>
    <div class="center_content div_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/teams.jsp" class="a_menu_item">Teams</a></div>
    <div id="admin" class="center_content div_menu_item"> <a href="http://localhost:8080/CyclingTeamHealth_war/backup.jsp" class="a_menu_item">Backup</a></div>
    <div id="userPresent1" class="center_content div_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/profile.jsp" class="a_menu_item">My profile</a></div>
    <div id="userPresent2" class="center_content div_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/users.jsp" class="a_menu_item">Team members</a></div>
    <div id="userPresent3" class="center_content div_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/chats.jsp" class="a_menu_item">Chats</a></div>
    <div id="teamMember" class="center_content div_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/trainings.jsp" class="a_menu_item">Trainings</a></div>
    <div id="userPresent4" class="center_content div_button"><button onclick="logOut()" class="button_menu">Log out</button></div>
    <div id="noUser1" class="center_content div_button"><button onclick="document.location='http://localhost:8080/CyclingTeamHealth_war/logIn.jsp'" class="button_menu">Log in</button></div>
    <div id="noUser2" class="center_content div_button"><button onclick="document.location='http://localhost:8080/CyclingTeamHealth_war/signUp.jsp'" class="button_menu">Sign up</button></div>
</div>
<div class="div_main_sign_up">
    <h2 style="margin-left: 200px;">Sign up</h2>
    <br>
    <div class="row div_sign_up_elements">
        <div class="div_sign_up_items">
            <h6>Login:</h6>
        </div>
        <input id="login" type="text" class="input_style_sign_up"/>
    </div>
    <div class="row div_sign_up_elements">
        <div class="div_sign_up_items">
            <h6>Password:</h6>
        </div>
        <input id="password" type="password" class="input_style_sign_up"/>
    </div>
    <div class="row div_sign_up_elements">
        <div class="div_sign_up_items">
            <h6>Repeat password:</h6>
        </div>
        <input id="repeatedPassword" type="password" class="input_style_sign_up"/>
    </div>
    <div class="row div_sign_up_elements">
        <div class="div_sign_up_items">
            <h6>First name:</h6>
        </div>
        <input id="firstName" type="text" class="input_style_sign_up"/>
        <p id="wrongFirstName"></p>
    </div>
    <div class="row div_sign_up_elements">
        <div class="div_sign_up_items">
            <h6>Last name:</h6>
        </div>
        <input id="lastName" type="text" class="input_style_sign_up"/>
        <p id="wrongLastName"></p>
    </div>
    <div class="row div_sign_up_elements">
        <div class="div_sign_up_items">
            <h6>E-mail:</h6>
        </div>
        <input id="email" type="text" class="input_style_sign_up"/>
        <p id="wrongEmail"></p>
    </div>
    <div class="row div_sign_up_elements">
        <div class="div_sign_up_items">
            <h6>Role:</h6>
        </div>
        <div>
            <label><input type="radio" onclick="openNewTeam()" name="role" id="trainer" value="trainer" checked/>Trainer</label><br>
            <label><input type="radio" onclick="closeNewTeam()" name="role" id="soigneur" value="soigneur"/>Soigneur</label><br>
            <label><input type="radio" onclick="openCyclistRole()" name="role" id="cyclist" value="cyclist"/>Cyclist</label><br>
            <label><input type="radio" onclick="closeNewTeam()" name="role" id="doctor" value="doctor"/>Doctor</label><br>
        </div>
    </div>
    <div class="row div_sign_up_elements">
        <div class="div_sign_up_items">
            <h6>Team type:</h6>
        </div>
        <div>
            <label style="width: 100px;"><input type="radio" onclick="createTeam()" name="teamType" id="newTeam" value="newTeam">New team</label>
            <label><input type="radio" onclick="enterTeamId()" name="teamType" id="enterTeam" value="enterTeam" checked>Enter team id</label><br>
            <div id="createTeam" hidden>
                <input type="text" id="teamName" class="input_style_sign_up"/>
            </div>
            <div id="enterTeamId">
                <input type="text" id="teamId" class="input_style_sign_up"/>
            </div>
        </div>
    </div>
    <div id="cyclistRole" >
        <div class="row div_sign_up_elements">
            <div class="div_sign_up_items">
                <h6>Birth date:</h6>
            </div>
            <input id="birthDate" type="date" class="input_style_sign_up"/>
            <p id="wrongBirthDate"></p>
        </div>
        <div class="row div_sign_up_elements">
            <div class="div_sign_up_items">
                <h6>Height:</h6>
            </div>
            <input id="height" type="number" min="0" class="input_style_sign_up"/>
            <p id="wrongHeight"></p>
        </div>
        <div class="row div_sign_up_elements">
            <div class="div_sign_up_items">
                <h6>Weight:</h6>
            </div>
            <input id="weight" type="number" min="0" class="input_style_sign_up"/>
            <p id="wrongWeight"></p>
        </div>
    </div>
    <div class="row div_sign_up_elements">
        <div class="div_sign_up_items">
            <h6>Gender:</h6>
        </div>
        <label style="width: 100px;"><input type="radio" name="gender" id="male" value="male" checked/>Male</label>
        <label><input type="radio" name="gender" id="female" value="female"/>Female</label>
    </div>

    <button class="button_signUp" onclick="signUp()">Sign up</button>
</div>
<script src="js/signUp.js"></script>
<script src="js/menu.js"></script>
</body>
</html>
