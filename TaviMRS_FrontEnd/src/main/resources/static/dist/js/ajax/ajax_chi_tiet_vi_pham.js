var textCongTy, textThoiGian, textDiaDiem, textMoTa, sttCarousel, imgCarousel, btnTaoBienBan, btnXemVideo, videoCarousel;
var idViPham = null;
var viPham = null;
$(function () {
    textCongTy = $("#text1");
    textThoiGian = $("#text2");
    textDiaDiem = $("#text3");
    textMoTa = $("#text4");
    sttCarousel = $("#stt-carousel");
    imgCarousel = $("#img-carousel");
    btnTaoBienBan = $("#btn-tao-bien-ban");
    btnXemVideo = $("#btn-xem-video");
    videoCarousel = $("#video-carousel");

    let href = new URL(window.location.href);
    idViPham = href.searchParams.get("id");

    runOwlCarousel(videoCarousel);
    clickTaoBaoCaoChiTietViPham();

    if(idViPham !== null) {
        viPhamFindById(idViPham).then(rs => {
            if(rs.result === "found") {
                viPham = rs.data;
                setViewChiTietViPham();
            } else {
                window.history.back();
            }
        }).catch(err => {
            window.history.back();
            console.log(err);
        })
    } else window.history.back();
})

function setViewChiTietViPham() {
    textCongTy.html(`<strong>Công ty:</strong> ${viewField(viPham.doanhNghiep !== null ? viPham.doanhNghiep.tenDoanhNghiep : null)}`);
    textThoiGian.html(`<strong>Thời gian:</strong> ${viewField(viewDateTime(viPham.thoiGianXayRa))}`);
    textDiaDiem.html(`<strong>Địa điểm:</strong> ${viewField(viPham.diaDiemViPham)}`);
    textMoTa.html(`<strong>Mô tả:</strong> ${viewField(viPham.noiDungViPham)}`);
    hinhAnhViPhamFindByViPhamAll(idViPham).then(rs => {
        if(rs.result === "found") {
            setViewCarouselChiTietViPham(rs.data);
        } else {
            alterDanger("Không có hình ảnh phù hơp", TIME_ALTER);
        }
    }).catch(err => {
        alterDanger("Không có hình ảnh phù hơp", TIME_ALTER);
        console.log(err);
    })
    videoLuuTruFindByViPhamAll(idViPham).then(rs => {
        if(rs.result === "found") {
            setViewVideoChiTietViPham(rs.data);
        } else {
            alterDanger("Không có video phù hơp", TIME_ALTER);
        }
    }).catch(err => {
        alterDanger("Không có video phù hơp", TIME_ALTER);
        console.log(err);
    })
}

function setViewCarouselChiTietViPham(list) {
    if (list !== null) {
        let viewDot =  "";
        let viewImage = "";
        list.map((item, index) => {
            viewDot += ` <li data-target="#myCarousel" data-slide-to="${index}"></li>`;
            viewImage += `<div class="item">
                        <img src="${viewSrc(item.hinhAnhLuuTru !== null ? item.hinhAnhLuuTru.hinhAnh : null)}">
                    </div>`;
        })
        sttCarousel.html(viewDot);
        imgCarousel.html(viewImage);
        sttCarousel.find("li:nth-child(1)").addClass("active");
        imgCarousel.find(".item:nth-child(1)").addClass("active");
    }
}

function setViewVideoChiTietViPham(list) {
    if(list !== null) {
        let viewVideo = list.map(item => `<div>
                                <video width="100%" controls>
                                    <source src="${viewSrc(item.duongDan)}" type="video/mp4">
                                </video>
                            </div>`).join("");
        replaceOwlCarousel(viewVideo);
    }
}

function clickTaoBaoCaoChiTietViPham() {
    btnTaoBienBan.click(function () {
        alterWarning("Chức năng này đang phát triển", TIME_ALTER);
    })
}