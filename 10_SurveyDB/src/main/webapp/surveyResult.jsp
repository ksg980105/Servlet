<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>소속회사</th>
		<th>이메일 주소</th>
		<th>과정 만족도</th>
		<th>관심 분야</th>
		<th>정보 발송 방법</th>
		<th>정보 발송 동의</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
	<c:forEach var="sb" items="${requestScope.lists}">
		<tr>
			<td>${sb.no}</td>
			<td>${sb.name}</td>
			<td>${sb.company}</td>
			<td>${sb.email}</td>
			<td>${sb.satisfaction}</td>
			<td>
				<c:if test="${sb.part==null}">
					선택항목 없음
				</c:if>
				<c:if test="${not(sb.part==null)}">
					${sb.part}
				</c:if>
			</td>
			<td>${sb.howto}</td>
			<td>
				<c:if test="${sb.agree==1}" >
					동의함
				</c:if>
				<c:if test="${sb.agree==0}">
					동의안함
				</c:if>
			</td>
			<td><a href="delete.sv?no=${sb.no}">삭제</a></td>
			<td><a href="updateForm.sv?no=${sb.no}">수정</a></td>
		</tr>
	</c:forEach>
</table>
<a href="insertForm.jsp">삽입</a>