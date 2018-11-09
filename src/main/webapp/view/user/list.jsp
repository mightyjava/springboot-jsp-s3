<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<div class="panel panel-default">
	<div class="panel-heading">
		<strong>
			<span class="glyphicon glyphicon-list"></span> User List
		</strong>
		<div class="btn-group pull-right">
			<a href="javascript:void(0);" class="btn btn-default btn-sm" onclick="addForm('user')" title="Add New User">
				<span class="glyphicon glyphicon-plus-sign"></span>
			</a>
			<a href="javascript:void(0);" class="btn btn-default btn-sm" onclick="refresh('user')" title="Refresh User">
				<span class="glyphicon glyphicon-refresh"></span>
			</a>
		</div>
	</div>
	<div class="panel-body">
		<table class="table table-bordered table-condensed table-hover table-striped">
			<thead>
				<tr>
					<th>Full Name</th>
					<th>User Id</th>
					<th>User Name</th>
					<th>Email</th>
					<th>Mobile</th>
					<th>Role</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${users.size() > 0}">
						<c:forEach items="${users}" var="user">
							<tr>
								<td>
									<c:choose>
										<c:when test="${user.profilePhoto eq null}">
											<span class="glyphicon glyphicon-user"></span>
										</c:when>
										<c:otherwise>
											<img alt="user-image" src="${user.profilePhoto}" class="img-circle" width="32" height="32">
										</c:otherwise>
									</c:choose>
									${user.fullName}
								</td>
								<td>${user.userId}</td>
								<td>${user.userName}</td>
								<td>${user.email}</td>
								<td>${user.mobile}</td>
								<td>${user.role.name}</td>
								<td><a href="javascript:void(0);" onclick="editForm('user', '${user.id}')"><span class="glyphicon glyphicon-edit"></span></a></td>
								<td><a href="javascript:void(0);" onclick="deleteData('user', '${user.id}')"><span class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr align="center">
							<td colspan="8">No Users available</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<c:if test="${users.size() > 0}">
		<div class="panel-footer">
			Showing ${number+1} to ${size} of ${totalElements}
			<ul class="pagination pull-right" style="margin:-7px;">
				<c:choose>
					<c:when test="${current == 1}">
						<li class="disabled"><a href="javascript:void(0);">First</a></li>
						<li class="disabled"><a href="javascript:void(0);">Prev</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:void(0);" onclick="list('user', '1')">First</a></li>
						<li><a href="javascript:void(0);" onclick="list('user', '${current - 1}')">Prev</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="${begin}" end="${end}" var="i">
					<c:choose>
						<c:when test="${i == current}">
							<li class="active"><a href="javascript:void(0);" onclick="list('user', '${i}')">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:void(0);" onclick="list('user', '${i}')">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${current == totalPages}">
						<li class="disabled"><a href="javascript:void(0);">Next</a></li>
						<li class="disabled"><a href="javascript:void(0);">Last</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:void(0);" onclick="list('user', '${current + 1}')">Next</a></li>
						<li><a href="javascript:void(0);" onclick="list('user', '${totalPages}')">Last</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</c:if>
</div>