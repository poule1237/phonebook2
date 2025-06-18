<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.PersonVO" %>

<%
    // request로 전달된 PersonVO 객체 가져오기
    PersonVO person = (PersonVO) request.getAttribute("pVO");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주소록 수정폼</title>
</head>
<body>
    <h1>주소록</h1>

    <h2>전화번호 수정폼</h2>
    <p>전화번호를 수정하는 폼 입니다.</p>

        <label>이름(name)</label>
        <input type="text" name="name" value="">
        <br>

        <label>핸드폰(hp)</label>
        <input type="text" name="hp" value="">
        <br>

        <label>회사(company)</label>
        <input type="text" name="company" value="">
        <br>

        <button type="submit">수정</button>
</body>
</html>
