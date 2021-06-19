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
  <script src="../bootstrap3.3.7/jquery/jquery-3.6.0.min.js"></script>
</head>
<script>
	$(function(){
		$("#frm").submit(function(){
			var title = $("input[name='title']");
			var content = $("textarea[name='content']");
			
			if(!title.val()){
				alert("제목을 입력해주세요.");
				title.focus();
				return false;
			}
			if(!content.val()){
				alert("내용을 입력해주세요.");
				content.focus();
				return false;
			}
		});
			
		$("#cancel").click(function(){
			location.href="../admin/list.do?flag=${flag}&type=${type}";
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
            게시판 관리 / 공지사항</div>
         <form action="../admin/write.do" method="post" id="frm">
          <div class="card-body">
            <div class="table-responsive">
				<table class="table table-bordered">
				<colgroup>
					<col width="20%"/>
					<col width="*"/>
				</colgroup>
				<tbody>
					<tr>
						<td style="display: none;">
							<input type="hidden" name="type" value="${type }" />
							<input type="hidden" name="flag" value="${flag }" />
						</td>
						<th class="text-center" 
							style="vertical-align:middle;">작성자</th>
						<td>
							<input name="name" type="text" value="관리자"
								class="form-control" style="width:100px;" readOnly/>
						</td>
					</tr>
					<tr>
						<th class="text-center" 
							style="vertical-align:middle;">제목</th>
						<td>
							<input name="title" type="text" class="form-control" />
						</td>
					</tr>
					<tr>
						<th class="text-center" 
							style="vertical-align:middle;">내용</th>
						<td>
							<textarea name="content" rows="10" class="form-control"></textarea>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<th class="text-center"  -->
<!-- 							style="vertical-align:middle;">첨부파일</th> -->
<!-- 						<td> -->
<!-- 							<input name="ofile" type="file" class="form-control" /> -->
<!-- 						</td> -->
<!-- 					</tr> -->
				</tbody>
				</table>
            </div>
          </div> 
          <div style="text-align: right;">
          	<button id="cancel" type="button" class="btn btn-danger" style="margin-right: 10px; margin-bottom: 10px;">취소하기</button>
          	<button id="write" type="submit" class="btn btn-info" style="margin-right: 10px; margin-bottom: 10px;">글쓰기</button>
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
          <button id="delete" class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
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
