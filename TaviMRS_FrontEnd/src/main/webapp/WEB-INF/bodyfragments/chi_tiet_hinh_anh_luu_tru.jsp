<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Giám sát camera Css -->
<link rel="stylesheet" href="./dist/css/giamsatcamera.css">
<script src="resources/dist/js/ajax/model/ajax_hinh_anh_luu_tru.js"></script>
<script src="resources/dist/js/ajax/giam_sat_camera/ajax_chi_tiet_hinh_anh_luu_tru.js"></script>
<!-- Main content -->
<section class="content">
    <div class="detailcamera">
        <div class="dtcmrtitle">
            <span class="page-title">Chi tiết hình ảnh camera</span>
        </div>
        <div class="dtcmrcontent">
            <div class="dtcmrctitem">
                            <span id="text1">Công
                                ty:...............................................................................................</span>
            </div>
            <div class="dtcmrctitem">
                            <span id="text2">Thời
                                gian:...............................................................................................</span>
            </div>
            <div class="dtcmrctitem">
                            <span id="text3">Địa
                                điểm:...............................................................................................</span>
            </div>
            <div class="dtcmrctitem">
                <strong>Hình ảnh:</strong>
                <div class="row">
                    <div class="col-md-4">
                        <div class="dtcmrimgitem">
                            <img src="./dist/img/no-image.jpg" alt="" id="img-hinh-anh">
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-default" id="back-history">Quay lại</button>
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