var inputTuNgay, inputDenNgay, inputDoanhNghiep, inputTieuDe, btnTimKiem, tableView;
$(function() {
    inputTuNgay = $("#bimo1");
    inputDenNgay = $("#bimo2");
    inputDoanhNghiep = $("#bimo3");
    inputTieuDe = $("#bimo4");
    btnTimKiem = $("#btn-tim-kiem");
    tableView = $("#table-view");

    findAllNBC();
    btnTimKiem.click(function () {
        if(inputTuNgay.val() === "" && inputDenNgay.val() === "" &&
            inputDoanhNghiep.val() === "" && inputTieuDe.val() === "") {
            findAllNBC();
        } else {
            console.log("search");
            findNBC();
        }
    })
})

function checkThoiGianTHDN() {
    let rs = true;
    let valTuNgay = inputTuNgay.val();
    let valDenNgay = inputDenNgay.val();
    let selectorDenNgay = inputDenNgay.parents(".form-group");
    if (valTuNgay !== "" && valDenNgay !== "" && !compareDate(valTuNgay, valDenNgay)) {
        rs = false;
        viewError(selectorDenNgay,"Thời gian giới hạn phải lớn hơn!");
    } else hiddenError(selectorDenNgay);
    return {check : rs, valTuNgay: valTuNgay, valDenNgay: valDenNgay};
}

function findNBC() {
    let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
    if(check) {
        let valTieuDe = inputTieuDe.val();
        let valDoanhNghiep = inputDoanhNghiep.val();
        valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
        valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
        baoCaoSearchDoanhNghiep(valTuNgay, valDenNgay, valTieuDe, valDoanhNghiep).then(rs => {
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
                            setViewTableNBC(rs.data.currentElements, 1);
                            return;
                        }
                        // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                        baoCaoSearchDoanhNghiep(valTuNgay, valDenNgay, valTieuDe, valDoanhNghiep, pagination.pageNumber).then(rs => {
                            setViewTableNBC(rs.data.currentElements, pagination.pageNumber);
                        }).catch(err => console.log(err))
                    }
                })
            } else {
                let view = `<tr>
                        <th>STT</th>
                        <th>Tiêu đề</th>
                        <th>Doanh Nghiêp</th>
                        <th>Tệp đính kèm</th>
                    </tr><tr><td colspan="4">Không có thông tin phù hợp</td></tr>`;
                tableView.html(view);
            }
        }).catch(err => {
            alterWarning("Xảy ra lỗi tìm kiếm", TIME_ALTER);
            console.log(err);
        })
    }
}

function findAllNBC() {
    baoCaoFindAll().then(rs => {
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
                    setViewTableNBC(rs.data.currentElements, 1);
                    return;
                }
                // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                baoCaoFindAll(pagination.pageNumber).then(rs => {
                    setViewTableNBC(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        console.log(err)
    })
}

function setViewTableNBC(list = [], pageNumber = 1) {
    let view = `<tr>
                <th>STT</th>
                <th>Tiêu đề</th>
                <th>Doanh nghiệp</th>
                <th class="tdk">Tệp đính kèm</th>
            </tr>`;
    view += list.map((data, index) => `<tr>
                <td>${10*(pageNumber - 1) + index + 1}</td>
                <td><a href="chi-tiet-bao-cao?id=${data.idBaoCao}">${viewField(data.tieuDe)}</a></td>
                <td>${viewField(data.nguoiDung !== null ? data.nguoiDung.doanhNghiep !== null ? data.nguoiDung.doanhNghiep.tenDoanhNghiep : null : null)}</td>
                <td class="tdk">${viewField(data.listTaiLieuDinhKem !== null ? setListFile(data.listTaiLieuDinhKem) : null)}</td>
            </tr>`).join("");
    let len = list.length;
    for (let i = len; i <= 9; i++) {
        view += `<tr>
                <td>${10*(pageNumber - 1) + i + 1}</td>
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