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
            } else viewError(selectorNL, `Mật khẩu nhập lại chưa chính xác`);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Mật khẩu chưa đúng định dạng");
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
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Tài khoản chưa đúng định dạng");
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

function checkChucVu() {
    let rs = false;
    let size = 50;
    let ten = inputChucVu.val();
    let selector = inputChucVu.parent();
    if (regexTen(ten)) {
        if (checkSize(size,ten)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Chức vụ chưa đúng định dạng");
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
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Số điện thoại chưa đúng định dạng");
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
                    alterSuccess("Chỉnh sửa thông tin người dùng thành công",TIME_ALTER);
                } else {
                    alterDanger("Chỉnh sửa thông tin người dùng không thành công",TIME_ALTER);
                }
            }).catch(err => {
                alterDanger("Chỉnh sửa thông tin người dùng không thành công",TIME_ALTER);
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
                                alterSuccess("Thêm người dùng thành công",TIME_ALTER);
                            } else {
                                alterDanger("Thêm người dùng không thành công",TIME_ALTER);
                            }
                        }).catch(err => console.log(err));
                    } else {
                        let {data} = rs;
                        if(data.indexOf("email") > -1) {
                            viewError(inputEmail.parent(), "Email đã tồn tại.");
                        } else {
                            hiddenError(inputEmail.parent());
                        }

                        if(data.indexOf("username") > -1) {
                            viewError(inputTaiKhoan.parent(), "Tài khoản đã tồn tại.");
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