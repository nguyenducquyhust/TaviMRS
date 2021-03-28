function hinhAnhViPhamFindByViPhamAll(id) {
    return ajaxGet(`v1/admin/hinh-anh-vi-pham/find-by-vi-pham-all?vi-pham-id=${id}`,1);
}

async function hinhAnhViPhamFindAllSort(sort = false, page = 1, size = 12) {
    return ajaxGet(`v1/admin/hinh-anh-vi-pham/find-all-sort?page=${page}&size=${size}&sort=${sort}`);
}

async function hinhAnhViPhamSearchDoanhNghiepSort(doanhNghiepId = 0, loaiViPhamId = 0, ngayDau = null, ngayCuoi = null, sort=false, page = 1, size = 12) {
    return ajaxGet(`v1/admin/hinh-anh-vi-pham/search-doanh-nghiep-sort?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}&doanh-nghiep-id=${doanhNghiepId}&loai-vi-pham-id=${loaiViPhamId}&sort=${sort}&page=${page}&size=${10}`,1);
}