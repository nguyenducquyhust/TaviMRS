async function baoCaoFindAll(page = 1, size = 10) {
    return ajaxGet(`v1/admin/bao-cao/find-all?page=${page}&size=${size}`,1);
}

async function baoCaoSearchDoanhNghiep(ngayDau = null, ngayCuoi = null, tieuDe = "", doanhNghiep = "", page = 1, size = 10) {
    return ajaxGet(`v1/admin/bao-cao/search-doanh-nghiep?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}tieu-de=${tieuDe}&doanh-nghiep=${doanhNghiep}&page=${page}&size=${size}`,1);
}

async function baoCaoFindById(id) {
    return ajaxGet(`v1/admin/bao-cao/find-by-id?id=${id}`,1);
}

async function baoCaoFindByThongBao(id, page = 1, size = 10) {
    return ajaxGet(`v1/admin/bao-cao/find-by-thong-bao?thong-bao-id=${id}`,1, page = 1, size = 10);
}