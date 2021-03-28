<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <img src="./dist/img/imgquy.png" class="img-circle" alt="User Image" />
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
                    <span>Quản lý hàng hóa</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="danh-sach-doanh-nghiep"><i class="fa fa-circle-o"></i> Danh sách hàng hóa</a></li>
                    <li><a href="danh-sach-xe-cho-duyet"><i class="fa fa-circle-o"></i> Danh mục</a></li>
                    <li><a href="danh-sach-xe-cho-duyet"><i class="fa fa-circle-o"></i> Thiết lập giá</a></li>
                    <li><a href="danh-sach-xe-cho-duyet"><i class="fa fa-circle-o"></i> Kiểm kho</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>Quản lý giao dịch</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="hinh-anh-luu-tru"><i class="fa fa-circle-o"></i> Hóa đơn</a></li>
                    <li><a href="hinh-anh-vi-pham"><i class="fa fa-circle-o"></i> Trả hàng</a></li>
                    <li><a href="xem-truc-tuyen"><i class="fa fa-circle-o"></i> Nhập hàng</a></li>
                    <li><a href="xem-truc-tuyen"><i class="fa fa-circle-o"></i> Trả hàng nhập</a></li>
                    <li><a href="xem-truc-tuyen"><i class="fa fa-circle-o"></i> Xuất hủy</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>Quản lý đối tác</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="hinh-anh-luu-tru"><i class="fa fa-circle-o"></i>Khách hàng</a></li>
                    <li><a href="hinh-anh-vi-pham"><i class="fa fa-circle-o"></i>Nhà cung cấp</a></li>
              
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>Quản lý nhân viên</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="hinh-anh-luu-tru"><i class="fa fa-circle-o"></i>Nhân viên</a></li>
                    <li><a href="hinh-anh-vi-pham"><i class="fa fa-circle-o"></i>Chấm công</a></li>
                    <li><a href="hinh-anh-vi-pham"><i class="fa fa-circle-o"></i>Bảng tính lương</a></li>
              
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
                    <li><a href="bao-cao-tong-hop-vi-pham"><i class="fa fa-circle-o"></i> Báo cáo</a></li>
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
                    <li><a href="tra-cuu-thiet-bi"><i class="fa fa-circle-o"></i> Tra cứu hàng hóa</a></li>
                    <li><a href="tra-cuu-doanh-nghiep"><i class="fa fa-circle-o"></i> Tra cứu thông tin khách hàng</a></li>
                    <li><a href="tra-cuu-thong-bao"><i class="fa fa-circle-o"></i> Tra cứu thông tin nhà cung cấp</a></li>
                    <li><a href="tra-cuu-nguoi-dung"><i class="fa fa-circle-o"></i> Tra cứu thông tin nhân viên</a></li>
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