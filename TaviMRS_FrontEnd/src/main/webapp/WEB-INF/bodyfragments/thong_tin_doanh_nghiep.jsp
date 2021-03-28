<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/dist/js/ajax/model/ajax_dia_gioi.js"></script>
<script src="resources/dist/js/ajax/model/ajax_mo.js"></script>
<script src="resources/dist/js/ajax/model/ajax_doanh_nghiep.js"></script>
<script src="resources/dist/js/ajax/model/ajax_thiet_bi.js"></script>
<script src="resources/dist/js/ajax/model/ajax_xe_van_chuyen.js"></script>
<script src="resources/dist/js/ajax/model/ajax_giay_phep_khai_thac.js"></script>
<script src="resources/dist/js/ajax/model/ajax_nguoi_dung.js"></script>
<script src="resources/dist/js/ajax/model/thu_tuc/ajax_thu_tuc.js"></script>
<script src="resources/dist/js/ajax/model/ajax_nganh_nghe.js"></script>
<script src="resources/dist/js/ajax/quan_ly_doanh_nghiep/ajax_thong_tin_doanh_nghiep.js"></script>
<!-- Quản lí thông tin doanh nghiệp Css -->
<link rel="stylesheet" href="./dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">thông tin chi tiết doanh nghiệp</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 col-md-6">
            <div class="etpinfop form-group">
                <label >Tên doanh nghiệp: <span class="text-red">(*)</span></label>
                <input type="text" id="ten-doanh-nghiep" class="form-control check-modifile-ttdn">
                <span class="help-block">Help block with success</span>
            </div>
            <div class="etpinfop form-group">
                <label>Người đại diện: <span class="text-red">(*)</span></label>
                <input type="text" id="nguoi-dai-dien" class="form-control check-modifile-ttdn">
                <span class="help-block">Help block with success</span>
            </div>
            <div class="etpinfop form-group">
                <label >Vốn điều lệ (VNĐ): <span class="text-red">(*)</span></label>
                <input type="number" id="von-dieu-le" class="form-control check-modifile-ttdn" min="1" placeholder="0">
                <span class="help-block">Help block with success</span>
            </div>
            <div class="etpinfopm">
                <div class="etpinfopi1 form-group">
                    <label >Địa chỉ: <span class="text-red">(*)</span></label>
                    <input type="text" id="dia-chi" class="form-control check-modifile-ttdn">
                    <span class="help-block">Help block with success</span>
                </div>
            </div>
            <div class="etpinfopm">
                <div class="etpinfopi3 form-group">
                    <label><a href="#" id="link-giay-phep" target="_blank">Giấy phép đăng kí kinh doanh</a></label>
                    <input type="file" name="" id="giay-phep-kinh-doanh" class="check-modifile-ttdn">
                </div>
            </div>
        </div>
        <div class="col-sm-12 col-md-6">
            <div class="etpinfop form-group">
                <label >Mã doanh nghiệp: <span class="text-red">(*)</span></label>
                <input type="text" id="ma-doanh-nghiep" class="form-control check-modifile-ttdn">
                <span class="help-block">Help block with success</span>
            </div>
            <div class="etpinfop sp">
                <label >Lĩnh vực hoạt động <span class="text-red">(*)</span></label>
                <select class="js-example-basic-multiple form-control select2" name="state" id="select4" multiple="multiple">
                    <option value="A01110">Trồng lúa</option>
                    <option value="A01120">Trồng ngô và cây lương thực có hạt khác</option>
                    <option value="A01120">Trồng ngô và cây lương thực có hạt khác</option>
                    <option value="A01120">Trồng ngô và cây lương thực có hạt khác</option>
                    <option value="A01120">Trồng ngô và cây lương thực có hạt khác</option>
                </select>
                <span class="help-block">Help block with success</span>
<%--                <input type="text">--%>
                <div class="etplus">
                    <span class="eptplusi">
                        <i class="fas fa-info-circle"></i>
                        <div class="eptplustable d-none">
                            <div class="eptplustb-title">
                                <span>Lĩnh vực hoạt động</span>
                            </div>
                            <div class="eptplustb-ct">
                                <table class="table table-hover" id="nganh-nghe-select">
                                    <tbody>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã ngành</th>
                                            <th>Tên ngành nghề</th>
                                        </tr>
<%--                                        <tr>--%>
<%--                                            <td>1</td>--%>
<%--                                            <td>Tên ngành</td>--%>
<%--                                            <td>AAA.BBB</td>--%>
<%--                                        </tr>--%>
                                    </tbody>
                                </table>
                            </div>
                            <div class="eptplustb-bt">
                                <button class="btn btn-default" id="dong-nganh-nghe">Đóng</button>
                            </div>
                        </div>
                    </span>
                </div>
            </div>
            <div class="etpinfopti">
                <div class="etpinfopi1ot form-group">
                    <label>Email: <span class="text-red">(*)</span></label>
                    <input type="text" id="email" class="form-control check-modifile-ttdn">
                    <span class="help-block">Help block with success</span>
                </div>
                <div class="etpinfopi1ot form-group">
                    <label>Điện thoại: <span class="text-red">(*)</span></label>
                    <input type="text" id="so-dien-thoai" class="form-control check-modifile-ttdn">
                    <span class="help-block">Help block with success</span>
                </div>
            </div>
            <div class="etpinfopm">
                <div class="container" style="padding: unset">
                    <div class="row">
                        <div class="etpinfopi2 col-sm-4">
                            <label ><span>Tỉnh</span></label>
                            <select class="js-example-basic-single select2" name="state" id="select1" style="width: 100%">
                                <option value="BG">Bắc Giang</option>
                            </select>
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="etpinfopi2 col-sm-4">
                            <label ><span>Huyện/TP</span></label>
                            <select class="js-example-basic-single select2" name="state" id="select2" style="width: 100%">
                                <option value="TBG">TP Bắc Giang</option>
                                <option value="HH">Hiệp Hòa</option>
                            </select>
                            <span class="help-block">Help block with success</span>
                        </div>
                        <div class="etpinfopi2 col-sm-4">
                            <label ><span>Xã/Phường</span></label>
                            <select class="js-example-basic-single select2" name="state" id="select3" style="width: 100%">
                                <option value="AL">Nguyễn Văn Cừ</option>
                                <option value="AL">Hoàng Văn Thụ</option>
                            </select>
                            <span class="help-block">Help block with success</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="text-left">
                <button class="btn btn-primary" id="save-doanh-nghiep">Lưu lại</button>
                <button class="btn btn-default" id="back-history">Quay lại</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="etptab">
            <div class="etptab-title">
                <span>Danh sách mỏ</span>
                <span>Danh sách xe vận chuyển</span>
                <span>Giấy phép khai thác</span>
                <span>Thiết bị tại mỏ</span>
                <span>Tài khoản quản trị</span>
                <span>Thủ tục hành chính</span>
                <span>Quản lý cấu hình</span>
            </div>
            <div class="etptab-content">
                <div class="etptabct-table table-responsive ">
                    <table class="table table-hover " id="tapMo">
                        <tbody>
                        <tr>
                            <th>STT</th>
                            <th>Tên Mỏ</th>
                            <th>Trữ lượng còn lại</th>
                            <th>Trữ lượng khai thác năm</th>
                            <th>Trữ lượng mỏ</th>
                            <th>Loại Khoáng Sản</th>
                            <th>Trạng Thái</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td></td>
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
                            <td></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td></td>
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
                            <td></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td></td>
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
                    <div class="pagi" id="pagination-mo">
                        <div class="paginationjs">
                        </div>
                    </div>
                </div>
            </div>
            <div class="etptab-content d-none">
                <div class="etptabct-table table-responsive ">
                    <table class="table table-hover " id="tapXeVanChuyen">
                        <tbody>
                        <tr>
                            <th>STT</th>
                            <th>Mã Thiết bị</th>
                            <th>Tên thiết bị</th>
                            <th>Vị trí</th>
                            <th>Mục Đích sử dụng</th>
                            <th>Mỏ</th>
                            <th>Trạng Thái</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf" aria-hidden="true"></i></a></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf" aria-hidden="true"></i></a></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf" aria-hidden="true"></i></a></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf" aria-hidden="true"></i></a></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf" aria-hidden="true"></i></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="receivepagi">
                    <div class="pagi" id="pagination-xe-van-chuyen">
                        <div class="paginationjs">
                        </div>
                    </div>
                </div>
            </div>
            <div class="etptab-content d-none">
                <div class="etptabct-table table-responsive ">
                    <table class="table table-hover " id="tapGiayPhep">
                        <tbody>
                        <tr>
                            <th>STT</th>
                            <th>Số quyết định</th>
                            <th>Cơ quan ban hành</th>
                            <th>Người ký</th>
                            <th>Diện tích/ trữ lượng</th>
                            <th>Mỏ</th>
                            <th>Đính kèm</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf"></i></a></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf"></i></a></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf"></i></a></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf"></i></a></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="#"><i class="far fa-file-pdf"></i></a></td>
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
            <div class="etptab-content d-none">
                <div class="etptabct-table table-responsive ">
                    <table class="table table-hover" id="tapThietBi">
                        <tbody>
                        <tr>
                            <th>STT</th>
                            <th>Mã Thiết bị</th>
                            <th>Tên thiết bị</th>
                            <th>Vị trí</th>
                            <th>Mục Đích sử dụng</th>
                            <th>Mỏ</th>
                            <th>Trạng Thái</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td></td>
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
                            <td></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td></td>
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
                            <td></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td></td>
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
                    <div class="pagi" id="pagination-thiet-bi">
                        <div class="paginationjs">
                        </div>
                    </div>
                </div>
            </div>
            <div class="etptab-content d-none">
                <div class="etptabct-table table-responsive" style="position: relative">
                    <table class="table table-hover " id="tapTaiKhoan">
                        <tbody>
                        <tr>
                            <th>STT</th>
                            <th>Tên đăng nhập</th>
                            <th>Chức vụ</th>
                            <th>Điện thoại</th>
                            <th>Trạng thái</th>
                        </tr>
                        <tr>
                            <td>1</td>
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
                        </tr>
                        <tr>
                            <td>3</td>
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
                        </tr>
                        <tr>
                            <td>5</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="page-link">
                        <a href="thong-tin-nguoi-dung?id=0"><i class="fas fa-plus-circle" aria-hidden="true"></i></a>
                    </div>
                </div>
                <div class="receivepagi">
                    <div class="pagi" id="pagination-tai-khoan">
                        <div class="paginationjs">
                        </div>
                    </div>
                </div>
            </div>
            <div class="etptab-content d-none">
                <div class="etptabct-table table-responsive ">
                    <table class="table table-hover " id="tapThuTuc">
                        <tbody>
                        <tr>
                            <th>STT</th>
                            <th>Tên thủ tục hành chính</th>
                            <th>Hiệu lực đến</th>
                            <th>Trạng thái</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="receivepagi">
                    <div class="pagi" id="pagination-thu-tuc">
                        <div class="paginationjs">
                        </div>
                    </div>
                </div>
            </div>
            <div class="etptab-content d-none">
                <div class="container-fuild">
                        <div class="row" style="margin-bottom: 15px">
                            <div class="col-xs-2">
                                <lable>Cho phép hoạt động</lable>
                            </div>
                            <div class="col-xs-4" id="trang-thai-phan-mem">
                                <div class="row">
                                    <input type="radio" name="cho-phep-hoat-dong" id="trang-thai-0" value="0">
                                    <label>Hoạt động</label>
                                </div>
                                <div class="row">
                                    <input type="radio" name="cho-phep-hoat-dong" id="trang-thai-1" value="1">
                                    <label>Khóa tài khoản truy cập</label>
                                </div>
                                <div class="row">
                                    <input type="radio" name="cho-phep-hoat-dong" id="trang-thai-2" value="2">
                                    <label>Khóa thiết bị kết nối</label>
                                </div>
                                <div class="row">
                                    <input type="radio" name="cho-phep-hoat-dong" id="trang-thai-3" value="3">
                                    <label>Dừng hoạt động</label>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-xs-1">
                                <lable>Khóa</lable>
                            </div>
                            <div class="col-xs-5">
                                <input type="text" id="khoa" placeholder="mã tự động gen cho mỗi doanh nghiệp" class="form-control" disabled>
                                <span class="help-block">Help block with success</span>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-xs-1">
                                <lable>Mã kết nối</lable>
                            </div>
                            <div class="col-xs-5">
                                <input type="text" id="ma-ket-noi" placeholder="gen theo kháo + quy luật định nghĩa" class="form-control" disabled>
                                <span class="help-block">Help block with success</span>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-xs-1">
                                <lable>Ghi chú</lable>
                            </div>
                            <div class="col-xs-5">
                                <input type="text" id="ghi-chu" class="form-control">
                                <span class="help-block">Help block with success</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <button class="btn btn-primary" id="save-cau-hinh">Lưu lại</button>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</section>
<script src="resources/dist/js/thongtinchitietdn.js"></script>
<!-- /.content -->