<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
      crossorigin="anonymous"
    />
    <title></title>
  </head>
  <body>
    <div class="container" style="margin-top: 50px;">
      <h3 class="text-center">상품 리스트</h3>
      <a href="product_add.jsp">상품등록</a>
      <table class="table table-bordered">
        <thead class="thead-dark text-center">
          <th scope="col">번 호</th>
          <th scope="col">카테고리</th>
          <th scope="col">상품명</th>
          <th scope="col">단 가</th>
          <th scope="col">수 량</th>
          <th scope="col">등록일자</th>
        </thead>
        <tbody>
          <c:forEach var="vo" items="${list}">
          	<tr>
				<td>${vo.pno}</td>
				<td>${vo.category}</td>
				<td>${vo.name}</td>
				<td>${vo.price}</td>
				<td>${vo.amount}</td>
				<td>${vo.regdate}</td>
          	</tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>
</html>
