<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../include/global_head.jsp" %>

<script>
	$(function(){
		$("#idFind").click(function(){
			if(!$("#id_name").val()){
				alert("이름을 입력해주세요.");
				$("#id_name").focus();
			}
			else if(!$("#id_email").val()){
				alert("이메일 주소를 입력해주세요.");
				$("#id_email").focus();
			}
			else{
				$.ajax({
					url : "../member/id.find",
					type : "post",
					data : {
						name : $("#id_name").val(),
						email : $("#id_email").val()
					},
					dataType : "json",
					success : function(data){ 
						alert(data.result);
						$("#id_name").val("");
						$("#id_email").val("");
					},
					error : function(data){
						alert("ERROR: " + data.status + ":" + data.statusText);
					}
				})
			}
		})
		
		$("#pwFind").click(function(){
			if(!$("#pw_id").val()){
				alert("아이디를 입력해주세요.");
				$("#pw_id").focus();
			}
			else if(!$("#pw_name").val()){
				alert("이름을 입력해주세요.");
				$("#pw_name").focus();
			}
			else if(!$("#pw_email").val()){
				alert("이메일 주소를 입력해주세요.");
				$("#pw_email").focus();
			}
			else{
				$.ajax({
					url : "../member/pw.find",
					type : "post",
					data : {
						id : $("#pw_id").val(),
						name : $("#pw_name").val(),
						email :	$("#pw_email").val()				
					},
					dataType : "json",
					success : function(data){
						alert(data.result);
						$("#pw_id").val("");
						$("#pw_name").val("");
						$("#pw_name").val("");
					},
					error : function(data){
						alert("ERROR: " + data.status + ":" + data.statusText);
					}
				})
			}
		})
	});
</script>

 <body>
	<center>
	<div id="wrap">
		<%@ include file="../include/top.jsp" %>

		<img src="../images/member/sub_image.jpg" id="main_visual" />

		<div class="contents_box">
			<div class="left_contents">
				<%@ include file = "../include/member_leftmenu.jsp" %>
			</div>
			<div class="right_contents">
				<div class="top_title">
					<img src="../images/member/id_pw_title.gif" alt="" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;멤버쉽&nbsp;>&nbsp;아이디/비밀번호찾기<p>
				</div>
				<div class="idpw_box">
					<div class="id_box">
						<form action="" name ="id_box">
							<ul>
								<li><input type="text" id="id_name" value="" class="login_input01" /></li>
								<li><input type="text" id="id_email" value="" class="login_input01" /></li>
							</ul>
							<img id="idFind" src="../images/member/id_btn01.gif" class="id_btn" style ="cursor: pointer"/>
							<a href="../member/join01.jsp"><img src="../images/login_btn03.gif" class="id_btn02" /></a>
						</form>
					</div>
					<div class="pw_box">
						<form action="">
							<ul>
								<li><input type="text" id="pw_id" value="" class="login_input01" /></li>
								<li><input type="text" id="pw_name" value="" class="login_input01" /></li>
								<li><input type="text" id="pw_email" value="" class="login_input01" /></li>
							</ul>
							<img id="pwFind" src="../images/member/id_btn01.gif" class="pw_btn" style ="cursor: pointer"/>
						</form>
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
