<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/dist/js/ajax/model/ajax_mo.js"></script>
<script src="resources/dist/js/ajax/model/ajax_dia_gioi.js"></script>
<script src="resources/dist/js/ajax/model/ajax_khoang_san.js"></script>
<script src="resources/dist/js/ajax/quan_ly_doanh_nghiep/ajax_thong_tin_mo.js"></script>
<!-- Quản lí thông tin doanh nghiệp Css -->
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">thông tin mỏ</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="caifop">
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Địa chỉ: <span>(*)</span></label>
                            <input type="text" class="form-control" id="dia-chi">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="caifop1li">
                            <label >Tỉnh</label>
                            <select class="js-example-basic-single select2" name="state" id="select1" style="width: 100%" disabled>
                                <option value="T">Bắc Giang</option>
                            </select>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="caifop1li">
                            <label >Huyện</label>
                            <select class="js-example-basic-single select2" name="state" id="select2" style="width: 100%" disabled>
                                <option value="H">Bắc Giang</option>
                            </select>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Trữ lượng mỏ (tấn): <span>(*)</span></label>
                            <input type="number" class="form-control" id="tru-luong-mo" min="1" placeholder="0" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Trữ lượng còn lại (tấn): <span>(*)</span></label>
                            <input type="number" class="form-control" id="tru-luong-con-lai" min="0" placeholder="0" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Trữ lượng khai thác năm (tấn/năm): <span>(*)</span></label>
                            <input type="number" class="form-control" id="tru-luong-khai-thac-nam" min="0" placeholder="0">
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="caifop1li form-group">
                            <label>Trữ lượng đã khai thác năm (tấn): <span>(*)</span></label>
                            <input type="number" class="form-control" id="tru-luong-da-khai-thac-nam" min="0" placeholder="0" disabled>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="caifop1li">
                            <label >Khoáng Sản</label>
                            <select class="js-example-basic-single select2" name="state" id="select3" style="width: 100%" disabled>
                                <option value="KS">Than</option>
                            </select>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr style="border-width: 3px;">
    <div class="row">
        <div class="caifbtwp">
            <button class="btn btn-primary" id="save-mo">Lưu thông tin</button>
            <button class="btn btn-default" id="back-history">Quay lại</button>
        </div>
    </div>
</section>
<!-- /.content -->