<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
<%--<link rel="stylesheet" type="text/css" href="../../vendor/select2/select2.min.css">--%>
<link rel="stylesheet" type="text/css" href="resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css" href="resources/dist/css/TK_BC_TABLE .css">
<link rel="stylesheet" type="text/css" href="resources/dist/css/TK_BC_TABLE 1.css">
<script src="resources/dist/js/ajax/model/ajax_dia_gioi.js"></script>
<script src="resources/dist/js/ajax/model/ajax_khoang_san.js"></script>
<script src="resources/dist/js/ajax/model/ajax_giay_phep_khai_thac.js"></script>
<script src="resources/dist/js/ajax/thong_ke_bao_cao/ajax_tong_hop_danh_sach_doanh_nghiep.js"></script>
<section>
    <div class="content-wrapper-header mgtb-20">
        <span>BÁO CÁO TỔNG HỢP DOANH NGHIỆP KHAI THÁC KHOÁNG SẢN</span>
    </div>
    <div class="form-horizontal">
        <div class="row">
            <div class="col-md-6 content-wrapper-content-form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Thời gian từ ngày:</label>
                    <div class="col-sm-8">
                        <div class="input-group date">
                            <input type="text" class="form-control border-gray date-vn" id="bimo1">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                        </div>
                        <span class="help-block">Help block with success</span>
                    </div>
                </div>
            </div>
            <div class="col-md-6 content-wrapper-content-form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Đến ngày:</label>
                    <div class="col-sm-8">
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
        </div>
        <div class="row">
            <div class="col-md-6 content-wrapper-content-form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Loại khoáng sản:</label>
                    <div class="col-sm-8">
                        <select class="js-example-basic-multiple" name="state" id="bimo3" multiple="multiple" style="width: 100%">
                            <option value="NN">Đồng</option>
                            <option value="II">Barit</option>
                            <option value="VV">Sắt</option>
                        </select>
                        <span class="help-block">Help block with success</span>
                    </div>
                </div>
            </div>
            <div class="col-md-6 content-wrapper-content-form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Thuộc huyện:</label>
                    <div class="col-sm-8">
                        <select class="js-example-basic-single" name="state" id="bimo4" style="width: 100%">
                            <option value=0>Tất cả</option>
                            <option value=1>Thành phố Bắc Giang</option>
                            <option value=2>Huyện Hiệp Hòa</option>
                            <option value=3>Huyện Tân Yên</option>
                        </select>
                        <span class="help-block">Help block with success</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6 text-right">
                <button class="btn btn-primary clickJQ" id="btn-bao-cao">Báo cáo</button>
            </div>
            <div class="col-xs-6 text-left">
                <button class="btn btn-default" id="btn-xoa-dieu-kien">Xóa điều kiện lọc</button>
            </div>
        </div>
    </div>
</section>
<!-- Table TK_BC -->
<div class="limiter">

    <div class="limiter-button">
        <button class="large red button" id="btn-excel">Xuất dữ liệu ra excel</button>
        <button class="large red button" id="btn-word">Xuất dữ liệu ra word</button>
        <button class="large red button" id="btn-pdf">Xuất dữ liệu ra pdf</button>
        <button class="large red button" id="btn-print">In dữ liệu</button>
        <div class="close"><i class="fa fa-times" aria-hidden="true"></i></div>
    </div>
    <div class="limiter-header">
        <div class="container limiter-header-h2">
            <span id="title-dia-ban"> DANH SÁCH DOANH NGHIỆP KHAI THÁC KHOÁNG SẢN TRÊN ĐỊA BÀN .....</span>
        </div>
        <div class="container limiter-header-h6" id="title-ngay">
            (Từ ngày .............. đến ...................)
        </div>
    </div>
    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100 ver1">
<%--                <div class="table100-head">--%>
<%--                    <ul>--%>
<%--                        <li class="column1">STT</li>--%>
<%--                        <li class="column5">Quyết định cấp phép: ngày, tháng, năm cơ quan có thẩm quyền cấp giấy phép</li>--%>
<%--                        <li class="column4">Doanh Nghiệp khai thác</li>--%>
<%--                        <li class="column5">Tên, vị trí khu vực mỏ</li>--%>
<%--                        <li class="column6">Loại khoáng sản</li>--%>
<%--                        <li class="column7">Diện tích (ha)</li>--%>
<%--                        <li class="column8">Trữ lượng mỏ/ công suất khai thác(m3, tấn)</li>--%>
<%--                        <li class="column9">Thời hạn cấp phép</li>--%>
<%--                    </ul>--%>

<%--                </div>--%>

                <div class="table100-body js-pscroll">
                    <table id="table-print">
                        <thead>
                            <th class="cell100 column1">STT</th>
                            <th class="cell100 column5" colspan="2">Quyết định cấp phép: ngày, tháng, năm cơ quan có thẩm quyền cấp giấy phép</th>
                            <th class="cell100 column4">Doanh Nghiệp khai thác</th>
                            <th class="cell100 column5">Tên, vị trí khu vực mỏ</th>
                            <th class="cell100 column6">Loại khoáng sản</th>
                            <th class="cell100 column7">Diện tích (ha)</th>
                            <th class="cell100 column8">Trữ lượng mỏ/ công suất khai thác(m3, tấn)</th>
                            <th class="cell100column9">Thời hạn cấp phép</th>
                        </thead>
                        <tbody id="table-data">
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">I</td>--%>
<%--                            <td colspan="2" class="cell100 column2 ">Giấy phép do Trung ương cấp</td>--%>
<%--                            <td class="cell100 column3"></td>--%>
<%--                            <td class="cell100 column4"></td>--%>
<%--                            <td class="cell100 column6"></td>--%>
<%--                            <td class="cell100 column6"></td>--%>
<%--                            <td class="cell100 column7"></td>--%>
<%--                            <td class="cell100 column8"></td>--%>
<%--                        </tr>--%>
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>

<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>

<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">II</td>--%>
<%--                            <td colspan="2" class="cell100 column2 ">Giấy phép do UBND tỉnh cấp</td>--%>
<%--                            <td class="cell100 column3"></td>--%>
<%--                            <td class="cell100 column4"></td>--%>
<%--                            <td class="cell100 column6"></td>--%>
<%--                            <td class="cell100 column6"></td>--%>
<%--                            <td class="cell100 column7"></td>--%>
<%--                            <td class="cell100 column8"></td>--%>
<%--                            <!-- <td class="cell100 column9"></td> -->--%>

<%--                        </tr>--%>

<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">II.1</td>--%>
<%--                            <td colspan="2" class="cell100 column2 ">Than(09)</td>--%>
<%--                            <td class="cell100 column3"></td>--%>
<%--                            <td class="cell100 column4"></td>--%>
<%--                            <td class="cell100 column6"></td>--%>
<%--                            <td class="cell100 column6"></td>--%>
<%--                            <td class="cell100 column7"></td>--%>
<%--                            <td class="cell100 column8"></td>--%>
<%--                            <!-- <td class="cell100 column9"></td> -->--%>

<%--                        </tr>--%>

<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>

<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>
<%--                        <tr class="row100 body">--%>
<%--                            <td class="cell100 column1">1.</td>--%>
<%--                            <td class="cell100 column2">2543/GP-BTNMT ngày 12/11/2014</td>--%>
<%--                            <td class="cell100 column3">Bộ TN&MT</td>--%>
<%--                            <td class="cell100 column4">Tổng Công ty Đông Bắc</td>--%>
<%--                            <td class="cell100 column5">Mỏ than Đồng Ri, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                            <td class="cell100 column6">Than</td>--%>
<%--                            <td class="cell100 column7">1.205,0</td>--%>
<%--                            <td class="cell100 column8">9.490.932/640.047</td>--%>
<%--                            <td class="cell100 column9">12 năm</td>--%>

<%--                        </tr>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="resources/dist/js/pages/ThongKe_BaoCao.js" ></script>
<script src="resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        var ps = new PerfectScrollbar(this);

        $(window).on('resize', function(){
            ps.update();
        })
    });

    $(document).ready(function () {
        $('.js-example-basic-single').select2({ width: 'resolve' });
        $('.js-example-basic-multiple').select2({ width: 'resolve' });
    });
</script>
<!--===============================================================================================-->
<script src="resources/dist/js/pages/TK_BC_TABLE.js"></script>