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
					<img src="../images/community/sub02_title.gif" alt="보호자 게시판" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;커뮤니티&nbsp;>&nbsp;보호자 게시판<p> 
				</div>
				<div>

<form enctype="multipart/form-data" method="post" action="../community/write.do">
	<input type="hidden" name="board" value="${board }" />
	<input type="hidden" name="flag" value="${flag }" />
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
				<input name="name" type="text" value="${mDto.name }"
					class="form-control" style="width:100px;" readOnly/>
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">이메일</th>
			<td>
				<input name="email" type="text" value="${mDto.email }"
					class="form-control" style="width:400px;" readOnly/>
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">패스워드</th>
			<td>
				<input name="pass" type="password" class="form-control" 
					style="width:200px;" />
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">제목</th>
			<td>
				<input name="title" type="text" class="form-control" />
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">내용</th>
			<td>
				<textarea name="content" rows="10" class="form-control"></textarea>
			</td>
		</tr>
		<tr>
			<th class="text-center" 
				style="vertical-align:middle;">첨부파일</th>
			<td>
				<input name="ofile" type="file" class="form-control" />
			</td>
		</tr>
	</tbody>
	</table>
	
	<div class="row text-center" style="">
		<!-- 각종 버튼 부분 -->
		
		<button type="submit" class="btn btn-danger">전송하기</button>
		<button type="reset" class="btn">Reset</button>
		<button type="button" class="btn btn-warning" 
			onclick="location.href='list.do?flag=data&board=sub01';">리스트보기</button>
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