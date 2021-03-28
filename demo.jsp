<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/model/thiet_bi/ajax_thiet_bi.js"></script>
<script src="resources/model/thiet_bi/ajax_nhom_thiet_bi.js"></script>
<script src="resources/model/thiet_bi/ajax_loai_thiet_bi.js"></script>
<script src="resources/model/chi_nhanh/ajax_chi_nhanh.js"></script>
<script src="resources/model/upload_file/ajax_upload_file.js"></script>
<script src="resources/pages/quan_ly_camera_giam_sat/ajax_quan_ly_he_thong_camera.js"></script>
<!-- Modal -->
<div class="modal fade in" id="modal-remove">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Xác nhận thao tác</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12" id="text-confirm">
                        Bạn có chắc chắn xóa camera này không ?
                    </div>
                </div>
            </div>
            <div class="modal-footer text-right">
                <button type="button" class="btn btn-danger mgr-10" data-dismiss="modal" id="confirm-yes">Đồng ý xóa</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Không xóa</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="modal fade" id="modal-1" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thêm phòng ban</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Tên phòng ban:</label>
                    <input type="text" class="form-control" id="input-text-6" placeholder="Nhập tên phòng ban">
                </div>
                <div class="form-group">
                    <label>Chi nhánh:</label>
<%--                    <select class="form-control select2bs4 select2-hidden-accessible" id="select-4" disabled>--%>
                    <select class="form-control select2bs4 select2-hidden-accessible" id="select-4">
                        <option value=1>Chi nhánh 1</option>
                        <option value=2>Chi nhánh 2</option>
                        <option value=3>Chi nhánh 3</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                <button type="button" class="btn btn-secondary" id="btn-7">Thêm mới</button>
            </div>
        </div>
    </div>
</div>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-12 text-center">
                    <h4 class="m-0 text-dark">Quản lý thông tin tài khoản</h4>
                </div><!-- /.col -->
            </div><!-- /.row -->
            <div class="row justify-content-md-center mt-3">
                <div class="col-md-8">
                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label">Chi nhánh</label>
                        <div class="col-lg-10">
                            <select class="form-control select2bs4 select2-hidden-accessible" id="select-1">
                                <option value=0>Tất cả các chi nhánh</option>
                                <option value=1>Chi nhánh 1</option>
                                <option value=2>Chi nhánh 2</option>
                                <option value=3>Chi nhánh 3</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <div class="card">
                <div class="card-header">
                    <div class="row align-items-center">
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Phòng ban</label>
                                <select class="form-control select2bs4 select2-hidden-accessible" id="select-5">
                                    <option value=0>Tất cả phòng ban</option>
                                    <option value=1>Phòng ban 1</option>
                                    <option value=2>Phòng ban 2</option>
                                    <option value=3>Phòng ban 3</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Thông tin tài khoản</label>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Nhập thông tin tài khoản" id="input-text-1">
                                    <div class="input-group-append">
                                        <button class="btn btn-secondary" id="btn-1" type="button"><i class="fas fa-search"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 text-lg-right text-sm-center">
                            <strong>Tổng số tài khoản: <span id="text-1">03</span></strong>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row table-responsive">
                        <table class="table table-bordered table-hover text-center align-middle">
                            <thead class="color-main">
                            <tr>
                                <th >STT</th>
                                <th>Mã tài khoản</th>
                                <th>Tên tài khoản/Nhóm</th>
                                <th>Trạng thái</th>
                                <th>Chức năng</th>
                                <th>QR Code</th>
                            </tr>
                            </thead>
                            <tbody id="data-table">
<%--                            <tr class="color-opan">--%>
<%--                                <td></td>--%>
<%--                                <td><input type="text" class="form-control" placeholder="Nhập mã" id="input-text-2"></td>--%>
<%--                                <td><input type="text" class="form-control" placeholder="Nhập tên" id="input-text-3"></td>--%>
<%--                                <td><input type="text" class="form-control" placeholder="Nhập vị trí" id="input-text-4"></td>--%>
<%--                                <td><input type="text" class="form-control" placeholder="Mô tả" id="input-text-5"></td>--%>
<%--                                <td><input type="text" class="form-control" placeholder="Chưa đăng ký" id="input-text-6"></td>--%>
<%--                                <td><i class="fas fa-signal"></i></td>--%>
<%--                                <td><a href="#"><i class="fas fa-paperclip"></i> Cấu hình</a></td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td>1</td>--%>
<%--                                <td>TB001</td>--%>
<%--                                <td>Camera 01</td>--%>
<%--                                <td>Trước barrier</td>--%>
<%--                                <td>Nhóm thiết bị</td>--%>
<%--                                <td>Loại thiết bị</td>--%>
<%--                                <td>Nhận dạng biển số</td>--%>
<%--                                <td>Đã duyệt/ Đồng ý</td>--%>
<%--                                <td><i class="fas fa-signal text-primary"></i></td>--%>
<%--                                <td><i class="fas fa-paperclip"></i> Cấu hình</td>--%>
<%--                            </tr>--%>
                            <tr id="click-load-data">
                                <td colspan="10"><i class="fas fa-arrow-circle-down"></i></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="row mb-3">
                        <div class="col-12 text-center">
                            <h4 class="m-0 text-dark">Thông tin chi tiết tài khoản</h4>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Mã tài khoản</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-2" placeholder="Nhập mã tài khoản" disabled>
                                    <span class="invalid-feedback">Please validation</span>
                                </div>
                            </div>
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Họ và tên</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-3" placeholder="Nhập họ và tên">
                                    <span class="invalid-feedback">Please validation</span>
                                </div>
                            </div>

                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Tài khoản (*)</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-9" placeholder="Nhập tài khoản">
                                    <span class="invalid-feedback">Please validation</span>
                                </div>
                            </div>

                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Tài khoản (*)</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-4" placeholder="Nhập tài khoản">
                                    <span class="invalid-feedback">Please validation</span>
                                </div>
                            </div>


                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Email (*)</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-5" placeholder="Nhập email" required="true">
                                </div>
                            </div>
        
        
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Số điện thoại (*)</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-6" placeholder="Nhập số điện thoại" required="true">
                                </div>
                            </div>
        
        
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Địa chỉ</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-7" placeholder="Nhập địa chỉ" required="true">
                                </div>
                            </div>
        
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Giới tính</label>
                                <div class="col-md-8">
                                    <input class="col-mb-4" type="radio" name="gender" value="1" id="gTNam">
                                    <label  class="mr-5 mb-0">Nam</label>
                                    <input class="col-mb-4" type="radio" name="gender" value="0" id="gTNu">
                                    <label class="mb-0">Nữ</label>
                                </div>
                            </div>
        
                            <div class="form-group row mb-3"  >
                                <label class="col-md-4 col-form-label">Qr Code</label>
                                <div class="col-md-8" id="qrcode"></div>
                            </div>
                         </div>
                    
    
                         <div class="col-md-6">
                         
                
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Ngày sinh</label>
                                <div class="col-md-8">
                                    <input type="date" class="form-control" id="input-date-1" value="2016-05-26" placeholder="Nhập ngày sinh">
                                </div>
                            </div>
        
    
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Bắt đầu làm việc</label>
                                <div class="col-md-8">
                                    <input type="date" class="form-control" id="input-date-2" placeholder="Nhập thời gian khởi tạo">
                                </div>
                            </div>
        
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Thời hạn hợp đồng</label>
                                <div class="col-md-8">
                                    <input type="date" class="form-control"  id="input-date-3" placeholder="Nhập thời gian kích hoạt">
                                </div>
                            </div>
        
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Mức lương</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control"  id="input-text-8" placeholder="Nhập mức lương">
                                </div>
                            </div>

                          
                        
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Trạng thái</label>
                                <div class="col-md-8">
                                    <input class="col-mb-4" type="radio" name="status" value="0" id="kichHoat">
                                    <label  class="mr-5 mb-0">Kích hoạt</label>
                                    <input class="col-mb-4" type="radio" name="status" value="1" id="khoa">
                                    <label class="mb-0">Khóa</label>
                                </div>
                            </div>

                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Ảnh đại diện</label>
                                <div class="col-md-8">
                                    <form class="custom-file" id="form-file">
                                        <input type="file" class="custom-file-input form-control" id="customFile-1" name="files">
                                        <label class="custom-file-label">Tệp tin cấu hình</label>
                                        <span class="invalid-feedback">Please validation</span>
                                    </form>
                                </div>
                            </div>

        
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <button type="button" class="btn btn-secondary m-1" data-toggle="modal" data-target="#modal-1" id="btn-6">Thêm phòng ban mới</button>
                        <button type="button" class="btn btn-secondary m-1" id="btn-3">Thêm mới</button>
                        <button type="button" class="btn btn-secondary m-1" id="btn-4">Cập nhập</button>
                        <button type="button" class="btn btn-secondary m-1" id="btn-2">Làm mới</button>
<%--                        <button type="button" class="btn btn-secondary m-1" id="btn-5" data-toggle="modal" data-target="#modal-remove">Xóa</button>--%>
                        <button type="button" class="btn btn-secondary m-1" id="btn-5">Xóa</button>
                    </div>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->