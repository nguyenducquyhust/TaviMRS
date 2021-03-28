<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script src="resources/dist/js/ajax/model/ajax_doanh_nghiep.js"></script>
<script src="resources/dist/js/ajax/model/ajax_xe_cho_duyet.js"></script>
<script src="resources/dist/js/ajax/quan_ly_doanh_nghiep/ajax_danh_sach_xe_cho_duyet.js"></script>

<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<!-- Main content -->
<section class="content">
    <div class="buifmaop">
        <div class="buifmaoptitle">
            <span class="page-title">danh sách xe chờ duyệt thông tin</span>
        </div>
        <div class="buifmaopct ">
            <div class="row">
                <div class="col-md-4">
                    <div class="caifop1li form-group">
                        <label for="bimo1">Biển số xe:</label>
                        <input type="text " id="bimo1">
                    </div>
                </div>

                <div class="col-md-4">
                        <div class="caifop1li">
                            <label for="bimo2">Thuộc doanh nghiệp:</label>
                            <select class="js-example-basic-single" name="state" id="bimo2">
                               </select>
                        </div>
                </div>

                <div class="col-md-4">
                    <label style="opacity: 0">btn</label>
                    <button class="btn btn-primary" style="display: block">Tìm Kiếm</button>
                </div>

            </div>
        </div>
    </div>
    <div class="buifmaoptb table-responsive">
        <table class="table table-hover">
            <tbody>

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
        $('.js-example-basic-single').select2();
    });
</script>