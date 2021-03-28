<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<script src="resources/dist/js/ajax/model/ajax_mo.js"></script>
<script src="resources/dist/js/ajax/model/ajax_loai_giay_phep.js"></script>
<script src="resources/dist/js/ajax/model/ajax_giay_phep_khai_thac.js"></script>
<script src="resources/dist/js/ajax/tra_cuu_thong_tin/ajax_tra_cuu_giay_phep_khai_thac.js"></script>
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">tra cứu giấy phép khai thác</span>
        </div>
        <div class="buifmaopct">
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label for="bimo1">Mỏ:</label>
                        <select class="js-example-basic-single" name="state" id="bimo1">
                            <option value=0>Tất cả</option>
                            <option value=1>Mỏ A</option>
                            <option value=2>Mỏ B</option>
                            <option value=3>Mỏ C</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label for="bimo2">Loại giấy phép:</label>
                        <select class="js-example-basic-single" name="state" id="bimo2">
                            <option value=0>Tất cả</option>
                            <option value=1>Giấy phép 1</option>
                            <option value=2>Giấy phép 2</option>
                            <option value=3>Giấy phép 3</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label for="bimo3">Trạng thái:</label>
                        <select class="js-example-basic-single" name="state" id="bimo3">
                            <option value=-1>Tất cả</option>
                            <option value=0>Hết hiệu lực</option>
                            <option value=1>Còn hiệu lực</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label for="bimo4">Thẩm quyền cấp:</label>
                        <select class="js-example-basic-single" name="state" id="bimo4">
                            <option value=-1>Tất cả</option>
                            <option value=0>Trung Ương</option>
                            <option value=1>UBND tỉnh</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li">
                        <div class="caifop1li form-group">
                            <label for="bimo5">Tìm kiếm:</label>
                            <input type="text" class="form-control" id="bimo5" placeholder="Số Quyết Định">
                        </div>
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
                <th>Số quyết định</th>
                <th>Cơ quan cấp</th>
                <th>Trạng thái</th>
                <th>Loại giấy phép</th>
                <th>Người cấp</th>
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
            <tr>
                <td>6</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>7</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>8</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>9</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>10</td>
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
