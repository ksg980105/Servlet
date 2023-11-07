<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
02_prodForm.jsp <br>
request.getMethod() :  
<%=request.getMethod()%>
<br>


<h3>상품 정보:</h3>

<form action="insert.prd">
	상품명 : <input type="text" name="name"><br>
	가격 : <input type="text" name="price"><br>
	<input type="submit" value="전송">
</form>