<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/dist/js/ajax/model/ajax_nguoi_dung.js"></script>
<script src="resources/dist/js/ajax/quan_ly_doanh_nghiep/ajax_thong_tin_nguoi_dung.js"></script>
<!-- Quản lí thông tin doanh nghiệp Css -->
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">thông tin người dùng</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="caifop">
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Tài khoản: <span>(*)</span></label>
                            <input type="text" class="form-control" id="tai-khoan">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Email: <span>(*)</span></label>
                            <input type="text" class="form-control" id="email">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row" id="view-mat-khau">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Mật khẩu: <span>(*)</span></label>
                            <input type="password" class="form-control" id="mat-khau">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Nhập lại mật khẩu: <span>(*)</span></label>
                            <input type="password" class="form-control" id="nhap-lai-mat-khau">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Chức vụ: <span>(*)</span></label>
                            <input type="text" class="form-control" id="chuc-vu">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6" id="view-so-dien-thoai">
                        <div class="caifop1li form-group">
                            <label>Số điện thoại: <span>(*)</span></label>
                            <input type="text" class="form-control" id="so-dien-thoai">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row" id="view-trang-thai">
                    <div class="col-sm-12">
                        <div class="caiopf1i2" id="trang-thai">
                            <label >Trạng thái</label>
                            <div class="caiopf1i2rb">
                                <div class="i2rbwp">
                                    <input type="radio" name="rb" id="rb1" value="1" checked>
                                    <label for="rb1">Đang hoạt động</label>
                                </div>
                                <div class="i2rbwp">
                                    <input type="radio" name="rb" id="rb0" value="0">
                                    <label for="rb1">Khóa</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr style="border-width: 3px;">
    <div class="row">
        <div class="caifbtwp">
            <button class="btn btn-primary" id="save-nguoi-dung">Lưu thông tin</button>
            <button class="btn btn-default" id="back-history">Quay lại</button>
        </div>
    </div>
</section>
<!-- /.content -->