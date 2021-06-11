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
		// 문서가 로드될 때 합계 표시
		var total = 0;
		$("input[name='goods_total_price']").each(function(){
			total += parseInt($(this).val());
		});
		
		$("#goods_total_sum").val(total);
		$("#goods_total_payment").val(total);
		
		total = moneyFormat(total) + "원";
		$("#sum").html(total);
		$("#payment").html(total);
		
		
		// 문서가 로드될 때 
		
		
		// 수량 변경 시 DB 및 합계 변경
		$("input[type='number']").bind('mouseup', function(){
			var wrapper = $(this).closest("tr");
			var goods_amount = $(this).val();
			var goods_product_idx = wrapper.find("input[name='goods_product_idx']").val();
			
			$.post(
				"../market/basket.do",
				{
					product_idx : goods_product_idx,
					amount : goods_amount
				},
				function(){
					wrapper.find("input[name='goods_amount']").val(goods_amount)
					alert("수량이 변경되었습니다.");
					location.reload();
				}
			); 
		});
		
		// 체크 해제 시 합계 변경
		$("input[type='checkbox'][name='cart']").change(function(){
			var wrapper = $(this).closest('tr');
			
			var goods_product_idx = wrapper.find("input[name='goods_product_idx']").val();
			var checked_total_price = parseInt(wrapper.find("input[name='goods_total_price']").val());
			var goods_total_sum = parseInt($("#goods_total_sum").val());

			if($(this).is(":checked")){
				$.post(
					"../market/basket.do",
					{
						product_idx: goods_product_idx,
						check: "Y"						
					}	
				);
				goods_total_sum += checked_total_price;
			}
			else{
				$.post(
					"../market/basket.do",
					{
						product_idx: goods_product_idx,
						check: "N"						
					}	
				);
				goods_total_sum -= checked_total_price;
			}
			$("#goods_total_sum").val(goods_total_sum);
			goods_total_sum = moneyFormat(goods_total_sum) + "원";
			
			$("#sum").html(goods_total_sum);
			$("#payment").html(goods_total_sum);
		});
		
		// 구매 버튼 클릭 시 이벤트 발생
		$("#buy_now").click(function(){
			$("input[type='checkbox']:not(:checked)").each(function(){
				var wrapper = $(this).closest("tr");
				var goods_product_idx = wrapper.find("input[name='goods_product_idx']").val();
				$.post(
					"../market/basket.do",
					{
						product_idx: goods_product_idx,
						check: "N"						
					}
				);
			});
			location.href = "../market/list.do?flag=order";
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
									<tr>
										<td style="display: none">
											<input type="hidden" name ="goods_total_price" value="${basket.price * basket.amount }" />
											<input type="hidden" name ="goods_normal_price" value="${basket.price }" />
											<input type="hidden" name ="goods_amount" value="${basket.amount }" />
											<input type="hidden" name ="goods_product_idx" value="${basket.product_idx }" />
											<input type="hidden" name ="goods_product_stock" value="${basket.stock}" />
										</td>
										<td><input type="checkbox" name="cart" value="Y" checked/></td>
										<td><img id="productImg" src="../uploads/${basket.sfile }" /></td>
										<td>${basket.product_name }</td>
										<td>${FormatFunc.moneyFormat(basket.price) }원</td>
										<td><img src="../images/market/j_icon.gif" />&nbsp;${FormatFunc.moneyFormat(basket.reserves) }원</td>
										<td><input type="number" min="1" max="${basket.stock }" value="${basket.amount }" class="basket_num" /></td>
										<td>무료배송</td>
										<td>[조건]</td>
										<td><span>${FormatFunc.moneyFormat(basket.price * basket.amount) }원</span></td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<p class="basket_text">
					<input type="hidden" id ="goods_total_sum" value =""/>
					<input type="hidden" id ="goods_total_payment" value =""/>
					[ 기본 배송 ] 
					<span>상품구매금액</span>&nbsp;   <span id="sum"></span> + 
					<span>배송비</span> 0 = 합계 :    <span id="payment" class="money"></span><br /><br />
					
					<!-- 쇼핑 계속하기 버튼 -->
					<a href="../market/list.do?flag=suamil"><img src="../images/market/basket_btn01.gif" /></a>&nbsp;
					<!-- 구매 버튼 -->
					<img id="buy_now" src="../images/market/basket_btn02.gif" />
				</p>
				
			</div>
		</div>
		<%@ include file="../include/quick.jsp" %>
	</div>
	

	<%@ include file="../include/footer.jsp" %>
	</center>
 </body>
</html>
