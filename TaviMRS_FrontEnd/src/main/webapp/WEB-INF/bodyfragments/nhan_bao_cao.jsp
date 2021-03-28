<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="./dist/css/nhanbaocao.css">
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<script src="resources/dist/js/ajax/model/ajax_bao_cao.js"></script>
<script src="resources/dist/js/ajax/thong_bao_bao_cao/ajax_nhan_bao_cao.js"></script>
<!-- Main content -->
<section class="content">
    <div class="receiverp">
        <div class="receiverp-title">
            <span class="page-title">nhận báo cáo từ doanh nghiệp</span>
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
            <div class="row">
                <div class="col-md-6">
                    <div class="caifop1li form-group">
                        <label class="col-sm-4 control-label">Doanh Nghiệp:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bimo3">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="caifop1li form-group">
                        <label class="col-sm-4 control-label">Tiêu Đề:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="bimo4">
                        </div>
                    </div>
                </div>
            </div>
            <div class="class-xs-12 text-center">
                <button class="btn btn-primary" style="margin: 30px" id="btn-tim-kiem">Tìm kiếm</button>
            </div>
        </div>
    </div>
    <div class="receivetb table-responsive">
        <table class="table table-hover ">
            <tbody id="table-view">
            <tr>
                <th>STT</th>
                <th>Tiêu đề</th>
                <th>Doanh nghiệp</th>
                <th class="tdk">Tệp đính kèm</th>
            </tr>
            <%--            <tr>--%>
            <%--                <td>1</td>--%>
            <%--                <td><a href="chi-tiet-bao-cao">v/v báo cáo tháng 6</a></td>--%>
            <%--                <td>Công ty ABC</td>--%>
            <%--                <td></td>--%>
            <%--            </tr>--%>
            <tr>
                <td>1</td>
                <td></td>
                <td></td>
                <td class="tdk"></td>
            </tr>
            <tr>
                <td>2</td>
                <td></td>
                <td></td>
                <td class="tdk"></td>
            </tr>
            <tr>
                <td>3</td>
                <td></td>
                <td></td>
                <td class="tdk"></td>
            </tr>
            <tr>
                <td>4</td>
                <td></td>
                <td></td>
                <td class="tdk"></td>
            </tr>
            <tr>
                <td>6</td>
                <td></td>
                <td></td>
                <td class="tdk"></td>
            </tr>
            <tr>
                <td>7</td>
                <td></td>
                <td></td>
                <td class="tdk"></td>
            </tr>
            <tr>
                <td>8</td>
                <td></td>
                <td></td>
                <td class="tdk"></td>
            </tr>
            <tr>
                <td>9</td>
                <td></td>
                <td></td>
                <td class="tdk"></td>
            </tr>
            <tr>
                <td>10</td>
                <td></td>
                <td></td>
                <td class="tdk"></td>
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
<!-- Main js -->
<script src="./dist/js/main.js"></script>