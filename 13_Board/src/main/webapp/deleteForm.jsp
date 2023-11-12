<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="color.jsp"%> 
<link rel="stylesheet" href="style.css" type="text/css">
<style type="text/css">
   body{
      text-align: center;
      background: <%= bodyback_c %>
   }
   table{
      margin: auto;
   }
</style>

<script type="text/javascript" src = "jquery.js"></script>
<script type="text/javascript">
	function checkData(){
		if($("input[name=passwd]").val() == ""){
			alert("비밀번호를 입력하세요");
			$("input[name=passwd]").focus();
			return false;
		}
	}
</script>

    <form action = "delete.bd">
    <input type = "hidden" name = "num" value = "${num}">
    <input type = "hidden" name = "pageNum" value = "${pageNum}">
    <h4>글삭제</h4>
    	<table>
    		<tr>
    			<td bgcolor = "<%= value_c %>" align = "center">비밀번호를 입력해 주세요.</td>
    		</tr>
    		<tr>
    			<td>
    				비밀번호:<input type = "password" name = "passwd">
    			</td>
    		</tr>
    		<tr>
    			<td bgcolor = "<%= value_c %>" align = "center">
    				<input type = "submit" value = "글삭제" onclick = "return checkData()">
    				<input type = "button" value = "글목록" onClick="location.href='select.bd?pageNum=${pageNum}'">
    			</td>
    		</tr>
    	</table>
    </form>