var selectmo, selectTrangThai, inputTenThietBi;
$(function () {

    selectmo = $("#bimo1");
    selectTrangThai = $("#bimo2");
    inputTenThietBi = $("#bimo3");
    //set view select
    setListMo(selectmo);

    $('.js-example-basic-single').select2({width: 'resolve'});
    $('.js-example-basic-multiple').select2({width: 'resolve'});

    findAllTCTB();

    $(".btn-primary").click(function () {
        let idMo = $("#bimo1").val();
        let trangThaiKetNoi = $("#bimo2").val();
        let maThietBi = $("#bimo3").val();
        if (idMo == 0 && trangThaiKetNoi == -1 && maThietBi === "") {
            findAllTCTB();
        } else {
            searchTCTB(idMo, trangThaiKetNoi, maThietBi);
        }
    })
})


function findAllTCTB() {
    thietBiFindAll(1).then(rs => {
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
                    setListThietBiTCTB(rs.data.currentElements, 1);
                    return;
                }
                thietBiFindAll(pagination.pageNumber).then(rs => {
                    setListThietBiTCTB(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        console.log(err);
    })
}

function setListThietBiTCTB(listThietBi, pageNumber) {
    console.log(listThietBi);
    let view = `<tr>
                <th>STT</th>
                <th>Mã thiết bị</th>
                <th>Mỏ</th>
                <th>Vị trí lắp</th>
                <th>Trạng thái</th>
            </tr>`;
    listThietBi.map((data, index) => {
        if (data.mo != null) {
            let mo = data.mo;

            view += `<tr>
                <td>${10 * (pageNumber - 1) + index + 1}</td>
                <td><a href="/thong-tin-thiet-bi?id=${data.idThietBi}">${data.maThietBi}</a></td>
                <td>${mo.diaChi}</td>
                <td>${data.viTriLap}</td>
                <td>${viewTrangThaiKetNoiThietBi(data)}</td>

            </tr>`;
        }
    })
    let len = listThietBi.length;
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

function searchTCTB(idMo, trangThaiKetNoi, maThietBi) {
    thietBiSearch(idMo, trangThaiKetNoi,maThietBi).then(rs => {
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
                        setListThietBiTCTB(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    thietBiSearch(idMo, trangThaiKetNoi, maThietBi, pagination.pageNumber).then(rs => {
                        setListThietBiTCTB(rs.data.currentElements, pagination.pageNumber);
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


