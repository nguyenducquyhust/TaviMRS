var inputMaThietBi, inputTenThietBi, inputViTriLap, inputMoTa, inputHinhAnh, radioTrangThai, inputNoiDungDuyet, imgThietBi;
var thietBiId = 0;
var thietBi = {};

$(function () {
    inputMaThietBi = $("#ma-thiet-bi");
    inputTenThietBi = $("#ten-thiet-bi");
    inputViTriLap = $("#vi-tri-lap");
    inputMoTa = $("#mo-ta");
    inputHinhAnh = $("#hinh-anh");
    radioTrangThai = "#trang-thai input:checked";
    inputNoiDungDuyet = $("#noi-dung-duyet");
    imgThietBi = $("#img-thiet-bi");

    let href = new URL(window.location.href);
    thietBiId = href.searchParams.get("id");

    changeDisabledTTTB(true);
    thietBiFindById(thietBiId).then(rs => {
        if(rs.result === "found") {
            rs = rs.data;
            thietBi = rs;
            console.log(thietBi);
            inputMaThietBi.val(viewField(rs.maThietBi));
            inputTenThietBi.val(viewField(rs.tenThietBi));
            inputViTriLap.val(viewField(rs.viTriLap));
            inputMoTa.val(viewField(rs.moTa));
            $(`#rb${rs.trangThai}`).prop("checked", true);
            inputNoiDungDuyet.val(viewField(rs.lyDoDuyet));
            imgThietBi.attr("href", viewField(rs.hinhAnh));
        } else {
            window.history.back();
        }
    }).catch(err => {
        console.log(err);
        window.history.back();
    });
    saveThietBi();
})

function checkMaThietBi() {
    let rs = false;
    let size = 100;
    let val = inputMaThietBi.val();
    let selector = inputMaThietBi.parent();
    if (regexMaThieBi(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Mã thiết bị chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkTenThietBi() {
    let rs = false;
    let size = 100;
    let val = inputTenThietBi.val();
    let selector = inputTenThietBi.parent();
    if (regexTenThieBi(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Tên thiết bị chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkViTriLap() {
    let rs = false;
    let size = 100;
    let val = inputViTriLap.val();
    let selector = inputViTriLap.parent();
    if (val === "") {
        hiddenError(selector);
        return {check: true, val: null};
    }
    if (checkSize(size,val)) {
        rs = true;
        hiddenError(selector);
    } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    return {check : rs, val: val};
}

function checkMoTa() {
    let rs = false;
    let size = 255;
    let val = inputMoTa.val();
    let selector = inputMoTa.parent();
    if (val === "") {
        hiddenError(selector);
        return {check: true, val: null};
    }
    if (checkSize(size,val)) {
        rs = true;
        hiddenError(selector);
    } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    return {check : rs, val: val};
}

function checkNoiDungDuyet() {
    let rs = false;
    let val = inputNoiDungDuyet.val();
    let selector = inputNoiDungDuyet.parent();
    if($(radioTrangThai).val() == 2 && (val.length < 1 || val.length > 255)) {
        viewError(selector, "Cần có lý do không đồng ý có đồ dài < 10");
    } else {
        rs = true;
        hiddenError(selector);
    }
    return {check : rs, val: val};
}

function saveThietBi() {
    $("#save-thiet-bi").click(function() {
        // let {check: checkMTB, val: valMTB} = checkMaThietBi();
        // let {check: checkTTB, val: valTTB} = checkTenThietBi();
        // let {check: checkVTL, val: valVTL} = checkViTriLap();
        // let {check: checkMT, val: valMT} = checkMoTa();
        let {check: checkND, val: valND} = checkNoiDungDuyet();
        // if(checkMTB && checkTTB && checkVTL && checkMT && checkND) {
        if(checkND) {
            // thietBi.maThietBi = valMTB;
            // thietBi.tenThietBi = valTTB;
            // thietBi.viTriLap = valVTL;
            // thietBi.moTa = valMT;
            thietBi.trangThai = $(radioTrangThai).val();
            thietBi.lyDoDuyet = valND;
            updateThietBi(thietBi).then(rs => {
                console.log(rs);
                if(rs.result === "updated") {
                    alterSuccess("Chỉnh sửa thông tin thiết bị thành công",TIME_ALTER);
                } else {
                    alterDanger("Chỉnh sửa thông tin thiết bị không thành công",TIME_ALTER);
                }
            }).catch(err => {
                alterDanger("Chỉnh sửa thông tin thiết bị không thành công",TIME_ALTER);
                console.log(err);
            });
        }
    })
}

function changeDisabledTTTB(check) {
    inputMaThietBi.prop("disabled",check);
    inputTenThietBi.prop("disabled",check);
    inputViTriLap.prop("disabled",check);
    inputMoTa.prop("disabled",check);
    inputHinhAnh.prop("disabled",check);
}
