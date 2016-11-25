<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Parking house</title>

<spring:url value="/resources/css/style.css" var="styleCss" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${styleCss}" rel="stylesheet" />

<spring:url value="/resources/js/jquery.1.10.2.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
<script src="${bootstrapJs}"></script>
<spring:url value="/resources/js/main.js" var="mainJs" />
<script src="${mainJs}"></script>
</head>

<jsp:include page="navigation.jsp" />

<div class="container">

	<div class="starter-template">
		<h1>Get invoice by username</h1>
		<br>

		<div id="feedbackForm"></div>

		<form id="invoiceForm" class="form-horizontal">
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Username</label>
				<div class="col-sm-10">
					<input type=text class="form-control" id="username">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="btnGetInvoice" class="btn btn-primary btn-lg">Get invoice</button>
				</div>
			</div>
		</form>

	</div>

	<br> <br> <br>
	<h2>Please choose the helper action:</h2>
	<div id="feedbackAction"></div>
	<div>
		<button id="addMock" class="btn btn-sm btn-default" type="button">Add mock</button>
		<button id="showUsers" class="btn btn-sm btn-default" type="button">Show users</button>
	</div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>