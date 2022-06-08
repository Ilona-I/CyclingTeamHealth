<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <div class="center_content div_selected_menu_item"><a href="http://localhost:8080/CyclingTeamHealth_war/index.jsp" class="a_selected_menu_item" >Home</a></div>
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

<div style="width: 100%;  margin-top: 60px; ">
    <div class="row">
        <div style="margin-left: 300px; width: 900px; margin-top: 50px;">
            <h1>Програмна система для відстеження фізичного стану велосипедистів</h1>
            <br>
            <p style="font-size: 22px;">
                Останнім часом у всьому світі все більше набирає популярність велоспорт,
                тому кількість людей, яка ним займається, або планує зайнятися зростає щодня,
                якщо не щогодини. Таким чином формується все більше велокоманд, яким треба
                багато тренуватися задля досягнення успіхів в аматорських та професійних
                велогонках. В склад велокоманди входить достатньо велика кількість людей:
                велогонщики, які і є ядром команди, а також тренери, менеджери, спортивні
                лікарі та інші. Звісно, що тренування мають бути максимально ефективними та
                ні в якому разі не нашкодити спортсменам, а саме для цих цілей ідеально
                підходить дана система, яка допомагає спортивним лікарям доглядати за
                фізичним станом велосипедистів, а тренерам - коригувати тренування для
                отримання найкращих результатів </p>
            <br>
            <h5>Де можна завантажити CyclistHealth? В PlayMarket!</h5>
        </div>
    </div>
    <div style="height: 200px;"></div>
    <hr >
    <div style="margin-left: 40px; text-align: center;">
        <h6>Про нас</h6>
        <p>
            Адреса: Харків<br>
            Телефон: 0999999999<br>
            E-mail: cyclisthealth@gmail.com<br>
        </p>
    </div>
</div>
<script src="js/menu.js"></script>
</body>
</html>

