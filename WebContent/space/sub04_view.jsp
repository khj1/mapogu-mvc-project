<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../include/isLogin.jsp" %>
<%@ include file="../include/global_head.jsp" %>
<%@ include file="./common/view_include.jsp" %>
<%! String board = "sub04"; %>
<script>
	$(function(){
		$("#edit").click(function(){
			location.href="sub04_edit.jsp?board_idx=<%=board_idx %>&pageNum=<%=pageNum %>";
		});
		$("#delete").click(function(){
			if(confirm("게시물을 삭제하시겠습니까?"))
				location.href="./common/delete_process.jsp?board_idx=<%=board_idx %>&board=<%=board%>&sfile=${dto.sfile}";
		});
		$("#list").click(function(){
			location.href="sub04.jsp?board_idx=<%=board_idx %>&pageNum=<%=pageNum %>&<%=searchStr %>";
		});
	});
</script>
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

<form enctype="multipart/form-data">
<table class="table table-bordered">
<colgroup>
	<col width="20%"/>
	<col width="30%"/>
	<col width="20%"/>
	<col width="*"/>
</colgroup>
<tbody>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">작성자</th>
		<td>
			${dto.name }
		</td>
		<th class="text-center" 
			style="vertical-align:middle;">작성일</th>
		<td>
			${dto.postdate }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">이메일</th>
		<td>
			${dto.email }
		</td>
		<th class="text-center" 
			style="vertical-align:middle;">조회수</th>
		<td>
			${dto.visitcount }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">제목</th>
		<td colspan="3">
			${dto.title }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">내용</th>
		<td colspan="3">
			<img src="../uploads/${dto.sfile }" /><br />
			${dto.content }
		</td>
	</tr>
	<tr>
		<th class="text-center" 
			style="vertical-align:middle;">첨부파일</th>
		<td colspan="3">
			${dto.ofile }
			<a href="./common/download_process.jsp?ofile=${dto.ofile }&sfile=${dto.sfile}&board_idx=${dto.board_idx}">
				[이미지 다운로드]
			</a>
		</td>
	</tr>
</tbody>
</table>

<div class="row text-center" style="">
	<c:if test="${user_id == dto.id }">
		<button type="button" class="btn btn-primary" id="edit">수정하기</button>
		<button type="button" class="btn btn-success" id="delete">삭제하기</button>	
	</c:if>
	<button type="button" class="btn btn-warning" id="list">리스트보기</button>
</div>
</form> 

				</div>
			</div>
		</div>
		<%@ include file="../include/quick.jsp" %>
	</div>


	<%@ include file="../include/footer.jsp" %>
	</center>
 </body>
</html>