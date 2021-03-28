var inputTuNgay, inputDenNgay, selectCongTy, selectLoaiViPham, btnBaoCao, btnXoaDieuKien, tableTKVP, titleNgay;

$(function () {
    inputTuNgay = $("#bimo1");
    inputDenNgay = $("#bimo2");
    selectCongTy = $("#bimo3");
    selectLoaiViPham = $("#bimo4");
    btnBaoCao = $("#btn-bao-cao");
    btnXoaDieuKien = $("#btn-xoa-dieu-kien");
    tableTKVP = $("#data-table");
    titleNgay = $("#title-ngay");

    //set view select
    setListLoaiViPham(selectLoaiViPham);
    setListDoanhNghiep(selectCongTy);
    $('.js-example-basic-single').select2({ width: 'resolve' });
    $('.js-example-basic-multiple').select2({ width: 'resolve' });
    //select Cong Ty
    //end set view select
    findTKVP();
    clickXoaDieuKienTKVP();
    clickPrintElement(".limiter-header, #table-print");
})

function clickXoaDieuKienTKVP() {
    btnXoaDieuKien.click(function () {
        inputTuNgay.val("");
        inputDenNgay.val("");
        selectCongTy.val("0");
        selectLoaiViPham.val("0");
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

function findTKVP() {
    btnBaoCao.click(function () {
        let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
        if (check) {
            titleNgay.html(viewNgayBaoCao(valTuNgay, valDenNgay));
            valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
            valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
            let valCongTy = selectCongTy.val();
            let valViPham = selectLoaiViPham.val();
            baoCaoViPham(valTuNgay, valDenNgay, valCongTy, valViPham).then(rs => {
                if (rs.result === "found") {
                    setViewTableTKVP(rs.data);
                    clickBaoCaoExcel(valTuNgay, valDenNgay, valCongTy, valViPham);
                    clickBaoCaoWord(valTuNgay, valDenNgay, valCongTy, valViPham);
                    clickBaoCaoPDF(valTuNgay, valDenNgay, valCongTy, valViPham);
                } else {
                    setViewTableTKVP([]);
                }
            }).catch(err => {
                alterDanger("Xảy ra lỗi tìm kiếm",TIME_ALTER);
                console.log(err);
            });
        }
    })
}

function clickBaoCaoExcel(valTuNgay, valDenNgay, valCongTy, valViPham) {
    $("#btn-excel").unbind("click").click(function () {
        baoCaoViPhamExcel(valTuNgay, valDenNgay, valCongTy, valViPham).then(rs => {
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

function clickBaoCaoWord(valTuNgay, valDenNgay, valCongTy, valViPham) {
    $("#btn-word").unbind("click").click(function () {
        baoCaoViPhamWord(valTuNgay, valDenNgay, valCongTy, valViPham).then(rs => {
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

function clickBaoCaoPDF(valTuNgay, valDenNgay, valCongTy, valViPham) {
    $("#btn-pdf").unbind("click").click(function () {
        // alterWarning("Chức năng đang trong thời gian phát triển", TIME_ALTER);
        baoCaoViPhamPDF(valTuNgay, valDenNgay, valCongTy, valViPham).then(rs => {
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

function setViewTableTKVP(data) {
    let viewTable = data.map((item, index) => `<tr class="row100 body">
                            <td class="cell100 column1">${index + 1}</td>
                            <td class="cell100 column2"><a href="chi-tiet-vi-pham?id=${item.idViPham}">${viewField(item.noiDungViPham)}</a></td>
                            <td class="cell100 column3">${viewField(item.doanhNghiep !== null ? item.doanhNghiep.tenDoanhNghiep: null)}</td>
                            <td class="cell100 column5">${viewField(viewDateTime(item.thoiGianXayRa))}</td>
                            <td class="cell100 column6">${viewTrangThaiViPham(item)}</td>
                        </tr>`).join();
    viewTable = viewTable !== "" ? viewTable : "<tr><td colspan=7>Không có dữ liệu báo cáo phù hợp</td></tr>";
    tableTKVP.html(viewTable);
    showLimiter();
}
