function viewTrangThaiViPham (viPham) {
    let {trangThaiXuLy} = viPham;
    return TRANG_THAI_VI_PHAM[trangThaiXuLy];
}

function baoCaoViPham(ngayDau = null, ngayCuoi = null, doanhNghiepId = 0, loaiViPhamId = 0) {
    return ajaxGet(`v1/admin/thong-ke/sai-pham/?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}doanh-nghiep-id=${doanhNghiepId}&loai-vi-pham=${loaiViPhamId}`,1);
}

function baoCaoViPhamExcel(ngayDau = null, ngayCuoi = null, doanhNghiepId = 0, loaiViPhamId = 0){
    return ajaxGet(`v1/admin/thong-ke/sai-pham/xuat-excel?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}doanh-nghiep-id=${doanhNghiepId}&loai-vi-pham=${loaiViPhamId}`,1);
}

function baoCaoViPhamWord(ngayDau = null, ngayCuoi = null, doanhNghiepId = 0, loaiViPhamId = 0) {
    return ajaxGet(`v1/admin/thong-ke/sai-pham/xuat-word?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}doanh-nghiep-id=${doanhNghiepId}&loai-vi-pham=${loaiViPhamId}`,1);
}

function baoCaoViPhamPDF(ngayDau = null, ngayCuoi = null, doanhNghiepId = 0, loaiViPhamId = 0) {
    return ajaxGet(`v1/admin/thong-ke/sai-pham/xuat-pdf?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}doanh-nghiep-id=${doanhNghiepId}&loai-vi-pham=${loaiViPhamId}`,1);
}

function viPhamFindById(id) {
    return ajaxGet(`v1/admin/vi-pham/find-by-id?vi-pham-id=${id}`,1);
}
