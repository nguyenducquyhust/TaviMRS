async function thietBiFindByDoanhNghiep(doanhNghiepId, page = 1, size = 10) {
    return ajaxGet(`v1/admin/thiet-bi/find-by-doanh-nghiep?doanh-nghiep-id=${doanhNghiepId}&page=${page}&size=${size}`,1);
}

async function thietBiFindByMoAndTrangThaiAndTen(moId = 0, trangThaiT = 0,tenThietBi = "", page = 1, size = 10) {
    return ajaxGet(`v1/admin/thiet-bi/find-by-mo-and-trang-thai-and-ten?mo-id=${moId}&trang-thai=${trangThaiT}&ten=${tenThietBi}&page=${page}&size=${size}`,1);
}

async function thietBiSearch(idMo = 0, trangThaiKetNoi = -1, maThietBi = "", page = 1, size = 10) {
    return ajaxGet(`v1/admin/thiet-bi/search?id-mo=${idMo}&trang-thai-ket-noi=${trangThaiKetNoi}&ma-thiet-bi=${maThietBi}&page=${page}&size=${size}`,1)
}

async function thietBiFindAll(page = 1, size = 10) {
    return ajaxGet(`v1/admin/thiet-bi/find-all?page=${page}&size=${size}`,1);
}

async function thietBiFindById(id) {
    return ajaxGet(`v1/admin/thiet-bi/find-by-id?id=${id}`);
}

async function updateThietBi(thietBi) {
    return ajaxPut(`v1/admin/thiet-bi/update`,thietBi,1);
}

function viewTrangThaiKetNoiThietBi(thietBi) {
    return TRANG_THAI_KET_NOI_THIET_BI[thietBi.trangThaiKetNoi];
}

function viewTrangThaiThietBi(thietBi) {
    let {trangThai} = thietBi;
    return TRANG_THAI_XU_LY[trangThai];
}
