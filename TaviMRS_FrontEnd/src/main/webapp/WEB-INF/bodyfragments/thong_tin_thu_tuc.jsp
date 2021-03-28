<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
<script src="resources/dist/js/ajax/model/thu_tuc/ajax_giay_phep_theo_thu_tuc.js"></script>
<script src="resources/dist/js/ajax/model/thu_tuc/ajax_giay_phep_theo_loai_thu_tuc.js"></script>
<script src="resources/dist/js/ajax/model/thu_tuc/ajax_thu_tuc.js"></script>
<script src="resources/dist/js/ajax/quan_ly_doanh_nghiep/ajax_thong_tin_thu_tuc.js"></script>
<!-- Quản lí thông tin doanh nghiệp Css -->
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">thông tin thủ tục</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="caifop">
                <div class="row">
                    <div class="col-md-12">
                        <div class="caifop1li form-group">
                            <label>Tên thủ tục hành chính: <span>(*)</span></label>
                            <input type="text" class="form-control" id="ten-thu-tuc-hanh-chinh" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row" id="view-mat-khau">
                    <div class="col-md-4">
                        <div class="caifop1li form-group">
                            <label>Số giấy phép/ quyết định: <span>(*)</span></label>
                            <input type="text" class="form-control" id="so-giay-phep" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="caifop1li form-group">
                            <label>Ngày cấp: <span>(*)</span></label>
<%--                            <div class="input-group date date-time-set">--%>
                                <input class="form-control" type="text" id="ngay-cap" disabled/>
<%--                                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="caifop1li form-group">
                            <label>Thời hạn đến: <span>(*)</span></label>
<%--                            <div class="input-group date date-time-set">--%>
                                <input class="form-control" type="text" id="thoi-han-den" disabled/>
<%--                                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="caifop1li form-group">
                            <label>Cơ quan cấp: <span>(*)</span></label>
                            <input type="text" class="form-control" id="co-quan-cap" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="caifop1li form-group">
                            <label>Người ký: <span></span></label>
                            <input type="text" class="form-control" id="nguoi-ky" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="caifop1li form-group">
                            <label>Chức vụ: <span></span></label>
                            <input type="text" class="form-control" id="chuc-vu" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="caifop1li form-group">
                            <label >Hồ sơ tài liệu kèm theo</label>
                            <ol class="tree font-14 list-decimal color-blue" id="list-ho-so">
<%--                                <li class="tree-view">--%>
<%--                                    <a href="">Đơn đề nghị.......</a>--%>
<%--                                </li>--%>
<%--                                <li class="tree-view">--%>
<%--                                    <a href="">Đơn đề nghị.......</a>--%>
<%--                                </li>--%>
                            </ol>
                            </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="caifop1li form-group">
                            <label >Giấy phép/ quyết định kèm theo</label>
                            <ul class="tree font-14 list-none">
                                <li class="tree-view">
                                    <a href="" id="link-giay-phep" target="_blank"></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row" id="view-trang-thai">
                    <div class="col-sm-12">
                        <div class="col-sm-12" id="trang-thai">
                            <label >Trạng thái</label>
                            <div class="caiopf1i2rb">
                                <div class="i2rbwp">
                                    <input type="radio" name="trangThai" id="rb1" value="1" disabled>
                                    <label for="rb1">Còn hiệu lực</label>
                                </div>
                                <div class="i2rbwp">
                                    <input type="radio" name="trangThai" id="rb0" value="0" disabled>
                                    <label for="rb0">Hết hiệu lực</label>
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
            <button class="btn btn-primary" id="save-thu-tuc" disabled>Lưu thông tin</button>
            <button class="btn btn-default" id="back-history">Quay lại</button>
        </div>
    </div>
</section>
<!-- /.content -->