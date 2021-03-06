var inputTaiKhoan, inputEmail, inputChucVu, inputSoDienThoai, radioTrangThai, inputMatKhau, inputNhapLaiMatKhau, viewMatKhau, viewTrangThai, viewSoDienThoai;
var nguoiDung = {};
var nguoiDungId = 0;
var thongTinNguoiDung = {};
var thongTinId = 0;
var doanhNghiepId = null;

$(function () {
    inputTaiKhoan = $("#tai-khoan");
    inputEmail = $("#email");
    inputChucVu = $("#chuc-vu");
    inputSoDienThoai = $("#so-dien-thoai");
    radioTrangThai = "#trang-thai input:checked";
    inputMatKhau = $("#mat-khau");
    inputNhapLaiMatKhau = $("#nhap-lai-mat-khau");
    viewMatKhau = $("#view-mat-khau");
    viewTrangThai = $("#view-trang-thai");
    viewSoDienThoai = $("#view-so-dien-thoai");

    let href = new URL(window.location.href);
    nguoiDungId = href.searchParams.get("id");
    doanhNghiepId = href.searchParams.get("doanh-nghiep-id");

    if (nguoiDungId != 0) {
        changeDisabledND(true);
        nguoiDungFindById(nguoiDungId).then(rs => {
            if(rs.result === "found") {
                rs = rs.data;
                nguoiDung = rs;
                inputChucVu.val(viewField(rs.chucVu));
                $(`#rb${rs.trangThai}`).prop("checked", true);
                thongTinId = rs.thongTinId;
                if(thongTinId !== null) {
                    thongTinNguoiDungFindById([thongTinId]).then(result => {
                        if(result.message === "found") {
                            result = result.data[0];
                            thongTinNguoiDung = result;
                            inputTaiKhoan.val(viewField(result.username));
                            inputEmail.val(viewField(result.email));
                            inputSoDienThoai.val(viewField(result.phoneNumber));
                            //update thong tin nguoi dung
                            nguoiDung.soDienThoai = inputSoDienThoai.val();
                            nguoiDung.email = inputEmail.val();
                            updateNguoiDung(nguoiDung).then(rs => {
                                console.log(rs);
                            }).catch(err => console.log(err));
                        } else {
                            window.history.back();
                        }
                    }).catch(err => {
                        console.log(err);
                        window.history.back();
                    })
                } else {
                    window.history.back();
                }
            } else {
                window.history.back();
            }
        }).catch(err => {
            console.log(err);
            window.history.back();
        })
    } else {
        if(doanhNghiepId === null) window.history.back();
        viewTrangThai.addClass("hidden");
        // viewSoDienThoai.addClass("hidden");
    }

    saveNguoiDung();
})

function checkMatKhau() {
    let rs = false;
    let size = 30;
    let ten = inputMatKhau.val();
    let selector = inputMatKhau.parent();
    let nhapLai = inputNhapLaiMatKhau.val();
    let selectorNL = inputNhapLaiMatKhau.parent();
    if (regexMatKhau(ten)) {
        if (checkSize(size,ten)) {
            if (ten === nhapLai) {
                rs = true;
                hiddenError(selector);
                hiddenError(selectorNL);
            } else viewError(selectorNL, `M???t kh???u nh???p l???i ch??a ch??nh x??c`);
        } else viewError(selector,`????? d??i ch??a ph?? h???p > 0 v?? < ${size}`);
    } else viewError(selector,"M???t kh???u ch??a ????ng ?????nh d???ng");
    return {check : rs, val: ten};
}

function checkTaiKhoan() {
    let rs = false;
    let size = 30;
    let ten = inputTaiKhoan.val();
    let selector = inputTaiKhoan.parent();
    if (regexTaiKhoan(ten)) {
        if (checkSize(size,ten)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`????? d??i ch??a ph?? h???p > 0 v?? < ${size}`);
    } else viewError(selector,"T??i kho???n ch??a ????ng ?????nh d???ng");
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
        } else viewError(selector,`????? d??i ch??a ph?? h???p > 0 v?? < ${size}`);
    } else viewError(selector,"Email ch??a ????ng ?????nh d???ng");
    return {check : rs, val: ten};
}

function checkChucVu() {
    let rs = false;
    let size = 50;
    let ten = inputChucVu.val();
    let selector = inputChucVu.parent();
    if (regexTen(ten)) {
        if (checkSize(size,ten)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`????? d??i ch??a ph?? h???p > 0 v?? < ${size}`);
    } else viewError(selector,"Ch???c v??? ch??a ????ng ?????nh d???ng");
    return {check : rs, val: ten};
}

function checkSoDienthoai() {
    let rs = false;
    let size = 10;
    let ten = inputSoDienThoai.val();
    let selector = inputSoDienThoai.parent();
    if (regexDienThoai(ten)) {
        if (checkSize(size,ten)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`????? d??i ch??a ph?? h???p > 0 v?? < ${size}`);
    } else viewError(selector,"S??? ??i???n tho???i ch??a ????ng ?????nh d???ng");
    return {check : rs, val: ten};
}

function saveNguoiDung() {
    $("#save-nguoi-dung").click(function () {
        if (nguoiDungId != 0) {
            // nguoiDung.chucVu = valCV;
            nguoiDung.trangThai = $(radioTrangThai).val();
            // nguoiDung.tenDangNhap = inputTaiKhoan.val();
            // nguoiDung.soDienThoai = inputSoDienThoai.val();
            // nguoiDung.email = inputEmail.val();
            updateNguoiDung(nguoiDung).then(rs => {
                console.log(rs);
                if(rs.result === "updated") {
                    alterSuccess("Ch???nh s???a th??ng tin ng?????i d??ng th??nh c??ng",TIME_ALTER);
                } else {
                    alterDanger("Ch???nh s???a th??ng tin ng?????i d??ng kh??ng th??nh c??ng",TIME_ALTER);
                }
            }).catch(err => {
                alterDanger("Ch???nh s???a th??ng tin ng?????i d??ng kh??ng th??nh c??ng",TIME_ALTER);
                console.log(err);
            })
        } else {
            let {check: checkTK, val: valTK} = checkTaiKhoan();
            let {check: checkMail, val: valMail} = checkEmail();
            let {check: checkCV, val: valCV} = checkChucVu();
            let {check: checkSDT, val: valSDT} = checkSoDienthoai();
            let {check: checkMK, val: valMK} = checkMatKhau();
            if (checkTK && checkMail && checkCV && checkMK) {
                let registerForm = {
                    email: valMail,
                    password: valMK,
                    username: valTK,
                    phoneNumber: valSDT
                };
                console.log(registerForm);
                register(registerForm).then(rs => {
                    if (rs.message === "register success") {
                        thongTinId = rs.data.id;
                        nguoiDung = {
                            chucVu: valCV,
                            thongTinId: thongTinId,
                            tenDangNhap: valTK,
                            soDienThoai: valSDT,
                            email: valMail
                        };
                        console.log(nguoiDung);
                        uploadNguoiDung(nguoiDung, doanhNghiepId).then(rs => {
                            if(rs.result === "uploaded") {
                                console.log(rs.data);
                                alterSuccess("Th??m ng?????i d??ng th??nh c??ng",TIME_ALTER);
                            } else {
                                alterDanger("Th??m ng?????i d??ng kh??ng th??nh c??ng",TIME_ALTER);
                            }
                        }).catch(err => console.log(err));
                    } else {
                        let {data} = rs;
                        if(data.indexOf("email") > -1) {
                            viewError(inputEmail.parent(), "Email ???? t???n t???i.");
                        } else {
                            hiddenError(inputEmail.parent());
                        }

                        if(data.indexOf("username") > -1) {
                            viewError(inputTaiKhoan.parent(), "T??i kho???n ???? t???n t???i.");
                        } else {
                            hiddenError(inputTaiKhoan.parent());
                        }
                    }
                }).catch(err => console.log(err))
            }
        }
    })
}

function changeDisabledND(check) {
    inputTaiKhoan.prop("disabled",check);
    inputEmail.prop("disabled",check);
    inputChucVu.prop("disabled",check);
    inputSoDienThoai.prop("disabled",check);
    viewMatKhau.addClass("hidden");
}