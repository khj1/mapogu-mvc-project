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

  <title>회원 관리</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
  <script src="../bootstrap3.3.7/jquery/jquery-3.6.0.min.js"></script>

</head>
<script>
	$(function(){
		$("#frm").submit(function(){
			var auth = $("input[name='auth']");
			if(!auth.val()){
				alert("권한을 설정해주세요.");
				auth.focus();
				return false;
			}
		});
			
		$("#cancel").click(function(){
			location.href="../admin/list.do?flag=${flag}&type";
		});
	});
</script>
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
            회원 관리 / 회원 정보</div>
          <form action="../admin/edit.do" method="post" id="frm">
          <div class="card-body">
            <div class="table-responsive">
              <input type="hidden" name="id" value="${id }"/>
              <table class="table table-bordered">
					<colgroup>
						<col width="10%" />
						<col width="*" />
					</colgroup>
					<tr>
						<th>이름</th>
						<td>${dto.name }</td>
					</tr>
					<tr>
						<th>권한</th>
						<td><input name="auth" type="text" class="form-control"/></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>${dto.id }</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>${dto.pass }</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${dto.tel }</td>
					</tr>
					<tr>
						<th>핸드폰번호</th>
						<td>${dto.mobile }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${dto.email }</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>${dto.address }</td>
					</tr>
				</table>
            </div>
          </div> 
          <div style="text-align: right;">
          	<button id = "cancel" type="button" class="btn btn-danger" style="margin-right: 10px; margin-bottom: 10px;">취소하기</button>
          	<button id = "edit" type="submit" class="btn btn-success" style="margin-right: 10px; margin-bottom: 10px;">권한수정</button>
          </div>
          </form>
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
