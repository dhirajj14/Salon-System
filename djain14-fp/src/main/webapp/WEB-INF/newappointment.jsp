<%-- 
    Document   : newapppointment
    Created on : 24 Sep, 2019, 11:56:51 AM
    Author     : dhira
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Schedule an Appointment</title>
    </head>
    <body>
        <div class="container rounded border row-bg" style="margin-top: 2%;margin-left: 13%;margin-bottom: 2%;margin-right: 7%; ">
            <div style="font-size: 30px;padding: 20px; align-content: center;text-align: center">Book Your Salon Appointment</div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <form action="/djain14-fp/SalonAppt" method="post" style="">
                        <div class="form-group">
                            <label for="fullName">Full Name</label>
                            <input type="text" class="form-control" value="${requestScope.saloncustomer.fullName}" name="fullName" placeholder="Enter you Name">
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" value="${requestScope.saloncustomer.address}" name="address" placeholder="Enter your Address">
                        </div>
                        <div class="form-group">
                            <label for="emailID">Email ID</label>
                            <input type="email" class="form-control" value="${requestScope.saloncustomer.emailId}" name="emailId" placeholder="Enter your Email-ID">
                        </div>
                        <div class="form-group">
                            <label for="contact">Contact</label>
                            <input type="tel" class="form-control" value="${requestScope.saloncustomer.contact}" name="contact" placeholder="Enter your Contact Number">
                        </div>
                        <div class="form-group">
                            <label for="serviceType">Service Type:</label>
                            <select class="form-control" name="serviceType">
                                <option Value="" <c:if test="${requestScope.saloncustomer.serviceType == ''}">selected="selected"</c:if>>Select Service</option>
                                <option Value="Hair Cut" <c:if test="${requestScope.saloncustomer.serviceType == 'Hair Cut'}">selected="selected"</c:if>>Hair Cut</option>
                                <option Value="Hair Color" <c:if test="${requestScope.saloncustomer.serviceType == 'Hair Color'}">selected="selected"</c:if>>Hair Color</option>
                                <option Value="Skin Care" <c:if test="${requestScope.saloncustomer.serviceType == 'Skin Care'}">selected="selected"</c:if>>Skin Care</option>
                                </select>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="date">Date</label>
                                        <input class="form-control" type="date" required value="${requestScope.saloncustomer.date}" name="date" placeholder="09-25-2019">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="time">Appointment Time:</label>
                                    <select class="form-control" name="time">
                                        <option Value="" <c:if test="${requestScope.saloncustomer.time == ''}">selected="selected"</c:if>>Select the Time</option>
                                        <option Value="10:00 AM" <c:if test="${requestScope.saloncustomer.time == '10:00 AM'}">selected="selected"</c:if>>10:00 AM</option>
                                        <option Value="10:30 AM" <c:if test="${requestScope.saloncustomer.time == '10:30 AM'}">selected="selected"</c:if>>10:30 AM</option>
                                        <option Value="12:30 PM" <c:if test="${requestScope.saloncustomer.time == '12:30 PM'}">selected="selected"</c:if>>12:30 PM</option>
                                        <option Value="01:00 PM" <c:if test="${requestScope.saloncustomer.time == '01:00 PM'}">selected="selected"</c:if>>01:00 PM</option>
                                        <option Value="02:00 PM" <c:if test="${requestScope.saloncustomer.time == '02:00 PM'}">selected="selected"</c:if>>02:00 PM</option>
                                        <option Value="02:30 PM" <c:if test="${requestScope.saloncustomer.time == '02:30 PM'}">selected="selected"</c:if>>02:30 PM</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <Button type="submit" class="btn btn-primary form-control">Submit</button>
                            </div>
                        </form>
                    <c:if test="${not empty requestScope.mistakes}">
                        <c:forEach items="${requestScope.mistakes}" var="mistakes">
                            <div class="alert alert-danger" role="alert">
                                There is a validation issue with ${mistakes.propertyPath}. ${mistakes.message}</div>
                            </c:forEach>
                        </c:if>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>