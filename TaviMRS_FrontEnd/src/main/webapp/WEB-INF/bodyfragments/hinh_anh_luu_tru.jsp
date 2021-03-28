<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="resources/dist/css/giamsatcamera.css">
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<script src="resources/dist/js/ajax/model/ajax_doanh_nghiep.js"></script>
<script src="resources/dist/js/ajax/model/ajax_hinh_anh_luu_tru.js"></script>
<script src="resources/dist/js/ajax/giam_sat_camera/ajax_hinh_anh_luu_tru.js"></script>
<!-- Main content -->
<section class="content">
    <div class="cmropt">
        <div class="cmroptitle">
        <div class="cmroptitle">
            <span class="page-title">hình ảnh lưu trữ camera</span>
        </div>
        <div class="cmroptct">
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li form-group">
                        <label>Thời gian từ ngày:</label>
                        <div class="input-group date">
                            <input type="text" class="form-control border-gray date-vn" id="bimo1">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                        </div>
                        <span class="help-block">Help block with success</span>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="caifop1li form-group">
                        <label>Đến ngày: </label>
                        <div class="input-group date">
                            <input type="text" class="form-control border-gray date-vn" id="bimo2">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                        </div>
                        <span class="help-block">Help block with success</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label>Công ty: </label>
                        <select class="js-example-basic-single" id="bimo3">
                            <option value="ALL">Tất cả</option>
                            <option value="AL">Công ty A</option>
                            <option value="WY">Công ty B</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <label style="opacity: 0">btn</label>
                    <button class="btn btn-primary" style="display: block" id="btn-tim-kiem">Tìm Kiếm</button>
                </div>
            </div>
        </div>
    </div>
    <div class="cmrimg">
        <div class="cmrimg-soft">
            <div class="cmrimgs" id="type-sort">
                <span>Thời gian</span>
                <span><i class="fas fa-arrow-up hover-pointer" value="true"></i></span>
                <span><i class="fas fa-arrow-down active-sort hover-pointer" value="false"></i></span>
            </div>
            <div class="cmrimgalli" id="view-data">
                <div class="cmr-img">
                    <a href="chi-tiet-hinh-anh-luu-tru"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
                <div class="cmr-img">
                    <a href="#"><img src="./dist/img/no-image.jpg" alt=""></a>
                </div>
            </div>
            <div class="receivepagi">
                <div class="pagi" id="pagination">
                    <div class="paginationjs">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- /.content -->
<script src="resources/dist/js/main.js"></script>
<script>
    $(document).ready(function () {
        $('.js-example-basic-single').select2();
    });
</script>
