<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="update.prd" method="post">
	<input type="hidden" name="id" value="${requestScope.pb.id}">
	상품명 : <input type="text" name="name" value="${requestScope.pb.name}"><br>
	가격 : <input type="text" name="price" value="${requestScope.pb.price}"><br>
	<input type="submit" value="수정하기">
</form>