var selectCongTy, viewListData, typeView, btnTimKiem;
$(function () {

    selectCongTy = $("#bimo3");
    btnTimKiem = $("#btn-tim-kiem");
    viewListData = $("#view-data");
    typeView = $("#type-view");

    //set view select
    setListDoanhNghiep(selectCongTy);
    $('.js-example-basic-single').select2({ width: 'resolve' });
    $('.js-example-basic-multiple').select2({ width: 'resolve' });
    //select Cong Ty
    //end set view select
    findAllCamera();
    selectCongTy.change(function () {
        searchCamera();
    });
    clickViewType();
})

function findAllCamera() {
    let valTypeView = typeView.find(".active-type").attr("value");
    cameraFindAll(1, valTypeView).then(rs => {
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
                    setListCamera(rs.data.currentElements, valTypeView);
                    return;
                }
                // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                cameraFindAll(pagination.pageNumber, valTypeView).then(rs => {
                    setListCamera(rs.data.currentElements, valTypeView);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        alterDanger("Xảy ra lỗi máy chủ");
        console.log(err);
    })
}

function setListCamera(list, numberElement) {
    let numberCol = 12;
    switch (numberElement) {
        case "4":
            numberCol = 6;
            break;
        case "9":
            numberCol = 4;
            break;
        case "16":
            numberCol = 3;
            break;
    }
    let view = list.map(item => `<div class="col-md-${numberCol}">
                                    <iframe src="${item.thongSo}" frameborder="0"
                                            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                                            allowfullscreen class="view-camera"></iframe>
                                </div>`).join("");
    view = view.length > 0 ? view : "Không có dữ liệu phù hợp tìm kiếm";
    viewListData.html(view);
}

function searchCamera() {
    let valCongTy = selectCongTy.val();
    if (valCongTy == 0) {
        findAllCamera();
    } else {
        findCamera(valCongTy);
    }
}

function findCamera(valCongTy) {
    let valTypeView = typeView.find(".active-type").attr("value");
    cameraFindByDoanhNghiep(valCongTy,1, valTypeView).then(rs => {
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
                    setListCamera(rs.data.currentElements, valTypeView);
                    return;
                }
                // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                cameraFindByDoanhNghiep(valCongTy, pagination.pageNumber, valTypeView).then(rs => {
                    setListCamera(rs.data.currentElements, valTypeView);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        alterDanger("Xảy ra lỗi máy chủ");
        console.log(err);
    })
}

function clickViewType() {
    typeView.find(".size-view").click(function () {
        if (typeView.find(".active-type").attr("value") !== $(this).attr("value")) {
            typeView.find(".size-view").removeClass("active-type");
            $(this).addClass("active-type");
            selectCongTy.select2().trigger("change");
        }
    })
}