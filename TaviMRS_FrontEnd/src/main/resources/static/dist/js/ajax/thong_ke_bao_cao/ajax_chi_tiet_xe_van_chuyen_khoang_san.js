var xeVanChuyenId, moId, paramNgayDau, paramNgayCuoi, tableCTXeVanChuyenKs;

$(function () {
    tableCTXeVanChuyenKs = $("#table-data");

    let href = new URL(window.location.href);
    xeVanChuyenId = href.searchParams.get("xe-van-chuyen-id");
    moId = href.searchParams.get("mo-id");
    paramNgayDau = href.searchParams.get("ngay-dau");
    paramNgayCuoi = href.searchParams.get("ngay-cuoi");
    clickPrintElement(".content-wrapper-header, #table-print");

    if(xeVanChuyenId !== null && moId !== null && paramNgayDau !== null && paramNgayCuoi !== null) {
        let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
        $("#title-ngay").html(viewNgayBaoCao(valTuNgay, valDenNgay));
        if(check) {
            valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
            valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
            nhatKyXeVaoMoChiTiet(xeVanChuyenId, moId,valTuNgay, valDenNgay).then(rs => {
                if(rs.result === "found") {
                    setTableChiTietXeVaoMo(rs.data);
                    clickBaoCaoExcel(valTuNgay, valDenNgay);
                    clickBaoCaoWord(valTuNgay, valDenNgay);
                    clickBaoCaoPDF(valTuNgay, valDenNgay);
                } else {
                    window.history.back();
                }
            }).catch(err => {
                console.log(err);
                window.history.back();
            })
        }
    } else {
        window.history.back();
    }
})

function setTableChiTietXeVaoMo(data) {
    let viewTable = `<tr>
                        <th>STT</th>
                        <th>Biển số xe</th>
                        <th>Mỏ</th>
                        <th>Ngày giờ vào</th>
                        <th>Ngày giờ ra</th>
                        <th>Trọng lượng khoáng sản</th>
                    </tr>`;
    viewTable += data.map((item, index) => `<tr>
                            <td>${index + 1}</td>
                            <td>${viewField(item.xeVanChuyen.bienSoXe)}</td>
                            <td>${viewField(item.mo.diaChi)}</td>
                            <td>${viewDateTime(item.thoiGianVao)}</td>
                            <td>${viewDateTime(item.thoiGianRa)}</td>
                            <td>${viewField(item.trongLuongKhoangSan)}</td>
                        </tr>`).join("");
    viewTable += data.length > 0 ? "" : "<tr><td colspan=6>Không có dữ liệu báo cáo phù hợp</td></tr>";
    tableCTXeVanChuyenKs.html(viewTable);
}

function checkThoiGianTHDN() {
    let rs = true;
    // let {check : checkTuNgay, val: valTuNgay} = checkNgayTKDSDN(paramNgayDau);
    // let {check : checkDenNgay, val: valDenNgay} = checkNgayTKDSDN(paramNgayCuoi);
    let valTuNgay = paramNgayDau;
    let valDenNgay = paramNgayCuoi;
    if (valTuNgay !== "" && valDenNgay !== "" && !compareDate(valTuNgay, valDenNgay)) {
        rs = false;
        alterDanger("Thời gian giới hạn phải lớn hơn!", TIME_ALTER);
    }
    return {check : rs, valTuNgay: valTuNgay, valDenNgay: valDenNgay};
}

function clickBaoCaoExcel(valTuNgay, valDenNgay) {
    $("#btn-excel").unbind("click").click(function () {
        nhatKyXeVaoMoChiTietExcel(xeVanChuyenId, moId, valTuNgay, valDenNgay).then(rs => {
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

function clickBaoCaoWord(valTuNgay, valDenNgay) {
    $("#btn-word").unbind("click").click(function () {
        nhatKyXeVaoMoChiTietWord(xeVanChuyenId, moId,valTuNgay, valDenNgay).then(rs => {
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

function clickBaoCaoPDF(valTuNgay, valDenNgay) {
    $("#btn-pdf").unbind("click").click(function () {
        // alterWarning("Chức năng đang trong thời gian phát triển", TIME_ALTER);
        nhatKyXeVaoMoChiTietPDF(xeVanChuyenId, moId,valTuNgay, valDenNgay).then(rs => {
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

// function checkNgayTKDSDN(val) {
//     let rs = false;
//     if (val === '' || regexDate(val)) {
//         rs = true;
//     } else window.history.back();
//     return {check : rs, val: val};
// }