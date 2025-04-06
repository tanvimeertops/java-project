<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="seller_header.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
	    </head>
	<body>	
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<h3 class="breadcrumb-header">Contact</h3>
						<ul class="breadcrumb-tree">
							<li><a href="seller_index.jsp">Home</a></li>
							<li class="active">Contact</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<div class="col-md-7">
						<!-- Billing Details -->
						<div class="billing-details">
						<div class="section-title">
							<h3 class="title">Change Password</h3>
						</div>
						<h3 style="color: red;">
							<%=request.getAttribute("msg")%>
						</h3>
						<h3 style="color: red;">${sessionScope.msg}</h3>

					</div>
							<form action="UserController" name="change_password" method="post">
							
							
							<div class="form-group">
								<input class="input" type="password" name="old_pass" placeholder="Old Password">
							</div>
							
								<div class="form-group">
								<input class="input" type="password" name="new_pass" placeholder="New Password">
							</div>
								<div class="form-group">
								<input class="input" type="password" name="confirm_new_pass" placeholder="Confirm New Password">
							</div>
							
							<div class="form-group">
						<input type="submit"  name="action"  value="Change Password" class="primary-btn order-submit">
					</div>
					</form>
					
					</div>
							
				</div>
				
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- NEWSLETTER -->
		<div id="newsletter" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
											</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /NEWSLETTER -->

		

		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>

	</body>
</html>
