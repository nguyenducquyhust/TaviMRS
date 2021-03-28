var inputTuNgay, inputDenNgay, selectCongTy, btnBaoCao, btnXoaDieuKien, tableTKLX, titleNgay;

$(function () {
    inputTuNgay = $("#bimo1");
    inputDenNgay = $("#bimo2");
    selectCongTy = $("#bimo3");
    btnBaoCao = $("#btn-bao-cao");
    btnXoaDieuKien = $("#btn-xoa-dieu-kien");
    tableTKLX = $("#table-data");
    titleNgay = $("#title-ngay");

    //set view select
    setListDoanhNghiep(selectCongTy);
    $('.js-example-basic-single').select2({ width: 'resolve' });
    $('.js-example-basic-multiple').select2({ width: 'resolve' });
    //select Cong Ty
    //end set view select
    findTKLX();
    clickXoaDieuKienTKLX();
    clickPrintElement(".limiter-header, #table-print");
})

function clickXoaDieuKienTKLX() {
    btnXoaDieuKien.click(function () {
        inputTuNgay.val("");
        inputDenNgay.val("");
        selectCongTy.val("0");
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

function findTKLX() {
    btnBaoCao.click(function () {
        let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
        if (check) {
            titleNgay.html(viewNgayBaoCao(valTuNgay, valDenNgay));
            valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
            valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
            let valCongTy = selectCongTy.val();
            nhatKyXeVaoMoBaoCao(valTuNgay, valDenNgay, valCongTy).then(rs => {
                if (rs.result === "found") {
                    setViewTableTKLX(rs.data);
                    clickBaoCaoExcel(valTuNgay, valDenNgay, valCongTy);
                    clickBaoCaoWord(valTuNgay, valDenNgay, valCongTy);
                    clickBaoCaoPDF(valTuNgay, valDenNgay, valCongTy);
                } else {
                    setViewTableTKLX([]);
                }
            }).catch(err => {
                alterDanger("Xảy ra lỗi tìm kiếm",TIME_ALTER);
                console.log(err);
            });
        }
    })
}

function clickBaoCaoExcel(valTuNgay, valDenNgay, valCongTy) {
    $("#btn-excel").unbind("click").click(function () {
        nhatKyXeVaoMoBaoCaoExcel(valTuNgay, valDenNgay, valCongTy).then(rs => {
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

function clickBaoCaoWord(valTuNgay, valDenNgay, valCongTy) {
    $("#btn-word").unbind("click").click(function () {
        nhatKyXeVaoMoBaoCaoWord(valTuNgay, valDenNgay, valCongTy).then(rs => {
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

function clickBaoCaoPDF(valTuNgay, valDenNgay, valCongTy) {
    $("#btn-pdf").unbind("click").click(function () {
        // alterWarning("Chức năng đang trong thời gian phát triển", TIME_ALTER);
        nhatKyXeVaoMoBaoCaoPDF(valTuNgay, valDenNgay, valCongTy).then(rs => {
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

function setViewTableTKLX(data) {
    let viewTable = data.map((item, index) => `<tr class="row100 body">
                                <td class="cell100 column1">${index + 1}</td>
                                <td class="cell100 column2"><a href="${hrefChiTietXeVanChuyen(item)}">${viewField(item.xeVanChuyen.bienSoXe)}</a></td>
                                <td class="cell100 column3">${viewField(item.mo.diaChi)}</td>
                                <td class="cell100 column4">${viewField(item.soLuot)}</td>
                                <td class="cell100 column5">${viewField(item.tong)}</td>
                            </tr>`).join();
    viewTable = viewTable !== "" ? viewTable : "<tr><td colspan=7>Không có dữ liệu báo cáo phù hợp</td></tr>";
    tableTKLX.html(viewTable);
    showLimiter();
}

function hrefChiTietXeVanChuyen(item) {
    return `chi-tiet-xe-van-chuyen-khoang-san?ngay-dau=${inputTuNgay.val()}&ngay-cuoi=${inputDenNgay.val()}&mo-id=${item.mo.idMo}&xe-van-chuyen-id=${item.xeVanChuyen.idXeVanChuyen}`;
}