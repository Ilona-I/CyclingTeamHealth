<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/menu.css" type="text/css">
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
<h2>Sign up</h2>
<h6>Login:</h6>
<input id="login" type="text" />
<h6>Password:</h6>
<input id="password" type="password" />
<h6>Repeat password:</h6>
<input id="repeatedPassword" type="password" />
<h6>First name:</h6>
<input id="firstName" type="text" />
<p id="wrongFirstName"></p>
<h6>Last name:</h6>
<input id="lastName" type="text"/>
<p id="wrongLastName"></p>
<h6>E-mail:</h6>
<input id="email" type="text" />
<p id="wrongEmail"></p>
<h6>Role:</h6>
<label><input type="radio" onclick="openNewTeam()" name="role" id="trainer" value="trainer" checked/>Trainer</label>
<label><input type="radio" onclick="closeNewTeam()" name="role" id="soigneur" value="soigneur"/>Soigneur</label>
<label><input type="radio" onclick="openCyclistRole()" name="role" id="cyclist" value="cyclist"/>Cyclist</label>
<label><input type="radio" onclick="closeNewTeam()" name="role" id="doctor" value="doctor"/>Doctor</label>
<h6>Team type</h6>
<label><input type="radio" onclick="createTeam()" name="teamType" id="newTeam" value="newTeam">New team</label>
<label><input type="radio" onclick="enterTeamId()" name="teamType" id="enterTeam" value="enterTeam" checked>Enter team id</label>
<div id="createTeam" hidden>
    <input type="text" id="teamName">
</div>
<div id="enterTeamId">
    <input type="text" id="teamId">
</div>
<div id="cyclistRole" hidden>
    <h6>Birth date:</h6>
    <input id="birthDate" type="date" />
    <p id="wrongBirthDate"></p>
    <h6>Height:</h6>
    <input id="height" type="number" min="0" />
    <p id="wrongHeight"></p>
    <h6>Weight:</h6>
    <input id="weight" type="number" min="0"/>
    <p id="wrongWeight"></p>
</div>
<h6>Gender:</h6>
<label><input type="radio" name="gender" id="male" value="male" checked/>Male</label>
<label><input type="radio" name="gender" id="female" value="female"/>Female</label>

<button onclick="signUp()">Sign up</button>
<script src="js/signUp.js"></script>
<script src="js/menu.js"></script>
</body>
</html>
