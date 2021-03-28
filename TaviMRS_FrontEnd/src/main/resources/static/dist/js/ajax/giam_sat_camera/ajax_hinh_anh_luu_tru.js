var inputTuNgay, inputDenNgay, selectCongTy, viewListData, typeSort, btnTimKiem;
$(function () {

    inputTuNgay = $("#bimo1");
    inputDenNgay = $("#bimo2");
    selectCongTy = $("#bimo3");
    btnTimKiem = $("#btn-tim-kiem");
    viewListData = $("#view-data");
    typeSort = $("#type-sort");

    //set view select
    setListDoanhNghiep(selectCongTy);
    $('.js-example-basic-single').select2({ width: 'resolve' });
    $('.js-example-basic-multiple').select2({ width: 'resolve' });
    //select Cong Ty
    //end set view select
    findAllHinhAnhLuuTru();
    clickTimKiemHALT();
    clickSortType();
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

function findHinhAnhLuuTru(valTuNgay, valDenNgay, valCongTy) {
    let checkSort = typeSort.find(".active-sort").attr("value");
    valTuNgay = valTuNgay === '' ? null : convertDateISO(valTuNgay).toISOString();
    valDenNgay = valDenNgay === '' ? null : convertDateISO(valDenNgay).toISOString();
    hinhAnhLuuTruSearchDoanhNghiepSort(valCongTy, valTuNgay, valDenNgay, checkSort).then(rs => {
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
                        setListHinhAnhLuuTru(rs.data.currentElements);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    hinhAnhLuuTruSearchDoanhNghiepSort(valCongTy, valTuNgay, valDenNgay, checkSort, pagination.pageNumber).then(rs => {
                        setListHinhAnhLuuTru(rs.data.currentElements);
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

function findAllHinhAnhLuuTru() {
    let check = typeSort.find(".active-sort").attr("value");
    hinhAnhLuuTruFindAllSort(check).then(rs => {
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
                    setListHinhAnhLuuTru(rs.data.currentElements);
                    return;
                }
                // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                hinhAnhLuuTruFindAllSort(check ,pagination.pageNumber).then(rs => {
                    setListHinhAnhLuuTru(rs.data.currentElements);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        alterDanger("Xảy ra lỗi máy chủ");
        console.log(err);
    })
}

function setListHinhAnhLuuTru(list) {
    let view = list.map(item => `<div class="cmr-img">
                                <a href="chi-tiet-hinh-anh-luu-tru?id=${item.idHinhAnh}"><img src="${viewSrc(item.hinhAnh)}"></a>
                            </div>`).join("");
    view = view.length > 0 ? view : "Không có dữ liệu phù hợp tìm kiếm";
    viewListData.html(view);
}

function clickTimKiemHALT() {
    btnTimKiem.click(function () {
        let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
        let valCongTy = selectCongTy.val();
        if(valTuNgay === "" && valDenNgay === "" && valCongTy == "0") {
            findAllHinhAnhLuuTru();
        } else {
            if (check) {
                findHinhAnhLuuTru(valTuNgay, valDenNgay, valCongTy);
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