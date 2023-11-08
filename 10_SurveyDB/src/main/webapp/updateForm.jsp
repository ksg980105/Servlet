<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String[] satisfaction = {"매우만족","만족","보통","불만족","매우불만족"};
	String[] part = {"JAVA","Spring","UML","JDBC","서블릿","JSP"};
	String[] howto = {"email","우편"};
	
%>

<form action="update.sv" method="post"> 
	<input type="hidden" name="no" value="${requestScope.sb.no}">
	 과정 만족도 설문<br>
	 아래 항목을 입력해 주세요.<P>
	
	 <b>개인 정보 :</b><br>
	이름 　　　　<input type="text" name="name" value="${requestScope.sb.name}"><br>
	소속 회사　　<input type="text" name="company" value="${requestScope.sb.company}"><br>
	이메일 주소　<input type="text" name="email" value="${requestScope.sb.email}"><br>
	<p>
	<b>본 교육 과정을 수강하고 과정에 만족도를 선택해 주세요.</b><br>
	<c:forEach var="satisfaction" items="<%=satisfaction%>">
		<input type="radio" name="satisfaction" value="${satisfaction}" 
		<c:if test="${requestScope.sb.satisfaction eq satisfaction}">checked</c:if>>${satisfaction}
	</c:forEach>
	<p>
<b>관심있는 분야는 무엇입니까?</b><br>
	<c:forEach var="part" items="<%=part%>">
		<input type="checkbox" name="part" value="${part}" 
		<c:if test="${fn:contains(requestScope.sb.part, part)}">checked</c:if>>${part}
	</c:forEach>
	<p>
	정보 발송 방법 
	<select name="howto">
	<c:forEach var="howto" items="<%=howto%>">
		<option value="${howto}" 
		<c:if test="${requestScope.sb.howto == howto}">selected</c:if>>${howto}</option>
	</c:forEach>
	</select>
	
	<p>
	<input type="checkbox" name="agree" 
	<c:if test="${requestScope.sb.agree == 1}">checked</c:if>> 정보 발송에 동의합니다.<br>
	<input type="submit" value="전송">
</form>