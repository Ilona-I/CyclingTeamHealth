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
    <div id="userPresent3" class="center_content div_selected_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/chats.jsp" class="a_selected_menu_item">Chats</a></div>
    <div id="teamMember" class="center_content div_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/trainings.jsp" class="a_menu_item">Trainings</a></div>
    <div id="userPresent4" class="center_content div_button"><button onclick="logOut()" class="button_menu">Log out</button></div>
    <div id="noUser1" class="center_content div_button"><button onclick="document.location='http://localhost:8080/CyclingTeamHealth_war/logIn.jsp'" class="button_menu">Log in</button></div>
    <div id="noUser2" class="center_content div_button"><button onclick="document.location='http://localhost:8080/CyclingTeamHealth_war/signUp.jsp'" class="button_menu">Sign up</button></div>
</div>

<div style="margin-left:10%; background-color: #e1e2e3; width: 80%; margin-top: 40px; ">
    <div style="margin-left: 5%; width: 90%">
        <h2 id="chatName" style=" margin-top: 10px;">Chat</h2>
            <div id="chat">
            </div>
    </div>
    <input style="margin-left: 10%; width: 60%;" type="text" id="text" placeholder="Type...">
    <button style="background-color: #ABDDC1; color: #353637; height: 40px; width: 100px; border-style: solid; border-color: #353637; border-width: 1px; font-size: 20px" onclick="sendMessage()">Send</button>
<br><br>
</div>



<script src="js/chat.js"></script>
<script src="js/menu.js"></script>
</body>
</html>
