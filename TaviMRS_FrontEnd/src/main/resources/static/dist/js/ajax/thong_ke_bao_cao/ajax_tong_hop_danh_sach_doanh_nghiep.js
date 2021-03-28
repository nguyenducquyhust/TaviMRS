var inputTuNgay, inputDenNgay, selectLoaiKhoangSan, selectHuyen, btnBaoCao, btnXoaDieuKien, tableTKDN, titleNgay, titleDiaBan;

$(function () {
    inputTuNgay = $("#bimo1");
    inputDenNgay = $("#bimo2");
    selectLoaiKhoangSan = $("#bimo3");
    selectHuyen = $("#bimo4");
    btnBaoCao = $("#btn-bao-cao");
    btnXoaDieuKien = $("#btn-xoa-dieu-kien");
    tableTKDN = $("#table-data");
    titleNgay = $("#title-ngay");
    titleDiaBan = $("#title-dia-ban");

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
    findTKDoanhNghiep();
    clickXoaDieuKienTKDN();
    clickPrintElement(".limiter-header, #table-print");
})

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

function findTKDoanhNghiep() {
    btnBaoCao.click(function () {
        let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
        if (check) {
            titleNgay.html(viewNgayBaoCao(valTuNgay, valDenNgay));
            let listKhoangSanIds = selectLoaiKhoangSan.val();
            listKhoangSanIds = listKhoangSanIds == null ? ["0"] : listKhoangSanIds;
            valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
            valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
            let valHuyen = selectHuyen.val();
            giayPhepKhaiThacBaoCao(valTuNgay, valDenNgay, listKhoangSanIds, valHuyen).then(rs => {
                if (rs.result === "found") {
                    setViewTableTKDN(rs.data);
                    let dataFile = {dataForm: {}};
                    dataFile.dataForm = convertGetFile(rs.data)
                    if (dataFile.dataForm !== null) {
                        dataFile.diaBan = selectHuyen.val() == 0 ? "Tỉnh Bắc Giang" : selectHuyen.find("option:checked").text();
                        console.log(dataFile);
                        clickBaoCaoExcel(valTuNgay, valDenNgay, dataFile);
                        clickBaoCaoWord(valTuNgay, valDenNgay, dataFile);
                        clickBaoCaoPDF(valTuNgay, valDenNgay, dataFile);
                    } else {
                        alterWarning("Tạo file không thành công", TIME_ALTER);
                    }
                } else {
                    setViewTableTKDN([]);
                }
                titleDiaBan.html("DANH SÁCH DOANH NGHIỆP KHAI THÁC KHOÁNG SẢN TRÊN ĐỊA BÀN " + (selectHuyen.val() == 0 ? "Tỉnh Bắc Giang" : selectHuyen.find("option:checked").text()));
            }).catch(err => {
                alterDanger("Xảy ra lỗi tìm kiếm",TIME_ALTER);
                console.log(err);
            });
        }
    })
}

function convertGetFile(list) {
    let rs = list;
    let listTrungUong = list["0"];
    listTrungUong = listTrungUong === undefined ? [] : listTrungUong;
    let listUBND = list["1"];
    listUBND = listUBND === undefined ? [] : listUBND;
    if (listTrungUong.length === 0 && listUBND.length === 0) rs = null;
    if (listTrungUong.length > 0) {
        rs["0"] = getArrFile(listTrungUong, 0);
    }
    let len = listTrungUong.length;
    if (listUBND.length > 0) {
        rs["1"] =  getArrFile(listUBND, len);
    }
    return rs;
}

function getArrFile(list, index) {
    return list.map((item, index) => [index + 1, viewField(item.soQuyetDinh), viewField(item.coQuanCap), viewField(item.doanhNghiep.tenDoanhNghiep), viewField(item.mo.diaChi), viewField(item.mo.khoangSan.tenKhoangSan), viewField(item.dienTichKhaiThac), `${viewField(item.truLuongKhoangSan)}/${viewField(item.congSuatKhaiThac)}`, viewField(item.thoiHanKhaiThac)]);
}

function clickBaoCaoExcel(valTuNgay, valDenNgay, data) {
    $("#btn-excel").unbind("click").click(function () {
        giayPhepKhaiThacBaoCaoExcel(valTuNgay, valDenNgay, data).then(rs => {
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

function clickBaoCaoWord(valTuNgay, valDenNgay, data) {
    $("#btn-word").unbind("click").click(function () {
        giayPhepKhaiThacBaoCaoWord(valTuNgay, valDenNgay, data).then(rs => {
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

function clickBaoCaoPDF(valTuNgay, valDenNgay, data) {
    $("#btn-pdf").unbind("click").click(function () {
        // alterWarning("Chức năng đang trong thời gian phát triển", TIME_ALTER);
        giayPhepKhaiThacBaoCaoPDF(valTuNgay, valDenNgay, data).then(rs => {
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


function setViewTableTKDN(data) {
    let listTrungUong = data["0"];
    listTrungUong = listTrungUong === undefined ? [] : listTrungUong;
    let listUBND = data["1"];
    listUBND = listUBND === undefined ? [] : listUBND;
    let viewTable = "";
    if (listTrungUong.length > 0) {
        viewTable = `<tr class="row100 body">
                            <td class="cell100 column1">I</td>
                            <td colspan="2" class="cell100 column2 ">Giấy phép do Trung ương cấp</td>
                            <td class="cell100 column3"></td>
                            <td class="cell100 column4"></td>
                            <td class="cell100 column6"></td>
                            <td class="cell100 column6"></td>
                            <td class="cell100 column7"></td>
                            <td class="cell100 column8"></td>
                        </tr>`;
        viewTable += listTrungUong.map((item, index) => getViewTrTKDN(item, index)).join("");
    }
    if (listUBND.length > 0) {
        let len = listTrungUong.length;
        viewTable += `<tr class="row100 body">
                            <td class="cell100 column1">II</td>
                            <td colspan="2" class="cell100 column2 ">Giấy phép do UBND tỉnh cấp</td>
                            <td class="cell100 column3"></td>
                            <td class="cell100 column4"></td>
                            <td class="cell100 column6"></td>
                            <td class="cell100 column6"></td>
                            <td class="cell100 column7"></td>
                            <td class="cell100 column8"></td>
                        </tr>`;
        viewTable += listUBND.map((item, index) => getViewTrTKDN(item, index + len)).join("");
    }
    viewTable = viewTable !== "" ? viewTable : "<tr><td colspan=9>Không có dữ liệu báo cáo phù hợp</td></tr>";
    tableTKDN.html(viewTable);
    showLimiter();
}

function getViewTrTKDN(item, index) {
    return `<tr class="row100 body">
                            <td class="cell100 column1">${index + 1}</td>
                            <td class="cell100 column2">${viewField(item.soQuyetDinh)}</td>
                            <td class="cell100 column3">${viewField(item.coQuanCap)}</td>
                            <td class="cell100 column4">${viewField(item.doanhNghiep.tenDoanhNghiep)}</td>
                            <td class="cell100 column5">${viewField(item.mo.diaChi)}</td>
                            <td class="cell100 column6">${viewField(item.mo.khoangSan.tenKhoangSan)}</td>
                            <td class="cell100 column7">${viewField(item.dienTichKhaiThac)}</td>
                            <td class="cell100 column8">${viewField(item.truLuongKhoangSan)}/${viewField(item.congSuatKhaiThac)}</td>
                            <td class="cell100 column9">${viewField(item.thoiHanKhaiThac)}</td>
                        </tr>`
}

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