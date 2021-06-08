<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function(){
		var overlapCheck = false;
		
		$("#overlapCheck").click(function(){
			if(!idCheck()){
				return;
			}
			else{
				$.ajax({
					url: "./idOverlap.jsp", 
					type: "post",
					data: {
						id : $("input[name='id']").val()
					},
					dataType: "json",
					success: function(data){
						if(data.result == "N"){
							overlapCheck = true;
							alert("회원가입이 가능한 아이디입니다.")
						}
						else{
							overlapCheck = false;
							alert("중복된 아이디가 존재합니다.");
							$("input[name='id']").focus();
						}
					},
					error: function(data){
						alert("ERROR :" + data.status + " : " + data.statusText);
					}
				});
			}
		});
		
		$("form").submit(function(){
			if(!$("input[name='name']").val()){
				alert("이름을 입력해주세요");
				$("input[name='name']").focus(); 
				return false;
			}
			else if(!idCheck()){ return false; }
			else if(!pwCheck()){ return false; }
			
			else if(!$("input[name='tel1']").val()){
				alert("전화번호를 입력해주세요");
				$("input[name='tel1']").focus(); 
				return false;
			}
			else if(!$("input[name='tel2']").val()){
				alert("전화번호를 입력해주세요");
				$("input[name='tel2']").focus(); 
				return false;
			}
			else if(!$("input[name='tel3']").val()){
				alert("전화번호를 입력해주세요");
				$("input[name='tel3']").focus(); 
				return false;
			}
			else if(!$("input[name='mobile1']").val()){
				alert("휴대전화 번호를 입력해주세요");
				$("input[name='mobile1']").focus(); 
				return false;
			}
			else if(!$("input[name='mobile2']").val()){
				alert("휴대전화 번호를 입력해주세요");
				$("input[name='mobile2']").focus(); 
				return false;
			}
			else if(!$("input[name='mobile3']").val()){
				alert("휴대전화 번호를 입력해주세요");
				$("input[name='mobile3']").focus(); 
				return false;
			}
			else if(!$("input[name='email_1']").val()){
				alert("이메일을 입력해주세요");
				$("input[name='email_1']").focus(); 
				return false;
			}
			else if(!$("input[name='email_2']").val()){
				alert("이메일을 입력해주세요");
				$("input[name='email_2']").focus(); 
				return false;
			}
			else if(!$("input[name='zip']").val()){
				alert("우편번호를 입력해주세요");
				$("input[name='zip1']").focus(); 
				return false;
			}
			else if(!$("input[name='addr1']").val()){
				alert("주소를 입력해주세요.");
				$("input[name='addr1']").focus();
				return false;
			}
			else if(!$("input[name='addr2']").val()){
				alert("상세주소를 입력해주세요");
				$("input[name='addr2']").focus();
				return false;
			}
			else{
				if(!overlapCheck){
					alert("아이디 중복체크를 해주세요.");
					$("input[name='id']").focus();
					return false;
				}
			}
		});
	});
	
	function idCheck() {
		var id = $("input[name='id']");
		if(!id.val()){
			alert("아이디를 입력해주세요");
			id.focus();
			return false;
		}
		if(id.val().length < 4 || id.val().length > 12){
            alert("아이디는 4~12자 이내로 입력해주세요.");
            id.focus();
            id.val("");
            return false;
        }
		
		for(var i=0; i<id.val().length; i++){
            if(!(id.val().toUpperCase().codePointAt(i)>=65 && 
            	 id.val().toUpperCase().codePointAt(i)<=90)
               && 
               !(id.val().toUpperCase().codePointAt(i)>=48 &&
				 id.val().toUpperCase().codePointAt(i)<=57)){
                alert("아이디는 숫자와 영문으로만 만들어주세요.");
                id.focus();
                id.val("");
                return false;
	        }
		}
		return true;
		
	}
	
	function pwCheck() {
		var pw = $("input[name='pass']");
		var pw2 = $("input[name='pass2']");
		if(!pw.val()){
			alert("비밀번호를 입력해주세요");
			pw.focus();
			return false;
		}
		if(pw.val().length < 4 || pw.val().length > 12){
            alert("비밀번호는 4~12자 이내로 입력해주세요.");
            pw.focus();
            pw.val("");
            pw2.val("");
            return false;
        }
		var cntN = 0; cntE = 0;
		for(var i=0; i<pw.val().length; i++){
            if(pw.val().toUpperCase().codePointAt(i)>=65 &&
           	   pw.val().toUpperCase().codePointAt(i)<=90)
            	cntE++;
            else if(pw.val().codePointAt(i)>=48 && 
            		pw.val().codePointAt(i)<=57)
            	cntN++;
		}
		if(cntN == 0 || cntE == 0){
	        alert("비밀번호는 숫자와 영문을 조합해서 만들어주세요.");
	        pw.focus();
	        pw.val("");
	        pw2.val("");
	        return false;
			
		}
		if(!pw2.val()){
			alert("확인 비밀번호를 입력해주세요");
			pw2.focus();
			return false;
		}
		return true;
		
	}
	
	function email_input(option) {
		var domain = $("input[name='email_2']");
		if(option.value == ""){
			domain.attr("readOnly", true);
			domain.val("");
		}
		else if(option.value == "1"){
			domain.attr("readOnly", false);
			domain.val("");
			domain.focus();
		}
		else{
			domain.attr("readOnly", true);
			domain.val(option.value);
		}
	}
	
	function zipcodeFind(){ 
		new daum.Postcode({
			oncomplete: function(data) {
			    $("input[name = 'zip']").val(data.zonecode);
			    $("input[name = 'addr1']").val(data.address);
			    $("input[name = 'addr2']").focus();
			}
		}).open();
	}
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
					<img src="../images/join_tit.gif" alt="회원가입" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;멤버쉽&nbsp;>&nbsp;회원가입<p>
				</div>

				<p class="join_title"><img src="../images/join_tit03.gif" alt="회원정보입력" /></p>
				
				<!-- 폼값 시작 -->
				<form action = "../member/regist.do" method ="POST">
					<table cellpadding="0" cellspacing="0" border="0" class="join_box">
						<colgroup>
							<col width="80px;" />
							<col width="*" />
						</colgroup>
						<tr>
							<th><img src="../images/join_tit001.gif" /></th>
							<td><input type="text" name="name" value="" class="join_input" /></td>
						</tr>
						<tr>
							<th><img src="../images/join_tit002.gif" /></th>
							<td><input type="text" name="id"  value="" class="join_input" />&nbsp;<img src="../images/btn_idcheck.gif" id = "overlapCheck" alt="중복확인" style = "cursor: pointer;"/>&nbsp;&nbsp;<span>* 4자 이상 12자 이내의 영문/숫자 조합하여 공백 없이 기입</span></td>
						</tr>
						<tr>
							<th><img src="../images/join_tit003.gif" /></th>
							<td><input type="password" name="pass" value="" class="join_input" />&nbsp;&nbsp;<span>* 4자 이상 12자 이내의 영문/숫자 조합</span></td>
						</tr>
						<tr>
							<th><img src="../images/join_tit04.gif" /></th>
							<td><input type="password" name="pass2" value="" class="join_input" /></td>
						</tr>
						
	
						<tr>
							<th><img src="../images/join_tit06.gif" /></th>
							<td>
								<input type="text" name="tel1" value="" maxlength="3" class="join_input" style="width:50px;" />&nbsp;-&nbsp;
								<input type="text" name="tel2" value="" maxlength="4" class="join_input" style="width:50px;" />&nbsp;-&nbsp;
								<input type="text" name="tel3" value="" maxlength="4" class="join_input" style="width:50px;" />
							</td>
						</tr>
						<tr>
							<th><img src="../images/join_tit07.gif" /></th>
							<td>
								<input type="text" name="mobile1" value="" maxlength="3" class="join_input" style="width:50px;" />&nbsp;-&nbsp;
								<input type="text" name="mobile2" value="" maxlength="4" class="join_input" style="width:50px;" />&nbsp;-&nbsp;
								<input type="text" name="mobile3" value="" maxlength="4" class="join_input" style="width:50px;" /></td>
						</tr>
						<tr>
							<th><img src="../images/join_tit08.gif" /></th>
							<td>
	 
							<input type="text" name="email_1" style="width:100px;height:20px;border:solid 1px #dadada;" value="" /> @ 
							<input type="text" name="email_2" style="width:150px;height:20px;border:solid 1px #dadada;" value="" readonly />
							<select name="last_email_check2" onChange="email_input(this);" class="pass" id="last_email_check2" >
								<option selected="" value="">선택해주세요</option>
								<option value="1" >직접입력</option>
								<option value="dreamwiz.com" >dreamwiz.com</option>
								<option value="empal.com" >empal.com</option>
								<option value="empas.com" >empas.com</option>
								<option value="freechal.com" >freechal.com</option>
								<option value="hanafos.com" >hanafos.com</option>
								<option value="hanmail.net" >hanmail.net</option>
								<option value="hotmail.com" >hotmail.com</option>
								<option value="intizen.com" >intizen.com</option>
								<option value="korea.com" >korea.com</option>
								<option value="kornet.net" >kornet.net</option>
								<option value="msn.co.kr" >msn.co.kr</option>
								<option value="nate.com" >nate.com</option>
								<option value="naver.com" >naver.com</option>
								<option value="netian.com" >netian.com</option>
								<option value="orgio.co.kr" >orgio.co.kr</option>
								<option value="paran.com" >paran.com</option>
								<option value="sayclub.com" >sayclub.com</option>
								<option value="yahoo.co.kr" >yahoo.co.kr</option>
								<option value="yahoo.com" >yahoo.com</option>
							</select>
		 
							<input type="checkbox" name="open_email" value="1">
							<span>이메일 수신동의</span></td>
						</tr>
						<tr>
							<th><img src="../images/join_tit09.gif" /></th>
							<td>
							<input type="text" name="zip" value=""  class="join_input" style="width:60px;" />
							<a href="javascript:;" title="새 창으로 열림" onclick="zipcodeFind();">[우편번호검색]</a>
							<br/>
							
							<input type="text" name="addr1" value=""  class="join_input" style="width:550px; margin-top:5px;" /><br>
							<input type="text" name="addr2" value=""  class="join_input" style="width:550px; margin-top:5px;" />
							
							</td>
						</tr>
					</table>
					<p style="text-align:center; margin-bottom:20px">
						<input type ="image" src="../images/btn01.gif" alt="회원가입"/>
						&nbsp;&nbsp;
						<a href="#">
							<img src="../images/btn02.gif" />
						</a>
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
