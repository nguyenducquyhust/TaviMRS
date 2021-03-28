var inputTenDoanhNghiep, inputDiaChi, inputTrangThaiPhanMem;

$(function () {

    inputTenDoanhNghiep = $("#bimo1");
    inputDiaChi = $("#bimo2");
    inputTrangThaiPhanMem = $("#bimo3");
    findAllTCDN();
    $(".btn-primary").click(function () {
        let tenDoanhNghiep = $("#bimo1").val();
        let diaChi = $("#bimo2").val();
        let trangThaiPhanMem = $("#bimo3").val();
        if (tenDoanhNghiep === "" && diaChi === "" && trangThaiPhanMem == -1) {
            findAllTCDN();
        } else {
            searchTCDN(tenDoanhNghiep, diaChi, trangThaiPhanMem);
        }
    })
})

function findAllTCDN() {
    doanhNghiepFindAll(1).then(rs => {
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
                    setListDoanhNghiepTCDN(rs.data.currentElements, 1);
                    return;
                }
                // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                doanhNghiepFindAll(pagination.pageNumber).then(rs => {
                    setListDoanhNghiepTCDN(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        console.log(err);
    })
}

function setListDoanhNghiepTCDN(listDoanhNghiep, pageNumber) {
    //chua lam: thongTinDoangNghiep, trangThaiHoat Dong
    let view = `<tr>
                <th>STT</th>
                <th>Tên Doanh nghiệp</th>
                <th>Địa chỉ</th>
                <th>Trạng Thái Phần Mềm</th>
            </tr>`;
    console.log(listDoanhNghiep);
    listDoanhNghiep.map((data, index) => {
        view += `<tr>
            <td>${10*(pageNumber - 1) + index + 1}</td>
            <td><a href="thong-tin-doanh-nghiep?id=${data.idDoanhNghiep}">${data.tenDoanhNghiep}</a></td>
            <td>${data.diaChi}</td>
            <td>${viewTrangThaiDoanhNghiep(data)}</td>
        </tr>`;
    })
    console.log(view);
    let len = listDoanhNghiep.length;
    for (let i = len; i <= 9; i++) {
        view += `<tr>
                <td>${10*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $(".buifmaoptb tbody").html(view);
}

function searchTCDN(tenDoanhNghiep, diaChi, trangThaiPhanMem) {
    doanhNghiepFindByTenAndDiaChiAndTrangThaiPhanMem(tenDoanhNghiep, diaChi, trangThaiPhanMem).then(rs => {
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
                        setListDoanhNghiepTCDN(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    doanhNghiepFindByTenAndDiaChiAndTrangThaiPhanMem(tenDoanhNghiep, diaChi, trangThaiPhanMem, pagination.pageNumber).then(rs => {
                        setListDoanhNghiepTCDN(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Tên Doanh nghiệp</th>
                <th>Địa chỉ</th>
                <th>Trạng Thái Phần Mềm</th>
            </tr><tr><td colspan="5">Không có thông tin phù hợp</td></tr>`;
            $(".buifmaoptb tbody").html(view);
        }
    }).catch(err => {
        console.log(err);
    })
}