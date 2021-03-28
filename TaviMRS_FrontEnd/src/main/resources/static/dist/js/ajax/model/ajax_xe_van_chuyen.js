async function xeVanChuyenFindByDoanhNghiep(doanhNghiepId, page = 1, size = 10) {
    return ajaxGet(`v1/admin/xe-van-chuyen/find-by-doanh-nghiep?doanh-nghiep-id=${doanhNghiepId}&page=${page}&size=${size}`,1);
}

async function xeVanChuyenFindById(id) {
    return ajaxGet(`v1/admin/xe-van-chuyen/find-by-id?xe-van-chuyen-id=${id}`,1);
}

async function updateXeVanChuyen(xeVanChuyen) {
    return ajaxPut(`v1/admin/xe-van-chuyen/update`,xeVanChuyen,1);
}

function viewTrangThaiXeVanChuyen(xeVanChuyen) {
    let {trangThai} = xeVanChuyen;
    return TRANG_THAI_DUYET[trangThai];
}

async function xeVanChuyenFindAll(page = 1, size = 10) {
    return ajaxGet(`v1/admin/xe-van-chuyen/find-all?page=${page}&size=${size}`,1);
}

async function xeVanChuyenSearch(bienSoXe = "", trangThai = -1, page = 1, size = 10) {
    return ajaxGet(`v1/admin/xe-van-chuyen/search?bien-so-xe=${bienSoXe}&trang-thai=${trangThai}&page=${page}&size=${size}`,1)
}