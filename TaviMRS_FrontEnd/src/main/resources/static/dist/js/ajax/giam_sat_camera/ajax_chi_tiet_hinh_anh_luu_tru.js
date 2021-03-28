var textCongTy, textThoiGian, textDiaDiem, imgHinhAnh;
var idHinhAnh = null;

$(function () {

    textCongTy = $("#text1");
    textThoiGian = $("#text2");
    textDiaDiem = $("#text3");
    imgHinhAnh = $("#img-hinh-anh");

    let href = new URL(window.location.href);
    idHinhAnh = href.searchParams.get("id");

    if(idHinhAnh !== null) {
        hinhAnhLuuTruFindById(idHinhAnh).then(rs => {
            if(rs.result === "found") {
                setViewChiTietHinhAnhLuuTru(rs.data);
            } else {
                window.history.back();
            }
        }).catch(err => {
            window.history.back();
            console.log(err);
        })
    } else window.history.back();
})

function setViewChiTietHinhAnhLuuTru(data) {
    textCongTy.html(`<strong>Công ty:</strong> ${viewField(data.mo !== null ? data.mo.doanhNghiep !== null ? data.mo.doanhNghiep.tenDoanhNghiep : null : null)}`);
    textThoiGian.html(`<strong>Thời gian:</strong> ${viewField(viewDateTime(data.thoiGian))}`);
    textDiaDiem.html(`<strong>Địa điểm:</strong> ${viewField(data.diaDiem)}`);
    imgHinhAnh.attr("src", viewSrc(data.hinhAnh));
}



