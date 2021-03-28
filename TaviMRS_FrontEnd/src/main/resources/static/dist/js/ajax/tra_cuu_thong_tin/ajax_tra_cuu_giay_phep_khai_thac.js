var selectMo, selectLoaiGiayPhep, selectTrangThai, selectThamQuyenCap, inputSoQuyetDinh;
$(function () {

    selectMo = $("#bimo1");
    selectLoaiGiayPhep = $("#bimo2");
    selectTrangThai = $("#bimo3");
    selectThamQuyenCap = $("#bimo4");
    inputSoQuyetDinh = $("#bimo5");

    //set view select

    setListMo(selectMo);
    setListLGP(selectLoaiGiayPhep);


    $('.js-example-basic-single').select2({width: 'resolve'});
    $('.js-example-basic-multiple').select2({width: 'resolve'});
    //khoang san
    //  setListHuyen("#bimo2");
    //dia_danh
    //end set view select

    findAllGPKT();
    $(".btn-primary").click(function () {
        let idMo  = $("#bimo1").val();
        let loaiGiayPhepId = $("#bimo2").val();
        let trangThai = $("#bimo3").val();
        let thamQuyenCap = $("#bimo4").val();
        let soQuyetDinh = $("#bimo5").val();


        if (idMo == 0 && loaiGiayPhepId == 0 && trangThai == -1 && thamQuyenCap == -1 && soQuyetDinh === "") {
            findAllGPKT();
        } else {
            searchGPKT(idMo, loaiGiayPhepId, trangThai, thamQuyenCap, soQuyetDinh);
        }
    })

})
function setListGiayPhepKhaiThacGPKT(listGiayPhepKhaiThac, pageNumber) {
    console.log(listGiayPhepKhaiThac);
    let view = `<tr>
                <th>STT</th>
                <th>Số quyết định</th>
                <th>Cơ quan cấp</th>
                <th>Trạng Thái</th>
                <th>Loại giấy phép</th>
                <th>Người cấp</th>
            </tr>`;
    listGiayPhepKhaiThac.map((data, index) => {
        let loaiGiayPhep = data.loaiGiayPhep;
        view += `<tr>
                <td>${10 * (pageNumber - 1) + index + 1}</td>
                <td><a href="thong-tin-giay-phep-khai-thac?id=${data.idGiayPhep}">${data.soQuyetDinh}</a></td>
                <td>${data.coQuanCap}</td>
                <td>${viewTrangThaiGiayPheoKhaiThac(data)}</td>
                <td>${loaiGiayPhep.loaiGiayPhep}</td>
                <td>${data.nguoiCap}</td>
            </tr>`;

    })
    let len = listGiayPhepKhaiThac.length;
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

function findAllGPKT() {
    giayPhepKhaiThacFindAll(1).then(rs => {
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
                    setListGiayPhepKhaiThacGPKT(rs.data.currentElements, 1);
                    return;
                }
                giayPhepKhaiThacFindAll(pagination.pageNumber).then(rs => {
                    setListGiayPhepKhaiThacGPKT(rs.data.currentElements, pagination.pageNumber);
                }).catch(err => console.log(err))
            }
        })
    }).catch(err => {
        console.log(err);
    })
}

function searchGPKT(idMo, loaiGiayPhepId, trangThai, thamQuyenCap, soQuyetDinh) {
    giayPhepKhaiThacSearch(idMo, loaiGiayPhepId, trangThai, thamQuyenCap, soQuyetDinh).then(rs => {
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
                        setListGiayPhepKhaiThacGPKT(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    giayPhepKhaiThacSearch(idMo, loaiGiayPhepId, trangThai, thamQuyenCap, soQuyetDinh, pagination.pageNumber).then(rs => {
                        setListGiayPhepKhaiThacGPKT(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Số Quyết Định</th>
                <th>Cơ Quan Cấp</th>
                <th>Trạng Thái</th>
                <th>Loại Giấy Phép</th>
                <th>Người Cấp</th>
            </tr><tr><td colspan="5">Không có thông tin phù hợp</td></tr>`;
            $(".table-hover").html(view);
        }
    }).catch(err => {
        console.log(err);
    })
}




