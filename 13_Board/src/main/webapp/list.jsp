<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="color.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<body bgcolor="<%= bodyback_c %>">
   <b>글목록(전체 글:${ count })</b>
   <table width="70%" align="center">
      <tr>
         <td align="right" bgcolor="<%= value_c %>"><a
            href="insertForm.jsp?pageNum=${ pageNum }">글쓰기</a></td>
      </tr>
   </table>
   <c:choose>
   <c:when test="${ count eq 0 }">
   <table width="70%">
      <tr>
         <td align="right" bgcolor="<%= value_c %>">게시판에 저장된 글이 없습니다.</td>
      </tr>

   </table>
	</c:when>
	
	<c:otherwise>
   <table width="70%" align="center">
      <tr bgcolor="<%= value_c %>">
         <td width="50">번호</td>
         <td width="250">제목</td>
         <td width="100">작성자</td>
         <td width="150">작성일</td>
         <td width="50">조회</td>
         <td width="100">IP</td>
      </tr>
      <c:forEach var="bb" items="${articleLists}">
      <tr>
         <td>${ number }</td>
         <c:set var="number" value="${ number-1 }"/>
         <td>
         	<c:set var="wid" value="0"/>
         	<c:if test="${bb.re_level>0}">
         		<c:set var="wid" value="${bb.re_level}"/>
         		<img src="images/level.gif" style="opacity: 0;" width="${ wid }" height = "30">
                <img src="images/re.gif">
         	</c:if>
         <a href = "content.bd?num=${bb.num}&pageNum=${currentPage}">
         ${bb.subject}
         </a>
         <c:if test="${bb.readcount>=10}">
         	<img src = "images/hot.gif">
         </c:if>
         </td>
         <td>${bb.writer}</td>
         <td><fmt:formatDate value="${bb.reg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
         <td>${bb.readcount}</td>
         <td>${bb.ip}</td>
      </tr>
      </c:forEach>
   </table>
   </c:otherwise>
   </c:choose>
   
   <c:if test="${ count>0 }">
		<c:if test="${endPage>pageCount}">
			<c:set var="endPage" value="${pageCount}"/>
		</c:if>
		<c:if test="${startPage>pageBlock}">
			<a href="select.bd?pageNum=${startPage-pageBlock}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="select.bd?pageNum=${i}">[${i}]</a>
		</c:forEach>
		<c:if test="${endPage<pageCount}">
			<a href="select.bd?pageNum=${startPage+pageBlock}">[다음]</a>
		</c:if>
   </c:if>
</body>