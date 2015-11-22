<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sponsor this Project</title>
</head>
<body>
<center>

<c:choose>
<c:when test="${empty user}">
<h2 align = "right"><a href="LogInPage">Log in</a></h2>
</c:when>
<c:otherwise>
<h2 align = "left"><a href="MyProfile">My Profile!</a></h2>
<h2 align = "right"><a href="LogOutPage">Log Out</a></h2>
</c:otherwise>
</c:choose>

<form action="SponsorThisProject?id=${proj.idSeed}" method="post">
        <p>${proj.title}<br />
        <p>by ${proj.name} <br />
        </p> <div style='text-align: left;'><p>${proj.desc}</p>
        <span>Funding Target</span>: ${proj.funTarget} 
        <br/> 
        
        <span> Days to Go</span>: ${proj.daysToGo}
        
        <br/>
        
        <span style='font-weight: bold;'><br>Enter Amount: <input type = "text" name = "amount"></span>
        <p style='font-weight: bold;'>
        <c:forEach items = "${proj.rewards}" var="rew">
        
        	<input type="radio" name = "reward" value = "${rew.reward}">${rew.amount} $: ${rew.reward}<br/>
        	
        </c:forEach>
		</p>
        </div>
        <input type = "submit" name = "sponsor" value ="Sponsor this Project">
</form>
        
</center>
       
</body>
</html>