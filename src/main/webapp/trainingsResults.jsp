<%--
  Created by IntelliJ IDEA.
  User: ill76
  Date: 05.06.2022
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
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
<h2 id="title"></h2>
<div id="trainingsResults">

</div>
<script src="js/trainingsResults.js"></script>
<script src="js/menu.js"></script>
</body>
</html>
