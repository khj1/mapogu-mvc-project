<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	$(function(){
		$("form").submit(function(){
			if(!$("input[name='user_id']").val()){
				alert("아이디를 입력해주세요.");
				$("input[name='user_id']").focus();
				return false;
			}
			if(!$("input[name='user_pass']").val()){
				alert("비밀번호를 입력해주세요.");
				$("input[name='user_pass']").focus();
				return false;
			}
		});
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
				<form action="../main/login.do" method = "POST">
					<div class="top_title">
						<img src="../images/login_title.gif" alt="인사말" class="con_title" />
						<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;멤버쉽&nbsp;>&nbsp;로그인<p>
					</div>
					<div class="login_box01">
						<img src="../images/login_tit.gif" style="margin-bottom:30px;" />
						<!-- 로그인폼 -->
							<ul>
								<li><img src="../images/login_tit001.gif" alt="아이디" style="margin-right:15px;" /><input type="text" name="user_id" value="${cookie_id }" class="login_input01" /></li>
								<li><img src="../images/login_tit002.gif" alt="비밀번호" style="margin-right:15px;" /><input type="password" name="user_pass" value="" class="login_input01" /></li>
							</ul>
							<input type="image" src="../images/login_btn.gif" class="login_btn01" />
					</div>
					<p style="text-align:center; margin-bottom:50px;">
						<input type="checkbox" name="idSave" value="Y" ${idSaveCheck }/><img src="../images/login_tit03.gif" alt="저장" />
						<a href="../member/id_pw.jsp"><img src="../images/login_btn02.gif" alt="아이디/패스워드찾기" /></a> &nbsp;
						<a href="../member/join01.jsp"><img src="../images/login_btn03.gif" alt="회원가입" /></a>
					</p>
				</form>
			</div>
		</div>
		<%@ include file="../include/quick.jsp" %>
	</div>
	

	<%@ include file="../include/footer.jsp" %>
	</center>
 </body>
</html>
