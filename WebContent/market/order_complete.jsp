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
		total = moneyFormat(total) + "원";
		$("#payment").html(total);
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
							<td style="text-align:left;">${oDto.order_name }</td>
						</tr>
						<tr>
							<th>주소</th>
							<td style="text-align:left;">${oDto.order_addr}</td>
						</tr>
						<tr>
							<th>휴대폰</th>
							<td style="text-align:left;">${oDto.order_mobile}</td>
						</tr>
						<tr>
							<th>이메일주소</th>
							<td style="text-align:left;">${oDto.order_email}</td>
						</tr>
					</tbody>
				</table>

				<p class="con_tit"><img src="../images/market/basket_title03.gif" /></p>
				<table cellpadding="0" cellspacing="0" border="0" class="con_table" style="width:100%;" style="margin-bottom:50px;">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th>성명</th>
							<td style="text-align:left;">${oDto.receipt_name }</td>
						</tr>
						<tr>
							<th>주소</th>
							<td style="text-align:left;">${oDto.receipt_addr }</td>
						</tr>
						<tr>
							<th>휴대폰</th>
							<td style="text-align:left;">${oDto.receipt_mobile }</td>
						</tr>
						<tr>
							<th>이메일주소</th>
							<td style="text-align:left;">${oDto.receipt_email }</td>
						</tr>
						<tr>
							<th>배송메세지</th>
							<td style="text-align:left;">${oDto.receipt_msg }</td>
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
					</tbody>
				</table>
				<p style="text-align:right;"><input type="button" id="main" value ="홈으로" onclick="location.href='../main/main.do'" /></p>
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
