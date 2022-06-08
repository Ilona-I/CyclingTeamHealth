<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/menu.css" type="text/css">
    <link rel="stylesheet" href="css/logIn.css" type="text/css">
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
<div class="div_main_log_in">
    <h2 style="margin-left: 100px;">Log in</h2>
    <br>
    <input type="text" id="login" class="input_style_log_in"/>
    <p id="wrongLogin"></p>
    <input type="password" id="password" autocomplete="off" class="input_style_log_in"/>
    <p id="wrongPassword"></p>
    <button class="button_log_in" type="submit" onfocus="logIn()">Log in</button>
</div>

</body>
<script src="js/logIn.js"></script>
<script src="js/menu.js"></script>
</html>
