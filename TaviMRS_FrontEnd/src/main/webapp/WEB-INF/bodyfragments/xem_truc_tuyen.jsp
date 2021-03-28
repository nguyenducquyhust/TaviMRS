<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Giám sát camera css -->
<link rel="stylesheet" href="resources/dist/css/giamsatcamera.css">
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<script src="resources/dist/js/ajax/model/ajax_doanh_nghiep.js"></script>
<script src="resources/dist/js/ajax/model/ajax_camera.js"></script>
<script src="resources/dist/js/ajax/giam_sat_camera/ajax_xem_truc_tuyen.js"></script>
<!-- Main content -->
<section class="content">
    <div class="cmropt">
        <div class="cmroptitle">
            <span class="page-title">camera trực tuyến</span>
        </div>
        <div class="cmroptct">
            <div class="row">
                <div class="col-xs-12">
                    <div class="caifop1li">
                        <label>Công ty: </label>
                        <select class="js-example-basic-single" id="bimo3">
                            <option value="ALL">Tất cả</option>
                            <option value="AL">Công ty A</option>
                            <option value="WY">Công ty B</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="cmrimg">
        <div class="cmrimg-soft">
            <div class="cmrimgs" id="type-view">
                <span>Kích thước:</span>
                <span class="size-view active-type hover-pointer" value="4">2x2</span>
                <span class="size-view hover-pointer" value="9">3x3</span>
                <span class="size-view hover-pointer" value="16">4x4</span>
            </div>
            <div class="cmrimgallc row" id="view-data">
                <div class="col-md-6">
                    <iframe src="https://www.youtube.com/embed/pDYM_JBAnp4" frameborder="0"
                            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen class="view-camera"></iframe>
                </div>
                <div class="col-md-6">
                    <iframe src="https://www.youtube.com/embed/pDYM_JBAnp4" frameborder="0"
                            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen class="view-camera"></iframe>
                </div>
                <div class="col-md-6">
                    <iframe src="https://www.youtube.com/embed/pDYM_JBAnp4" frameborder="0"
                            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen class="view-camera"></iframe>
                </div>
                <div class="col-md-6">
                    <iframe src="https://www.youtube.com/embed/pDYM_JBAnp4" frameborder="0"
                            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen class="view-camera"></iframe>
                </div>
            </div>
        </div>
        <div class="receivepagi">
            <div class="pagi" id="pagination">
                <div class="paginationjs">
                </div>
            </div>
        </div>
    </div>
</section>
<!-- /.content -->
<!-- Main js -->
<script src="resources/dist/js/main.js"></script>
<script>
    $(document).ready(function () {
        $('.js-example-basic-single').select2();
    });
</script>