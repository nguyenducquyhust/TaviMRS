<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<script src="resources/dist/js/ajax/model/ajax_xe_van_chuyen.js"></script>
<script src="resources/dist/js/ajax/tra_cuu_thong_tin/ajax_tra_cuu_xe_van_chuyen.js"></script>
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">tra cứu xe vận chuyển</span>
        </div>
        <div class="buifmaopct">
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li form-group">
                        <label for="bimo1">Tìm kiếm:</label>
                        <input type="text" class="form-control" id="bimo1" placeholder="Biển số xe">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label for="bimo2">Trạng thái:</label>
                        <select class="js-example-basic-single" name="state" id="bimo2">
                            <option value=-1>Tất cả</option>
                            <option value=0>Chờ duyệt</option>
                            <option value=1>Đồng ý</option>
                            <option value=2>Không đồng ý</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
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
                <th>Biển số xe</th>
                <th>Doanh nghiệp</th>
                <th>Khối lượng xe</th>
                <th>Khối lượng hàng</th>
                <th>Trạng thái</th>
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