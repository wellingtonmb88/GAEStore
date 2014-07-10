<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="/resources/css/bootstrap.min.css" />
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>

<title><tiles:getAsString name="title" /></title>


</head>

<body>

	<div class="container">


		<%-- 		<tiles:insertAttribute name="header" /> --%>
		<!-- 		<br /> -->

		<tiles:insertAttribute name="menu" />

		<br />
		<tiles:insertAttribute name="body" />

		<br />
		<tiles:insertAttribute name="footer" />

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->



</body>


</html>