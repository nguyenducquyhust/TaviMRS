<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<script src="resources/dist/js/ajax/model/ajax_mo.js"></script>
<script src="resources/dist/js/ajax/model/ajax_thiet_bi.js"></script>
<script src="resources/dist/js/ajax/model/ajax_doanh_nghiep.js"></script>
<script src="resources/dist/js/ajax/model/ajax_khoang_san.js"></script>
<script src="resources/dist/js/ajax/model/ajax_dia_gioi.js"></script>
<script src="resources/dist/js/ajax/tra_cuu_thong_tin/ajax_tra_cuu_thiet_bi.js"></script>
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">tra cứu thiết bị</span>
        </div>
        <div class="buifmaopct">
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li">
                        <div class="caifop1li">
                            <label for="bimo1">Thuộc Mỏ:</label>
                            <select class="js-example-basic-single" name="state" id="bimo1">
                                <option value=0>Tất cả</option>
                                <option value=1>Doanh nghiệp 1</option>
                                <option value=2>Doanh nghiệp 2</option>
                                <option value=3>Doanh nghiệp 3</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label for="bimo2">Trạng thái:</label>
                        <select class="js-example-basic-single" name="state" id="bimo2">
                            <option value=-1>Tất cả</option>
                            <option value=0>Không kết nối</option>
                            <option value=1>Kết nối</option>
                            <option value=2>Khóa thiết bị</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li form-group">
                        <label for="bimo3">Tìm kiếm:</label>
                        <input type="text" class="form-control" id="bimo3" placeholder="Mã thiết bị">
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
                <th>Mã thiết bị</th>
                <th>Mỏ</th>
                <th>Vị trí lắp</th>
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
            <tr>
                <td>6</td>
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
            </tr>
            <tr>
                <td>8</td>
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
            </tr>
            <tr>
                <td>10</td>
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