var inputDiaChi, selectTinh, selectHuyen, inputTruLuongMo, inputTruLuongConLai, inputTruLuongKhaiThacNam, inputTruLuongDaKhaiThacNam, selectKhoangSan;
var moId = 0;
var mo = {};

$(function () {

    inputDiaChi = $("#dia-chi");
    selectTinh = $("#select1");
    selectHuyen = $("#select2");
    inputTruLuongMo = $("#tru-luong-mo");
    inputTruLuongConLai = $("#tru-luong-con-lai");
    inputTruLuongKhaiThacNam = $("#tru-luong-khai-thac-nam");
    inputTruLuongDaKhaiThacNam = $("#tru-luong-da-khai-thac-nam");
    selectKhoangSan = $("#select3");

    let href = new URL(window.location.href);
    moId = href.searchParams.get("id");

    //set_dia_gioi
    provinceFindByCountry(1).then(rs => {
        setListTinhHuyenXa(selectTinh, rs);
        selectTinh.change(function () {
            setSelectHuyen();
        })
    }).catch(err => console.log(err));
    setListKhoangSan(selectKhoangSan);
    $('.select2').select2({ width: 'resolve' });
    //end set view select

    moFindById(moId).then(rs => {
        if(rs.result == "found") {
            rs = rs.data;
            mo = rs;
            inputDiaChi.val(viewField(rs.diaChi));
            let tinhId = rs.tinhId;
            let huyenId = rs.huyenId;
            if (tinhId !== null && huyenId !== null) {
                selectTinh.val(tinhId);
                districtFindByProvince(tinhId).then(rsHuyen => {
                    setListTinhHuyenXa(selectHuyen, rsHuyen);
                    selectHuyen.val(huyenId);
                }).catch(err => console.log(err));
            }
            inputTruLuongMo.val(viewField(rs.truLuongMo));
            inputTruLuongConLai.val(viewField(rs.truLuongConLai));
            inputTruLuongKhaiThacNam.val(viewField(rs.truLuongKhaiThacNam));
            inputTruLuongDaKhaiThacNam.val(viewField(rs.truLuongDaKhaiThacNam));
            let khoangSan = rs.khoangSan;
            if(khoangSan !== null) selectKhoangSan.val(khoangSan.idKhoangSan);
            $(".select2").select2().trigger("change");
        }
    }).catch(err => console.log(err));
    $(".select2").select2().trigger("change");
    saveMo();
})

function setSelectHuyen() {
    districtFindByProvince(selectTinh.val()).then(rsHuyen => {
        setListTinhHuyenXa(selectHuyen, rsHuyen);
    }).catch(err => {
        console.log(err);
    })
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

function checkTruLuongKhaiThacNam() {
    let rs = false;
    let size = 100;
    let val = inputTruLuongKhaiThacNam.val();
    let selector = inputTruLuongKhaiThacNam.parent();
    if (regexTruLuongKhaiThacNam(val)) {
        if (checkSize(size,val)) {
            rs = true;
            hiddenError(selector);
        } else viewError(selector,`Độ dài chưa phù hợp > 0 và < ${size}`);
    } else viewError(selector,"Trữ lượng khai thác năm > 0");
    return {check : rs, val: val};
}

function saveMo() {
    $("#save-mo").click(function () {
        let {check: checkDC, val: valDC} = checkDiaChi();
        let {check: checkTLKTN, val: valTLKTN} = checkTruLuongKhaiThacNam();
        if(checkDC && checkTLKTN) {
            mo.diaChi = valDC;
            mo.truLuongKhaiThacNam = valTLKTN;
            updateMo(mo).then(rs => {
                console.log(rs);
                if(rs.result === "updated") {
                    alterSuccess("Chỉnh sửa thông tin mỏ thành công",TIME_ALTER);
                } else {
                    alterDanger("Chỉnh sửa thông tin mỏ không thành công",TIME_ALTER);
                }
            }).catch(err => {
                alterDanger("Chỉnh sửa thông tin mỏ không thành công",TIME_ALTER);
                console.log(err);
            })
        }
    })
}