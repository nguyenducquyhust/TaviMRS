<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<script src="resources/dist/js/ajax/model/ajax_doanh_nghiep.js"></script>
<script src="resources/dist/js/ajax/tra_cuu_thong_tin/ajax_tra_cuu_thong_tin_doanh_nghiep.js"></script>
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">tra cứu thông tin doanh nghiệp</span>
        </div>
        <div class="buifmaopct">
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li form-group">
                        <label for="bimo1">Tên Doanh Nghiệp:</label>
                        <input type="text" class="form-control" id="bimo1">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="caifop1li form-group">
                        <label for="bimo2">Địa chỉ:</label>
                        <input type="text" class="form-control" id="bimo2">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label for="bimo3">Trạng thái phần mềm:</label>
                        <select class="js-example-basic-single" name="state" id="bimo3">
                            <option value=-1>Tất cả</option>
                            <option value=0>Hoạt động</option>
                            <option value=1>Khóa tài khoản truy cập</option>
                            <option value=2>Khóa thiết bị kết nối</option>
                            <option value=3>Dừng hoạt động</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <label style="opacity: 0">btn</label>
                    <button class="btn btn-primary" style="display: block">Tìm Kiếm</button>
                </div>
            </div>
        </div>
    </div>
    <div class="buifmaoptb table-responsive">
        <table class="table table-hover">
            <tbody>
            <tr>
                <th>STT</th>
                <th>Tên Doanh nghiệp</th>
                <th>Địa chỉ</th>
                <th>Trạng Thái Phần Mềm</th>
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
            <tr>
                <td>6</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>7</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>8</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>9</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>10</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="receivepagi">
        <div class="pagi" id="pagination">
            <div class="paginationjs">
            </div>
        </div>
    </div>
</section>
<!-- /.content -->
<script>
    $(document).ready(function () {
        $('.js-example-basic-single').select2({ width: 'resolve' });
        $('.js-example-basic-multiple').select2({ width: 'resolve' });
    });
</script>

