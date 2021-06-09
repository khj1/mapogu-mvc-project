<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../include/isLogin.jsp" %>
<%@ include file="../include/global_head.jsp" %>
<script> 
	$(function(){
		$("form").submit(function(){
			if(!$("input[name='pass']").val()){
				alert("비밀번호를 입력해주세요.")
				return false;
			}
			if(!$("input[name='title']").val()){
				alert("제목을 입력해주세요.")
				return false;
			}
			if(!$("textarea[name='content']").val()){
				alert("내용을 입력해주세요.")
				return false;
			}
		});
	});
</script>
 <body>
	<center>
	<div id="wrap">
		<%@ include file="../include/top.jsp" %>

		<img src="../images/community/sub_image.jpg" id="main_visual" />

		<div class="contents_box">
			<div class="left_contents">
				<%@ include file = "../include/community_leftmenu.jsp" %>
			</div>
			<div class="right_contents">
				<div class="top_title">
					<img src="../images/community/sub01_title.gif" alt="직원자료실" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;커뮤니티&nbsp;>&nbsp;직원자료실<p> 
				</div>
				<div>

<form enctype="multipart/form-data" method="post" action="../community/edit.do">
	<input type="hidden" name="flag" value="data" />
	<input type="hidden" name="board" value="${board }" />
	<input type="hidden" name="prevOfile" value="${bDto.ofile }" />
	<input type="hidden" name="prevSfile" value="${bDto.sfile }" />
	<input type="hidden" name="board_idx" value="${bDto.board_idx }" />
	<input type="hidden" name="pageNum" value="${pageNum }" />
	<table class="table table-bordered">
	<colgroup>
		<col width="20%"/>
		<col width="*"/>
	</colgroup>
	<tbody>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">작성자</th>
			<td>
				<input name="name" type="text" value="${bDto.name }"
					class="form-control" style="width:100px;" readOnly/>
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">이메일</th>
			<td>
				<input name="email" type="text" value="${bDto.email }"
					class="form-control" style="width:400px;" readOnly/>
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">패스워드</th>
			<td>
				<input name="pass" type="password" 
					class="form-control" style="width:200px;" />
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">제목</th>
			<td>
				<input name="title" type="text" class="form-control" value="${bDto.title }"/>
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">내용</th>
			<td>
				<textarea name="content" rows="10" class="form-control">${bDto.content }</textarea>
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">첨부파일</th>
			<td>
				<c:if test="${not empty bDto.ofile }">
					이미 첨부된 파일이 있습니다. 파일을 새로 첨부하면 기존 파일은 제거됩니다.
				</c:if>
				<input name="ofile" type="file" class="form-control"/>
			</td>
		</tr>
	</tbody>
	</table>
	
	<div class="row text-center" style="">
		<!-- 각종 버튼 부분 -->
		<button type="submit" class="btn btn-danger">수정하기</button>
		<button type="button" class="btn" onclick="history.back();">취소하기</button>
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