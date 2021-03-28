<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/dist/js/ajax/model/ajax_thiet_bi.js"></script>
<script src="resources/dist/js/ajax/quan_ly_doanh_nghiep/ajax_thong_tin_thiet_bi.js"></script>
<!-- Quản lí thông tin doanh nghiệp Css -->
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">thông tin về thiết bị</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 col-md-6 ">
            <div class="caifop">
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Mã thiết bị: <span>(*)</span></label>
                            <input type="text" class="form-control" id="ma-thiet-bi">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Tên thiết bị: <span>(*)</span></label>
                            <input type="text" class="form-control" id="ten-thiet-bi">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Vị trí lắp:</label>
                            <input type="text" class="form-control" id="vi-tri-lap">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="caifop1li">
                            <label>Mô tả:</label>
                            <textarea type="text" class="form-control" id="mo-ta" style="max-width: 100%; max-height: 54px"></textarea>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="caiopf1i1">
                            <label >Hình ảnh:</label>
                            <input type="file" name="" id="hinh-anh">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12" id="trang-thai">
                        <label >Trạng thái</label>
                        <div class="caiopf1i2rb">
                            <div class="i2rbwp">
                                <input type="radio" name="rb" id="rb0" value="0">
                                <label for="rb0">Chờ duyệt</label>
                            </div>
                            <div class="i2rbwp">
                                <input type="radio" name="rb" id="rb1" value="1">
                                <label for="rb1">Duyệt-Đồng ý</label>
                            </div>
                            <div class="i2rbwp">
                                <input type="radio" name="rb" id="rb2" value="2">
                                <label for="rb2">Duyệt-Không đồng ý</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="caifop1li form-group">
                            <label>Nội dung duyệt:</label>
                            <input type="text" class="form-control" id="noi-dung-duyet">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-12 col-md-6">
            <div class="div-wp">
                <img src="" id="img-thiet-bi"></img>
            </div>
        </div>
    </div>
    <hr style="border-width: 3px;">
    <div class="row">
        <div class="caifbtwp">
            <button class="btn btn-primary" id="save-thiet-bi">Lưu thông tin</button>
            <button class="btn btn-default" id="back-history">Quay lại</button>
        </div>
    </div>
</section>
<!-- /.content -->