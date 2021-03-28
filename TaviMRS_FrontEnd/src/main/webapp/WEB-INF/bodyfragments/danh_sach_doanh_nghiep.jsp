<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/dist/js/ajax/model/ajax_mo.js"></script>
<script src="resources/dist/js/ajax/model/ajax_doanh_nghiep.js"></script>
<script src="resources/dist/js/ajax/model/ajax_khoang_san.js"></script>
<script src="resources/dist/js/ajax/model/ajax_dia_gioi.js"></script>
<script src="resources/dist/js/ajax/quan_ly_doanh_nghiep/ajax_danh_sach_doanh_nghiep.js"></script>
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">danh sách doanh nghiệp khai thác khoáng sản</span>
        </div>
        <div class="buifmaopct">
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li form-group">
                        <label for="bimo1">Tên doanh nghiệp:</label>
                        <input type="text" class="form-control" id="bimo1">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label for="bimo2">Thuộc huyện:</label>
                        <select class="js-example-basic-single" name="state" id="bimo2">
                            <option value=0>Tất cả</option>
                            <option value=1>Thành phố Bắc Giang</option>
                            <option value=2>Huyện Hiệp Hòa</option>
                            <option value=3>Huyện Tân Yên</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li">
                        <label for="bimo3">Loại khoáng sản:</label>
                        <select class="js-example-basic-multiple" name="state" id="bimo3" multiple="multiple">
                            <option value="NN">Đồng</option>
                            <option value="II">Barit</option>
                            <option value="VV">Sắt</option>
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
                <th>Loại khoáng sản</th>
                <th>Trạng thái</th>
            </tr>
<%--            <tr>--%>
<%--                <td>1</td>--%>
<%--                <td><a href="thong-tin-doanh-nghiep">Công ty Đông Bắc</a></td>--%>
<%--                <td>Mỏ than Đồng Rì, xã Thanh Sơn và xã Thanh Luận, huyện Sơn Động</td>--%>
<%--                <td>Than</td>--%>
<%--                <td>Đang hoạt động</td>--%>
<%--            </tr>--%>
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
        <div class="page-link">
            <a href="thong-tin-doanh-nghiep?id=0"><i class="fas fa-plus-circle"></i></a>
        </div>
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