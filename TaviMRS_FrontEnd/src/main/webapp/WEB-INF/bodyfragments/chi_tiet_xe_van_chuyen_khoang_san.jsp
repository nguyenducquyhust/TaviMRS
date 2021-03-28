<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="./dist/css/qlttdn.css">
<script src="resources/dist/js/ajax/model/ajax_nhat_ky_xe_vao_mo.js"></script>
<script src="resources/dist/js/ajax/thong_ke_bao_cao/ajax_chi_tiet_xe_van_chuyen_khoang_san.js"></script>
<section>
    <div class="content-wrapper-header mgtb-20">
        <span>Danh sách xe vận chuyển khoáng sản</span>
        <div class="container limiter-header-h6" id="title-ngay">
            (Từ ngày .............. đến ...................)
        </div>
    </div>
    <div class="row" style="padding-right: 20px">
        <div class="col-md-2">
            <button class="btn btn-default  w100" style="display: none">No view</button>
        </div>
        <div class="col-md-2">
            <button class="btn btn-default  w100" id="btn-excel">Xuất dữ liệu excel</button>
        </div>
        <div class="col-md-2">
            <button class="btn btn-default  w100" id="btn-word">Xuất dữ liệu word</button>
        </div>
        <div class="col-md-2">
            <button class="btn btn-default  w100" id="btn-pdf">Xuất dữ liệu pdf</button>
        </div>
        <div class="col-md-2">
            <button class="btn btn-default  w100" id="btn-print">In dữ liệu</button>
        </div>
        <div class="col-md-2">
            <button class="btn btn-default  w100" id="back-history">Quay lại</button>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="etptab-content" style="border: unset">
                <div class="etptabct-table table-responsive ">
                    <table class="table table-hover" id="table-print">
                        <tbody id="table-data">
                        <tr>
                            <th>STT</th>
                            <th>Biển số xe</th>
                            <th>Mỏ</th>
                            <th>Ngày giờ vào</th>
                            <th>Ngày giờ ra</th>
                            <th>Trọng lượng khoáng sản</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="receivepagi">
                    <div class="pagi" id="pagination-giay-phep">
                        <div class="paginationjs">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>