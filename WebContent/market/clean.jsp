<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <body>
	<center>
	<div id="wrap">
		<%@ include file="../include/top.jsp" %>

		<img src="../images/market/sub_image.jpg" id="main_visual" />

		<div class="contents_box">
			<div class="left_contents">
				
				<%@ include file = "../include/market_leftmenu.jsp" %>
			</div>
			<div class="right_contents">
				<div class="top_title">
					<img src="../images/market/sub03_title.gif" alt="블루크리닝 견적 의뢰" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;열린장터&nbsp;>&nbsp;블루크리닝 견적 의뢰<p>
				</div>
				
				<!-- 폼값 시작 -->
				<form action="">
				<table cellpadding="0" cellspacing="0" border="0" class="con_table" style="width:100%;">
					<colgroup>
						<col width="25%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th>고객명/회사명</th>
							<td style="text-align:left;"><input type="text" name="name"  value="" class="join_input" /></td>
						</tr>
						<tr>
							<th>청소할 곳 주소</th>
							<td style="text-align:left;"><input type="text" name="addr"  value="" class="join_input" style="width:300px;" /></td>
						</tr>
						<tr>
							<th>연락처</th>
							<td style="text-align:left;"><input type="text" name="tel1"  value="" class="join_input" style="width:50px;" /> - <input type="text" name="tel2"  value="" class="join_input" style="width:50px;" /> - <input type="text" name="tel3"  value="" class="join_input" style="width:50px;" /></td>
						</tr>
						<tr>
							<th>휴대전화</th>
							<td style="text-align:left;"><input type="text" name="mobile1"  value="" class="join_input" style="width:50px;" /> - <input type="text" name="mobile2"  value="" class="join_input" style="width:50px;" /> - <input type="text" name="mobile3"  value="" class="join_input" style="width:50px;" /></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td style="text-align:left;"><input type="text" name="email1"  value="" class="join_input" style="width:100px;" /> @ <input type="text" name="email2"  value="" class="join_input" style="width:100px;" /></td>
						</tr>
						<tr>
							<th>청소의뢰내역</th>
							<td style="text-align:left;" style="padding:0px;">
								<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
									<tr>
										<td>청소종류</td>
										<td style="border-right:0px;"><input type="text" name="cleanType"  value="" class="join_input" /></td>
									</tr>
									<tr>
										<td style="border-bottom:0px;">분양평수/등기평수</td>
										<td style="border:0px;"><input type="text" name="acreage"  value="" class="join_input" /></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>청소 희망날짜</th>
							<td style="text-align:left;"><input type="text" name="date"  value="" class="join_input" /></td>
						</tr>
						<tr>
							<th>접수종류 구분</th>
							<td style="text-align:left;">
								<input type="radio" name="receiptType"  value="예약신청" /> 예약신청&nbsp;&nbsp;&nbsp;				
								<input type="radio" name="receiptType"  value="견적문의" /> 견적문의
							</td>
						</tr>
						<tr>
							<th>기타특이사항</th>
							<td style="text-align:left;"><input type="text" name="etc"  value="" class="join_input" style="width:400px;" /></td>
						</tr>
					</tbody>
				</table>
				<p style="text-align:center; margin-bottom:40px"><a href=""><img src="../images/btn01.gif" /></a>&nbsp;&nbsp;<a href="#"><img src="../images/btn02.gif" /></a></p>
				</form>
				<!-- 폼값 끝 -->
				
			</div>
		</div>
		<%@ include file="../include/quick.jsp" %>
	</div>
	

	<%@ include file="../include/footer.jsp" %>
	</center>
 </body>
</html>
