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
        <button onclick="logOut()">Log out</button>
    </div>
    <div id="noUser">
        <a href="http://localhost:8080/CyclingTeamHealth_war/logIn.jsp">Log in</a>
        <a href="http://localhost:8080/CyclingTeamHealth_war/signUp.jsp">Sign up</a>
    </div>
    <div id="admin">
        <a href="http://localhost:8080/CyclingTeamHealth_war/backup.jsp">Backup</a>
    </div>
</div>

    <h1>Chats</h1>
    <div id="chats">
    </div>
    <script src="js/chats.js"></script>
<script src="js/menu.js"></script>
</body>
</html>
