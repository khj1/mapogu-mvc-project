<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마포구립장애인 직업재활센터</title>
<style type="text/css" media="screen">
@import url("../css/common.css");
@import url("../css/main.css");
@import url("../css/sub.css");
</style>
<script src="../bootstrap3.3.7/jquery/jquery-3.6.0.min.js"></script>
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
</head>
<body>
<center>
	<div id="wrap">
		<%@ include file="../include/top.jsp"%>
		
		<div id="main_visual">
		<a href="../product/sub01.jsp"><img src="../images/main_image_01.jpg" /></a><a href="../product/sub01_02.jsp"><img src="../images/main_image_02.jpg" /></a><a href="../product/sub01_03.jsp"><img src="../images/main_image_03.jpg" /></a><a href="../product/sub02.jsp"><img src="../images/main_image_04.jpg" /></a>
		</div>

		<div class="main_contents">
			<div class="main_con_left">
				<p class="main_title" style="border:0px; margin-bottom:0px;"><img src="../images/main_title01.gif" alt="로그인 LOGIN" /></p>
				<div class="login_box">
					<c:choose>
						<c:when test="${not empty user_id}">
							<p style="padding:10px 0px 10px 10px"><span style="font-weight:bold; color:#333;">${dto.name }님,</span> 반갑습니다.<br />로그인 하셨습니다.</p>
							<p style="text-align:right; padding-right:10px;">
								<a href=""><img src="../images/login_btn04.gif" id="memberEdit" style ="cursor: pointer"/></a>
								<a href="/Project02/main/logout.do"><img src="../images/login_btn05.gif"/></a>
							</p> 
						</c:when>
						<c:otherwise>
							<form action="../main/login.do" method = "POST">
								<table cellpadding="0" cellspacing="0" border="0">
									<colgroup>
										<col width="45px" />
										<col width="120px" />
										<col width="55px" />
									</colgroup>
									<tr>
										<th><img src="../images/login_tit01.gif" alt="아이디" /></th>
										<td><input type="text" name="user_id" value="${cookie_id }" class="login_input" /></td>
										<td rowspan="2"><input type="image" src="../images/login_btn01.gif" alt="로그인" /></td>
									</tr>
									<tr>
										<th><img src="../images/login_tit02.gif" alt="패스워드" /></th>
										<td><input type="password" name= "user_pass" value="" class="login_input" /></td>
									</tr>
								</table>
								<p>
									<input type="checkbox" name="idSave" value="Y" ${idSaveCheck }/><img src="../images/login_tit03.gif" alt="저장" />
									<a href="../member/id_pw.jsp"><img src="../images/login_btn02.gif" alt="아이디/패스워드찾기" /></a>
									<a href="../member/join01.jsp"><img src="../images/login_btn03.gif" alt="회원가입" /></a>
								</p>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="main_con_center">
				<p class="main_title"><img src="../images/main_title02.gif" alt="공지사항 NOTICE" /><a href="../space/sub01.jsp"><img src="../images/more.gif" alt="more" class="more_btn" /></a></p>
				<ul class="main_board_list">
					<c:forEach items="${noticeList }" var="notice" end="4">
						<li><p><a href="../space/sub01_view.jsp?board_idx=${notice.board_idx }&pageNum=1">${notice.title }</a><span>${notice.postdate }</span></p></li> 
					</c:forEach>
				</ul>
			</div>
			<div class="main_con_right">
				<p class="main_title"><img src="../images/main_title03.gif" alt="자유게시판 FREE BOARD" /><a href="../space/sub03.jsp"><img src="../images/more.gif" alt="more" class="more_btn" /></a></p>
				<ul class="main_board_list">
					<c:forEach items="${freeList }" var="free" end="4">
						<li><p><a href="../space/sub03_view.jsp?board_idx=${free.board_idx }&pageNum=1">${free.title }</a><span>${free.postdate }</span></p></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div class="main_contents">
			<div class="main_con_left">
				<p class="main_title"><img src="../images/main_title04.gif" alt="월간일정 CALENDAR" /></p>
				<img src="../images/main_tel.gif" />
			</div>
			<div class="main_con_center">
				<p class="main_title" style="border:0px; margin-bottom:0px;"><img src="../images/main_title05.gif" alt="월간일정 CALENDAR" /></p>
				<div class="cal_top">
					<table cellpadding="0" cellspacing="0" border="0">
						<colgroup>
							<col width="13px;" />
							<col width="*" />
							<col width="13px;" />
						</colgroup>
						<tr>
							<td><a href=""><img src="../images/cal_a01.gif" style="margin-top:3px;" /></a></td>
							<td><img src="../images/calender_2012.gif" />&nbsp;&nbsp;<img src="../images/calender_m1.gif" /></td>
							<td><a href=""><img src="../images/cal_a02.gif" style="margin-top:3px;" /></a></td>
						</tr>
					</table>
				</div>
				<div class="cal_bottom">
					<table cellpadding="0" cellspacing="0" border="0" class="calendar">
						<colgroup>
							<col width="14%" />
							<col width="14%" />
							<col width="14%" />
							<col width="14%" />
							<col width="14%" />
							<col width="14%" />
							<col width="*" />
						</colgroup>
						<tr>
							<th><img src="../images/day01.gif" alt="S" /></th>
							<th><img src="../images/day02.gif" alt="M" /></th>
							<th><img src="../images/day03.gif" alt="T" /></th>
							<th><img src="../images/day04.gif" alt="W" /></th>
							<th><img src="../images/day05.gif" alt="T" /></th>
							<th><img src="../images/day06.gif" alt="F" /></th>
							<th><img src="../images/day07.gif" alt="S" /></th>
						</tr>
						<tr>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">1</a></td>
							<td><a href="">2</a></td>
							<td><a href="">3</a></td>
						</tr>
						<tr>
							<td><a href="">4</a></td>
							<td><a href="">5</a></td>
							<td><a href="">6</a></td>
							<td><a href="">7</a></td>
							<td><a href="">8</a></td>
							<td><a href="">9</a></td>
							<td><a href="">10</a></td>
						</tr>
						<tr>
							<td><a href="">11</a></td>
							<td><a href="">12</a></td>
							<td><a href="">13</a></td>
							<td><a href="">14</a></td>
							<td><a href="">15</a></td>
							<td><a href="">16</a></td>
							<td><a href="">17</a></td>
						</tr>
						<tr>
							<td><a href="">18</a></td>
							<td><a href="">19</a></td>
							<td><a href="">20</a></td>
							<td><a href="">21</a></td>
							<td><a href="">22</a></td>
							<td><a href="">23</a></td>
							<td><a href="">24</a></td>
						</tr>
						<tr>
							<td><a href="">25</a></td>
							<td><a href="">26</a></td>
							<td><a href="">27</a></td>
							<td><a href="">28</a></td>
							<td><a href="">29</a></td>
							<td><a href="">30</a></td>
							<td><a href="">31</a></td>
						</tr>
						<tr>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">&nbsp;</a></td>
							<td><a href="">&nbsp;</a></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="main_con_right">
				<p class="main_title"><img src="../images/main_title06.gif" alt="사진게시판 PHOTO BOARD" /><a href="../space/sub04.jsp"><img src="../images/more.gif" alt="more" class="more_btn" /></a></p>
				<ul class="main_photo_list">
					<c:forEach items="${photoList }" var="photo">
						<li>
							<dl>
								<dt><a href=""><img src="../uploads/${photo.sfile }" /></a></dt>
								<dd><a href="../space/sub04_view.jsp?board_idx=${photo.board_idx }&pageNum=1">${photo.title }</a></dd>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<%@ include file="../include/quick.jsp"%>
	</div>

	<%@ include file="../include/footer.jsp"%>
	
</center>
</body>
</html>