var inputTuNgay, inputDenNgay, selectLoaiKhoangSan, selectHuyen, btnBaoCao, btnXoaDieuKien, tableTKTLKT, titleNgay;

$(function () {
    inputTuNgay = $("#bimo1");
    inputDenNgay = $("#bimo2");
    selectLoaiKhoangSan = $("#bimo3");
    selectHuyen = $("#bimo4");
    btnBaoCao = $("#btn-bao-cao");
    btnXoaDieuKien = $("#btn-xoa-dieu-kien");
    tableTKTLKT = $("#table-data");
    titleNgay = $("#title-ngay");

    //set view select
    setListKhoangSan(selectLoaiKhoangSan);
    districtFindByProvince(24).then(rs => {
        setListTinhHuyenXa(selectHuyen, rs);
        selectHuyen.prepend(`<option value="0">Tất cả</option>`);
        selectHuyen.val("0");
    }).catch(err => console.log(err));
    $('.js-example-basic-single').select2({ width: 'resolve' });
    $('.js-example-basic-multiple').select2({ width: 'resolve' });
    //end set view select
    findTKTLKT();
    clickXoaDieuKienTKDN();
    clickPrintElement(".limiter-header, #table-print");
})

function clickXoaDieuKienTKDN() {
    btnXoaDieuKien.click(function () {
        inputTuNgay.val("");
        inputDenNgay.val("");
        selectLoaiKhoangSan.val(null);
        selectHuyen.val("0");
        $('.js-example-basic-single').select2({ width: 'resolve' });
        $('.js-example-basic-multiple').select2({ width: 'resolve' });
    })
}

function checkThoiGianTHDN() {
    let rs = true;
    // let {check : checkTuNgay, val: valTuNgay} = checkNgayTKDSDN(inputTuNgay);
    // let {check : checkDenNgay, val: valDenNgay} = checkNgayTKDSDN(inputDenNgay);
    let valTuNgay = inputTuNgay.val();
    let valDenNgay = inputDenNgay.val();
    let selectorDenNgay = inputDenNgay.parents(".form-group");
    if (valTuNgay !== "" && valDenNgay !== "" && !compareDate(valTuNgay, valDenNgay)) {
        rs = false;
        viewError(selectorDenNgay,"Thời gian giới hạn phải lớn hơn!");
    } else hiddenError(selectorDenNgay);
    return {check : rs, valTuNgay: valTuNgay, valDenNgay: valDenNgay};
}

// function checkNgayTKDSDN(selectorNgay) {
//     let rs = false;
//     let val = selectorNgay.val();
//     let selector = selectorNgay.parents(".form-group");
//     if (val === '' || regexDate(val)) {
//         rs = true;
//         hiddenError(selector);
//     } else viewError(selector,"Chưa đúng định dạng ngày");
//     return {check : rs, val: val};
// }

function findTKTLKT() {
    btnBaoCao.click(function () {
        let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
        if (check) {
            titleNgay.html(viewNgayBaoCao(valTuNgay, valDenNgay));
            let listKhoangSanIds = selectLoaiKhoangSan.val();
            listKhoangSanIds = listKhoangSanIds == null ? ["0"] : listKhoangSanIds;
            valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
            valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
            let valHuyen = selectHuyen.val();
            nhatKyKhaiThacBaoCao(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen).then(rs => {
                if (rs.result === "found") {
                    setViewTableTKTLKT(rs.data);
                    clickBaoCaoExcel(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen);
                    clickBaoCaoWord(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen);
                    clickBaoCaoPDF(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen);
                } else {
                    setViewTableTKTLKT([]);
                }
            }).catch(err => {
                alterDanger("Xảy ra lỗi tìm kiếm",TIME_ALTER);
                console.log(err);
            });
        }
    })
}

function clickBaoCaoExcel(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen) {
    $("#btn-excel").unbind("click").click(function () {
        nhatKyKhaiThacBaoCaoExcel(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen).then(rs => {
            if(rs.result === "uploaded") {
                window.open(rs.data);
            } else {
                alterDanger("Lỗi xuất file excel", TIME_ALTER);
            }
        }).catch(err => {
            console.log(err);
            alterDanger("Lỗi xuất file excel", TIME_ALTER);
        })
    })

}

function clickBaoCaoWord(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen) {
    $("#btn-word").unbind("click").click(function () {
        nhatKyKhaiThacBaoCaoWord(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen).then(rs => {
            if(rs.result === "uploaded") {
                window.open(rs.data);
            } else {
                alterDanger("Lỗi xuất file word", TIME_ALTER);
            }
        }).catch(err => {
            console.log(err);
            alterDanger("Lỗi xuất file word", TIME_ALTER);
        })
    })
}

function clickBaoCaoPDF(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen) {
    $("#btn-pdf").unbind("click").click(function () {
        // alterWarning("Chức năng đang trong thời gian phát triển", TIME_ALTER);
        nhatKyKhaiThacBaoCaoPDF(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen).then(rs => {
            if(rs.result === "uploaded") {
                window.open(rs.data);
            } else {
                alterDanger("Lỗi xuất file pdf", TIME_ALTER);
            }
        }).catch(err => {
            console.log(err);
            alterDanger("Lỗi xuất file pdf", TIME_ALTER);
        })
    })
}

function setViewTableTKTLKT(data) {
    let viewTable = data.map((item, index) => `<tr class="row100 body">
                            <td class="cell100 column1">${index + 1}</td>
                            <td class="cell100 column2">${viewField(item.mo.doanhNghiep.tenDoanhNghiep)}</td>
                            <td class="cell100 column3">${viewField(item.mo.diaChi)}</td>
                            <td class="cell100 column4">${viewField(item.mo.khoangSan.tenKhoangSan)}</td>
                            <td class="cell100 column5">${viewField(item.mo.truLuongMo)}</td>
                            <td class="cell100 column6">${viewField(item.mo.congSuatKhaiThac)}</td>
                            <td class="cell100 column7">${viewField(item.tong)}</td>
                        </tr>`).join();
    viewTable = viewTable !== "" ? viewTable : "<tr><td colspan=7>Không có dữ liệu báo cáo phù hợp</td></tr>";
    tableTKTLKT.html(viewTable);
    showLimiter();
}