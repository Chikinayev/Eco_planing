<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Events</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <h1><a href="/" style="text-decoration: none">EcoPlaning</a></h1></h1>
    <h4>Volunteer's events</h4>
    <#if user.email??>
        <h3> Имя пользавателя <i>${user.name}</i></h3>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Выйти" />
        </form>
        <#if user.isAdmin() >
            <a href="/admin">Admin panel</a>
        </#if>
    <#else >
        <a href="/login"> Enter </a>
    </#if>
    <#list events as event>
        <div>
            <p><b>${event.title}</b>${event.description}<br> |
                <a href="/event/${event.id}">Read more...</a></p>

        </div>
        <#else>
        <h3>There are no events</h3>
    </#list>
    <#if user.email??>
    <hr>
    <h3>Create a new event</h3>
    <form action="/event/create" method="post" enctype="multipart/form-data">
        Event title: <input type="text" name="title" required><br><br>
        Event description: <input type="text" name="description" required><br><br>
        Event city: <input type="text" name="city" required><br><br>
        Event organizer: <input type="text" name="organizer" required><br><br>
        Event photo1: <input type="file" name="file1" required><br><br>
        Event photo2: <input type="file" name="file2"><br><br>
        Event photo3: <input type="file" name="file3"><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="add event">
    </form>
</#if>
</body>
</html>