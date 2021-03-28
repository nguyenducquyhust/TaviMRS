var inputTenDoanhNghiep, selectHuyen, selectLoaiKhoangSan;

$(function () {


    inputTenDoanhNghiep = $("#ten-doanh-nghiep");
    selectHuyen = $("#bimo2");
    selectLoaiKhoangSan = $("#bimo3");
    //set view select

    setListKhoangSan(selectLoaiKhoangSan);
    districtFindByProvince(24).then(rs => {
        setListTinhHuyenXa(selectHuyen, rs);
        selectHuyen.prepend(`<option value="0">Tất cả</option>`);
        selectHuyen.val("0");
    }).catch(err => console.log(err));
    $('.js-example-basic-single').select2({ width: 'resolve' });
    $('.js-example-basic-multiple').select2({ width: 'resolve' });
    //khoang san
    //  setListHuyen("#bimo2");
    //dia_danh
    //end set view select

    findAllDSDN();
    $(".btn-primary").click(function () {
        let ten = $("#bimo1").val();
        let listKhoangSanIds = $("#bimo3").val();
        listKhoangSanIds = listKhoangSanIds == null ? ["0"] : listKhoangSanIds;
        let huyenId = $("#bimo2").val();
        if (ten === "" && listKhoangSanIds[0] === "0" && huyenId === "0") {
            findAllDSDN();
        } else {
            searchDSDN(ten, listKhoangSanIds, huyenId);
        }
    })
})


function setListDoanhNghiepDSDN(listMo, pageNumber) {
    //chua lam: thongTinDoangNghiep, trangThaiHoat Dong
    let view = `<tr>
                <th>STT</th>
                <th>Tên Doanh nghiệp</th>
                <th>Địa chỉ</th>
                <th>Loại khoáng sản</th>
                <th>Trạng thái</th>
            </tr>`;
    listMo.map((data, index) => {
        if (data.doanhNghiep != null && data.khoangSan !== null) {
            let khoangSan = data.khoangSan;
            let doanhNghiep = data.doanhNghiep;
            view += `<tr>
                <td>${10*(pageNumber - 1) + index + 1}</td>
                <td><a href="thong-tin-doanh-nghiep?id=${doanhNghiep.idDoanhNghiep}">${doanhNghiep.tenDoanhNghiep}</a></td>
                <td>${viewField(doanhNghiep.diaChi)}</td>
                <td>${khoangSan.tenKhoangSan}</td>
                <td>${viewTrangThaiDoanhNghiep(doanhNghiep)}</td>
            </tr>`;
        }
    })
    let len = listMo.length;
    for (let i = len; i <= 9; i++) {
        view += `<tr>
                <td>${10*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $(".buifmaoptb tbody").html(view);
}

function findAllDSDN() {
    moFindAll().then(rs => {
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
                    setListDoanhNghiepDSDN(rs.data.currentElements, 1);
                    return;
                }
                // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                moFindAll(pagination.pageNumber).then(rs => {
                    setListDoanhNghiepDSDN(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        console.log(err);
    })
}

function searchDSDN(ten, listKhoangSanIds, huyenId) {
    moFindByKhoangSanAndThongTinDoanhNghiep(ten, listKhoangSanIds, huyenId).then(rs => {
        if (rs.result == "found") {
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
                        setListDoanhNghiepDSDN(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    moFindByKhoangSanAndThongTinDoanhNghiep(ten, listKhoangSanIds, huyenId, pagination.pageNumber).then(rs => {
                        setListDoanhNghiepDSDN(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Tên Doanh nghiệp</th>
                <th>Địa chỉ</th>
                <th>Loại khoáng sản</th>
                <th>Trạng thái</th>
            </tr><tr><td colspan="5">Không có thông tin phù hợp</td></tr>`;
            $(".buifmaoptb tbody").html(view);
        }
    }).catch(err => {
        console.log(err);
    })
}
