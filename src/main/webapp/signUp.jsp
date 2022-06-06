<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="http://localhost:8080/CyclingTeamHealth_war/index.jsp">Home</a>
    <a href="http://localhost:8080/CyclingTeamHealth_war/feedbacks.jsp">Feedbacks</a>
    <a href="http://localhost:8080/CyclingTeamHealth_war/teams.jsp">Teams</a>
    <div id="userPresent">
        <a href="http://localhost:8080/CyclingTeamHealth_war/profile.jsp">My profile</a>
        <a href="http://localhost:8080/CyclingTeamHealth_war/users.jsp">Team members</a>
        <a href="http://localhost:8080/CyclingTeamHealth_war/chats.jsp">Chats</a>
        <p id="userLogin"></p>
        <button onclick="logOut()">Log out</button>
    </div>
    <div id="noUser">
        <a href="http://localhost:8080/CyclingTeamHealth_war/logIn.jsp">Log in</a>
        <a href="http://localhost:8080/CyclingTeamHealth_war/signUp.jsp">Sign up</a>
    </div>
    <div id="admin">
        <a href="http://localhost:8080/CyclingTeamHealth_war/backup.jsp">Backup</a>
    </div>
    <div id="teamMember">
        <a href="http://localhost:8080/CyclingTeamHealth_war/trainings.jsp">Trainings</a>
    </div>
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
