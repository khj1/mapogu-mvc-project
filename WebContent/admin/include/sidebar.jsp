<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="sidebar navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="../admin/list.do?flag=member">
          <i class="fas fa-fw fa-table"></i>
          <span>회원 관리</span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-fw fa-table"></i>
          <span>신청 현황</span></a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
          <a class="dropdown-item" href="../admin/list.do?flag=request&type=clean">블루클리닝</a>
          <a class="dropdown-item" href="../admin/list.do?flag=request&type=field">체험학습</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-fw fa-folder"></i>
          <span>게시물 관리</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
          <h6 class="dropdown-header">열린공간:</h6>
          <a class="dropdown-item" href="../admin/list.do?flag=board&type=notice">공지사항</a>
          <a class="dropdown-item" href="../admin/list.do?flag=board&type=calendar">프로그램일정</a>
          <a class="dropdown-item" href="../admin/list.do?flag=board&type=free">자유게시판</a>
          <a class="dropdown-item" href="../admin/list.do?flag=board&type=photo">사진게시판</a>
          <a class="dropdown-item" href="../admin/list.do?flag=board&type=archive">정보자료실</a>
          <div class="dropdown-divider"></div>
          <h6 class="dropdown-header">커뮤니티:</h6>
          <a class="dropdown-item" href="../admin/list.do?flag=board&type=data">직원자료실</a>
          <a class="dropdown-item" href="../admin/list.do?flag=board&type=comp">보호자 게시판</a>
        </div>
      </li>
      
      
    </ul>
</body>
</html>