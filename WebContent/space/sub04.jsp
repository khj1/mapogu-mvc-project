<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!String flag = "photo";%>
<style>
	.photo_wrap{width: 800px;}
	.photo_list{float: left;}
	.photo_list li{float: left; width:200px; margin-bottom:5px;}
	.photo_list dl :nth-child(2){font-size:13px;}
	.photo_list dl :nth-child(3){font-size:11px; color:#797979;}
	.photo_list li img{border:1px solid #cecece; width: 180px; height: 180px; margin-bottom: 5px;}
</style>
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
					<img src="../images/space/sub04_title.gif" alt="사진게시판" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;열린공간&nbsp;>&nbsp;사진게시판<p> 
				</div>
				<div>

<div class="row text-right" style="margin-bottom:20px;
		padding-right:50px;">
<!-- 검색부분 -->
<form class="form-inline">	
	<div class="form-group">
		<select name="searchField" class="form-control">
			<option value="title">제목</option>
			<option value="name">작성자</option>
		</select>
	</div>
	<div class="input-group">
		<input type="text" name="searchWord"  class="form-control"/>
		<div class="input-group-btn">
			<button type="submit" class="btn btn-default">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
</form>	
</div>
<div class="photo_wrap">
	<p><b>전체 게시물: ${totalCount }개</b></p>
	<ul class="photo_list">
		<c:choose>
			<c:when test="${empty boardList }">
				<li style ="text-align: center">
					<dl>
						<dt>등록된 게시물이 없습니다.</dt>
					</dl>
				</li>
			</c:when>
			<c:otherwise>
				<c:forEach items="${boardList }" var="photo">
					<li>
						<dl>
							<dt><a href="../space/sub04_view.jsp?board_idx=${photo.board_idx }&pageNum=<%=pageNum%>&<%=searchStr %>"><img src="../uploads/${photo.sfile }" /></a></dt>
							<dd>
								<a href="../space/sub04_view.jsp?board_idx=${photo.board_idx }&pageNum=<%=pageNum%>&<%=searchStr %>">
									<!-- 긴 제목 문자열 처리를 한번 더 해줌 -->
									<c:choose>
										<c:when test="${photo.title.length() > 12 }">
											${photo.title.substring(0,12) }...
										</c:when>
										<c:otherwise>
											${photo.title }
										</c:otherwise>
									</c:choose>
								</a>
							</dd>
							<dd>
								${photo.name}&nbsp;&nbsp;
								<i class='far fa-calendar-alt' style='font-size:11px'></i> ${photo.postdate.substring(5, 10)}&nbsp;&nbsp;
								<i class='far fa-eye' style='font-size:11px'></i> ${photo.visitcount }
							</dd>
						</dl>
					</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<div class="row text-right" style="padding-right:50px; position: relative; padding-bottom: 20px">
	<!-- 각종 버튼 부분 -->
	<div style="position: absolute; bottom: 0; right:0;">
		<button type="button" class="btn btn-default"
			onclick="location.href='sub04_write.jsp';">글쓰기</button>
	</div>
</div>
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