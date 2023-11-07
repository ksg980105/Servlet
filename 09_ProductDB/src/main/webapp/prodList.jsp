<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
prodList.jsp <br>

request.getMethod() :  
<%=request.getMethod()%><br>

<table border="1">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>단가</th>
		<th>입고일자</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
	<c:forEach var="pb" items="${requestScope.lists}">
		<tr>
			<td>${pb.id}</td>
			<td>${pb.name}</td>
			<td>${pb.price}</td>
			<td>
				<fmt:parseDate var="regdate" value="${pb.regdate}" pattern="yyyy-MM-dd"/>
				<fmt:formatDate value="${regdate}" pattern="yyyy-MM-dd"/>
			</td>
			<td><a href="delete.prd?id=${pb.id}">삭제</a></td>
			<td><a href="updateForm.prd?id=${pb.id}">수정</a></td>
		</tr>
	</c:forEach>
</table>
<a href="prodForm.jsp">삽입</a>