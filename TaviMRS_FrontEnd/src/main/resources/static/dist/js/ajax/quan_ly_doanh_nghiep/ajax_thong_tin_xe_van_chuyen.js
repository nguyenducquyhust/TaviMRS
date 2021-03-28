var inputBienSoXe, inputSoQuanLy, inputLoaiPhuongTien, inputNhanHieu, inputSoMay, inputSoKhung, inputNamSanXuat, inputNienHan, inputKhoiLuongBanThan, inputKhoiLuongHang, inputGiayDangKiem, imgGiayDangKiem, radioTrangThai, inputLyDo;
var xeVanChuyenId = 0;
var xeVanChuyen = {};



$(function () {

    inputBienSoXe = $('#bien-so-xe');
    inputSoQuanLy = $('#so-quan-ly');
    inputLoaiPhuongTien = $('#loai-phuong-tien');
    inputNhanHieu = $('#nhan-hieu');
    inputSoMay = $('#so-may');
    inputSoKhung = $('#so-khung');
    inputNamSanXuat = $('#nam-san-xuat');
    inputNienHan = $('#nien-han-su-dung');
    inputKhoiLuongBanThan = $('#khoi-luong-ban-than');
    inputKhoiLuongHang = $('#khoi-luong-hang');
    inputGiayDangKiem = $('#giay-dang-kiem');
    imgGiayDangKiem = $('#img-giay-dang-kiem');
    radioTrangThai = "#trang-thai input:checked";
    inputLyDo = $('#ly-do');

    let href = new URL(window.location.href);
    xeVanChuyenId = href.searchParams.get("id");
    changeDisabledXVC(true);
    xeVanChuyenFindById(xeVanChuyenId).then(rs => {
        if(rs.result === "found") {
            rs = rs.data;
            xeVanChuyen = rs;
            console.log(rs);
            inputBienSoXe.val(viewField(rs.bienSoXe));
            inputSoQuanLy.val(viewField(rs.soQuanLy));
            inputLoaiPhuongTien.val(viewField(rs.loaiPhuongTien));
            inputNhanHieu.val(viewField(rs.nhanHieu));
            inputSoMay.val(viewField(rs.soMay));
            inputSoKhung.val(viewField(rs.soKhung));
            inputNamSanXuat.val(viewField(rs.namSanXuat));
            inputNienHan.val(viewField(rs.nienHanSuDung));
            inputKhoiLuongBanThan.val(viewField(rs.khoiLuongBanThan));
            inputKhoiLuongHang .val(viewField(rs.khoiLuongHang));
            imgGiayDangKiem.attr("src",viewField(rs.giayDangKiem));
            $(`#rb${rs.trangThai}`).prop( "checked", true );
            inputLyDo.val(viewField(rs.lyDoDuyet));
        } else {
            $("#back-history").trigger("click");
        }
    }).catch(err => {
        console.log(err);
        $("#back-history").trigger("click");
    });
    saveXeVanChuyen();
})

function checkBienSoXe() {
    let rs = false;
    let size = 10;
    let val = inputBienSoXe.val();
    let selector = inputBienSoXe.parent();
    if (regexBienSoXe(val)) {
        if (checkSize(size,val) && val.length > 7) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 7 và < ${size}`);
    } else viewError(selector,"Biển số xe chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkSoMay() {
    let rs = false;
    let size = 100;
    let val = inputSoMay.val();
    let selector = inputSoMay.parent();
    if (regexSoMay(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Số Máy chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkSoKhung() {
    let rs = false;
    let size = 100;
    let val = inputSoKhung.val();
    let selector = inputSoKhung.parent();
    if (regexSoKhung(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Số khung chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkNamSanXuat() {
    let rs = false;
    let size = 5;
    let val = inputNamSanXuat.val();
    let selector = inputNamSanXuat.parent();
    if (regexNamSanXuat(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Năm sản xuất phải > 1900");
    return {check : rs, val: val};
}

function checkNienHanSuDung() {
    let rs = false;
    let size = 2;
    let val = inputNienHan.val();
    let selector = inputNienHan.parent();
    if (regexNienHanSuDung(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Niên hạn sử dụng < 99");
    return {check : rs, val: val};
}

function checkKhoiLuongBanThan() {
    let rs = false;
    let size = 100;
    let val = inputKhoiLuongBanThan.val();
    let selector = inputKhoiLuongBanThan.parent();
    if (regexKhoiLuongBanThan(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Khối lượng bản thân > 0");
    return {check : rs, val: val};
}

function checkKhoiLuongHang() {
    let rs = false;
    let size = 100;
    let val = inputKhoiLuongHang.val();
    let selector = inputKhoiLuongHang.parent();
    if (regexKhoiLuongHang(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Khối lượng bản thân > 0");
    return {check : rs, val: val};
}

function checkLyDo() {
    let rs = false;
    let val = inputLyDo.val();
    let selector = inputLyDo.parent();
    if($(radioTrangThai).val() == 2 && (val.length < 1 || val.length > 255)) {
        viewError(selector, "Cần có lý do không đồng ý có đồ dài < 10");
    } else {
        rs = true;
        hiddenError(selector);
    }
    return {check : rs, val: val};
}

function saveXeVanChuyen() {
    $("#save-xe-van-chuyen").click(function () {
        // let {check: checkBSX, val: valBSX} = checkBienSoXe();
        // let {check: checkSM, val: valSM} = checkSoMay();
        // let {check: checkSK, val: valSK} = checkSoKhung();
        // let {check: checkNamSX, val: valNamSX} = checkNamSanXuat();
        // let {check: checkNH, val: valNH} = checkNienHanSuDung();
        // let {check: checkKLBT, val: valKLBT} = checkKhoiLuongBanThan();
        // let {check: checkKLH, val: valKLH} = checkKhoiLuongHang();
        let {check: checkLD, val: valLD} = checkLyDo();

        // if(checkSM && checkSK && checkNamSX && checkNH && checkKLBT && checkKLH && checkLD) {
        if(checkLD) {
            // xeVanChuyen.soQuanLy = inputSoQuanLy.val();
            // xeVanChuyen.loaiPhuongTien = inputLoaiPhuongTien.val();
            // xeVanChuyen.nhanHieu = inputNhanHieu.val();
            // xeVanChuyen.soMay = valSM;
            // xeVanChuyen.soKhung = valSK;
            // xeVanChuyen.namSanXuat = valNamSX;
            // xeVanChuyen.nienHanSuDung = valNH;
            // xeVanChuyen.khoiLuongBanThan = valKLBT;
            // xeVanChuyen.khoiLuongHang = valKLH;
            xeVanChuyen.trangThai = $(radioTrangThai).val();
            xeVanChuyen.lyDoDuyet = inputLyDo.val();
            updateXeVanChuyen(xeVanChuyen).then(rs => {
                console.log(rs);
                if(rs.result === "updated") {
                    alterSuccess("Chỉnh sửa xe vận chuyển thành công",TIME_ALTER);
                } else {
                    alterDanger("Chỉnh sửa xe vận chuyển không thành công",TIME_ALTER);
                }
            }).catch(err => {
                alterDanger("Chỉnh sửa xe vận chuyển không thành công",TIME_ALTER);
                console.log(err);
            })
        }
    })
}

function changeDisabledXVC(check) {
    inputBienSoXe.prop("disabled",check);
    inputSoQuanLy.prop("disabled",check);
    inputLoaiPhuongTien.prop("disabled",check);
    inputNhanHieu.prop("disabled",check);
    inputSoMay.prop("disabled",check);
    inputSoKhung.prop("disabled",check);
    inputNamSanXuat.prop("disabled",check);
    inputNienHan.prop("disabled",check);
    inputKhoiLuongBanThan.prop("disabled",check);
    inputKhoiLuongHang.prop("disabled",check);
    inputGiayDangKiem.prop("disabled",check);
}