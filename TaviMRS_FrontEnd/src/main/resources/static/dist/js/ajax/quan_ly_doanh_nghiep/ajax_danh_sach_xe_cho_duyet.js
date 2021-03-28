var inputBienSoXe, selectDoanhNghiep;

$(function () {
    inputBienSoXe = $("#bimo1");
    selectDoanhNghiep = $("#bimo2");

    $('.js-example-basic-single').select2({ width: 'resolve' });
    $('.js-example-basic-multiple').select2({ width: 'resolve' });
    //end set view select
    findAllDSXCD();
    findAlDSDN(selectDoanhNghiep);
    $(".btn-primary").click(function () {
        let bienSoXe = $("#bimo1").val();
        let doanhNghiepId = $("#bimo2").val();
        console.log(bienSoXe);
        console.log(doanhNghiepId);
        if (bienSoXe === "" && doanhNghiepId == 0) {
            findAllDSXCD();
        } else {
            console.log("tim kiem");
            seachDSXCD( doanhNghiepId,bienSoXe);
        }
    })
})

function setListDSXCD(listDSXCD, pageNumber) {
    console.log(listDSXCD);
    let view = `<tr>
                <th>STT</th>
                <th>Biển số xe</th>
                <th>Doanh nghiệp</th>
                <th>Ngày khai báo</th>
                <th>Trạng thái</th>
            </tr>`;
    listDSXCD.map((data, index) => {
//.getFullYear(),data.thoiGianTao.getMonth(),data.thoiGianTao.getDay(), data.thoiGianTao.getHours(),data.thoiGianTao.getMinutes(), data.thoiGianTao.getSeconds()
        var date = new Date(data.thoiGianTao);
        console.log(data.thoiGianTao);
        var dateStr =
            ("00" +  date.getDate()).slice(-2) + "/" +
            ("00" + (date.getMonth() + 1)).slice(-2) + "/" +
            date.getFullYear() + " " +
            ("00" + date.getHours()).slice(-2) + ":" +
            ("00" + date.getMinutes()).slice(-2) + ":" +
            ("00" + date.getSeconds()).slice(-2);


        if (data.doanhNghiep != null ) {
            let doanhNghiep = data.doanhNghiep;

            view += `<tr>
                <td>${10*(pageNumber - 1) + index + 1}</td>
                <td><a href="/thong-tin-xe-van-chuyen?id=${data.idXeVanChuyen}">${data.bienSoXe}</a></td>
                <td>${doanhNghiep.tenDoanhNghiep}</td> 
                <td>${dateStr}</td>
                <td>${viewTrangThaiXeChoDuyet(data)}</td>
             
            </tr>`;
        }
    })
    //map
    let len = listDSXCD.length;
    for (let i = len; i <= 9; i++) {
        view += `<tr>
                <td>${10*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $(".table-hover").html(view);
}

function findAllDSXCD() {
    xcdFindAll().then(rs => {
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
                    setListDSXCD(rs.data.currentElements, 1);
                    return;
                }
                // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                xcdFindAll(pagination.pageNumber).then(rs => {
                    setListDSXCD(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    })
    seachDSXCD(0,"");
}

function seachDSXCD(doanhNghiepId, bienSoXe) {
    xcdSearch(doanhNghiepId, bienSoXe).then(rs => {
        console.log("search" + rs);
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
                        setListDSXCD(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    xcdSearch(doanhNghiepId, bienSoXe, pagination.pageNumber).then(rs => {
                        setListDSXCD(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Biển số xe</th>
                <th>Doanh nghiệp</th>
                <th>Ngày khai báo</th>
                <th>Trạng thái</th>
            </tr><tr><td colspan="5">Không có thông tin phù hợp</td></tr>`;
            $(".table-hover").html(view);
        }
    }).catch(err => {
        console.log(err);
    })
}

function findAlDSDN(selector) {
    doanhNghiepFindAll(1, 9999).then(rs => {
        console.log(rs)
        if (rs.result === "found") {
            rs = rs.data.currentElements;
            let view = ` <option value="0">Tat ca</option>`;
            view += rs.map(item => `<option value="${item.idDoanhNghiep}">${item.tenDoanhNghiep}</option>`).join('');
            selector.html(view);
        }
    }).catch(err => {
        console.log(err)
    })
}


// End Aja
