<%@page import="utils.FormatFunc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	.productImg{ width: 100px; height: 66.5px;}
	th{text-align: center;}
</style>
<script>
	$(function(){
		$("img[name='buy_now']").click(function(){
			
		});
		
		$("img[name='basket']").click(function(){
			var wrapper = $(this).closest("tr");
			if(wrapper.find("input[name='check']").is(":checked")){
				$.post(
					"../market/basket.do",
					{
						product_idx : wrapper.find("input[name='goods_product_idx']").val(),
						amount : wrapper.find("input[name='goods_amount']").val()
					},
					function(){
						location.href = "../market/list.do?flag=basket";					
					}
				)
			}
			else{
				alert("원하시는 상품을 체크해주세요.")
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
					<img src="../images/market/sub01_title.gif" alt="수아밀 제품 주문" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;열린장터&nbsp;>&nbsp;수아밀 제품 주문<p>
				</div>
				<table cellpadding="0" cellspacing="0" border="0" class="market_board01">
					<colgroup>
						<col width="5%" />
						<col width="20%" />
						<col width="*" />
						<col width="10%" />
						<col width="10%" />
						<col width="15%" />
					</colgroup>
					<tr>
						<th>선택</th>
						<th>상품이미지</th>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
						<th>구매</th>
					</tr>
						<c:choose>
							<c:when test="${empty boardList }">
								<tr>
									<td colspan="6" align="center">등록된 상품이 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${boardList }" var="list" varStatus="loop">
									<tr>
										<td style="display: none;">
											<input type="hidden" name= "goods_product_idx" value="${list.product_idx }"/>
										</td>
										<td><!-- 상품선택 -->
											<input type="checkbox" name="check" value="Y" />
										</td>
										
										<td><!-- 상품이미지 -->
											<a href="../market/view.do?flag=suamil&product_idx=${list.product_idx }">
												<img class ="productImg" src="../uploads/${list.sfile }" />
											</a>
										</td>
										
										<!-- 상품이름 -->
										<td class="t_left">
											<a href="../market/view.do?flag=suamil&product_idx=${list.product_idx }&pageNum=${pageNum}">
												${list.product_name }
											</a>
										</td>
										
										<!-- 상품가격 -->
										<td class="p_style">${FormatFunc.moneyFormat(list.price)}원</td>
										
										<td><!-- 상품수량 -->
											<input type="number" name="goods_amount" value="1" min="1" max="${list.stock }" class="n_box" />
										</td>
										
										<td><!-- 바로구매 -->
											<img name="buy_now" src="../images/market/btn01.gif" style="margin-bottom:5px; cursor: pointer" />
											
											<!-- 장바구니 -->
											<img name="basket" src="../images/market/btn02.gif" style="cursor: pointer"/>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
				</table>
				<div class="row text-center">
					<!-- 페이지번호 부분 -->
					<ul class="pagination">
						${pagingStr }
					</ul>	
				</div>
			</div>
		</div>
		<%@ include file="../include/quick.jsp" %>
	</div>
	

	<%@ include file="../include/footer.jsp" %>
	</center>
 </body>
</html>
