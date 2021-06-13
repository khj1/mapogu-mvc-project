<%@page import="utils.FormatFunc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	.basket_list th{text-align: center;}
	.product_img{width: 70px; height: 50px;}
</style>
<script>
	$(function(){
		//문서가 로드될 때 합계 표시
		var total = 0;
		$("input[name='goods_total_price']").each(function(){
			total += parseInt($(this).val());
		});
		$("input[name='hidden_total_price']").val(total);
		total = moneyFormat(total) + "원";
		$("#payment").html(total);
	
		//주문자 정보를 배송지 정보에 그대로 입력
		$("input[name='isEqual']").click(function(){
			if($(this).val() == 'Y'){
				$("input[name='receipt_name']").val($("input[name='order_name']").val());
				$("input[name='receipt_zipcode']").val($("input[name='order_zipcode']").val());
				$("input[name='receipt_addr1']").val($("input[name='order_addr1']").val());
				$("input[name='receipt_addr2']").val($("input[name='order_addr2']").val());
				$("input[name='receipt_mobile1']").val($("input[name='order_mobile1']").val());
				$("input[name='receipt_mobile2']").val($("input[name='order_mobile2']").val());
				$("input[name='receipt_mobile3']").val($("input[name='order_mobile3']").val());
				$("input[name='receipt_email1']").val($("input[name='order_email1']").val());
				$("input[name='receipt_email2']").val($("input[name='order_email2']").val());
			}
			else{
				$("input[name='receipt_name']").val("");
				$("input[name='receipt_zipcode']").val("");
				$("input[name='receipt_addr1']").val("");
				$("input[name='receipt_addr2']").val("");
				$("input[name='receipt_mobile1']").val("");
				$("input[name='receipt_mobile2']").val("");
				$("input[name='receipt_mobile3']").val("");
				$("input[name='receipt_email1']").val("");
				$("input[name='receipt_email2']").val("");
			}
		});
		
		// 결제하기 버튼을 눌렀을 때
		$("#orderFrm").submit(function(){
			var order_name = $("input[name = 'order_name']");
			var order_zipcode = $("input[name = 'order_zipcode']");
			var order_addr1 = $("input[name = 'order_addr1']");
			var order_addr2 = $("input[name = 'order_addr2']");
			var order_mobile1 = $("input[name = 'order_mobile1']");
			var order_mobile2 = $("input[name = 'order_mobile2']");
			var order_mobile3 = $("input[name = 'order_mobile3']");
			var order_email1 = $("input[name = 'order_email1']");
			var order_email2 = $("input[name = 'order_email2']");
			var receipt_name = $("input[name = 'receipt_name']");
			var receipt_zipcode = $("input[name = 'receipt_zipcode']");
			var receipt_addr1 = $("input[name = 'receipt_addr1']");
			var receipt_addr2 = $("input[name = 'receipt_addr2']");
			var receipt_mobile1 = $("input[name = 'receipt_mobile1']");
			var receipt_mobile2 = $("input[name = 'receipt_mobile2']");
			var receipt_mobile3 = $("input[name = 'receipt_mobile3']");
			var receipt_email1 = $("input[name = 'receipt_email1']");
			var receipt_email2 = $("input[name = 'receipt_email2']");
			
			if(!order_name.val()){
				alert("주문자의 이름을 입력해주세요.");
				order_name.focus();
				return false;
			}
			if(!order_zipcode.val()){
				alert("주문자의 주소를 입력해주세요.");
				order_zipcode.focus();
				return false;
			}
			if(!order_addr1.val()){
				alert("주문자의 주소를 입력해주세요.");
				order_addr1.focus();
				return false;
			}
			if(!order_mobile1.val()){
				alert("주문자의 휴대전화 번호를 입력해주세요.");
				order_mobile1.focus();
				return false;
			}
			if(!order_mobile2.val()){
				alert("주문자의 휴대전화 번호를 입력해주세요.");
				order_mobile2.focus();
				return false;
			}
			if(!order_mobile3.val()){
				alert("주문자의 휴대전화 번호를 입력해주세요.");
				order_mobile3.focus();
				return false;
			}
			if(!receipt_name.val()){
				alert("수령인의 이름을 입력해주세요.");
				receipt_name.focus();
				return false;
			}
			if(!receipt_zipcode.val()){
				alert("수령인의 주소를 입력해주세요.");
				receipt_zipcode.focus();
				return false;
			}
			if(!receipt_addr1.val()){
				alert("배송지 주소를 입력해주세요.");
				receipt_addr1.focus();
				return false;
			}
			if(!receipt_mobile1.val()){
				alert("수령인의 휴대전화 번호를 입력해주세요.");
				receipt_mobile1.focus();
				return false;
			}
			if(!receipt_mobile2.val()){
				alert(" 수령인의 휴대전화 번호를 입력해주세요.");
				receipt_mobile2.focus();
				return false;
			}
			if(!receipt_mobile3.val()){
				alert("수령인의 휴대전화 번호를 입력해주세요.");
				receipt_mobile3.focus();
				return false;
			}
			if(!receipt_email1.val()){
				alert("수령인의 이메일을 입력해주세요.");
				receipt_email1.focus();
				return false;
			}
			if(!receipt_email2.val()){
				alert("수령인의 이메일을 입력해주세요.");
				receipt_email2.focus();
				return false;
			}
			if(!$("input[type='radio'][name='payment_type']").is(":checked")){
				alert("결제 방식을 선택해주세요.");
				return false;
			}
		});
	});
	
	// 화폐 단위로 변환해주는 함수
	function moneyFormat(price) {
	    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	}
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
					<img src="../images/market/sub01_title.gif" alt="수아밀 제품 주문" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;열린장터&nbsp;>&nbsp;수아밀 제품 주문<p>
				</div>
				<p class="con_tit"><img src="../images/market/basket_title01.gif" /></p>
				<table cellpadding="0" cellspacing="0" border="0" class="basket_list" style="margin-bottom:50px;">
					<colgroup>
						<col width="10%" />
						<col width="*" />
						<col width="10%" />
						<col width="10%" />
						<col width="8%" />
						<col width="10%" />
						<col width="10%" />
						<col width="8%" />
					</colgroup>
					<thead>
						<tr>
							<th>이미지</th>
							<th>상품명</th>
							<th>판매가</th>
							<th>수량</th>
							<th>총 적립금</th>
							<th>배송구분</th>
							<th>배송비</th>
							<th>합계</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orderList }" var="order">
							<tr>
								<td style="display: none;">
									<input type="hidden" name ="goods_total_price" value="${order.price * order.amount }" />
									<input type="hidden" name ="goods_normal_price" value="${order.price }" />
									<input type="hidden" name ="goods_product_idx" value="${order.product_idx }" />
								</td>
								<td><img class ="product_img" src="../uploads/${order.sfile }" /></td>
								<td>${order.product_name }</td>
								<td>${FormatFunc.moneyFormat(order.price) }원</td>
								<td>${order.amount }</td>
								<td><img src="../images/market/j_icon.gif" />&nbsp;${FormatFunc.moneyFormat(order.amount * order.reserves )}원</td>
								<td>무료배송</td>
								<td>[조건]</td>
								<td><span>${FormatFunc.moneyFormat(order.amount * order.price) }원<span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<p class="con_tit"><img src="../images/market/basket_title02.gif" /></p>
				
				<!-- 폼값 시작 -->
				<form id ="orderFrm" action="../market/order.do" method="post">
				<input type="hidden" name="hidden_total_price" value=""/>
				<table cellpadding="0" cellspacing="0" border="0" class="con_table" style="width:100%;" style="margin-bottom:50px;">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th>성명</th>
							<td style="text-align:left;"><input type="text" name="order_name"  value="${mDto.name }" class="join_input" /></td>
						</tr>
						<tr>
							<th>주소</th>
							<td style="text-align:left;">
								<input type="text" name="order_zipcode"  value="${mDto.address.substring(0, 5)}" class="join_input" style="width:80px; margin-bottom:5px;" /><a href=""><img src="../images/market/basket_btn03.gif" style="margin-bottom:5px;" /></a><br />
								<input type="text" name="order_addr1"  value="${mDto.address.substring(6)}" class="join_input" style="width:300px; margin-bottom:5px;" /> 기본주소<br />
								<input type="text" name="order_addr2"  value="" class="join_input" style="width:300px;" /> 나머지주소</td>
						</tr>
						<tr>
							<th>휴대폰</th>
							<td style="text-align:left;"><input type="text" name="order_mobile1"  value="${mDto.mobile.split('-')[0] }" class="join_input" style="width:50px;" /> - <input type="text" name="order_mobile2"  value="${mDto.mobile.split('-')[1] }" class="join_input" style="width:50px;" /> - <input type="text" name="order_mobile3"  value="${mDto.mobile.split('-')[2] }" class="join_input" style="width:50px;" /></td>
						</tr>
						<tr>
							<th>이메일주소</th>
							<td style="text-align:left;"><input type="text" name="order_email1"  value="${mDto.email.split('@')[0] }" class="join_input" style="width:100px;" /> @ <input type="text" name="order_email2"  value="${mDto.email.split('@')[1] }" class="join_input" style="width:100px;" /></td>
						</tr>
					</tbody>
				</table>

				<p class="con_tit"><img src="../images/market/basket_title03.gif" /></p>
				<p style="text-align:right">
					배송지 정보가 주문자 정보와 동일합니까? 
					예<input type="radio" name =isEqual value = "Y"/> 
					아니오<input type="radio" name =isEqual value = "N" />
				</p>
				<table cellpadding="0" cellspacing="0" border="0" class="con_table" style="width:100%;" style="margin-bottom:50px;">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th>성명</th>
							<td style="text-align:left;"><input type="text" name="receipt_name"  value="" class="join_input" /></td>
						</tr>
						<tr>
							<th>주소</th>
							<td style="text-align:left;">
								<input type="text" name="receipt_zipcode"  value="" class="join_input" style="width:80px; margin-bottom:5px;" />
								<a href="">
									<img src="../images/market/basket_btn03.gif" style="margin-bottom:5px;" />
								</a><br />
								<input type="text" name="receipt_addr1"  value="" class="join_input" style="width:300px; margin-bottom:5px;" /> 기본주소<br />
								<input type="text" name="receipt_addr2"  value="" class="join_input" style="width:300px;" /> 나머지주소</td>
						</tr>
						<tr>
							<th>휴대폰</th>
							<td style="text-align:left;"><input type="text" name="receipt_mobile1"  value="" class="join_input" style="width:50px;" /> - <input type="text" name="receipt_mobile2"  value="" class="join_input" style="width:50px;" /> - <input type="text" name="receipt_mobile3"  value="" class="join_input" style="width:50px;" /></td>
						</tr>
						<tr>
							<th>이메일주소</th>
							<td style="text-align:left;"><input type="text" name="receipt_email1"  value="" class="join_input" style="width:100px;" /> @ <input type="text" name="receipt_email2"  value="" class="join_input" style="width:100px;" /></td>
						</tr>
						<tr>
							<th>배송메세지</th>
							<td style="text-align:left;">
								<input type="text" name="receipt_msg"  value="" class="join_input" style="width:500px;" />
							</td>
						</tr>
					</tbody>
				</table>

				<p class="con_tit"><img src="../images/market/basket_title04.gif" /></p>
				<table cellpadding="0" cellspacing="0" border="0" class="con_table" style="width:100%;" style="margin-bottom:30px;">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th>결제금액</th>
							<td style="text-align:left;"><span class="money" id="payment"></span></td>
						</tr>
						<tr>
							<th>결제방식선택</th>
							<td style="text-align:left;">
								<input type="radio" name="payment_type" value="card"/> 카드결제&nbsp;&nbsp;&nbsp;
								<input type="radio" name="payment_type" value="without"/> 무통장입금&nbsp;&nbsp;&nbsp;
								<input type="radio" name="payment_type" value="transfer"/> 실시간 계좌이체&nbsp;&nbsp;&nbsp;
								<input type="radio" name="payment_type" value="kakao"/> 카카오페이</td>
						</tr>
					</tbody>
				</table>
				<p style="text-align:right;"><input type="image" id="pay" src="../images/market/basket_btn04.gif"/></p>
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
