<%@page import="board.BoardBean"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="color.jsp" %>
<link rel="stylesheet" href="style.css" type="text/css">
<style type="text/css">

   body{
      text-align: center;
   }
   table{
      margin: auto;
   }
</style>

<script type="text/javascript" src = "jquery.js"></script>
<script type="text/javascript" src = "script.js"></script>

<body bgcolor="<%= bodyback_c %>">
   <b>글수정</b>
   <form method="post" name="updateForm" action="update.bd?num=${bb.num}&pageNum=${pageNum}">
      <table width="450" bgcolor="<%= bodyback_c %>" align="center">
         <tr>
            <td width="150" bgcolor="<%= value_c %>" align="center">이 름</td>
            <td width="300">
               <input type="text" name="writer" maxlength="10" value = "${bb.writer}"> 
            </td>
         </tr>      
         <tr>
            <td width="150" bgcolor="<%= value_c %>" align="center">제 목</td>
            <td width="300">
               <input type="text" name="subject" maxlength="50" value = "${bb.subject}">
            </td>
         </tr>
         <tr>
            <td width="150" bgcolor="<%= value_c %>" align="center">Email</td>
            <td width="300">
               <input type="text" name="email" maxlength="20" value = "${bb.email}">
            </td>
         </tr>
         <tr>
            <td width="150" bgcolor="<%= value_c %>" align="center">내 용</td>
            <td width="300">
               <textarea name="content" id="ta" rows="15" cols="50">${bb.content}</textarea>
            </td>
         </tr>
         <tr>
            <td width="150" bgcolor="<%= value_c %>" align="center">비밀번호</td>
            <td width="300">
               <input type="password" name="passwd">
            </td>
         </tr>
         <tr>
            <td colspan="2" bgcolor="<%= value_c %>" align="center">
               <input type="submit" value="글수정" onclick="return writeSave()">
               <input type="reset" value="다시작성">
               <input type="button" value="목록보기" onClick="location.href='select.bd?pageNum=${pageNum}'">
            </td>
         </tr>
      </table>
   </form>
</body>