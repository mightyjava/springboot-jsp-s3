<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="${path}/js/jquery.save.js"></script>
<div class="panel panel-default">
	<div class="panel-heading">
		<strong>
			<c:choose>
				<c:when test="${isNew}"><span class="glyphicon glyphicon-plus-sign"></span></c:when>
				<c:otherwise><span class="glyphicon glyphicon-edit"></span></c:otherwise>
			</c:choose> User
		</strong>
	</div>
	<form:form method="post" class="form-horizontal" action="${path}/user/add" commandName="userForm" id="submitUserForm">
		<form:hidden path="id"/>
		<div class="panel-body">
			<div class="form-group">
				<label class="col-md-2 control-label">Full Name : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="fullName" placeholder="Enter Full Name" required="true"/>
				</div>
				
				<label class="col-md-2 control-label">User Id : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="userId" placeholder="Enter User Id" required="true"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">User Name : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="userName" placeholder="Enter User Name" required="true"/>
				</div>
				
				<label class="col-md-2 control-label">Password : </label>
				<div class="col-md-4">
					<form:password class="form-control" path="password" placeholder="Enter Password" required="true"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">Email : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="email" placeholder="Enter Email Address" required="true"/>
				</div>
				
				<label class="col-md-2 control-label">Mobile : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="mobile" placeholder="Enter Mobile Number" required="true"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">Role :</label>
				<div class="col-md-4">
					<form:select class="form-control" path="roleId">
						<c:forEach items="${roles}" var="role">
							<form:option value="${role.id}">${role.name}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<c:if test="${userForm.id ne null}">
					<label class="col-md-2 control-label">Profile Picture :</label>
					<div class="col-md-4">
						<button type="button" class="btn btn-default" data-toggle="modal" data-target="#uploadModal">
							<span class="glyphicon glyphicon-upload"></span> Upload
						</button>
					</div>
				</c:if>
			</div>
		</div>
		<div class="panel-footer">
			<form:button value="Save" class="btn btn-xs btn-default">
				<span class="glyphicon glyphicon-floppy-disk"></span>
				<c:choose>
					<c:when test="${isNew}"> Save</c:when>
					<c:otherwise> Update</c:otherwise>
				</c:choose>
			</form:button>
		</div>
	</form:form>
</div>
<c:if test="${userForm.id ne null}">
	<div id="uploadModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="uploadImage" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Upload User Profile for ${userForm.fullName}</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<input type="hidden" name="editUserId" value="${userForm.id}">
							<label class="col-md-3 control-label">Upload File :</label>
							<div class="col-md-6">
								<input type="file" name="file" class="form-control">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">Upload</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</c:if>