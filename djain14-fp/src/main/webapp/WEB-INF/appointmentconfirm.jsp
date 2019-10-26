<%-- 
    Document   : appointmentconfirm
    Created on : 25 Sep, 2019, 1:35:40 AM
    Author     : dhira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container" style=" margin-top: 2%;margin-left: 13%;margin-bottom: 2%;margin-right: 7%; ">
            <div class="alert-success text-center">
                <h1 class="display-3">Thank You!</h1>
                <p class="lead"><strong>Your appointment has been booked with us.</strong></p>
                <hr>
                <br>
                <h3 class="display-6">
                    <strong>Your Booking Details </strong>
                </h3>
                <div style="color: black; padding:20px; ">
                    <br>
                    <p class="lead"><strong>Name: </strong>${requestScope.saloncustomer.fullName}</p>
                    <p class="lead"><strong>Address: </strong>${requestScope.saloncustomer.address}</p>
                    <p class="lead"><strong>Email ID: </strong>${requestScope.saloncustomer.emailId}</p>
                    <p class="lead"><strong>Contact: </strong>${requestScope.saloncustomer.contact}</p>
                </div></div></div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
