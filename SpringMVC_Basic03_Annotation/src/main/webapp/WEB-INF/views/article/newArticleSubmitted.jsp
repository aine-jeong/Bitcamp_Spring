<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력값 확인하기</title>
</head>
<body>
 	<%-- <h3>게시판 입력 내용 확인</h3>
 	제목: ${newArticleCommand.title}<br>
 	내용: ${newArticleCommand.content}<br>
 	순번: ${newArticleCommand.parentId}<br> --%>
 	
 	<!-- @ModelAttribute("Articladata") NewArticleCommand command 사용하면서 바뀐 부분 -->
 	<h3>게시판 입력 내용 확인</h3>
 	제목: ${Articladata.title}<br>
 	내용: ${Articladata.content}<br>
 	순번: ${Articladata.parentId}<br>
 </body>
</html>