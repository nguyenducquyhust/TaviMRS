<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/dist/js/ajax/model/ajax_loai_giay_phep.js"></script>
<script src="resources/dist/js/ajax/model/ajax_giay_phep_khai_thac.js"></script>
<script src="resources/dist/js/ajax/quan_ly_doanh_nghiep/ajax_thong_tin_giay_phep_khai_thac.js"></script>
<!-- Quản lí thông tin doanh nghiệp Css -->
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">thông tin giấy phép khai thác</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 col-md-6 ">
            <div class="caifop">
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Số quyết định: <span>(*)</span></label>
                            <input type="text" class="form-control" id="so-quyet-dinh">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Cơ quan cấp: <span>(*)</span></label>
                            <input type="text" class="form-control" id="co-quan-cap">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Người ký:</label>
                            <input type="text" class="form-control" id="nguoi-ky">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Chức vụ:</label>
                            <input type="text" class="form-control" id="chuc-vu">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li">
                            <label>Loại giấy phép</label>
                            <select class="js-example-basic-single select2" name="state" id="select1" style="width: 100%">
                                <option value="T">Cấp phép</option>
                            </select>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Thời hạn khai thác: <span>(*)</span></label>
                            <input type="number" class="form-control" id="thoi-han-khai-thac" min="1">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Trữ lượng địa chất (tấn): <span></span></label>
                            <input type="number" class="form-control" id="tru-luong-dia-chat" min="1">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Trữ lượng khoán sản (tấn): <span>(*)</span></label>
                            <input type="number" class="form-control" id="tru-luong-khoang-san" min="1" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Công suất khai thác (tấn/năm): <span>(*)</span></label>
                            <input type="number" class="form-control" id="cong-suat-khai-thac" min="1">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Diện tích khai thác (ha): <span>(*)</span></label>
                            <input type="number" class="form-control" id="dien-tich-khai-thac" min="1">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="caiopf1i1">
                            <label >Giấy phép gốc:</label>
                            <input type="file" name="" id="giay-phep-goc">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12" id="trang-thai">
                        <label >Trạng thái</label>
                        <div class="caiopf1i2rb">
                            <div class="i2rbwp">
                                <input type="radio" name="trangThai" id="rb0" value="1" checked>
                                <label for="rb0">Còn hiệu lực</label>
                            </div>
                            <div class="i2rbwp">
                                <input type="radio" name="trangThai" id="rb1" value="0">
                                <label for="rb1">Hết hiệu lực</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12" id="tham-quyen-cap">
                        <label >Thẩm quyền cấp</label>
                        <div class="caiopf1i2rb">
                            <div class="i2rbwp">
                                <input type="radio" name="thamQuyenCap" id="rb2" value="0" checked>
                                <label for="rb2">Trung ương</label>
                            </div>
                            <div class="i2rbwp">
                                <input type="radio" name="thamQuyenCap" id="rb3" value="1">
                                <label for="rb3">UBND tỉnh</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-12 col-md-6">
            <div class="div-wp">
                <iframe src="" id="iframe-giay-phep" style="width: 100%; min-height: 513px" ></iframe>
            </div>
        </div>
    </div>
    <hr style="border-width: 3px;">
    <div class="row">
        <div class="caifbtwp">
            <button class="btn btn-primary" id="save-giay-phep">Lưu thông tin</button>
            <button class="btn btn-default" id="back-history">Quay lại</button>
        </div>
    </div>
</section>
<!-- /.content -->