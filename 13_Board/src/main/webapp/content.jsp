<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="color.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<link rel="stylesheet" href="style.css" type="text/css">
<style type="text/css">

   body{
      text-align: center;
   }
   table{
      margin: auto;
   }
</style>

    <h1>글내용 ${bb.ref} / ${bb.re_step} / ${bb.re_level}</h1>
	<body bgcolor = "<%=bodyback_c%>">
    <form>
    	<table bgcolor = "<%=bodyback_c%>" align = "center" width = "500">
			<tr>
				<td align = "center" width = "125" bgcolor="<%=value_c%>">글번호</td>
				<td align = "center" width = "125">${bb.num}</td>
				<td align = "center" width = "125" bgcolor="<%=value_c%>">조회수</td>
				<td align = "center" width = "125">${bb.readcount}</td>
			</tr>
			<tr>
				<td align = "center" width = "125" bgcolor="<%=value_c%>">작성자</td>
				<td align = "center" width = "125">${bb.writer}</td>
				<td align = "center" width = "125" bgcolor="<%=value_c%>">작성일</td>
				<td align = "center" width = "125"><fmt:formatDate value="${bb.reg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
			</tr>
			<tr>
				<td align = "center" width = "125" bgcolor="<%=value_c%>">글제목</td>
				<td colspan="3" align = "center" width = "375">${bb.subject}</td>
			</tr>
			<tr>
				<td align = "center" width = "125" bgcolor="<%=value_c%>">글내용</td>
				<td colspan="3" align = "left" width = "375" height = "100">${bb.content}</td>
			</tr>
			<tr>
				<td colspan="4" align = "right" bgcolor="<%=value_c%>">
					<input type = "button" value = "글수정" onClick = "location.href = 'updateForm.bd?num=${bb.num}&pageNum=${pageNum}'"> &nbsp;&nbsp;
					<input type = "button" value = "글삭제" onClick = "location.href = 'deleteForm.bd?num=${bb.num}&pageNum=${pageNum}'"> &nbsp;&nbsp;
					<input type = "button" value = "답글쓰기" onclick = "location.href='replyForm.bd?ref=${bb.ref}&re_step=${bb.re_step}&re_level=${bb.re_level}&pageNum=${pageNum}'"> &nbsp;&nbsp;
					<input type = "button" value = "글목록" onClick = "location.href = 'select.bd?pageNum=${pageNum}'">
				</td>
			</tr>
    	</table>
    </form>
    </body>