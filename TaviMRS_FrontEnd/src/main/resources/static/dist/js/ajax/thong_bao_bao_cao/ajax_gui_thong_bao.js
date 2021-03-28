var groupNguoiDung, inputTieuDe, inputNoiDung, fileDinhKem, checkboxHinhThucGui, checkboxYeuCauPhanHoi, btnGuiThongBao;
var test = null;
$(function () {
    groupNguoiDung = $("#group-nguoi-dung");
    inputTieuDe = $("#tieu-de");
    inputNoiDung = $("#noi-dung");
    fileDinhKem = $("#file-dinh-kem");
    checkboxHinhThucGui = $("#hinh-thuc-gui");
    checkboxYeuCauPhanHoi = $("#yeu-cau-phan-hoi");
    btnGuiThongBao = $("#btn-gui-thong-bao");

    viewGroupNguoiDung();
    clickGuiThongBao();
})

function viewGroupNguoiDung() {
    nguoiDungFindAllGroupDoanhnghiep().then(rs => {
        if(rs.result === "found") {
            rs = rs.data;
            test = rs;
            let view = rs.map(data => `<div class="com">
                        <div class="com1">
                            <span class="cmp">+</span>
                            <span class="cmp d-none">-</span>
                            <span><input type="checkbox" class="check-doanh-nghiep" value="${data[0].doanhNghiep.idDoanhNghiep}">${data[0].doanhNghiep.tenDoanhNghiep}</span>
                        </div>` + data.map(item => `<div class="com2 d-none">
                            <span><input type="checkbox"class="check-nguoi-dung" value="${item.idNguoiDung}">${item.tenDangNhap} - ${item.chucVu}</span>
                        </div>`).join("") +"</div>").join("");
            groupNguoiDung.html(view);
            handleClickNguoiDungGroup();
        } else {
            // window.location.href = "man-hinh-theo-doi";
        }
    }).catch(err => {
        console.log(err);
        // window.location.href = "man-hinh-theo-doi";
    })
}

function checkTieuDe() {
    let check = false;
    let val = inputTieuDe.val();
    let selector = inputTieuDe.parents(".form-group");
    let size = 100;
    if (checkSize( 100, val)) {
        check = true;
        hiddenError(selector);
    } else {viewError(selector,`Tiêu đề phải > 0 và < ${size} ký tự`)}
    return {check: check, val:val};
}

function checkNoidung() {
    let check = false;
    let val = inputNoiDung.val();
    let selector = inputNoiDung.parents(".form-group");
    let size = 1023;
    if (checkSize( size, val)) {
        check = true;
        hiddenError(selector);
    } else {viewError(selector,`Tiêu đề phải > 0 và < ${size} ký tự`)}
    return {check: check, val:val};
}

function checkHinhThucGui () {
    let check = false;
    let val = [];
    checkboxHinhThucGui.find(":checked").map((index, data) => val.push($(data).val()));
    let selector = checkboxHinhThucGui.parents(".form-group");
    if (val.length > 0 ) {
        check = true;
        val = val.reduce((sum, num) => (sum - 0) + (num - 0));
        hiddenError(selector);
    } else  {viewError(selector, "Vui lòng chọn một hình thức gửi")}
    return {check: check, val: val}
}

function checkGroupNguoiDung() {
    let check = false;
    let val = groupNguoiDung.find(".check-nguoi-dung:checked");
    let count = 0;
    let arr = [];
    if(val.length > 0) {
        check = true;
        val.map((index, data) => arr.push($(data).val()));
        groupNguoiDung.find(".com").map((index, data) => count += $(data).find(".check-nguoi-dung:checked").length);
    } else alterDanger("Vui lòng chọn người nhận", TIME_ALTER);
    return {check: check, val: arr, count: count}
}

function clickGuiThongBao() {
    btnGuiThongBao.click(function () {
        let {check: checkTD, val: valTD} = checkTieuDe();
        let {check: checkND, val: valND} = checkNoidung();
        let {check: checkHTG, val: valHTG} = checkHinhThucGui();
        let {check: checkGND, val: valGND, count} = checkGroupNguoiDung();
        if (checkTD && checkND && checkHTG && checkGND) {
            uploadFiles(fileDinhKem).then(rs => {
                if (rs !== null) {
                    thongBaoSend(valGND, rs, valHTG, valND, count, valTD, checkboxYeuCauPhanHoi.is(":checked")).then(result => {
                        if(result.result === "success") {
                            alterSuccess("Gửi thông báo thành công", TIME_ALTER);
                            console.log(result.data);
                        } else {
                            alterDanger("Gửi thông báo không thành công", TIME_ALTER);
                        }
                    }).catch(err => {
                        console.log(err);
                        alterDanger(err.responseJSON.data, TIME_ALTER);
                    })
                }
            }).catch(err => {
                console.log(err);
            })
        }
    })
}