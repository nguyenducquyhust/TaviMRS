<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/dist/js/ajax/model/ajax_xe_van_chuyen.js"></script>
<script src="resources/dist/js/ajax/quan_ly_doanh_nghiep/ajax_thong_tin_xe_van_chuyen.js"></script>
<!-- Quản lí thông tin doanh nghiệp Css -->
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">thông tin xe vận chuyển</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 col-md-6 ">
            <div class="caifop">
                <div class="caifopi1">
                    <div class="caifop1l">
                        <div class="caifop1li form-group">
                            <label>Biển số xe: <span>(*)</span></label>
                            <input type="text" class="form-control" id="bien-so-xe" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="caifop1li form-group">
                            <label >Loại phương tiện: <span></span></label>
                            <input type="text" class="form-control" id="loai-phuong-tien">
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="caifop1li form-group">
                            <label >Số máy: <span>(*)</span></label>
                            <input type="text" class="form-control" id="so-may">
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="caifop1li form-group">
                            <label >Năm sản xuất: <span>(*)</span></label>
                            <input type="number" class="form-control" id="nam-san-xuat" min="1900" placeholder="1900">
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="caifop1li form-group">
                            <label >Khối lượng bản thân (kg): <span>(*)</span></label>
                            <input type="number" class="form-control" id="khoi-luong-ban-than" min="1" placeholder="0">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="caifop1r">
                        <div class="caifop1li form-group">
                            <label >Số quản lí: <span></span></label>
                            <input type="text" class="form-control" id="so-quan-ly">
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="caifop1li form-group">
                            <label >Nhãn hiệu: <span></span></label>
                            <input type="text" class="form-control" id="nhan-hieu">
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="caifop1li form-group">
                            <label >Số khung: <span>(*)</span></label>
                            <input type="text" class="form-control" id="so-khung">
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="caifop1li form-group">
                            <label >Niên hạn sử dụng (năm): <span>(*)</span></label>
                            <input type="number" class="form-control" id="nien-han-su-dung" min="1" max="99" placeholder="0">
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="caifop1li form-group">
                            <label >Khối lượng hàng (kg): <span>(*)</span></label>
                            <input type="number" class="form-control" id="khoi-luong-hang" min="1" placeholder="0">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="caifop1">
                <div class="caiopf1i1">
                    <label >Giấy đăng kiểm:</label>
                    <input type="file" name="" id="giay-dang-kiem">
                </div>
                <div class="caiopf1i2" id="trang-thai">
                    <label >Trạng thái</label>
                    <div class="caiopf1i2rb">
                        <div class="i2rbwp">
                            <input type="radio" name="rb" id="rb0" value="0" checked>
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
                <div class="caiopf1i3 form-group">
                    <label >Lý do duyệt</label>
                    <input type="text" class="form-control" id="ly-do">
                    <span class="help-block">Help block with success</span>
                </div>
            </div>
        </div>
        <div class="col-sm-12 col-md-6">
            <div class="div-wp">
                <img src="./dist/img/user1-128x128.jpg" alt="" id="img-giay-dang-kiem">
            </div>
        </div>
    </div>
    <hr style="border-width: 3px;">
    <div class="row">
        <div class="caifbtwp">
            <button class="btn btn-primary" id="save-xe-van-chuyen">Duyệt thông tin</button>
            <button class="btn btn-default" id="back-history">Quay lại</button>
        </div>
    </div>
</section>
<!-- /.content -->