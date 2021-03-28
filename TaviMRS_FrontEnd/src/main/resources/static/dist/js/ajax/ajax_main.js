var url_api = "http://localhost:9999/api/";
// var url_api = "http://103.9.86.61:8080/admin_mrs/api/";
var url_api_cms = "http://103.9.86.61:8080/admin_cms/api/";
// var url_api_cms = "http://localhost:9898/api/";
var url_img = "http://103.9.86.61:8080/resources/upload/mrs/";
var perfixPrintThis = "/mrs/";

const TRANG_THAI_DOANH_NGHIEP = ["Hoạt động", "Khóa tài khoản truy cập", "Khóa thiết bị kết nối", "Dừng hoạt động"];
const TRANG_THAI_KET_NOI = ["Mất kết nối", "Kết nối"];
const HIEU_LUC = ["Hết hiệu lực", "Còn hiệu lực"];
const THAM_QUYEN = ["Trung Ương", "UBND tỉnh"];
const TRANG_THAI_TAI_KHOAN = ["Khóa","Hoạt Động"];
const TRANG_THAI_DUYET = ["Chờ duyệt","Đồng ý", "Không đồng ý"];
const TRANG_THAI_XU_LY = ["Chưa xử lý", "Đang xử lý", "Đã xử lý"];
const TRANG_THAI_KET_NOI_THIET_BI = ["Không kết nối", "Kết nối", "Khóa thiết bị"];
const TRANG_THAI_VI_PHAM = ["Chưa xử lý", "Đang xử lý", "Đã xử lý"];
const TIME_ALTER = 3000;
const TIME_REPEAT = 10000;
const TIME_REPEAT_CAMERA = 10000;

var ss_lg = "";
var marqueeCanhbao;

$(function () {
    ss_lg = sessionStorage.getItem("ss_lg");
    buttonBackHistory();
    viewDateVn();

    marqueeCanhbao = $("#marq");
    loopMarquee();
})

function loopMarquee() {
    runMarquee();
    setInterval(runMarquee, TIME_REPEAT);
}

function runMarquee() {
    // console.log("loop");
     let view = "";
    canhBaoFind().then(rs => {
        if(rs.result === "found") {
            rs = rs.data;
            view = viewMarquee(rs);
            marqueeCanhbao.html(view);
        }
    }).catch(err => {
        console.log(err);
        alterWarning(`Server Error ${err.statusText}`, TIME_ALTER);
        marqueeCanhbao.html(view);
    })
}

function viewMarquee(listCanhBao) {
    if (listCanhBao === null || listCanhBao.length === 0) return "";
    return listCanhBao.map(item => `${viewField(item.mo !== null ? "<span>- "+item.mo.diaChi + viewLyDoCanhBao(item)+"</span>" : null )}`).join("");
}

function viewLyDoCanhBao(canhBao) {
    return canhBao.moTa == undefined ? canhBao.noiDungViPham == undefined ? "": " "+canhBao.noiDungViPham : " "+canhBao.moTa;
}
//url link get api, option select url_api (1) or url_api_cms (2)
async function ajaxGet(url, option = 1) {
    let rs = null;
    let urlBack = option === 1 ? url_api : url_api_cms;
    await $.ajax({
        type: 'GET',
        headers: {

            "Authorization": ""

        },
        dataType: "json",
        url: urlBack + url,
        timeout: 30000,
        success: function (result) {
            rs = result;
        }
    })
    return rs;
}

//url link get api, option select url_api (1) or url_api_cms (2)
async function ajaxPost(url, data ,option = 1) {
    let rs = null;
    let urlBack = option === 1 ? url_api : url_api_cms;
    await $.ajax({
        type: 'POST',
        data: JSON.stringify(data),
        headers: {
            "Authorization": ss_lg
        },
        url: urlBack + url,
        timeout: 30000,
        contentType: "application/json",
        success: function (result) {
            rs = result
        }
    })
    return rs;
}

async function ajaxPut(url, data ,option = 1) {
    let rs = null;
    let urlBack = option === 1 ? url_api : url_api_cms;
    await $.ajax({
        type: 'PUT',
        data: JSON.stringify(data),
        headers: {
            "Authorization": ss_lg
        },
        url: urlBack + url,
        timeout: 30000,
        contentType: "application/json",
        success: function (result) {
            rs = result
        }
    })
    return rs;
}

async function ajaxUploadFile(url, file, option = 1) {
    let rs = null;
    let urlBack = option === 1 ? url_api : url_api_cms;
    await $.ajax({
        type: "POST",
        url: urlBack + url,
        data: file,
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function (result) {
            rs = result;
        }
    });
    return rs;
}

//view Field
function viewField(data) {
    return data !== null ? data : "";
}

//view error
function viewError(selector, message) {
    selector.addClass("has-error");
    selector.find(".help-block").text(`${message}. Mời nhập lại!`);
}

//hidden error
function hiddenError(selector) {
    selector.removeClass("has-error");
}

//regex field

function removeAscent (str) {
    if (str === null || str === undefined) return str;
    str = str.toLowerCase();
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    return str;
}

function regexTen(name) {
    return /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/g.test(removeAscent(name));
}

function regexEmail(email) {
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)
}

function regexDienThoai(soDienThoai) {
    return /((09|03|07|08|05)+([0-9]{8})\b)/g.test(soDienThoai)
}

function checkSize(size, text) {
    return text.length > 0 && text.length <= size;
}

function regexUsername(text) {
    return /^[A-Za-z0-9]+/.test(text);
}

function regexPassword(text) {
    return /^[A-Za-z0-9]+/.test(text);
}

function regexBienSoXe(text) {
    return true;
}

function regexVonDieuLe(text) {
    let rs = false;
    if(text > 0) rs = true;
    return rs;
}

function regexSoMay(text) {
    return true;
}

function regexSoKhung(text) {
    return true;
}

function regexNamSanXuat(text) {
    let rs = false;
    if(text > 1900) rs = true;
    return rs;
}

function regexNienHanSuDung(text) {
    let rs = false;
    if(text > 0 && text < 99) rs = true;
    return rs;
}

function regexKhoiLuongBanThan(text) {
    let rs = false;
    if(text > 0) rs = true;
    return rs;
}

function regexKhoiLuongHang(text) {
    let rs = false;
    if(text > 0) rs = true;
    return rs;
}

function regexTruLuongKhaiThacNam(text) {
    let rs = false;
    if(text > 0) rs = true;
    return rs;
}

function regexSoQuyetDinh(text) {
    let rs = false;
    if(text.length > 0) rs = true;
    return rs;
}

function regexThoiHanKhaiThac(text) {
    let rs = false;
    if(text > 0) rs = true;
    return rs;
}

function regexTruLuongDiaChat(text) {
    let rs = false;
    if(text > 0) rs = true;
    return rs;
}

function regexCongSuatKhaiThac(text) {
    let rs = false;
    if(text > 0) rs = true;
    return rs;
}

function regexDienTichKhaiThac(text) {
    let rs = false;
    if(text > 0) rs = true;
    return rs;
}

function regexMaThieBi(text) {
    let rs = false;
    if(text.length > 0) rs = true;
    return rs;
}

function regexTenThieBi(text) {
    let rs = false;
    if(text.length > 0) rs = true;
    return rs;
}

function regexTaiKhoan(text) {
    let rs = false;
    if(text.length > 1) rs = true;
    return rs;
}

function regexMatKhau(text) {
    let rs = false;
    if(text.length > 1) rs = true;
    return rs;
}

function regexTenThuTuc(text) {
    let rs = false;
    if(text.length > 2) rs = true;
    return rs;
}

function regexDate(text) {
    return /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(text);
}
//end regex field

//set disable button save
function setDisabledButton(selector, check) {
    selector.prop('disabled', check);
}
//end set disable button save

//back-history
function buttonBackHistory() {
    $("#back-history").click(function () {
        window.history.back();
    })
}
//end-back-history


//format date iso backend
function formatDate(date) {
    if (date !== null) {
        let format = date.trim().split("-");
        console.log(format[2]+"/"+format[1]+"/"+format[0]);
        return format[2]+"/"+format[1]+"/"+format[0];
    }
}

function compareDate(date1, date2) {
    return convertDateISO(date1).getTime() < convertDateISO(date2).getTime();
}

function convertDateISO(date) {
    date = date.split("/");
    date = date.reverse().join("/");
    return new Date(date);
}
//end format date

//alter
function alterImage(text) {
    $.notify({
        icon: 'resources/dist/img/logo-tamviet-01.png',
        title: 'Tavi MRS',
        message: text
    }, {
        delay: 3000,
        offset: {x: 15, y:15},
        icon_type: 'image',
        type: 'minimalist',
        template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
            '<img data-notify="icon" class="img-circle pull-left">' +
            '<div class="text-mess">' +
            '<span data-notify="title">{1}</span>' +
            '<span data-notify="message">{2}</span>' +
            '</div>' +
            '</div>'
    });
}

function alterSuccess(text, time = 2000) {
    $.notify({
        icon: 'far fa-check-circle',
        message: text
    }, {
        delay: time,
        offset: {x: 15, y: 15},
        type: 'success',
    });
}

function alterInfo(text, time = 2000) {
    $.notify({
        icon: 'fas fa-info-circle',
        message: text
    }, {
        delay: time,
        offset: {x: 15, y: 15},
        type: 'info',
    });
}

function alterWarning(text, time = 2000) {
    $.notify({
        icon: 'fas fa-exclamation',
        message: text
    }, {
        delay: time,
        offset: {x: 15, y: 15},
        type: 'warning',
    });
}

function alterDanger(text, time = 2000) {
    $.notify({
        icon: 'fas fa-exclamation-triangle',
        message: text
    }, {
        delay: time,
        offset: {x: 15, y: 15},
        type: 'danger',
    });
}
//end alter
function viewDateVn() {
    if ($(".date-vn").length > 0) {
        $(".date-vn").datepicker({
            language: "vi"
        });
    }
}

function viewDateTime(date) {
    if(date !== null) date = new Date(date).toLocaleString();
    return date;
}

function viewSrc(src, check) {
    return url_img+src;
}

function runOwlCarousel() {
    let selector = $(".owl-carousel");
    selector.owlCarousel({
        loop:true,
        dots:true,
        nav:true,
        margin:10,
        autoHeight:true,
        responsive:{
            0:{
                items:1
            }
        }
    });
    selector.on('mousewheel', '.owl-stage', function (e) {
        if (e.deltaY>0) {
            selector.trigger('next.owl');
        } else {
            selector.trigger('prev.owl');
        }
        e.preventDefault();
    });
}

function replaceOwlCarousel(html) {
    $('.owl-carousel').trigger('replace.owl.carousel', html).trigger('refresh.owl.carousel');
}

function viewNgayBaoCao(valTuNgay, valDenNgay) {
    let text = "";
    if (valTuNgay !== "")  text += `Từ ngày ${valTuNgay}`;
    if (valDenNgay !== "") text += ` đến ${valDenNgay}`;
    return text !== "" ? `(${text})` : "Từ trước đến nay";
}

function textToIconFile(nameFile) {
    if((/(.doc|.docx)$/ig).test(nameFile)) return '<i class="fas fa-file-word text-primary"></i>';
    if((/(xls|xlsx)$/ig).test(nameFile)) return '<i class="fas fa-file-excel text-success"></i>';
    if((/(ppt|pptx)$/ig).test(nameFile)) return '<i class="fas fa-file-powerpoint text-danger"></i>';
    if((/(zip|rar|tar|gzip|gz|7z)$/ig).test(nameFile)) return '<i class="fas fa-file-archive text-muted"></i>';
    if((/(htm|html)$/ig).test(nameFile)) return '<i class="fas fa-file-code text-info"></i>';
    if((/(txt|ini|csv|java|php|js|css)$/ig).test(nameFile)) return '<i class="fas fa-file-text text-info"></i>';
    if((/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/ig).test(nameFile)) return '<i class="fas fa-file-movie-o text-warning"></i>';
    if((/(mp3|wav)$/ig).test(nameFile)) return '<i class="fas fa-file-audio text-warning"></i>';
    if((/(.jpg|.png|.gif)$/ig).test(nameFile)) return '<i class="fas fa-file-image text-primary"></i>';
    if((/(.pdf)$/ig).test(nameFile)) return '<i class="fas fa-file-pdf text-danger"></i>';
    return '<i class="fas fa-file"></i>'
}

function clickPrintElement(selector) {
    $('#btn-print').on("click", function () {
        console.log("in");
        $(selector).printThis({
            importCSS: true,
            printDelay: 333,
        });
    });
}

function getMonthAndYear() {
    let date = new Date();
    return {
        month: date.getMonth() + 1,
        year: date.getFullYear()
    }
}

function drawChart(selector, options) {
    $(selector).CanvasJSChart(options);
}