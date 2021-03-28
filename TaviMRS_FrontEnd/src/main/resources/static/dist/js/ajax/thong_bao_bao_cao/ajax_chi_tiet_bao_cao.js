var textDoanhNghiep, textTieuDe, textNoiDung, textThoiGianGui, textDinhKem, danhSachDinhKem, tableView, textTongSo, inputTieuDeTB, inputNoiDungTB,  danhSachDinhKemTB;
var idBaoCao = null;
var baoCao = null;
var thongBao = null;
var idThongBao = null;
$(function() {
    textDoanhNghiep = $("#text1");
    textTieuDe = $("#text2");
    textNoiDung = $("#text3");
    textThoiGianGui = $("#text4");
    textDinhKem = $("#text5");
    danhSachDinhKem = $("#list-dinh-kem");
    tableView = $("#table-doanh-nghiep-khac");
    textTongSo = $("#tong-don-vi");
    inputTieuDeTB = $("#text6");
    inputNoiDungTB = $("#text7");
    danhSachDinhKemTB = $("#list-dinh-kem-1");

    let href = new URL(window.location.href);
    idBaoCao = href.searchParams.get("id");

    baoCaoFindById(idBaoCao).then(rs => {
        if(rs.result === "found") {
            baoCao = rs.data;
            setViewBaoCao(baoCao);
            thongBao = baoCao.thongBao;
            if(thongBao != null) setViewThongBao(thongBao);
        } else {
            console.log(err);
            window.history.back();
        }
    }).catch(err => {
        console.log(err);
        window.history.back();
    })
})

function setViewBaoCao(data) {
    textDoanhNghiep.html(viewField(`<strong>Doanh nghiêp:</strong> ${viewField(data.nguoiDung !== null ? data.nguoiDung.doanhNghiep !== null ? data.nguoiDung.doanhNghiep.tenDoanhNghiep : null : null)}`));
    textTieuDe.html(viewField(`<strong>Tiêu đề:</strong> ${data.tieuDe}`));
    textNoiDung.html(viewField(`<strong>Nội Dung:</strong> ${data.noiDung}`));
    textThoiGianGui.html(viewField(`<strong>Thời gian gửi:</strong> ${viewDateTime(data.thoiGianGui)}`));
    let listTaiLieuDinhKem = data.listTaiLieuDinhKem;
    if(listTaiLieuDinhKem !== null && listTaiLieuDinhKem.length > 0) {
        textDinhKem.html("<strong>Tài liệu:</strong>");
        danhSachDinhKem.html(viewTaiLieuDinhKem(listTaiLieuDinhKem));
    }
}

function viewTaiLieuDinhKem(list) {
    return list.map(data => `<li>
                                <a href="${viewSrc(data.duongDan)}" target="_blank" style="color: unset">${viewField(data.duongDan !== null ? `${textToIconFile(data.duongDan)}: ${data.duongDan}` : null)}</a>
                            </li>`).join("");
}

function setViewTableDNK(list = [], pageNumber = 1) {
    let view = `<tr>
                <th>STT</th>
                <th>Tiêu đề</th>
                <th>Doanh nghiệp</th>
                <th class="tdk">Tệp đính kèm</th>
            </tr>`;
    view += list.map((data, index) => `<tr>
                <td>${5*(pageNumber - 1) + index + 1}</td>
                <td><a href="chi-tiet-bao-cao?id=${data.idBaoCao}">${viewField(data.tieuDe)}</a></td>
                <td>${viewField(data.nguoiDung !== null ? data.nguoiDung.doanhNghiep !== null ? data.nguoiDung.doanhNghiep.tenDoanhNghiep : null : null)}</td>
                <td class="tdk">${viewField(data.listTaiLieuDinhKem !== null ? setListFile(data.listTaiLieuDinhKem) : null)}</td>
            </tr>`).join("");
    let len = list.length;
    for (let i = len; i <= 4; i++) {
        view += `<tr>
                <td>${5*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    tableView.html(view);
}

function setListFile(listFile) {
    if(listFile !== null) {
        return listFile.map(data => `<a href="${viewSrc(data.duongDan)}" target="_blank" style="color: unset">${viewField(data.duongDan !== null ? textToIconFile(data.duongDan) : null)}</a>`)
    }
}

function setViewThongBao(data) {
    inputTieuDeTB.val(viewField(data.tieuDe));
    inputNoiDungTB.val(viewField(data.noiDung));
    idThongBao = data.idThongBao;
    baoCaoFindByThongBao(idThongBao, 5).then(rs => {
        if(rs.result === "found") {
            $('#pagination').pagination({
                dataSource: function (done) {
                    let result = [];
                    for (let i = 1; i <= rs.data.totalPages; i++) result.push(1);
                    done(result);
                },
                locator: 'items',
                totalNumber: rs.data.totalPages,
                pageSize: 1,
                showPageNumbers: true,
                showPrevious: true,
                showNext: true,
                // showNavigator: true,
                showFirstOnEllipsisShow: true,
                showLastOnEllipsisShow: true,
                callback: function (response, pagination) {
                    if (pagination.pageNumber == 1) {
                        textTongSo.html(`Tổng số ${rs.data.totalElements}/${thongBao.soLuongDonViNhan === null ? 0 : thongBao.soLuongDonViNhan} đơn vị phản hồi.`)
                        setViewTableDNK(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    baoCaoFindByThongBao(idThongBao, pagination.pageNumber, 5).then(rs => {
                        setViewTableDNK(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        }
    }).catch(err => {
        alterWarning("Xảy ra lỗi tìm kiếm", TIME_ALTER);
        console.log(err);
    })
}