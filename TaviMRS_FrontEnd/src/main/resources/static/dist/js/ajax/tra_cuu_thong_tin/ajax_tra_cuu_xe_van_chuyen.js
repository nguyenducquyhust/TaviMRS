var selectTrangThai, inputBienSoXe;

$(function () {
    inputBienSoXe = $("#bimo1");
    selectTrangThai = $("#bimo2");

    //set view select

    $('.js-example-basic-single').select2({width: 'resolve'});
    $('.js-example-basic-multiple').select2({width: 'resolve'});


    findAllXVC();

    $(".btn-primary").click(function () {
        let bienSoXe = $("#bimo1").val();
        let trangThai = $("#bimo2").val();
        if (bienSoXe === "" && trangThai == -1) {
            findAllXVC();
        } else {
            searchXVC(bienSoXe, trangThai);
        }
    })
})

function findAllXVC() {
    xeVanChuyenFindAll(1).then(rs => {
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
                    setListXeVanChuyenXVC(rs.data.currentElements, 1);
                    return;
                }
                xeVanChuyenFindAll(pagination.pageNumber).then(rs => {
                    setListXeVanChuyenXVC(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        console.log(err);
    })
}

function setListXeVanChuyenXVC(listXeVanChuyen, pageNumber) {
    console.log(listXeVanChuyen);
    let view = `<tr>
                <th>STT</th>
                <th>Biển số xe</th>
                <th>Doanh Nghiệp</th>
                <th>Khối lượng xe</th>
                <th>Khối lượng hàng</th>
                <th>Trạng Thái</th>
            </tr>`;
    listXeVanChuyen.map((data, index) => {
        if (data.doanhNghiep != null) {
            let doanhNghiep = data.doanhNghiep;

            view += `<tr>
                <td>${10 * (pageNumber - 1) + index + 1}</td>
                <td><a href="/thong-tin-xe-van-chuyen?id=${data.idXeVanChuyen}">${data.bienSoXe}</a></td>
                <td>${doanhNghiep.tenDoanhNghiep}</td>
                <td>${data.khoiLuongXe}</td>
                <td>${data.khoiLuongHang}</td>
                <td>${viewTrangThaiXeVanChuyen(data)}</td>

            </tr>`;
        }
    })
    let len = listXeVanChuyen.length;
    for (let i = len; i <= 9; i++) {
        view += `<tr>
                <td>${10 * (pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $(".buifmaoptb tbody").html(view);
}

function searchXVC(bienSoXe, trangThai) {
    xeVanChuyenSearch(bienSoXe, trangThai).then(rs => {
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
                        setListXeVanChuyenXVC(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    xeVanChuyenSearch(bienSoXe, trangThai, pagination.pageNumber).then(rs => {
                        setListXeVanChuyenXVC(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Biển số xe</th>
                <th>Doanh Nghiệp</th>
                <th>Khối lượng xe</th>
                <th>Khối lượng hàng</th>
                <th>Trạng thái</th>
            </tr><tr><td colspan="5">Không có thông tin phù hợp</td></tr>`;
            $(".table-hover").html(view);
        }
    }).catch(err => {
        console.log(err);
    })
}

