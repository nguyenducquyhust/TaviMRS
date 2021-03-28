<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<script src="resources/dist/js/ajax/model/ajax_doanh_nghiep.js"></script>
<script src="resources/dist/js/ajax/model/ajax_thong_bao.js"></script>
<script src="resources/dist/js/ajax/tra_cuu_thong_tin/ajax_tra_cuu_thong_bao.js"></script>
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">tra cứu thông báo</span>
        </div>
        <div class="buifmaopct">
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li form-group">
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
                <div class="col-md-6">
                    <div class="caifop1li form-group">
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
<%--            <div class="row">--%>
<%--                <div class="col-md-12">--%>
<%--                    <div class="caifop1li">--%>
<%--                        <label for="bimo1">Thuộc doanh nghiệp:</label>--%>
<%--                        <select class="js-example-basic-single" name="state" id="bimo3">--%>
<%--                            <option value=0>Tất cả</option>--%>
<%--                            <option value=1>Doanh nghiệp 1</option>--%>
<%--                            <option value=2>Doanh nghiệp 2</option>--%>
<%--                            <option value=3>Doanh nghiệp 3</option>--%>
<%--                        </select>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="row">
                <div class="col-md-6">
<%--                    <div class="caifop1li form-group">--%>
<%--                        <label for="bimo3">Tiêu đề:</label>--%>
<%--                        <input type="text" class="form-control" id="bimo3">--%>
<%--                    </div>--%>
                    <div class="caifop1li form-group">
                        <label for="bimo3" class="col-sm-4 control-label">Tiêu Đề:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bimo3">
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
                <th>Tiêu đề</th>
                <th>Doanh nghiệp</th>
                <th>Tệp đính kèm</th>
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