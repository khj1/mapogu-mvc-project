<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!String flag = "notice";%>
<%@ include file="./common/list_include.jsp" %>
 <body>
	<center>
	<div id="wrap">
		<%@ include file="../include/top.jsp" %>

		<img src="../images/space/sub_image.jpg" id="main_visual" />

		<div class="contents_box">
			<div class="left_contents">
				<%@ include file = "../include/space_leftmenu.jsp" %>
			</div>
			<div class="right_contents">
				<div class="top_title">
					<img src="../images/space/sub01_title.gif" alt="공지사항" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;열린공간&nbsp;>&nbsp;공지사항<p>
				</div>
				<div>

<div class="row text-right" style="margin-bottom:20px;
		padding-right:50px;">
<!-- 검색부분 -->
<form class="form-inline">	
	<div class="form-group">
		<select name="keyField" class="form-control">
			<option value="">제목</option>
			<option value="">작성자</option>
			<option value="">내용</option>
		</select>
	</div>
	<div class="input-group">
		<input type="text" name="keyString"  class="form-control"/>
		<div class="input-group-btn">
			<button type="submit" class="btn btn-default">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
</form>	
</div>
<div class="row">
	<!-- 게시판리스트부분 -->
	<table class="table table-bordered table-hover">
	<colgroup>
		<col width="80px"/>
		<col width="*"/>
		<col width="120px"/>
		<col width="120px"/>
		<col width="80px"/>
		<col width="50px"/>
	</colgroup>
	
	<thead>
	<tr class="success">
		<th class="text-center">번호</th>
		<th class="text-left">제목</th>
		<th class="text-center">작성자</th>
		<th class="text-center">작성일</th>
		<th class="text-center">조회수</th>
		<th class="text-center">첨부</th>
	</tr>
	</thead>
	
	<tbody>
	<!-- 리스트반복 -->
	<c:choose>
		<c:when test="${empty boardList }">
			<tr>
				<td colspan="6" align = "center">
					등록된 게시물이 없습니다.
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${ boardList}" var ="list" varStatus="loop"> 
				<tr>
					<td class="text-center">${totalCount - start - loop.index }</td> 
					<td class="text-left"><a href="sub01_view.jsp?board_idx=${list.board_idx }&pageNum=<%=pageNum %>&<%=searchStr %>">${list.title }</a></td>
					<td class="text-center">${list.name }</td>
					<td class="text-center">${list.postdate.substring(0, 10) }</td> 
					<td class="text-center">${list.visitcount }</td>
					<td class="text-center">
						<c:if test="${not empty list.ofile}">
							<i class="material-icons">attach_file</i> 
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</tbody>
	</table>
</div>
<div class="row text-right" style="padding-right:50px;"></div>
<div class="row text-center">
	<!-- 페이지번호 부분 -->
	<ul class="pagination">
		<%=BoardPage.pagingImg2(totalCount, pageNum, request.getRequestURI(), searchStr, flag) %>
	</ul>	
</div>

				</div>
			</div>
		</div>
		<%@ include file="../include/quick.jsp" %>
	</div>


	<%@ include file="../include/footer.jsp" %>
	</center>
 </body>
</html>