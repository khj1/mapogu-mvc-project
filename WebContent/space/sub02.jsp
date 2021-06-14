<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/global_head.jsp" %>
<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
 	th{text-align: center;}
	.calendar_center{ width:100%; }
	.calendar_top{ width:100%; }
	.calendar_top table{ width:100%; text-align: center;}
	.calendar_bottom{ width: 100%; margin-top: 10px;}
	.calendar_content{ width:100%; }
	.calendar_content th{ border: 1px solid black; }
	.calendar_content td{ border: 1px solid black; height: 100px;}
 </style> 
 <script>
 	var year = ${year};
	var month = ${month};
	var i = 14;
 	
 	$(function(){
 		// 문서가 로드될 때 달력 레이아웃 출력
 		getCalendar(year, month);
 		
 		// 이전달 버튼을 눌렀을 때
		$("#prev_month").click(function(){
			if(month == 1){
				year = year - 1;
				month = 12;
			}
			else{
				month = month - 1;
			}
			location.href="../space/list.cal?year=" + year + "&month=" + month + "#calendar_start";
		});
		
		// 다음달 버튼을 눌렀을 때
		$("#next_month").click(function(){
			if(month == 12){
				year = year + 1;
				month = 1;
			}
			else{
				month = month + 1;
			}
			location.href="../space/list.cal?year=" + year + "&month=" + month + "#calendar_start";
		});
 	});
 	
 	function getCalendar(year_input, month_input) {
		$.ajax({
			url: "../calendar/preview.cal",
			type: "get",
			data: {
				year : year_input,
				month : month_input
			},
			dataType: "json",
			success: function(data){
				var html = "";
				$.each(data, function(index, week){
					html += "<tr>";
					$.each(week, function(index, day){
						if(index == 0){
							html += "<td id=" + day + " valign='top' style='color:red;'>";
						}
						else if(index == 6){
							html += "<td id=" + day + " valign='top' style='color:#1c8dff;'>";
						}
						else{
							html += "<td id=" + day + " valign='top'>";
						}
						
						html += 	day + "<br/>" ; 
						
						// 해당 날짜에 맞는 게시물 출력
						<c:forEach items="${cList}" var="cDto">
							var dayStr = ${cDto.caldate.substring(8,10)};
							var calDay = parseInt(dayStr);
							if(calDay == day){
								html +=  "<a href='../space/sub02_view.jsp?board_idx=${cDto.board_idx }&year=${year}&month=${month}'> ${cDto.title} </a>";
							}
						</c:forEach>
						
						html += "</td>";
					});
					html += "</tr>";
				});
				$("#today").html(year + "년&nbsp;" + month + "월");
				$("#dayImages").after(html);
				
			},
			error: function(data){
				alert("ERROR: " + data.status + ":" + data.statusText);
			}
		});
	}
 </script>
 <body> 

	<center>
	<div id="wrap">
		<%@ include file="../include/top.jsp" %> 

		<img src="../images/space/sub_image.jpg" id="main_visual" />

		<div class="contents_box">
			<div class="left_contents">
				<%@ include file = "../include/space_leftmenu.jsp" %>
			</div>
			<div class="right_contents">
				<div class="top_title">
					<img src="../images/space/sub02_title.gif" alt="프로그램 일정" class="con_title" />
					<p class="location"><img src="../images/center/house.gif" />&nbsp;&nbsp;열린공간&nbsp;>&nbsp;프로그램 일정<p>
				</div>
				<div>

				<!-- 캘린더 시작 -->
				<div class="calendar_center" align="center"> 
					<div class="calendar_top"> 
						<table cellpadding="0" cellspacing="0" border="0">
							<colgroup>
								<col width="13px;" /> 
								<col width="*" />
								<col width="13px;" />
							</colgroup>
							<tr>
								<td><img id="prev_month" src="../images/cal_a01.gif" style="margin-top:3px; cursor: pointer;" /></td>
								<td id="today" style="font-weight: bold; font-size: 1.1em; cursor: pointer;"></td>
								<td><img id="next_month" src="../images/cal_a02.gif" style="margin-top:3px; cursor: pointer;" /></td>
							</tr>
						</table>
					</div>
					<div class="calendar_bottom">
						<table cellpadding="0" cellspacing="0" border="0" class="calendar_content" style="width: 100%;">
							<colgroup>
								<col width="14%" />
								<col width="14%" />
								<col width="14%" />
								<col width="14%" />
								<col width="14%" />
								<col width="14%" />
								<col width="*" />
							</colgroup>
							
							<tr id ="dayImages">
								<th>일</th>
								<th>월</th>
								<th>화</th>
								<th>수</th>
								<th>목</th>
								<th>금</th>
								<th>토</th>
							</tr>
						</table>
						<a name="calendar_start"></a> <!-- 앵커 지정 -->
					</div>
				</div>
				<!-- 캘린더 끝 -->


				</div>
			</div>
		</div>
		<%@ include file="../include/quick.jsp" %>
	</div>


	<%@ include file="../include/footer.jsp" %>
	</center>
 </body>
</html>