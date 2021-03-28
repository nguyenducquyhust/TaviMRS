<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Nhận báo cáo Css -->
<link rel="stylesheet" href="resources/dist/css/nhanbaocao.css">
<script src="resources/dist/js/ajax/model/ajax_bao_cao.js"></script>
<script src="resources/dist/js/ajax/model/ajax_thong_bao.js"></script>
<script src="resources/dist/js/ajax/thong_bao_bao_cao/ajax_chi_tiet_bao_cao.js"></script>
<!-- Main content -->
<section class="content">
    <div class="receiverp">
        <div class="receiverp-title">
            <span class="page-title">nhận báo cáo từ doanh nghiệp</span>
        </div>
    </div>
    <div class="receivetabct">
        <div class="rctabctfct">
            <div class="dtcmrctitem" id="text1">
                            <span>Doanh
                                nghiệp:...............................................................................................</span>
            </div>
            <div class="dtcmrctitem" id="text2">
                            <span>Tiêu
                                đề:...............................................................................................</span>
            </div>
            <div class="dtcmrctitem" id="text3">
                            <span>Nội
                                dung:...............................................................................................</span>
            </div>
            <div class="dtcmrctitem" id="text4">
                            <span>Thời gian gửi:...............................................................................................</span>
            </div>
            <div class="dtcmrctitem">
                            <span class="vertical-top" id="text5">Tệp đính
                                kèm:...............................................................................................</span>
                            <ul class="tep-dinh-kem-ngang" id="list-dinh-kem">
<%--                                <li>--%>
<%--                                    <a href="http://103.9.86.61:8080/resources/upload/mrs/806000000thumb_html-css-js-mr-website-dev-human-55187847.png" target="_blank" style="color: unset"><i class="fas fa-file-image text-primary" aria-hidden="true"></i>: 806000000thumb_html-css-js-mr-website-dev-human-55187847.png</a>--%>
<%--                                </li>--%>
                            </ul>
            </div>
        </div>
        <div class="retabctsct">
            <div class="retabctsct-title">
                <span>Doanh nghiệp khác</span>
                <span>Nội dung thông báo</span>
            </div>
            <div class="retabctsctct">
                <div class="retabctsct-table table-responsive ">
                    <table class="table table-hover ">
                        <tbody id="table-doanh-nghiep-khac">
                        <tr>
                            <th>STT</th>
                            <th>Tiêu đề</th>
                            <th>Doanh nghiệp</th>
                            <th>Tệp đính kèm</th>
                        </tr>
                        <tr>
                            <td colspan="4"></td>
                        </tr>
                        </tbody>
                    </table>
                    <strong id="tong-don-vi">Tổng số 0/0 đơn vị phản hồi</strong>
                    <div class="receivepagi">
                        <div class="pagi" id="pagination">
                            <div class="paginationjs">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="retabctsct-form d-none">
                    <div class="rtsctfitem">
                        <label>Tiêu đề</label>
                        <input type="text" id="text6" disabled>
                    </div>
                    <div class="rtsctfitem">
                        <label>Nội dung</label>
                        <textarea id="text7" disabled></textarea>
                    </div>
                    <span class="vertical-top">Tệp đính kèm theo: </span>
                    <ul class="tep-dinh-kem-ngang" id="list-dinh-kem-1">
<%--                        <li>--%>
<%--                            <a href="http://103.9.86.61:8080/resources/upload/mrs/806000000thumb_html-css-js-mr-website-dev-human-55187847.png" target="_blank" style="color: unset"><i class="fas fa-file-image text-primary" aria-hidden="true"></i></a>--%>
<%--                        </li>--%>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</section>
<!-- /.content -->
<!-- Main js -->
<script src="resources/dist/js/main.js"></script>
<!-- Chi tiết báo cáo js -->
<script src="resources/dist/js/chitietbaocao.js"></script>