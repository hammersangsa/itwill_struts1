<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="500" align="center">
<tr height="30">
	<td align="right">
	<input type="button" value="파일 업로드"
	onclick="location.href='<%=cp%>/file.do?method=write';"/>
	</td>
</tr>	
</table>

<table width="500" align="center" border="1" style="font-size: 10pt;">
<tr height="30">
	<td width="50" align="center">번호</td>
	<td width="200">제목</td>
	<td width="200">파일이름</td>
	<td width="50">비고</td>
</tr>

<c:forEach var="dto" items="${lists }">
<tr onmouseover="this.style.backgroundColor='#e4e4e4'"
	onmouseout="this.style.backgroundColor=''" bgcolor="#ffffff" style="margin: auto;">
	<td width="50" align="center">${dto.listNum }</td>
	<td width="200" align="left">${dto.subject }</td>
	<td width="200" align="left">
		<a href="${dto.urlFile }">${dto.originalFileName }</a>
	</td>
	<td width="50" align="left">
		<a href="<%=cp %>/file.do?method=delete_ok&num=${dto.num }">삭제</a>
	</td>
</tr>	
</c:forEach>

<c:if test="${totalDataCount==0 }">
<tr bgcolor="#ffffff">
	<td align="left" colspan="5">등록된 자료가 없습니다!!!</td>
</tr>
</c:if>

</table>

<c:if test="${totalDataCount!=0 }">
<table width="500" border="0" cellpadding="0" cellspacing="0" align="center">
<tr align="center">
	<td align="center" headers="30">${pageIndexList }</td>
</tr>
</table>
</c:if>

</body>
</html>