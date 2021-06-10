<%@page import="utils.FormatFunc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	th{text-align: center;}
	#productImg{width: 70px; height: 50px;}
	input[type='number']{width: 30px;}
</style>
<script>
	$(function(){
		var total = 0;
		$("input[name='hiddenTotal']").each(function(){
			total += parseInt($(this).val());
		});
		var total = moneyFormat(total) + "원";
		$("#sum").html(total);
		$("#payment").html(total);
		
		$("input[type='number']").bind('mouseup', function(){
			$.ajax({
				
			});
		});
	});
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
				<table cellpadding="0" cellspacing="0" border="0" class="basket_list">
					<colgroup>
						<col width="7%" />
						<col width="10%" />
						<col width="*" />
						<col width="10%" />
						<col width="8%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="8%" />
					</colgroup>
					<thead>
						<tr>
							<th>선택</th>
							<th>이미지</th>
							<th>상품명</th>
							<th>판매가</th>
							<th>적립금</th>
							<th>수량</th>
							<th>배송구분</th>
							<th>배송비</th>
							<th>합계</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty basketList }">
								<tr>
									<td colspan="9">장바구니가 비었습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${basketList }" var="basket">
									<input type="hidden" name ="hiddenTotal" value="${basket.price * basket.amount }" />
									<tr>
										<td><input type="checkbox" name="check" value="Y" /></td>
										<td><img id="productImg" src="../uploads/${basket.sfile }" /></td>
										<td>${basket.product_name }</td>
										<td>${FormatFunc.moneyFormat(basket.price) }원</td>
										<td><img src="../images/market/j_icon.gif" />&nbsp;${FormatFunc.moneyFormat(basket.reserves) }원</td>
										<td><input type="number" id="${ }" min="1" max="${basket.stock }" value="1" class="basket_num" /></td>
										<td>무료배송</td>
										<td>[조건]</td>
										<td><span id="totalPrice">${FormatFunc.moneyFormat(basket.price * basket.amount) }원</span></td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<p class="basket_text">
					[ 기본 배송 ] <span>상품구매금액</span>&nbsp;<span id="sum"></span> + <span>배송비</span> 0 = 합계 : <span id="payment" class="money"></span><br /><br />
					<a href=""><img src="../images/market/basket_btn01.gif" /></a>&nbsp;<a href="basket02.jsp"><img src="../images/market/basket_btn02.gif" /></a>
				</p>
			</div>
		</div>
		<%@ include file="../include/quick.jsp" %>
	</div>
	

	<%@ include file="../include/footer.jsp" %>
	</center>
 </body>
</html>
