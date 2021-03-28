async function hinhAnhLuuTruFindAllSort(sort = false, page = 1, size = 12) {
    return ajaxGet(`v1/admin/hinh-anh-luu-tru/find-all-sort?page=${page}&size=${size}&sort=${sort}`);
}

async function hinhAnhLuuTruSearchDoanhNghiepSort(doanhNghiepId = 0, ngayDau = null, ngayCuoi = null, sort=false, page = 1, size = 12) {
    return ajaxGet(`v1/admin/hinh-anh-luu-tru/search-doanh-nghiep-sort?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}&doanh-nghiep-id=${doanhNghiepId}&sort=${sort}&page=${page}&size=${10}`,1);
}

async function hinhAnhLuuTruFindById(id) {
    return ajaxGet(`v1/admin/hinh-anh-luu-tru/find-by-id?id=${id}`);
}