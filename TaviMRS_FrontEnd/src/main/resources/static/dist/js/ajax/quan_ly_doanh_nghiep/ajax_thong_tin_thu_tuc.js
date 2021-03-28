var inputTenThuTuc, inputSoGiayPhep, inputNgayCap, inputThoiHanDen, inputCoQuanCap, inputNguoiKy, inputChucVu, listHoSoTaiLieu, linkGiayPhep, radioTrangThai;
var thuTuc = {};
var thuTucId = 0;
var giayPhepKhaiThac = null;

$(function () {
    // $('.date-time-set').datepicker({
    //     format: 'dd/mm/yyyy',
    // }).datepicker('update', new Date());
    //set datetime

    inputTenThuTuc = $("#ten-thu-tuc-hanh-chinh");
    inputSoGiayPhep = $("#so-giay-phep");
    inputNgayCap = $("#ngay-cap");
    inputThoiHanDen = $("#thoi-han-den");
    inputCoQuanCap = $("#co-quan-cap");
    inputNguoiKy = $("#nguoi-ky");
    inputChucVu = $("#chuc-vu");
    listHoSoTaiLieu = $("#list-ho-so");
    linkGiayPhep = $("#link-giay-phep");
    radioTrangThai = "#trang-thai input:checked";

    let href = new URL(window.location.href);
    thuTucId = href.searchParams.get("id");

    thuTucFindBydId(thuTucId).then(rs => {
        if(rs.result === "found") {
            rs = rs.data;
            thuTuc = rs;
            inputTenThuTuc.val(viewField(rs.tenThuTuc));
            giayPhepKhaiThac = rs.giayPhepKhaiThac;
            if (giayPhepKhaiThac !== null) {
                $("#trang-thai input").prop("disabled", false);
                $("#save-thu-tuc").prop("disabled", false);
                inputSoGiayPhep.val(viewField(giayPhepKhaiThac.soQuyetDinh));
                inputNgayCap.val(formatDate(giayPhepKhaiThac.ngayCap));
                inputThoiHanDen.val(formatDate(giayPhepKhaiThac.ngayHetHan));
                inputCoQuanCap.val(viewField(giayPhepKhaiThac.coQuanCap));
                inputNguoiKy.val(viewField(giayPhepKhaiThac.nguoiKy));
                inputChucVu.val(viewField(giayPhepKhaiThac.chucVu));
                linkGiayPhep.attr("href", viewField(giayPhepKhaiThac.giayPhep));
                $(`#rb${giayPhepKhaiThac.trangThai}`).prop( "checked", true );
                setLinkGiayPhep(giayPhepKhaiThac);
                //set Ho So
                giayPhepTheoLoaiThuTucFindByLoaiThuTuc(rs.loaiThuTuc.idLoaiThuTuc).then(rs => {
                    if(rs.result === "found") {
                        setListHoSoTaiLieu(rs.data);
                        giayPhepTheoThuTucFindByThuTuc(thuTucId).then(rs => {
                            //lon hon la loi data
                            if(rs.result === "found") {
                                setListGiayPhepHoSo(rs.data);
                            }
                        }).catch(err => console.log(err));
                    }
                }).catch(err => {
                    console.log(err);
                })
            } else {
                inputSoGiayPhep.prop("disabled", true);
                inputNgayCap.prop("disabled", true);
                inputThoiHanDen.prop("disabled", true);
                inputCoQuanCap.prop("disabled", true);
                inputNguoiKy.prop("disabled", true);
                inputChucVu.prop("disabled", true);
            }
        } else {
            window.history.back();
        }
    }).catch(err => {
        console.log(err);
        window.history.back();
    });

    saveThuTuc();
})

function setLinkGiayPhep(giayPhepKT) {
    if(giayPhepKT.giayPhep !== null) {
        console.log(giayPhepKT.giayPhep);
        linkGiayPhep.attr("href", giayPhepKT.giayPhep);
        linkGiayPhep.text(giayPhepKT.soQuyetDinh);
    }
}

function setListHoSoTaiLieu(list) {
    let view = "";
    list.map(data => {
        view += `<li class="tree-view">
                    <a href="" data-id="${data.idGiayPhepTheoLoaiThuTuc}" target="_blank"><strong>${data.tenGiayPhep} :</strong></a>
                </li>`
    })
    listHoSoTaiLieu.html(view);
}

function setListGiayPhepHoSo(list) {
    list.map(data => {
        let selector = listHoSoTaiLieu.find(`a[data-id="${data.giayPhepTheoLoaiThuTuc.idGiayPhepTheoLoaiThuTuc}"]`);
        selector.attr("href",data.duongDanGiayPhep);
        selector.html(`<strong>${selector.text()}</strong> ${data.tenGiayPhepTheoThuTuc}`);
    })
}

function checkTenThuTuc() {
    let rs = false;
    let size = 100;
    let val = inputTenThuTuc.val();
    let selector = inputTenThuTuc.parent();
    if (regexTenThuTuc(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Tên thủ tục đúng định dạng.");
    return {check : rs, val: val} ;
}

function checkSoGiayPhep() {
    let rs = false;
    let size = 20;
    let val = inputSoGiayPhep.val();
    let selector = inputSoGiayPhep.parent();
    if (regexSoQuyetDinh(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Số quyết định đúng định dạng.");
    return {check : rs, val: val} ;
}

function checkNgayCap() {
    let rs = false;
    let val = inputNgayCap.val();
    let selector = inputNgayCap.parent();
    if (regexDate(val)) {
        rs = true;
        hiddenError(selector);
    } else viewError(selector,"Ngày cấp chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkThoiHanDen() {
    let rs = false;
    let val = inputThoiHanDen.val();
    let selector = inputThoiHanDen.parent();
    if (regexDate(val)) {

    } else viewError(selector,"Thời hạn đến chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkCoQuanCap() {
    let rs = false;
    let size = 100;
    let val = inputCoQuanCap.val();
    let selector = inputCoQuanCap.parent();
    if (regexTen(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Cơ quan cấp chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkNguoiKy() {
    let rs = false;
    let size = 100;
    let val = inputNguoiKy.val();
    let selector = inputNguoiKy.parent();
    if (val === "") {
        hiddenError(selector);
        return {check: true, val: null};
    }
    if (regexTen(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Người ký chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkChucVu() {
    let rs = false;
    let size = 100;
    let val = inputChucVu.val();
    let selector = inputChucVu.parent();
    if (val === "") {
        hiddenError(selector);
        return {check: true, val: null};
    }
    if (regexTen(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Chức vụ chưa đúng định dạng");
    return {check : rs, val: val};
}

function saveThuTuc() {
    $("#save-thu-tuc").click(function () {
        let valTrangThai = $(radioTrangThai).val();
        thuTuc.trangThai = valTrangThai;
        if (giayPhepKhaiThac !== null) thuTuc.giayPhepKhaiThac.trangThai = valTrangThai;
        updateThuTuc(thuTuc).then(rs => {
            console.log(rs);
            if(rs.result === "updated") {
                alterSuccess("Chỉnh sửa thông tin thủ tục thành công",TIME_ALTER);
            } else {
                alterDanger("Chỉnh sửa thông tin thủ tục không thành công",TIME_ALTER);
            }
        }).catch(err => {
            console.log(err);
            alterDanger("Chỉnh sửa thông tin thủ tục không thành công",TIME_ALTER);
        })
    })
}