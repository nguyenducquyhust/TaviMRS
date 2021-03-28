var selectDoanhNghiep, selectTrangThai, inputTenNguoiDung;
$(function () {

    inputTenNguoiDung = $("#bimo1");
    selectDoanhNghiep = $("#bimo2");
    selectTrangThai = $("#bimo3");
    //set view select
    setListDN(selectDoanhNghiep);


    $('.js-example-basic-single').select2({width: 'resolve'});
    $('.js-example-basic-multiple').select2({width: 'resolve'});
    //khoang san
    //  setListHuyen("#bimo2");
    //dia_danh
    //end set view select
    findAllTCND();

    $(".btn-primary").click(function () {
        let tenDangNhap = $("#bimo1").val();
        let idDoanhNghiep = $("#bimo2").val();
        let trangThai = $("#bimo3").val();
        if (tenDangNhap === "" && idDoanhNghiep == 0 && trangThai == 0) {
            findAllTCND();
        } else {
            searchTCND(tenDangNhap, idDoanhNghiep, trangThai);
        }
    })
})


function findAllTCND() {
    nguoiDungFindAll(1).then(rs => {
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
                    setListNguoiDungTCDN(rs.data.currentElements, 1);
                    return;
                }
                nguoiDungFindAll(pagination.pageNumber).then(rs => {
                    setListNguoiDungTCDN(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        console.log(err);
    })
}

function setListNguoiDungTCDN(listNguoiDung, pageNumber) {
    console.log(listNguoiDung);
    let view = `<tr>
                <th>STT</th>
                <th>Tài khoản</th>
                <th>Doanh Nghiệp</th>
                <th>Chức vụ</th>
                <th>Trạng thái</th>
            </tr>`;
    listNguoiDung.map((data, index) => {
                let doanhNghiep = data.doanhNghiep;

            view += `<tr>
                <td>${10 * (pageNumber - 1) + index + 1}</td>
                <td><a href="/thong-tin-nguoi-dung?id=${data.idNguoiDung}">${data.tenDangNhap}</a></td>
                <td>${doanhNghiep.tenDoanhNghiep}</td>
                <td>${data.chucVu}</td>
                <td>${data.trangThai}</td>

            </tr>`;
    })
    let len = listNguoiDung.length;
    for (let i = len; i <= 9; i++) {
        view += `<tr>
                <td>${10 * (pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $(".buifmaoptb tbody").html(view);
}

function searchTCND(tenDangNhap, idDoanhNghiep, trangThai) {
    nguoiDungSearch(tenDangNhap, idDoanhNghiep, trangThai).then(rs => {
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
                        setListNguoiDungTCDN(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    nguoiDungSearch(tenDangNhap, idDoanhNghiep, trangThai, pagination.pageNumber).then(rs => {
                        setListNguoiDungTCDN(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Tài khoản</th>
                <th>Mỏ</th>
                <th>Chức vụ</th>
                <th>Trạng thái</th>
            </tr><tr><td colspan="5">Không có thông tin phù hợp</td></tr>`;
            $(".table-hover").html(view);
        }
    }).catch(err => {
        console.log(err);
    })
}