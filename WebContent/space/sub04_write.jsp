<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../include/isLogin.jsp" %>
<%@ include file="../include/global_head.jsp" %>
<%@ include file="./common/write_include.jsp" %>
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
        
		$("input[name='ofile']").change(function(){
            readURL(this);
        });
        
        $("img").load(function(){
        });
	});
    function readURL(input) {
        if (input.files && input.files[0]) {
           var reader = new FileReader();
           reader.onload = function (e) {
        	   $('#preview').html("<img src="+ e.target.result +">");
           }
           reader.readAsDataURL(input.files[0]);
        }
    }
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

<form enctype="multipart/form-data" method="post" action="./common/write_process.jsp">
	<input type="hidden" name="board" value="sub04" />
	<input type="hidden" name="flag" value="photo" />
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
				style="vertical-align:middle;">업로드 된 이미지</th>
			<td>
				<div id="preview"></div>
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
				style="vertical-align:middle;">이미지 업로드</th>
			<td>
				<input name="ofile" type="file" accept=".bmp,.jpg,.jpeg,.png" class="form-control" />
			</td>
		</tr>
	</tbody>
	</table>
	
	<div class="row text-center" style="">
		<!-- 각종 버튼 부분 -->
		
		<button type="submit" class="btn btn-danger">글쓰기</button>
		<button type="button" class="btn btn-warning" 
			onclick="location.href='sub04.jsp';">취소하기</button>
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