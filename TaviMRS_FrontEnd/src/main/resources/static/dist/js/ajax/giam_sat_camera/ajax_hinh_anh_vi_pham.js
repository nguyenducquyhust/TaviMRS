var inputTuNgay, inputDenNgay, selectCongTy, selectPhanLoai, viewListData, typeSort, btnTimKiem;
$(function () {
    inputTuNgay = $("#bimo1");
    inputDenNgay = $("#bimo2");
    selectCongTy = $("#bimo3");
    selectPhanLoai = $("#bimo4");
    btnTimKiem = $("#btn-tim-kiem");
    viewListData = $("#view-data");
    typeSort = $("#type-sort");

    //set view select
    setListLoaiViPham(selectPhanLoai);
    setListDoanhNghiep(selectCongTy);
    $('.js-example-basic-single').select2({ width: 'resolve' });
    $('.js-example-basic-multiple').select2({ width: 'resolve' });
    //select Cong Ty
    //end set view select
    findAllHinhAnhViPham();
    clickTimKiemHAVP();
    clickSortType();
})

function findAllHinhAnhViPham() {
    let check = typeSort.find(".active-sort").attr("value");
    hinhAnhViPhamFindAllSort(check).then(rs => {
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
                    setListHinhAnhViPham(rs.data.currentElements);
                    return;
                }
                // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                hinhAnhViPhamFindAllSort(check ,pagination.pageNumber).then(rs => {
                    setListHinhAnhViPham(rs.data.currentElements);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        alterDanger("Xảy ra lỗi máy chủ");
        console.log(err);
    })
}

function findHinhAnhViPham(valTuNgay, valDenNgay, valCongTy, valLoaiViPham) {
    let checkSort = typeSort.find(".active-sort").attr("value");
    valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
    valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
    hinhAnhViPhamSearchDoanhNghiepSort(valCongTy, valLoaiViPham, valTuNgay, valDenNgay, checkSort).then(rs => {
        if(rs.result === "found") {
            $('#pagination').pagination({
                dataSource: [0],
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
                        setListHinhAnhViPham(rs.data.currentElements);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    hinhAnhViPhamSearchDoanhNghiepSort(valCongTy, valLoaiViPham, valTuNgay, valDenNgay, checkSort, pagination.pageNumber).then(rs => {
                        setListHinhAnhViPham(rs.data.currentElements);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            viewListData.html("Không có dữ liệu phù hợp tìm kiếm");
        }
    }).catch(err => {
        alterDanger("Xảy ra lỗi tìm kiếm", TIME_ALTER);
        console.log(err);
    })
}

function setListHinhAnhViPham(list) {
    let view = list.map(item => `<div class="cmr-img">
                                <a href="chi-tiet-vi-pham?id=${item.viPham !== null ? item.viPham.idViPham : ""}"><img src="${viewSrc(item.hinhAnhLuuTru !== null ? item.hinhAnhLuuTru.hinhAnh : "")}"></a>
                            </div>`).join("");
    view = view.length > 0 ? view : "Không có dữ liệu phù hợp tìm kiếm";
    viewListData.html(view);
}

function clickTimKiemHAVP() {
    btnTimKiem.click(function () {
        let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
        let valCongTy = selectCongTy.val();
        let valLoaiViPham = selectPhanLoai.val();
        if(valTuNgay === "" && valDenNgay === "" && valCongTy == "0") {
            findAllHinhAnhViPham();
        } else {
            if (check) {
                findHinhAnhViPham(valTuNgay, valDenNgay, valCongTy, valLoaiViPham);
            }
        }
    })
}

function clickSortType() {
    typeSort.find("i").click(function () {
        if (typeSort.find(".active-sort").attr("value") !== $(this).attr("value")) {
            typeSort.find("i").removeClass("active-sort");
            $(this).addClass("active-sort");
            btnTimKiem.trigger("click");
        }
    })
}

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