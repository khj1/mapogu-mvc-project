<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	$(function(){
		$("input[name='name']").val("테스트 고객명");
		$("input[name='addr']").val("테스트 주소");
		$("input[name='tel1']").val("031");
		$("input[name='tel2']").val("1234");
		$("input[name='tel3']").val("5678");
		$("input[name='mobile1']").val("010");
		$("input[name='mobile2']").val("1234");
		$("input[name='mobile3']").val("5678");
		$("input[name='email1']").val("dhepd488");
		$("input[name='email2']").val("naver.com");
		$("input[name='cleanType']").val("테스트 청소분류");
		$("input[name='acreage']").val("테스트 등기평수");
		$("input[name='reqDate']").val("테스트 희망날짜");
		$("input[name='etc']").val("테스트 특이사항");
		
		$("#frmBtn").click(function(){
			var name = $("input[name = 'name']");
			var addr = $("input[name = 'addr']");
			var tel1 = $("input[name = 'tel1']");
			var tel2 = $("input[name = 'tel2']");
			var tel3 = $("input[name = 'tel3']");
			var mobile1 = $("input[name = 'mobile1']");
			var mobile2 = $("input[name = 'mobile2']");
			var mobile3 = $("input[name = 'mobile3']");
			var email1 = $("input[name = 'email1']");
			var email2 = $("input[name = 'email2']");
			var cleanType = $("input[name = 'cleanType']");
			var acreage = $("input[name = 'acreage']");
			var reqDate = $("input[name = 'reqDate']");
			var receiptType = $("input[name = 'receiptType']");
			
			if(!name.val()){
				alert("이름을 입력해주세요.");
				name.focus();
			}
			else if(!addr.val()){
				alert("주소를 입력해주세요.");
				addr.focus();
			}
			else if(!tel1.val()){
				alert("전화번호를 입력해주세요.");
				tel1.focus();
			}
			else if(!tel2.val()){
				alert("전화번호를 입력해주세요.");
				tel2.focus();
			}
			else if(!tel3.val()){
				alert("전화번호를 입력해주세요.");
				tel3.focus();
			}
			else if(!mobile1.val()){
				alert("휴대전화 번호를 입력해주세요.");
				mobile1.focus();
			}
			else if(!mobile2.val()){
				alert("휴대전화 번호를 입력해주세요.");
				mobile2.focus();
			}
			else if(!mobile3.val()){
				alert("휴대전화 번호를 입력해주세요.");
				mobile3.focus();
			}
			else if(!email1.val()){
				alert("이메일을 입력해주세요.");
				email1.focus();
			}
			else if(!email2.val()){
				alert("이메일을 입력해주세요.");
				email2.focus();
			}
			else if(!cleanType.val()){
				alert("청소 종류를 입력해주세요.");
				cleanType.focus();
			}
			else if(!acreage.val()){
				alert("등기 평수를 입력해주세요.");
				acreage.focus();
			}
			else if(!reqDate.val()){
				alert("청소 희망 날짜를 입력해주세요.");
				reqDate.focus();
			}
			else if(!receiptType.val()){
				alert("접수 종류를 선택해주세요.");
				receiptType.focus();
			}
			else{
				$.ajax({
					url : "../market/clean.req",
					type : "post",
					data : $("#frm").serialize(),
					dataType : "json",
					success : function(data){
						alert(data.msg);
						location.href = data.url;
					},
					error : function(data){
						alert("ERROR: " + data.status + " : " + data.statusText);
					}
				});
			}
		});
	});
</script>
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
				<form id="frm">
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
							<td style="text-align:left;"><input type="text" name="tel1"  maxlength="3" value="" class="join_input" style="width:50px;" /> - <input type="text" name="tel2" maxlength="4" value="" class="join_input" style="width:50px;" /> - <input type="text" name="tel3" maxlength="4"  value="" class="join_input" style="width:50px;" /></td>
						</tr>
						<tr>
							<th>휴대전화</th>
							<td style="text-align:left;"><input type="text" name="mobile1"  maxlength="3" value="" class="join_input" style="width:50px;" /> - <input type="text" name="mobile2" maxlength="4"  value="" class="join_input" style="width:50px;" /> - <input type="text" name="mobile3" maxlength="4"  value="" class="join_input" style="width:50px;" /></td>
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
							<td style="text-align:left;"><input type="text" name="reqDate"  value="" class="join_input" /></td>
						</tr>
						<tr>
							<th>접수종류 구분</th>
							<td style="text-align:left;">
								<input type="radio" name="receiptType"  value="예약신청" checked/> 예약신청&nbsp;&nbsp;&nbsp;				
								<input type="radio" name="receiptType"  value="견적문의" /> 견적문의
							</td>
						</tr>
						<tr>
							<th>기타특이사항</th>
							<td style="text-align:left;"><input type="text" name="etc"  value="" class="join_input" style="width:400px;" /></td>
						</tr>
					</tbody>
				</table>
				<p style="text-align:center; margin-bottom:40px"><img id="frmBtn" src="../images/btn01.gif" />&nbsp;&nbsp;<a href="../main/main.do"><img src="../images/btn02.gif" /></a></p>
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
