<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="resources/plugins/owlcarousel/owl.carousel.min.css">
<link rel="stylesheet" href="resources/plugins/owlcarousel/owl.theme.default.min.css">
<script src="resources/plugins/owlcarousel/owl.carousel.min.js"></script>
<link rel="stylesheet" href="resources/dist/css/giamsatcamera.css">
<script src="resources/dist/js/ajax/model/vi_pham/ajax_vi_pham.js"></script>
<script src="resources/dist/js/ajax/model/vi_pham/ajax_hinh_anh_vi_pham.js"></script>
<script src="resources/dist/js/ajax/model/vi_pham/ajax_video_vi_pham.js"></script>
<script src="resources/dist/js/ajax/ajax_chi_tiet_vi_pham.js"></script>

<div class="modal fade in" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span></button>
                <h4 class="modal-title">Video vi phạm</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="owl-carousel owl-theme owl-loaded owl-drag" id="video-carousel">
                            <div>
                                <video width="100%" controls>
                                <source src="http://103.9.86.61:8080/resources/upload/mrs/video-count-down.mp4" type="video/mp4">
                                Your browser does not support HTML5 video.
                                </video>
                            </div>
                            <div>
                                <video width="100%" controls>
                                    <source src="http://103.9.86.61:8080/resources/upload/mrs/video-count-down.mp4" type="video/mp4">
                                    Your browser does not support HTML5 video.
                                </video>
                            </div>
                            <div>
                                <video width="100%" controls>
                                    <source src="http://103.9.86.61:8080/resources/upload/mrs/video-count-down.mp4" type="video/mp4">
                                    Your browser does not support HTML5 video.
                                </video>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- Main content -->
<section class="content">
    <div class="detailcamera">
        <div class="dtcmrtitle">
            <span class="page-title">Chi tiết vi phạm</span>
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
                            <span id="text4">Mô tả vi
                                phạm:...............................................................................................</span>
            </div>
            <div class="dtcmrctitem">
                <strong>Hình ảnh:</strong>
                <div class="row">
                    <div class="col-md-4">
                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators" id="stt-carousel">
                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" id="img-carousel">
                                <div class="item active">
                                    <img src="./dist/img/no-image.jpg" alt="Los Angeles">
                                </div>

                                <div class="item">
                                    <img src="./dist/img/no-image.jpg" alt="Chicago">
                                </div>

                                <div class="item">
                                    <img src="./dist/img/no-image.jpg" alt="New York">
                                </div>
                            </div>

                            <!-- Left and right controls -->
                            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-default" id="btn-tao-bien-ban">Tạo biên bản</button>
            <button class="btn btn-default" data-toggle="modal" data-target="#modal-default" id="btn-xem-video">Xem video</button>
            <button class="btn btn-default" id="back-history">Quay lại</button>
        </div>
    </div>
</section>
<!-- /.content -->
<script src="resources/dist/js/main.js"></script>
