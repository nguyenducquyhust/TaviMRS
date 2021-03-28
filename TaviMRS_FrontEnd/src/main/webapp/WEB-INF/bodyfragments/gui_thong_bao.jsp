<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Gửi thông báo css -->
<link rel="stylesheet" href="resources/dist/css/guithongbao.css">
<script src="./dist/js/guithogbao.js"></script>
<script src="resources/dist/js/ajax/model/ajax_nguoi_dung.js"></script>
<script src="resources/dist/js/ajax/model/ajax_upload_file.js"></script>
<script src="resources/dist/js/ajax/model/ajax_thong_bao.js"></script>
<script src="resources/dist/js/ajax/thong_bao_bao_cao/ajax_gui_thong_bao.js"></script>
<!-- Main content -->
<section class="content">
    <div class="sennoti">
        <div class="senot-title">
            <span class="page-title">gửi báo cáo đến doanh nghiệp</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 col-md-5">
            <div class="choosecom">
                <div class="chocom-title">
                    <span>Gửi đến</span>
                </div>
                <div class="chocom-content" id="group-nguoi-dung">
<%--                    <div class="com">--%>
<%--                        <div class="com1">--%>
<%--                            <span class="cmp d-none">+</span>--%>
<%--                            <span class="cmp">-</span>--%>
<%--                            <span><input type="checkbox" name="" id="">Công ty A</span>--%>
<%--                        </div>--%>
<%--                        <div class="com2">--%>
<%--                            <span><input type="checkbox" name="" id="">Nguyễn Văn A - Giám Đốc</span>--%>
<%--                            <span><input type="checkbox" name="" id="">Nguyễn Văn B - Phó Giám Đốc</span>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </div>
            </div>
        </div>
        <div class="col-sm-12 col-md-7">
            <div class="senot-form">
                <div class="snf-item form-group">
                    <label>Tiêu đề:</label>
                    <input type="text" id="tieu-de">
                    <span class="help-block">Help block with success</span>
                </div>
                <div class="snf-item form-group">
                    <label>Nội dung:</label>
                    <textarea id="noi-dung"></textarea>
                    <span class="help-block">Help block with success</span>
                </div>
                <form class="snf-item inpf" id="form-file">
                    <label>Tài liệu kèm theo:</label>
                    <input type="file" id="file-dinh-kem" name="files" multiple>
                </form>
                <div class="snf-item htg form-group">
                    <label>Hình thức gửi:</label>
                    <div class="mtinf" id="hinh-thuc-gui">
                        <div class="mtinfi">
                            <input type="checkbox" id="cb1" value="2">
                            <label for="cb1">Qua phần mềm</label>
                        </div>
                        <div class="mtinfi">
                            <input type="checkbox" id="cb2" value="1">
                            <label for="cb2">Qua Email</label>
                        </div>
                        <span class="help-block">Help block with success</span>
                    </div>
                </div>
                <div class="snf-item lit">
                    <label>Yêu cầu có phản hồi</label>
                    <div class="mtinfi ">
                        <input type="checkbox" id="yeu-cau-phan-hoi">
                        <label> Có yêu cầu có phản hồi</label>
                    </div>
                </div>
                <button class="btn btn-primary" id="btn-gui-thong-bao">Gửi thông báo</button>
                <button class="btn btn-default" id="btn-reset-gui-den">Xóa danh sách gửi đến</button>
                <button class="btn btn-default" id="btn-reset-thong-bao">Xóa nội dung thông báo</button>
            </div>
        </div>
    </div>
</section>
<!-- Main js -->
<!-- /.content -->