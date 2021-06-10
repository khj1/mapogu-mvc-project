<%@page import="utils.FormatFunc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	#productImg{width: 300px; height: 199.5px;}
	#totalPrice{font-weight: bold; color: #C10000;}
</style>
<script>
	$(function(){
		$("#amount").bind('mouseup', function(){
			var amount = $(this).val();
			var price = ${dto.price};
			var totalPrice = amount * price;
			var text = moneyFormat(totalPrice) + "원";
			$("#totalPrice").html(text);
		});
		
		$("#basketBtn").click(function(){
			$.post(
				"../market/basket.do",
				{
					product_idx : ${dto.product_idx},
					amount : $("#amount").val()
				},
				function(){
					location.href="../market/list.do?flag=basket";
				}
			);
		});
		
		$("#buyBtn").click(function(){
			
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
				<div class="market_view_box">
					<div class="market_left">
						<img id="productImg" src="../uploads/${dto.sfile }" />
						<p class="plus_btn"><a href=""><img src="../images/market/plus_btn.gif" /></a></p>
					</div>
					<div class="market_right">
						<p class="m_title">${dto.product_name }
						<p>- ${dto.description }</p>
						<ul class="m_list">
							<li>
								<dl>
									<dt>가격</dt>
									<dd class="p_style">${FormatFunc.moneyFormat(dto.price) }원</dd>
								</dl>
								<dl>
									<dt>적립금</dt>
									<dd>${FormatFunc.moneyFormat(dto.reserves) }원</dd>
								</dl>
								<dl>
									<dt>수량</dt>
									<dd><input type="number" id="amount" min="1" max="${dto.stock }" value="1" class="n_box" /></dd>
								</dl>
								<dl style="border-bottom:0px;">
									<dt>주문가격</dt>
									<dd id="totalPrice">${FormatFunc.moneyFormat(dto.price) }원</dd>
								</dl>
								<div style="clear:both;"></div>
							</li>
						</ul>
						<p class="btn_box">
							<img id="buyBtn" src="../images/market/m_btn01.gif" alt="바로구매" style="cursor: pointer; "/>&nbsp;&nbsp;
							<img id="basketBtn" src="../images/market/m_btn02.gif" alt="장바구니" style="cursor: pointer;"/>
						</p>
					</div>
				</div>
				<img src="../uploads/${dto.sfile }" />

			</div>
		</div>
		<%@ include file="../include/quick.jsp" %>
	</div>
	

	<%@ include file="../include/footer.jsp" %>
	</center>
 </body>
</html>
