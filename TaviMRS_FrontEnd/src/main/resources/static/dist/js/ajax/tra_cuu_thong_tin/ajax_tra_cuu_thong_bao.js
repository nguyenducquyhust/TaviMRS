var selectDoanhNghiep, inputTieuDe, inputTuNgay, inputDenNgay;
$(function () {

    inputTuNgay = $("#bimo1");
    inputDenNgay = $("#bimo2");
    inputTieuDe = $("#bimo3");
    //set view select
    setListDN(selectDoanhNghiep);


    $('.js-example-basic-single').select2({width: 'resolve'});
    $('.js-example-basic-multiple').select2({width: 'resolve'});
    //khoang san
    //  setListHuyen("#bimo2");
    //dia_danh
    //end set view select
    findAllTCTB();

    $(".btn-primary").click(function () {
        let ngayDau = $("#bimo1").val();
        let ngayCuoi = $("#bimo2").val();
        let tieuDe = $("#bimo3").val();
        if (ngayDau === null && ngayCuoi === null && tieuDe === "") {
            findAllTCTB();
        } else {
            searchTCTB(ngayDau, ngayCuoi, tieuDe);
        }
    })
})

function findAllTCTB() {
    thongBaoFindAll(1).then(rs => {
        console.log(rs);
        $('#pagination').pagination({
            dataSource: function (done) {
                let result = [];
                for (let i = 1; i <= rs.data.totalPages; i++) result.push(1);
                console.log(result);
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
                    setListThongBaoTCTB(rs.data.currentElements, 1);
                    return;
                }
                thongBaoFindAll(pagination.pageNumber).then(rs => {
                    setListThongBaoTCTB(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        console.log(err);
    })
}

function setListThongBaoTCTB(listThongBao, pageNumber) {
    console.log(listThongBao);
    let view = `<tr>
                <th>STT</th>
                <th>Tiêu đề</th>
                <th>Số người nhận</th>
                <th>Ngày gửi</th>
            </tr>`;
    listThongBao.map((data, index) => {

        view += `<tr>
                <td>${10 * (pageNumber - 1) + index + 1}</td>
                <td><a href="/thong-tin-thiet-bi?id=${data.idThongBao}">${data.tieuDe}</a></td>
                <td>${data.soLuongDonViNhan}</td>
                <td>${data.thoiGianGui}</td>
            </tr>`;
    })
    let len = listThongBao.length;
    for (let i = len; i <= 9; i++) {
        view += `<tr>
                <td>${10 * (pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $(".buifmaoptb tbody").html(view);
}


function searchTCTB(ngayDau, ngayCuoi, tieuDe) {
    let {check, valTuNgay, valDenNgay} = checkThoiGianTHDN();
    if (check) {

        ngayDau = ngayDau === '' ? null : convertDateISO(ngayDau).toISOString();
        ngayCuoi = ngayCuoi === '' ? null : convertDateISO(ngayCuoi).toISOString();
        thongBaoFindByNgayAndTieuDe(ngayDau, ngayCuoi, tieuDe).then(rs => {
            if (rs.result == "found") {
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
                            setListThongBaoTCTB(rs.data.currentElements, 1);
                            return;
                        }
                        // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                        thongBaoFindByNgayAndTieuDe(ngayDau, ngayCuoi, tieuDe, pagination.pageNumber).then(rs => {
                            setListThongBaoTCTB(rs.data.currentElements, pagination.pageNumber);
                        }).catch(err => console.log(err))
                    }
                })
            } else {
                let view = `<tr>
                <th>STT</th>
                <th>Mã thiết bị</th>
                <th>Mỏ</th>
                <th>Vị trí lắp</th>
                <th>Trạng thái</th>
            </tr><tr><td colspan="5">Không có thông tin phù hợp</td></tr>`;
                $(".table-hover").html(view);
            }
        }).catch(err => {
            console.log(err);
        })

    }
}


function checkThoiGianTHDN() {
    let rs = true;
    let {check: checkTuNgay, val: valTuNgay} = checkNgayTKDSDN(inputTuNgay);
    let {check: checkDenNgay, val: valDenNgay} = checkNgayTKDSDN(inputDenNgay);
    let selectorDenNgay = inputDenNgay.parents(".form-group");
    if (valTuNgay !== "" && valDenNgay !== "" && !compareDate(valTuNgay, valDenNgay)) {
        rs = false;
        viewError(selectorDenNgay, "Thời gian giới hạn phải lớn hơn!");
    } else hiddenError(selectorDenNgay);
    return {check: rs, valTuNgay: valTuNgay, valDenNgay: valDenNgay};
}

function checkNgayTKDSDN(selectorNgay) {
    let rs = false;
    let val = selectorNgay.val();
    let selector = selectorNgay.parents(".form-group");
    if (val === '' || regexDate(val)) {
        rs = true;
        hiddenError(selector);
    } else viewError(selector, "Chưa đúng định dạng ngày");
    return {check: rs, val: val};
}

// function searchTCTB(ngayDau, ngayCuoi, tieuDe) {
//     thongBaoFindByNgayAndTieuDe(ngayDau, ngayCuoi, tieuDe).then(rs => {
//         if (rs.result == "found") {
//             $('#pagination').pagination({
//                 dataSource: function (done) {
//                     let result = [];
//                     for (let i = 1; i <= rs.data.totalPages; i++) result.push(1);
//                     done(result);
//                 },
//                 locator: 'items',
//                 totalNumber: rs.data.totalPages,
//                 pageSize: 1,
//                 showPageNumbers: true,
//                 showPrevious: true,
//                 showNext: true,
//                 // showNavigator: true,
//                 showFirstOnEllipsisShow: true,
//                 showLastOnEllipsisShow: true,
//                 callback: function (response, pagination) {
//                     if (pagination.pageNumber == 1) {
//                         setListThongBaoTCTB(rs.data.currentElements, 1);
//                         return;
//                     }
//                     // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
//                     thongBaoFindByNgayAndTieuDe(ngayDau, ngayCuoi, tieuDe, pagination.pageNumber).then(rs => {
//                         setListThongBaoTCTB(rs.data.currentElements, pagination.pageNumber);
//                     }).catch(err => console.log(err))
//                 }
//             })
//         } else {
//             let view = `<tr>
//                 <th>STT</th>
//                 <th>Mã thiết bị</th>
//                 <th>Mỏ</th>
//                 <th>Vị trí lắp</th>
//                 <th>Trạng thái</th>
//             </tr><tr><td colspan="5">Không có thông tin phù hợp</td></tr>`;
//             $(".table-hover").html(view);
//         }
//     }).catch(err => {
//         console.log(err);
//     })
// }
