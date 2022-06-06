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

    <h2>Profile</h2>
    <form>
        <div id="profile">

        </div>
        <button type="submit" onclick="saveProfile()">Save changes</button>
    </form>

<script src="js/menu.js"></script>
<script src="js/profile.js"></script>
</body>
</html>
