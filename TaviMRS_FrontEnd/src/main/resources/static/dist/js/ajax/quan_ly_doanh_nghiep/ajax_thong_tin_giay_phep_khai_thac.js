var inputSoQuyetDinh, inputCoQuanCap, inputNguoiKy, inputChucVu, selectLoaiGiayPhep, inputThoiHanKhaiThac, inputTruLuongDiaChat, inputTruLuongKhoangSan, inputCongSuatKhaiThacNam, inputDienTichKhaiThac, inputGiayPhepGoc, iframeGiayPhep, radioTrangThai, radioThamQuyenCap;
var giayPhepKhaiThacId = 0;
var giayPhepKhaiThac = {};
var arrLoaiGiayPhep = [];

$(function () {

    inputSoQuyetDinh = $("#so-quyet-dinh");
    inputCoQuanCap = $("#co-quan-cap");
    inputNguoiKy = $("#nguoi-ky");
    inputChucVu = $("#chuc-vu");
    selectLoaiGiayPhep = $("#select1");
    inputThoiHanKhaiThac = $("#thoi-han-khai-thac");
    inputTruLuongDiaChat = $("#tru-luong-dia-chat");
    inputTruLuongKhoangSan = $("#tru-luong-khoang-san");
    inputCongSuatKhaiThacNam = $("#cong-suat-khai-thac");
    inputDienTichKhaiThac = $("#dien-tich-khai-thac");
    inputGiayPhepGoc = $("#giay-phep-goc");
    iframeGiayPhep = $("#iframe-giay-phep");
    radioTrangThai = "#trang-thai input:checked";
    radioThamQuyenCap = "#tham-quyen-cap input:checked";

    let href = new URL(window.location.href);
    giayPhepKhaiThacId = href.searchParams.get("id");

    //setLoaiGiayPhep
    setListLoaiGiayPhep(selectLoaiGiayPhep).then(rs => {
        arrLoaiGiayPhep = rs;
    }).catch(err => console.log(err));
    //end setLoaiGiayPhep

    changeDisabledGPKT(true);
   giayPhepKhaiThacFindById(giayPhepKhaiThacId).then(rs => {
       if(rs.result === "found") {
           rs = rs.data;
           giayPhepKhaiThac = rs;
           console.log(rs);
           inputSoQuyetDinh.val(viewField(rs.soQuyetDinh));
           inputCoQuanCap.val(viewField(rs.coQuanCap));
           inputNguoiKy.val(viewField(rs.nguoiKy));
           inputChucVu.val(viewField(rs.chucVu));
           let loaiGiayPhep = rs.loaiGiayPhep;
           if (loaiGiayPhep !== null) selectLoaiGiayPhep.val(loaiGiayPhep.idLoaiGiayPhep);
           inputThoiHanKhaiThac.val(viewField(rs.thoiHanKhaiThac));
           inputTruLuongDiaChat.val(viewField(rs.truLuongDiaChat));
           inputTruLuongKhoangSan.val(viewField(rs.truLuongKhoangSan));
           inputCongSuatKhaiThacNam.val(viewField(rs.congSuatKhaiThac));
           inputDienTichKhaiThac.val(viewField(rs.dienTichKhaiThac));
           iframeGiayPhep.attr("src", viewField(rs.giayPhep));
           console.log(rs.trangThai);
           $(`#rb${rs.trangThai}`).prop( "checked", true );
           $(`#rb${rs.thamQuyenCap + 2}`).prop( "checked", true );
           $(".select2").select2().trigger("change");
       }
   }).catch(err => console.log(err))
    $(".select2").select2().trigger("change");
   saveGiayPhep();
})

function checkSoQuyetDinh(){
    let rs = false;
    let size = 20;
    let val = inputSoQuyetDinh.val();
    let selector = inputSoQuyetDinh.parent();
    if (regexSoQuyetDinh(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Số quyết định đúng định dạng.");
    return {check : rs, val: val} ;
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

function checkThoiHanKhaiThac() {
    let rs = false;
    let size = 2;
    let val = inputThoiHanKhaiThac.val();
    let selector = inputThoiHanKhaiThac.parent();
    if (regexThoiHanKhaiThac(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Thời hạn khai thác chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkTruLuongDiaChat() {
    let rs = false;
    let size = 10;
    let val = inputTruLuongDiaChat.val();
    let selector = inputTruLuongDiaChat.parent();
    if (regexTruLuongDiaChat(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Trữ lượng địa chất chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkCongSuatKhaiThac() {
    let rs = false;
    let size = 10;
    let val = inputCongSuatKhaiThacNam.val();
    let selector = inputCongSuatKhaiThacNam.parent();
    if (regexCongSuatKhaiThac(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Công suất khai thác chưa đúng định dạng");
    return {check : rs, val: val};
}

function checkDienTichKhaiThac() {
    let rs = false;
    let size = 10;
    let val = inputDienTichKhaiThac.val();
    let selector = inputDienTichKhaiThac.parent();
    if (regexDienTichKhaiThac(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Diện tích khai thác chưa đúng định dạng");
    return {check : rs, val: val};
}

function saveGiayPhep() {
    $("#save-giay-phep").click(function () {
        // let {check: checkSQD, val: valSQD} = checkSoQuyetDinh();
        // let {check: checkCQC, val: valCQC} = checkCoQuanCap();
        // let {check: checkNK, val: valNK} = checkNguoiKy();
        // let {check: checkCV, val: valCV} = checkChucVu();
        // let {check: checkTHKT, val: valTHKT} = checkThoiHanKhaiThac();
        // let {check: checkTLDC, val: valTLDC} = checkTruLuongDiaChat();
        // let {check: checkCSKT, val: valCSKT} = checkCongSuatKhaiThac();
        // let {check: checkDTKT, val: valDTKT} = checkDienTichKhaiThac();
        // if(checkSQD && checkCQC && checkNK && checkCV && checkTHKT && checkTLDC && checkCSKT && checkDTKT) {
        //     giayPhepKhaiThac.soQuyetDinh = valSQD;
        //     giayPhepKhaiThac.coQuanCap = valCQC;
        //     giayPhepKhaiThac.coQuanCap = valCQC;
        //     giayPhepKhaiThac.nguoiKy = valNK;
        //     giayPhepKhaiThac.chucVu = valCV;
        //     giayPhepKhaiThac.thoiHanKhaiThac = valTHKT;
        //     giayPhepKhaiThac.truLuongDiaChat = valTLDC;
        //     giayPhepKhaiThac.congSuatKhaiThac = valCSKT;
        //     giayPhepKhaiThac.dienTichKhaiThac = valDTKT;
        //     giayPhepKhaiThac.trangThai = $(radioTrangThai).val();
        //     giayPhepKhaiThac.thamQuyenCap = $(radioThamQuyenCap).val();
        //     giayPhepKhaiThac.loaiGiayPhep = arrLoaiGiayPhep.find(data => data.idLoaiGiayPhep == selectLoaiGiayPhep.val());
        //     updateGiayPhepKhaiThac(giayPhepKhaiThac).then(rs => {
        //         console.log(rs);
        //     }).catch(err => console.log(err))
        // }
        giayPhepKhaiThac.trangThai = $(radioTrangThai).val();
        giayPhepKhaiThac.thamQuyenCap = $(radioThamQuyenCap).val();
        updateGiayPhepKhaiThac(giayPhepKhaiThac).then(rs => {
            console.log(rs);
            if (rs.result === "updated") {
                alterSuccess("Chỉnh sửa thông tin giấy phép thành công",TIME_ALTER);
            } else {
                alterDanger("Chỉnh sửa giấy phép không thành công",TIME_ALTER);
            }
        }).catch(err => {
            alterDanger("Chỉnh sửa giấy phép không thành công",TIME_ALTER);
            console.log(err);
        })
    })
}

function changeDisabledGPKT(check) {
    inputSoQuyetDinh.prop("disabled",check);
    inputCoQuanCap.prop("disabled",check);
    inputNguoiKy.prop("disabled",check);
    inputChucVu.prop("disabled",check);
    selectLoaiGiayPhep.prop("disabled",check);
    inputThoiHanKhaiThac.prop("disabled",check);
    inputTruLuongDiaChat.prop("disabled",check);
    inputTruLuongKhoangSan.prop("disabled",check);
    inputCongSuatKhaiThacNam.prop("disabled",check);
    inputDienTichKhaiThac.prop("disabled",check);
    inputGiayPhepGoc.prop("disabled",check);
}