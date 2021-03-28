<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/dist/js/ajax/model/ajax_canh_bao.js"></script>
<header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo"><b>Tâm</b>Việt</a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
    </nav>
</header>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="dist/img/avatar04.png" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
                <p>Lê Anh Đức</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Chuyên viên</a>
            </div>
        </div>
        <!-- search form -->
        <!-- <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search..." />
                <span class="input-group-btn">
                    <button type='submit' name='search' id='search-btn' class="btn btn-flat"><i
                            class="fa fa-search"></i></button>
                </span>
            </div>
        </form> -->
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="active">
                <a href="man-hinh-theo-doi">
                    <i class="fa fa-dashboard"></i> <span>Màn hình theo dõi</span>
                </a>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>Quản lý doanh nghiệp</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="danh-sach-doanh-nghiep"><i class="fa fa-circle-o"></i> Danh sách doanh nghiệp</a></li>
                    <li><a href="danh-sach-xe-cho-duyet"><i class="fa fa-circle-o"></i> Danh sách xe chờ duyệt</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>Thống kê, báo cáo</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="tong-hop-danh-sach-doanh-nghiep"><i class="fa fa-circle-o"></i> Tổng hợp danh sách doanh nghiệp</a></li>
                    <li><a href="tong-hop-tru-luong-khai-thac"><i class="fa fa-circle-o"></i> Tổng hợp trữ lượng khai thác</a></li>
                    <li><a href="bao-cao-luong-xe"><i class="fa fa-circle-o"></i> Báo cáo theo lượng xe ra vào mỏ</a></li>
                    <li><a href="bao-cao-tong-hop-vi-pham"><i class="fa fa-circle-o"></i> Báo cáo sai phạm</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>Giám sát camera</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="hinh-anh-luu-tru"><i class="fa fa-circle-o"></i> Hình ảnh lưu trữ</a></li>
                    <li><a href="hinh-anh-vi-pham"><i class="fa fa-circle-o"></i> Hình ảnh vi phạm</a></li>
                    <li><a href="xem-truc-tuyen"><i class="fa fa-circle-o"></i> Xem trực tuyến</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-laptop"></i>
                    <span>Tra cứu thông tin</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="tra-cuu-thong-bao"><i class="fa fa-circle-o"></i> Tra cứu thông báo</a></li>
                    <li><a href="tra-cuu-nguoi-dung"><i class="fa fa-circle-o"></i> Tra cứu người dùng</a></li>
                    <li><a href="tra-cuu-thiet-bi"><i class="fa fa-circle-o"></i> Tra cứu thiết bị</a></li>
                    <li><a href="tra-cuu-xe-van-chuyen"><i class="fa fa-circle-o"></i> Tra cứu xe vận chuyển</a></li>
                    <li><a href="tra-cuu-giay-phep-khai-thac"><i class="fa fa-circle-o"></i> Tra cứu giấy phép khai thác</a></li>
                    <li><a href="tra-cuu-doanh-nghiep"><i class="fa fa-circle-o"></i> Tra cứu thông tin doanh nghiệp</a></li>
                    <li><a href="tra-cuu-mo"><i class="fa fa-circle-o"></i> Tra cứu thông tin mỏ</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>Thông báo, báo cáo</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="gui-thong-bao"><i class="fa fa-circle-o"></i> Gửi thông báo</a></li>
                    <li><a href="nhan-bao-cao"><i class="fa fa-circle-o"></i> Nhận báo cáo</a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="noti">
        <div class="notileft">
            <span>Thông báo</span>
        </div>
        <marquee id="marq" scrollamount="10" direction="left" loop="infinite" scrolldelay="0" behavior="scroll"
                 onmouseover="this.stop()" onmouseout="this.start()" class="notiright">
<%--            <span>- Doanh nghiệp A bị mất kết nối</span>--%>
<%--            <span>- Doanh nghiệp A bị mất kết nối</span>--%>
<%--            <span>- Doanh nghiệp A bị mất kết nối</span>--%>
<%--            <span>- Doanh nghiệp A bị mất kết nối</span>--%>
<%--            <span>- Doanh nghiệp A bị mất kết nối</span>--%>
        </marquee>
    </section>

<!-- Right side column. Contains the navbar and content of the page -->