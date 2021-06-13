<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
$(function(){
	$("input[name='name']").val("테스트 고객명");
	$("input[name='tel1']").val("031");
	$("input[name='tel2']").val("1234");
	$("input[name='tel3']").val("5678");
	$("input[name='mobile1']").val("010");
	$("input[name='mobile2']").val("1234");
	$("input[name='mobile3']").val("5678");
	$("input[name='email1']").val("dhepd488");
	$("input[name='email2']").val("naver.com");
	$("input[name='cake']").val("테스트 케이크");
	$("input[name='cookie']").val("테스트 쿠키");
	$("input[name='reqDate']").val("테스트 희망날짜");
	$("input[name='etc']").val("테스트 특이사항");
	
	$("input[name='isdis']").change(function(){
		var wrapper = $(this).closest("tr");
		if($(this).val() == "N"){
			$("#disType_th").remove();
			$("#disType_td").remove();
		}
		else{
			var html = "";
			html += "<th id='disType_th' style='border-bottom:0px;' width='100px'>주요장애유형</th>";
			html += "<td id='disType_td' style='border-right:0px; border-bottom:0px;'>";
			html += "<input type='text' name='disType'  value='테스트 내용' class='join_input' />";
			html += "</td>";
			wrapper.append(html);
		}
	});
	$("input[name='useDev']").change(function(){
		var wrapper = $(this).closest("tr");
		if($(this).val() == "N"){
			$("#devType_th").remove();
			$("#devType_td").remove();
		}
		else{
			var html = "";
			html += "<th id='devType_th' style='border-bottom:0px;' width='100px'>보장구 명</th>";
			html += "<td id='devType_td' style='border-right:0px; border-bottom:0px;'>";
			html += "<input type='text' name='devType'  value='테스트 내용' class='join_input' />";
			html += "</td>";
			wrapper.append(html);
		}
	});
	
	// 동적으로 생성된 폼값을 전달하기 위해 on() 메소드를 사용
	$("#frmBtn").on("click", function(){
		var name = $("input[name = 'name']");
		var isdis = $("input[name = 'isdis']");
		var disType = $("input[name = 'disType']");
		var useDev = $("input[name = 'useDev']");
		var devType = $("input[name = 'devType']");
		var tel1 = $("input[name = 'tel1']");
		var tel2 = $("input[name = 'tel2']");
		var tel3 = $("input[name = 'tel3']");
		var mobile1 = $("input[name = 'mobile1']");
		var mobile2 = $("input[name = 'mobile2']");
		var mobile3 = $("input[name = 'mobile3']");
		var email1 = $("input[name = 'email1']");
		var email2 = $("input[name = 'email2']");
		var cake = $("input[name = 'cake']");
		var cookie = $("input[name = 'cookie']");
		var reqDate = $("input[name = 'reqDate']");
		var receiptType = $("input[name = 'receiptType']");
		
		if(!name.val()){
			alert("이름을 입력해주세요.");
			name.focus();
		}
		else if(!isdis.val()){
			alert("장애 여부를 선택해주세요.");
			isdis.focus();
		}
		else if(!useDev.val()){
			alert("보장구 사용 여부를 선택해주세요.");
			useDev.focus();
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
		else if(!cake.val()){
			alert("케익 체험 내용을 입력해주세요.");
			cake.focus();
		}
		else if(!cookie.val()){
			alert("쿠키 체험 내용을 입력해주세요.");
			cookie.focus();
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
				url : "../market/field.req",
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
					<img src="../images/market/sub05_title.gif" alt="체험학습신청" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;열린장터&nbsp;>&nbsp;체험학습신청<p>
				</div>
				<p class="con_tit"><img src="../images/market/sub05_tit01.gif" /></p>
				<ul class="dot_list">
					<li>무 방부제 • 무첨가제 수제 빵 제작 체험</li>
					<li>사전에 준비된 반죽으로 성형 및 굽기 체험</li>
					<li>참가비 : 일인당 20,000원 (교육비, 재료비 포함)</li>
				</ul>
				<p class="con_tit"><img src="../images/market/sub05_tit02.gif" /></p>
				<ul class="dot_list">
					<li>내가만든 맛있는 쿠키~!</li>
					<li>쿠키의 반죽 ,성형, 굽기 기술 경험의 기회! </li>
					<li>참가비 : 일인당 15,000원 (교육비, 재료비 포함)</li>
				</ul>
				<p class="con_tit"><img src="../images/market/sub05_tit03.gif" /></p>
				<ul class="dot_list">
					<li>만드는 즐거움 받는이에겐 감동을~!</li>
					<li>직접 데코레이션한 세상에서 하나뿐인
케익을 소중한 사람에게 전하세요~!
</li>
					<li>준비된 케익시트에 테코레이션 과정 체험</li>
					<li>참가비 : 일인당 25,000원 (교육비, 재료비 포함)</li>
				</ul>
				<div style="text-align:left">
					<img src="../images/market/sub05_img01.jpg" style="margin-bottom:30px;" />
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
							<th>장애유무</th>
							<td style="text-align:left;" style="padding:0px;">
								<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
									<tr>
										<td style="border-bottom:0px;">
											<input type="radio" name="isdis"  value="Y" /> 유&nbsp;&nbsp;&nbsp;
											<input type="radio" name="isdis"  value="N" checked/> 무
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>보장구 사용유무</th>
							<td style="text-align:left;" style="padding:0px;">
								<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
									<tr>
										<td style="border-bottom:0px;">
											<input type="radio" name="useDev"  value="Y" /> 유&nbsp;&nbsp;&nbsp;
											<input type="radio" name="useDev"  value="N" checked/> 무
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td style="text-align:left;"><input type="text" name="tel1"  value="" maxlength="3" class="join_input" style="width:50px;" /> - <input type="text" name="tel2"  value="" maxlength="4" class="join_input" style="width:50px;" /> - <input type="text" name="tel3"  value=""  maxlength="4" class="join_input" style="width:50px;" /></td>
						</tr>
						<tr>
							<th>담당자 휴대전화</th>
							<td style="text-align:left;"><input type="text" name="mobile1"  value="" maxlength="3"  class="join_input" style="width:50px;" /> - <input type="text" name="mobile2"  value=""  maxlength="4" class="join_input" style="width:50px;" /> - <input type="text" name="mobile3"  maxlength="4"  value="" class="join_input" style="width:50px;" /></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td style="text-align:left;"><input type="text" name="email1"  value="" class="join_input" style="width:100px;" /> @ <input type="text" name="email2"  value="" class="join_input" style="width:100px;" /></td>
						</tr>
						<tr>
							<th>체험내용</th>
							<td style="text-align:left;" style="padding:0px;">
								<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
									<tr>
										<td>케익체험</td>
										<td style="border-right:0px;"><input type="text" name="cake"  value="" class="join_input" /></td>
									</tr>
									<tr>
										<td style="border-bottom:0px;">쿠키체험</td>
										<td style="border:0px;"><input type="text" name="cookie"  value="" class="join_input" /></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>체험희망날짜</th>
							<td style="text-align:left;"><input type="text" name="reqDate"  value=" class="join_input" /></td>
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
