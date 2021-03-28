var selectDoanhNghiep, selectHuyen, selectLoaiKhoangSan;
$(function () {

    selectDoanhNghiep = $("#bimo1");
    selectHuyen = $("#bimo2");
    selectLoaiKhoangSan = $("#bimo3");
    //set view select
    setListKhoangSan(selectLoaiKhoangSan);
    setListDN(selectDoanhNghiep);
    districtFindByProvince(24).then(rs => {
        setListTinhHuyenXa(selectHuyen, rs);
        selectHuyen.prepend(`<option value="0">Tất cả</option>`);
        selectHuyen.val("0");
    }).catch(err => console.log(err));


    $('.js-example-basic-single').select2({ width: 'resolve' });
    $('.js-example-basic-multiple').select2({ width: 'resolve' });

})

function setListDoanhNghiepDSTB(listTB, pageNumber) {
    console.log(listTB);
    let view = `<tr>
                <th>STT</th>
                <th>Mã thiết bị</th>
                <th>Mỏ</th>
                <th>Vị trí lắp</th>
                <th>Trạng thái</th>
            </tr>`;
    listTB.map((data, index) => {
        if (data.doanhNghiep != null && data.khoangSan !== null) {
            let khoangSan = data.khoangSan;
            doanhNghiep = data.doanhNghiep;
            view += `<tr>
                <td>${10*(pageNumber - 1) + index + 1}</td>
                <td><a href="thong-tin-doanh-nghiep?id=${doanhNghiep.idDoanhNghiep}">${doanhNghiep.thongTinDoanhNghiep.name}</a></td>
                <td>${viewField(doanhNghiep.thongTinDoanhNghiep.address)}</td>
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
                    setListDoanhNghiepDSTB(rs.data.currentElements, 1);
                    return;
                }
                // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                moFindAll(pagination.pageNumber).then(rs => {
                    setListDoanhNghiepDSTB(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        console.log(err);
    })
    searchDSDN("",[0], 0);
}