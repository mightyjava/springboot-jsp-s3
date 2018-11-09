<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<div class="panel panel-default">
	<div class="panel-heading">
		<strong>
			<span class="glyphicon glyphicon-list"></span> Address List
		</strong>
		<div class="btn-group pull-right">
			<a href="javascript:void(0);" class="btn btn-default btn-sm" onclick="addForm('address')" title="Add New Address">
				<span class="glyphicon glyphicon-plus-sign"></span>
			</a>
			<a href="javascript:void(0);" class="btn btn-default btn-sm" onclick="refresh('address')" title="Refresh Address">
				<span class="glyphicon glyphicon-refresh"></span>
			</a>
		</div>
	</div>
	<div class="panel-body">
		<table class="table table-bordered table-condensed table-hover table-striped">
			<thead>
				<tr>
					<th>Country</th>         
					<th>State</th>
					<th>City</th>
					<th>Line</th>
					<th>Pin Code</th>
					<th>User</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${addresses.size() > 0}">
						<c:forEach items="${addresses}" var="address">
							<tr>
								<td>${address.country}</td>
								<td>${address.state}</td>
								<td>${address.city}</td>
								<td>${address.addressLine}</td>
								<td>${address.pinCode}</td>
								<td>${address.user.fullName}</td>
								<td><a href="javascript:void(0);" onclick="editForm('address', '${address.id}')"><span class="glyphicon glyphicon-edit"></span></a></td>
								<td><a href="javascript:void(0);" onclick="deleteData('address', '${address.id}')"><span class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr align="center">
							<td colspan="8">No Addresses available</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<c:if test="${addresses.size() > 0}">
		<div class="panel-footer">
			Showing ${number+1} to ${size} of ${totalElements}
			<ul class="pagination pull-right" style="margin:-7px;">
				<c:choose>
					<c:when test="${current == 1}">
						<li class="disabled"><a href="javascript:void(0);">First</a></li>
						<li class="disabled"><a href="javascript:void(0);">Prev</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:void(0);" onclick="list('address', '1')">First</a></li>
						<li><a href="javascript:void(0);" onclick="list('address', '${current - 1}')">Prev</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="${begin}" end="${end}" var="i">
					<c:choose>
						<c:when test="${i == current}">
							<li class="active"><a href="javascript:void(0);" onclick="list('address', '${i}')">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:void(0);" onclick="list('address', '${i}')">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${current == totalPages}">
						<li class="disabled"><a href="javascript:void(0);">Next</a></li>
						<li class="disabled"><a href="javascript:void(0);">Last</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:void(0);" onclick="list('address', '${current + 1}')">Next</a></li>
						<li><a href="javascript:void(0);" onclick="list('address', '${totalPages}')">Last</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</c:if>
</div>