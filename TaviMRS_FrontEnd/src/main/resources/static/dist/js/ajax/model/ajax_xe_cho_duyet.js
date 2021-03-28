
async function xcdFindAll(page = 1, size = 10) {
    return ajaxGet(`v1/admin/xe-van-chuyen/find-by-trang-thai?page=${page}&size=${size}`,1)
}

async function xcdSearch(doanhNghiepId, bienSoXe, page = 1, size = 10) {
    return ajaxGet(`v1/admin/xe-van-chuyen/search-xe-cho-duyet?doanh-nghiep-id=${doanhNghiepId}&bien-so-xe=${bienSoXe}&page=${page}&size=${size}`,1)
}

function viewTrangThaiXeChoDuyet(xechoduyet) {
    return TRANG_THAI_DUYET[xechoduyet.trangThai];
}