<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>게시판 관리</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Navbar -->
  <%@ include file= "../include/navbar.jsp" %>

  <div id="wrapper">

    <!-- Sidebar -->
    <%@ include file= "../include/sidebar.jsp" %>

    <div id="content-wrapper">
 
      <div class="container-fluid">

        <!-- DataTables Example -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-table"></i>
            신청현황 / 체험학습</div>
          <div class="card-body">
            <div class="table-responsive" align="center">
              <table class="table table-bordered" style="width: 70%">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th>고객명/회사명</th>
							<td style="text-align:left;">${dto.name }</td>
						</tr>
						<tr>
							<th>장애유무</th>
							<td style="text-align:left;" style="padding:0px;">
								<table class="table table-bordered" >
									<tr>
										<td style="text-align:left;" style="padding:0px;">
											${dto.isdis }
										</td>
										<th style='border-bottom:0px;' width='100px'>장애유형</th>
										<td style='border-right:0px; border-bottom:0px;'>${dto.disType }</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>보장구 사용유무</th>
							<td style="text-align:left;" style="padding:0px;">
								<table class="table table-bordered" >
									<tr>
										<td style="text-align:left;" style="padding:0px;">
											${dto.useDev }
										</td>
										<th style='border-bottom:0px;' width='100px'>보장구 명</th>
										<td style='border-right:0px; border-bottom:0px;'>${dto.devType }</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td style="text-align:left;">${dto.tel }</td>
						</tr>
						<tr>
							<th>담당자 휴대전화</th>
							<td style="text-align:left;">${dto.mobile }</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td style="text-align:left;">${dto.email }</td>
						</tr>
						<tr>
							<th>체험내용</th>
							<td style="text-align:left;" style="padding:0px;">
								<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
									<tr>
										<td width="10%">케익체험</td>
										<td style="border-right:0px;">${dto.cake }</td>
									</tr>
									<tr>
										<td width="10%" style="border-bottom:0px;">쿠키체험</td>
										<td style="border:0px;">${dto.cookie }</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>체험희망날짜</th>
							<td style="text-align:left;">${dto.reqDate }</td>
						</tr>
						<tr>
							<th>접수종류 구분</th>
							<td style="text-align:left;">
								${dto.receiptType }
							</td>
						</tr>
						<tr>
							<th>기타특이사항</th>
							<td style="text-align:left;">${dto.etc }</td>
						</tr>
					</tbody>
				</table>
            </div>
          </div> 
          <div style="text-align: right;">
          	<button type="button" class="btn btn-danger" style="margin-right: 10px; margin-bottom: 10px;">삭제하기</button>
          </div>
          <div class="card-footer small text-muted">Updated at 11:59 PM</div>
        </div>

      </div>
      <!-- /.container-fluid -->

      <!-- Sticky Footer -->
      <footer class="sticky-footer">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright © Your Website 2019</span>
          </div>
        </div>
      </footer>

    </div>
    <!-- /.content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Page level plugin JavaScript-->
  <script src="vendor/datatables/jquery.dataTables.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin.min.js"></script>

  <!-- Demo scripts for this page-->
  <script src="js/demo/datatables-demo.js"></script>

</body>

</html>
