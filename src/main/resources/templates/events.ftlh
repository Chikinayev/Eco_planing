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
    <h1>EcoPlaning</h1><hr>
    <h4>Volunteer's events</h4>
    <form action="/" method="get">
        Search by title: <input type="text" name="title"><br>
        <input type="submit" value="Search">
    </form>
    <#list events as event>
        <div>
            <p><b>${event.title}</b>${event.description}<br> |
                <a href="/event/${event.id}">Read more...</a></p>

        </div>
        <#else>
        <h3>There are no events</h3>
    </#list>
    <hr>
    <h3>Create a new event</h3>
    <form action="/event/create" method="post">
        Event title: <input type="text" name="title" required><br><br>
        Event description: <input type="text" name="description" required><br><br>
        Event city: <input type="text" name="city" required><br><br>
        Event organizer: <input type="text" name="organizer" required><br><br>
        <input type="submit" value="add event">
    </form>
</body>
</html>