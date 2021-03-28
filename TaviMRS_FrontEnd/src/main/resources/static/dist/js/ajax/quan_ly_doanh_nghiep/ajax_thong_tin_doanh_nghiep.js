var inputTenDN, inputMaDN, inputNguoiDaiDien, selectNganhNghe, inputVonDieuLe, inputEmail, inputDienThoai, inputDiaChi, selectTinh, selectHuyen, selectXa, 
inputGiayPhep, linkGiayPhep, radioTrangThaiHoatDong, inputKhoa, inputMaKetNoi, inputGhiChu, btnSaveDoanhNghiep;
var doanhNghiep = {};
var thongTinDoanhNghiep = {};
var arrXa = {};
var address = {};
var doanhNghiepId = 0;
var trangThaiPhanMemCu = null;
$(function () {
    $('#dong-nganh-nghe').click(function () {
        $(".fa-info-circle").trigger("click");
    })
    clickInforNganhNghe();
    //end function view

    inputTenDN = $("#ten-doanh-nghiep");
    inputMaDN = $("#ma-doanh-nghiep");
    inputNguoiDaiDien = $("#nguoi-dai-dien");
    selectNganhNghe = $("#select4");
    inputVonDieuLe = $("#von-dieu-le");
    inputEmail = $("#email");
    inputDienThoai = $("#so-dien-thoai");
    inputDiaChi = $("#dia-chi");
    selectTinh = $("#select1");
    selectHuyen = $("#select2");
    selectXa = $("#select3");
    inputGiayPhep = $("#giay-phep-kinh-doanh");
    linkGiayPhep = $("#link-giay-phep");
    radioTrangThaiHoatDong= $("#trang-thai-phan-mem");
    inputKhoa = $("#khoa");
    inputMaKetNoi = $("#ma-ket-noi");
    inputGhiChu = $("#ghi-chu");
    btnSaveDoanhNghiep = $("#save-doanh-nghiep");

    //set view select
    provinceFindByCountry(1).then(rs => {
        setListTinhHuyenXa(selectTinh, rs);
        selectTinh.change(function () {
            setSelectHuyen();
        })
        selectHuyen.change(function () {
            setSelectXa();
        })
        if(doanhNghiepId == 0) setSelectHuyen(); //set select Huyen Xa mac dinh
    }).catch(err => console.log(err));
    //nganh_nghe
    $('.select2').select2({ width: 'resolve' });
    //end set view select

    let href = new URL(window.location.href);
    doanhNghiepId = href.searchParams.get("id");
    $(".page-link a").attr("href",`thong-tin-nguoi-dung?id=0&doanh-nghiep-id=${doanhNghiepId}`);

    if (doanhNghiepId == 0 ){
        console.log("Thêm mới");
        //dia_gioi
        setListCarrer(selectNganhNghe);
    } else {
        changeDisabledTTDN(true);
        //dia_gioi load => change
        setListCarrer(selectNganhNghe).then(() => {
            //Thông tin doanh nghiệp
            setDoanhNghiepTTDN(doanhNghiepId);
        }).catch(err => console.log(err));
        //Thông tin theo doanh nghiệp
        searchMoTTDN(doanhNghiepId);
        searchThietBiTTDN(doanhNghiepId);
        searchXeVanChuyenTTDN(doanhNghiepId);
        searchGiayPhepKhaiThacTTDN(doanhNghiepId);
        searchNguoiDungTTDN(doanhNghiepId);
        searchThuTucTTDN(doanhNghiepId);
        // checkModifileTTDN();
    }
    saveDoanhNghiep();
    saveCauHinh();
})

//set thong tin doanh nghiep
function setDoanhNghiepTTDN(doanhNghiepId) {
    doanhNghiepFindById(doanhNghiepId).then(rs => {
        if (rs.result === "found") {
            doanhNghiep = rs.data;
            trangThaiPhanMemCu = doanhNghiep.trangThaiPhanMem;
            console.log(trangThaiPhanMemCu);
            $(`#trang-thai-${trangThaiPhanMemCu}`).prop( "checked", true );
            inputKhoa.val(viewField(doanhNghiep.khoa));
            inputMaKetNoi.val(viewField(doanhNghiep.maKetNoi));
            inputGhiChu.val(viewField(doanhNghiep.ghiChu));
            thongTinDoanhNghiepFindById(doanhNghiep.thongTinDoanhNghiepId).then(result => {
                if(result.message === "found") {
                    result = result.data;
                    thongTinDoanhNghiep = result;
                    address = result.address;
                    inputTenDN.val(viewField(result.name));
                    inputMaDN.val(viewField(result.code));
                    inputNguoiDaiDien.val(viewField(result.representative));
                    // selectNganhNghe.val(viewField(result.businessLicense));
                    inputVonDieuLe.val(viewField(result.charterCapital));
                    inputEmail.val(viewField(result.eportal));
                    inputDienThoai.val(viewField(result.phoneNumber));
                    if(address !== null) {
                        inputDiaChi.val(address.location);
                        if (address.province !== null) {
                            selectTinh.val(address.province.id);
                            districtFindByProvince(selectTinh.val()).then(rsHuyen => {
                                setListTinhHuyenXa(selectHuyen, rsHuyen);
                                if (address.district !== null) selectHuyen.val(address.district.id);
                                communeFindByDistrict(selectHuyen.val()).then(rsXa => {
                                    setListTinhHuyenXa(selectXa, rsXa);
                                    arrXa = rsXa.data;
                                    if (address.commune !== null) selectXa.val(address.commune.id);
                                }).catch(err => console.log(err));
                            }).catch(err => console.log(err));
                            //update doanh nghiep
                            doanhNghiep.tenDoanhNghiep = thongTinDoanhNghiep.name;
                            doanhNghiep.diaChi = address.location;
                            doanhNghiep.xaId = address.commune.id;
                            doanhNghiep.huyenId = address.district.id;
                            updateDoanhNghiep(doanhNghiep).then(rs => {
                                console.log(rs);
                            }).catch(err => console.log(err));
                        }
                    }
                    setSelectCarrer(selectNganhNghe,result.id);
                    linkGiayPhep.attr("href",result.businessLicense);
                } else {
                    window.location.href = "danh-sach-doanh-nghiep";
                }
            }).catch(err => {
                console.log(err);
                window.location.href = "danh-sach-doanh-nghiep";
            })
        } else {
            window.location.href = "danh-sach-doanh-nghiep";
        }
    }).catch(err => {
        console.log(err);
        // window.location.href = "danh-sach-doanh-nghiep";
    })
}
//end set thong tin doanh nghiep

//set list mo
function searchMoTTDN(doanhNghiepId) {
    moFindByDoanhNghiep(doanhNghiepId, 1, 5).then(rs => {
        if (rs.result == "found") {
            $('#pagination-mo').pagination({
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
                        setListMoTTDN(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    moFindByDoanhNghiep(doanhNghiepId, pagination.pageNumber, 5).then(rs => {
                        setListMoTTDN(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                            <th>STT</th>
                            <th>Địa chỉ</th>
                            <th>Trữ lượng còn lại</th>
                            <th>Trữ lượng khai thác năm</th>
                            <th>Trữ lượng mỏ</th>
                            <th>Loại Khoáng Sản</th>
                            <th>Trạng Thái</th>
                        </tr><td colspan="7">Không có thông tin phù hợp</td></tr>`;
            $("#tapMo tbody").html(view);
        }
    }).catch(err => console.log(err))
}
function setListMoTTDN(list, pageNumber) {
    //chua lam: thongTinDoangNghiep, trangThaiHoat Dong
    let view = `<tr>
                    <th>STT</th>
                    <th>Địa chỉ</th>
                    <th>Trữ lượng còn lại</th>
                    <th>Trữ lượng khai thác năm</th>
                    <th>Trữ lượng mỏ</th>
                    <th>Loại Khoáng Sản</th>
                    <th>Trạng Thái</th>
                </tr>`;
    list.map((data, index) => {
        view += `<tr>
                <td>${5*(pageNumber - 1) + index + 1}</td>
                <td>${viewField(data.diaChi)}</td>
                <td>${viewField(data.truLuongConLai)}</td>
                <td>${viewField(data.truLuongKhaiThacNam)}</td>
                <td>${viewField(data.truLuongConLai)}</td>
                <td>${data.khoangSan !== null ? data.khoangSan.tenKhoangSan: ""}</td>
                <td>${viewTrangThaiMo(data)}</td>
            </tr>`;
    })
    let len = list.length;
    for (let i = len; i <= 4; i++) {
        view += `<tr>
                <td>${5*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $("#tapMo tbody").html(view);
}
//end set list mo

//set list thiet bi
function searchThietBiTTDN(doanhNghiepId) {
    thietBiFindByDoanhNghiep(doanhNghiepId, 1, 5).then(rs => {
        if (rs.result == "found") {
            $('#pagination-thiet-bi').pagination({
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
                        setListThietBiTTDN(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    thietBiFindByDoanhNghiep(doanhNghiepId, pagination.pageNumber, 5).then(rs => {
                        setListThietBiTTDN(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Mã Thiết bị</th>
                <th>Tên thiết bị</th>
                <th>Vị trí</th>
                <th>Mục Đích sử dụng</th>
                <th>Mỏ</th>
                <th>Trạng Thái</th>
                </tr>
                <tr><td colspan="7">Không có thông tin phù hợp</td></tr>`;
            $("#tapThietBi tbody").html(view);
        }
    }).catch(err => console.log(err))
}
function setListThietBiTTDN(list, pageNumber) {
    //chua lam: thongTinDoangNghiep, trangThaiHoat Dong
    let view = `<tr>
                <th>STT</th>
                <th>Mã Thiết bị</th>
                <th>Tên thiết bị</th>
                <th>Vị trí</th>
                <th>Mục Đích sử dụng</th>
                <th>Mỏ</th>
                <th>Trạng Thái</th>
            </tr>`;
    list.map((data, index) => {
        view += `<tr>
                <td>${5*(pageNumber - 1) + index + 1}</td>
                <td><a href="thong-tin-thiet-bi?id=${data.idThietBi}">${viewField(data.maThietBi)}</a></td>
                <td>${viewField(data.tenThietBi)}</td>
                <td>${viewField(data.viTriLap)}</td>
                <td>${data.mo !== null ? data.mo.diaChi: ""}</td>
                <td>${viewField(data.moTa)}</td>
                <td>${viewTrangThaiKetNoiThietBi(data)}</td>
            </tr>`;
    })
    let len = list.length;
    for (let i = len; i <= 4; i++) {
        view += `<tr>
                <td>${5*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $("#tapThietBi tbody").html(view);
}
//end set list thiet bi

//set list xe van chuyen
function searchXeVanChuyenTTDN(doanhNghiepId) {
    xeVanChuyenFindByDoanhNghiep(doanhNghiepId, 1, 5).then(rs => {
        if (rs.result == "found") {
            $('#pagination-xe-van-chuyen').pagination({
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
                        setListXeVanChuyenTTDN(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    xeVanChuyenFindByDoanhNghiep(doanhNghiepId, pagination.pageNumber, 5).then(rs => {
                        setListXeVanChuyenTTDN(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Biển số xe</th>
                <th>Khối lượng xe</th>
                <th>Khối lượng hàng</th>
                <th>Trạng thái</th>
                <th>Đăng kiểm</th>
            </tr><tr><td colspan="6">Không có thông tin phù hợp</td></tr>`;
            $("#tapXeVanChuyen tbody").html(view);
        }
    }).catch(err => console.log(err))
}
function setListXeVanChuyenTTDN(list, pageNumber) {
    //chua lam: thongTinDoangNghiep, trangThaiHoat Dong
    let view = `<tr>
                <th>STT</th>
                <th>Biển số xe</th>
                <th>Khối lượng xe</th>
                <th>Khối lượng hàng</th>
                <th>Trạng thái</th>
                <th>Đăng kiểm</th>
            </tr>`;
    list.map((data, index) => {
        view += `<tr>
                <td>${5*(pageNumber - 1) + index + 1}</td>
                <td><a href="thong-tin-xe-van-chuyen?id=${data.idXeVanChuyen}">${viewField(data.bienSoXe)}</a></td>
                <td>${viewField(data.khoiLuongBanThan)}</td>
                <td>${viewField(data.khoiLuongHang)}</td>
                <td>${viewTrangThaiXeVanChuyen(data)}</td>
                <td><a href="${data.giayDangKiem}"><i class="far fa-file-pdf" aria-hidden="true"></i></a></td>
            </tr>`;
    })
    let len = list.length;
    for (let i = len; i <= 4; i++) {
        view += `<tr>
                <td>${5*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $("#tapXeVanChuyen tbody").html(view);
}
//end set list xe van chuyen

//set list giay phep khai thac
function searchGiayPhepKhaiThacTTDN(doanhNghiepId) {
    giayPhepKhaiThacFindByDoanhNghiep(doanhNghiepId, 1, 5).then(rs => {
        if (rs.result == "found") {
            $('#pagination-giay-phep').pagination({
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
                        setListGiayPhepKhaiThacTTDN(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                   giayPhepKhaiThacFindByDoanhNghiep(doanhNghiepId, pagination.pageNumber, 5).then(rs => {
                        setListGiayPhepKhaiThacTTDN(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Số quyết định</th>
                <th>Cơ quan ban hành</th>
                <th>Người ký</th>
                <th>Diện tích/ trữ lượng</th>
                <th>Mỏ</th>
                <th>Đính kèm</th>
            </tr>
            <tr><td colspan="7">Không có thông tin phù hợp</td></tr>`;
            $("#tapGiayPhep tbody").html(view);
        }
    }).catch(err => console.log(err))
}
function setListGiayPhepKhaiThacTTDN(list, pageNumber) {
    //chua lam: thongTinDoangNghiep, trangThaiHoat Dong
    let view = `<tr>
                <th>STT</th>
                <th>Số quyết định</th>
                <th>Cơ quan ban hành</th>
                <th>Người ký</th>
                <th>Diện tích/ trữ lượng</th>
                <th>Mỏ</th>
                <th>Đính kèm</th>
            </tr>`;
    list.map((data, index) => {
        view += `<tr>
                <td>${5*(pageNumber - 1) + index + 1}</td>
                <td><a href="thong-tin-giay-phep-khai-thac?id=${data.idGiayPhep}">${viewField(data.soQuyetDinh)}</a></td>
                <td>${viewField(data.coQuanCap)}</td>
                <td>${viewField(data.nguoiKy)}</td>
                <td>${data.dienTichKhaiThac/data.truLuongKhoangSan}</td>
                <td>${data.mo !== null ? data.mo.diaChi : ""}</td>
                <td><a href="${data.giayPhep}"><i class="far fa-file-pdf"></i></a></td>
            </tr>`;
    })
    let len = list.length;
    for (let i = len; i <= 4; i++) {
        view += `<tr>
                <td>${5*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $("#tapGiayPhep tbody").html(view);
}
//end set list giay phep khai thac

//set list nguoi dung
function searchNguoiDungTTDN(doanhNghiepId) {
    nguoiDungFindByDoanhNghiep(doanhNghiepId, 1, 5).then(rs => {
        if (rs.result == "found") {
            $('#pagination-tai-khoan').pagination({
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
                        setListNguoiDungTTDN(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    nguoiDungFindByDoanhNghiep(doanhNghiepId, pagination.pageNumber, 5).then(rs => {
                        setListNguoiDungTTDN(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Tên đăng nhập</th>
                <th>Chức vụ</th>
                <th>Điện thoại</th>
                <th>Mỏ</th>
                <th>Trạng thái</th>
            </tr>
            <tr><td colspan="6">Không có thông tin phù hợp</td></tr>`;
            $("#tapTaiKhoan tbody").html(view);
        }
    }).catch(err => console.log(err))
}
function setListNguoiDungTTDN(list, pageNumber) {
    //chua lam: thongTinDoangNghiep, trangThaiHoat Dong
    let view = `<tr>
                <th>STT</th>
                <th>Tên đăng nhập</th>
                <th>Chức vụ</th>
                <th>Điện thoại</th>
                <th>Trạng thái</th>
            </tr>`;
    list.map((data, index) => {
        view += `<tr>
                <td>${5*(pageNumber - 1) + index + 1}</td>
                <td><a href="thong-tin-nguoi-dung?id=${data.idNguoiDung}">${viewField(data.tenDangNhap)}</a></td>
                <td>${viewField(data.chucVu)}</td>
                <td>${viewField(data.soDienThoai)}</td>
                <td>${viewTrangThaiNguoiDung(data)}</td>
            </tr>`;
    })
    let len = list.length;
    for (let i = len; i <= 4; i++) {
        view += `<tr>
                <td>${5*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $("#tapTaiKhoan tbody").html(view);
}
//end set list nguoi dung

//set list thu tuc
function searchThuTucTTDN(doanhNghiepId) {
    thuTucFindByDoanhNghiep(doanhNghiepId, 1, 5).then(rs => {
        if (rs.result == "found") {
            $('#pagination-thu-tuc').pagination({
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
                        setListThuTucTTDN(rs.data.currentElements, 1);
                        return;
                    }
                    // console.log(pagination.pageNumber); // khi click sẽ đọc ra số trang click
                    thuTucFindByDoanhNghiep(doanhNghiepId, pagination.pageNumber, 5).then(rs => {
                        setListThuTucTTDN(rs.data.currentElements, pagination.pageNumber);
                    }).catch(err => console.log(err))
                }
            })
        } else {
            let view = `<tr>
                <th>STT</th>
                <th>Tên thủ tục hành chính</th>
                <th>Hiệu lực đến</th>
                <th>Trạng thái</th>
            </tr>
            <tr><td colspan="4">Không có thông tin phù hợp</td></tr>`;
            $("#tapThuTuc tbody").html(view);
        }
    }).catch(err => console.log(err))
}
function setListThuTucTTDN(list, pageNumber) {
    //chua lam: thongTinDoangNghiep, trangThaiHoat Dong
    let view = `<tr>
                <th>STT</th>
                <th>Tên thủ tục hành chính</th>
                <th>Hiệu lực đến</th>
                <th>Trạng thái</th>
            </tr>`;
    list.map((data, index) => {
        view += `<tr>
                <td>${5*(pageNumber - 1) + index + 1}</td>
                <td><a href="thong-tin-thu-tuc?id=${data.idThuTuc}">${viewField(data.tenThuTuc)}</a></td>
                <td>${viewField(data.giayPhepKhaiThac !== null ? formatDate(data.giayPhepKhaiThac.ngayHetHan) : null)}</td>
                <td>${viewTrangThaiThuTuc(data)}</td>
            </tr>`;
    })
    let len = list.length;
    for (let i = len; i <= 4; i++) {
        view += `<tr>
                <td>${5*(pageNumber - 1) + i + 1}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>`;
    }
    $("#tapThuTuc tbody").html(view);
}
//end set list thu tuc

//check field
function checkNguoiDaiDien() {
    let rs = false;
    let size = 100;
    let ten = inputNguoiDaiDien.val();
    let selector = inputNguoiDaiDien.parent();
    if (regexTen(ten)) {
        if (checkSize(size,ten)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Tên chưa đúng định dạng");
    return {check : rs, val: ten};
}

function checkTenDoanhNghiep() {
    let rs = false;
    let size = 100;
    let ten = inputTenDN.val();
    let selector = inputTenDN.parent();
    if (checkSize(size,ten)) {
        rs = true;
        hiddenError(selector);
    } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    return {check : rs, val: ten};
}

function checkMaDoanhNghiep() {
    let rs = false;
    let size = 15;
    let ten = inputMaDN.val();
    let selector = inputMaDN.parent();
    if (checkSize(size,ten)) {
        rs = true;
        hiddenError(selector);
    } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    return {check : rs, val: ten};
}

function checkVonDieuLe() {
    let rs = false;
    let size = 50;
    let ten = inputVonDieuLe.val();
    let selector = inputVonDieuLe.parent();
    if (regexVonDieuLe(ten)) {
        if (checkSize(size,ten)) {
            rs = true;
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,`Vốn điều lệ phải > 0`);
    return {check : rs, val: ten};
}

function checkEmail() {
    let rs = false;
    let size = 100;
    let ten = inputEmail.val();
    let selector = inputEmail.parent();
    if (regexEmail(ten)) {
        if (checkSize(size,ten)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Email chưa đúng định dạng");
    return {check : rs, val: ten};
}

function checkDienThoai() {
    let rs = false;
    let size = 10;
    let ten = inputDienThoai.val();
    let selector = inputDienThoai.parent();
    if (regexDienThoai(ten)) {
        if (checkSize(size,ten)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Số điện thoại chưa đúng định dạng");
    return {check : rs, val: ten};
}

function checkDiaChi() {
    let rs = false;
    let size = 255;
    let ten = inputDiaChi.val();
    let selector = inputDiaChi.parent();
    if (checkSize(size,ten)) {
        rs = true;
        hiddenError(selector);
    } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    return {check : rs, val: ten};
}

function checkNganhNghe() {
    let rs = false;
    let arrNN = selectNganhNghe.val();
    let selector = selectNganhNghe.parent();
    if (arrNN !== null) {
        rs = true;
        // arrNN = arrNN.map(data => data - 0);
        hiddenError(selector)
    } else viewError(selector, `Vui lòng không để lĩnh vực hoạt động trống`);
    return {check : rs, val: arrNN};
}
//end check field

function checkModifileTTDN() {
    setDisabledButton($("#save-doanh-nghiep"), true);
    //check slelect
    $(".check-modifile-ttdn").change(function () {
        setDisabledButton($("#save-doanh-nghiep"), false);
    })
}

function saveDoanhNghiep() {
    btnSaveDoanhNghiep.on("click", function () {
        let {check: testTDN, val: textTDN} = checkTenDoanhNghiep();
        let {check: testMDN, val: textMDN} = checkMaDoanhNghiep();
        let {check: testNDD, val: textNDD} = checkNguoiDaiDien();
        let {check: testVDL, val: textVDL} = checkVonDieuLe();
        let {check: testEmail, val: textEmail} = checkEmail();
        let {check: testDT, val: textDT} = checkDienThoai();
        let {check: testDC, val: textDC} = checkDiaChi();
        let {check: testNN, val: arrNN} = checkNganhNghe();
        if (testTDN && testMDN && testNDD && testVDL && testEmail && testDT && testDC && testNN) {
            thongTinDoanhNghiep.name = textTDN;
            thongTinDoanhNghiep.code = textMDN;
            thongTinDoanhNghiep.representative = textNDD;
            thongTinDoanhNghiep.charterCapital = textVDL;
            thongTinDoanhNghiep.eportal = textEmail;
            thongTinDoanhNghiep.phoneNumber = textDT;
            //giay phep kinh doanh;
            if (doanhNghiepId == 0) {
                uploadThongTinDoanhNghiep(thongTinDoanhNghiep, arrNN, textDC, selectXa.val()).then(rs => {
                    if (rs.message === "uploaded") {
                        rs = rs.data;
                        let doanhNghiepMoi = {
                            thongTinDoanhNghiepId : rs.id
                        }
                        uploadDoanhNghiep(doanhNghiepMoi).then(rs => {
                            window.location.href = `thong-tin-doanh-nghiep?id=${rs.data.idDoanhNghiep}`;
                        }).catch(err => {
                            console.log(err);
                        });
                    }
                }).catch(err => {
                    console.log(err);
                });
            } else {
                // address.location = textDC;
                // let commune = arrXa.find(data => data.id == selectXa.val());
                // address.commune = commune;
                // thongTinDoanhNghiep.address = address;
                // organizationCareerUpdateList(thongTinDoanhNghiep.id, arrNN).then(rs => {
                //     console.log(rs);
                // }).catch(err => console.log(err));
                // updateThongTinDoanhNghiep(thongTinDoanhNghiep).then(rs => {
                //     console.log(rs);
                // }).catch(err => console.log(err));
            }
        }
    })
}

function clickInforNganhNghe() {
    $(".fa-info-circle").click(function () {
        let listNganhNgheSelect = selectNganhNghe.find(":selected");
        let viewTable = "<tr><th>STT</th><th>Mã ngành</th><th>Tên ngành nghề</th></tr>";
        listNganhNgheSelect.map((index, data) => {
            viewTable += `<tr><td>${index+1}</td><td>${data.label.split(":")[0].trim()}</td><td>${data.label.split(":")[1].trim()}</td></tr>`;
        })
        $("#nganh-nghe-select").html(viewTable);
    })
}

function setSelectXa() {
    communeFindByDistrict(selectHuyen.val()).then(rs => {
        if(rs.message === "found") arrXa = rs.data;
        setListTinhHuyenXa(selectXa, rs);
    }).catch(err => console.log(err));
}

function setSelectHuyen() {
    districtFindByProvince(selectTinh.val()).then(rsHuyen => {
        setListTinhHuyenXa(selectHuyen, rsHuyen);
        setSelectXa();
    }).catch(err => {
        console.log(err);
    })
}

function checkGhiChu() {
    let rs = false;
    let val = inputGhiChu.val();
    let selector = inputGhiChu.parent();
    if (val.length <= 255) {
        rs = true;
        hiddenError(selector);
    } else viewError(selector,`Độ dài chưa phù hợp < 255`);
    return {check : rs, val: val};
}

function saveCauHinh() {
    $("#save-cau-hinh").click(function () {
        let valCauHinh = $("#trang-thai-phan-mem input:checked").val();
        let {check: checkGC, val: valGC} = checkGhiChu();
        if (checkGC) {
            doanhNghiep.ghiChu = valGC;
            if(valCauHinh == trangThaiPhanMemCu) {
                updateDoanhNghiep(doanhNghiep).then(rs => {
                    if(rs.result === "updated") {
                        alterSuccess("Sửa cấu hình thành công.",TIME_ALTER);
                    } else {
                        alterDanger("Chỉnh sửa cấu hình lỗi",TIME_ALTER);
                    }
                }).catch(err => {
                    console.log(err)
                    alterDanger("Chỉnh sửa cấu hình lỗi",TIME_ALTER);
                });
            } else {
                doanhNghiep.trangThaiPhanMem = valCauHinh;
                updateCauHinh(doanhNghiep).then(rs => {
                    if(rs.result === "updated") {
                        rs = rs.data;
                        trangThaiPhanMemCu = rs.trangThaiPhanMem;
                        switch (trangThaiPhanMemCu) {
                            case 0:
                                alterSuccess("Đã kích hoạt toàn bộ tài khoản và thiết bị.",TIME_ALTER);
                                break;
                            case 1:
                                alterSuccess("Đã khóa toàn bộ tài khoản đăng nhập.",TIME_ALTER);
                                break;
                            case 2:
                                alterSuccess("Đã khóa kết nối toàn bộ thiết bị.",TIME_ALTER);
                                break;
                            case 3:
                                alterSuccess("Đã khóa toàn bộ tài khoản, thiết bị.",TIME_ALTER);
                                break;
                        }
                        searchThietBiTTDN(doanhNghiepId);
                        searchNguoiDungTTDN(doanhNghiepId);
                    } else {
                        alterDanger("Chỉnh sửa cấu hình lỗi",TIME_ALTER);
                    }
                }).catch(err => {
                    alterDanger("Chỉnh sửa cấu hình lỗi",TIME_ALTER);
                    console.log(err);
                });
            }
        }
    })
}

function changeDisabledTTDN(check) {
    inputTenDN.prop("disabled",check);
    inputMaDN.prop("disabled",check);
    inputNguoiDaiDien.prop("disabled",check);
    selectNganhNghe.prop("disabled",check);
    inputVonDieuLe.prop("disabled",check);
    inputEmail.prop("disabled",check);
    inputDienThoai.prop("disabled",check);
    inputDiaChi.prop("disabled",check);
    selectTinh.prop("disabled",check);
    selectHuyen.prop("disabled",check);
    selectXa.prop("disabled",check);
    inputGiayPhep.prop("disabled",check);
    btnSaveDoanhNghiep.prop("disabled",check);
}