async function nhatKyXeVaoMoBaoCao(ngayDau = null, ngayCuoi = null, doanhNghiepId) {
    return ajaxGet(`v1/admin/thong-ke/luong-xe-vao-mo/bao-cao?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}doanh-nghiep-id=${doanhNghiepId}`,1);
}

async function nhatKyXeVaoMoChiTiet(xeVanChuyenId, moId, ngayDau = null, ngayCuoi = null) {
    return ajaxGet(`v1/admin/thong-ke/nhat-ky-xe/?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}xe-id=${xeVanChuyenId}&mo-id=${moId}`,1);
}

async function nhatKyXeVaoMoChiTietExcel(xeVanChuyenId, moId, ngayDau = null, ngayCuoi = null) {
    return ajaxGet(`v1/admin/thong-ke/nhat-ky-xe/xuat-excel?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}xe-id=${xeVanChuyenId}&mo-id=${moId}`,1);
}

async function nhatKyXeVaoMoChiTietWord(xeVanChuyenId, moId, ngayDau = null, ngayCuoi = null) {
    return ajaxGet(`v1/admin/thong-ke/nhat-ky-xe/xuat-word?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}xe-id=${xeVanChuyenId}&mo-id=${moId}`,1);
}

async function nhatKyXeVaoMoChiTietPDF(xeVanChuyenId, moId, ngayDau = null, ngayCuoi = null) {
    return ajaxGet(`v1/admin/thong-ke/nhat-ky-xe/xuat-pdf?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}xe-id=${xeVanChuyenId}&mo-id=${moId}`,1);
}

async function nhatKyXeVaoMoBaoCaoExcel(ngayDau = null, ngayCuoi = null, doanhNghiepId) {
    return ajaxGet(`v1/admin/thong-ke/luong-xe-vao-mo/xuat-excel?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}doanh-nghiep-id=${doanhNghiepId}`,1);
}

async function nhatKyXeVaoMoBaoCaoWord(ngayDau = null, ngayCuoi = null, doanhNghiepId) {
    return ajaxGet(`v1/admin/thong-ke/luong-xe-vao-mo/xuat-word?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}doanh-nghiep-id=${doanhNghiepId}`,1);
}

async function nhatKyXeVaoMoBaoCaoPDF(ngayDau = null, ngayCuoi = null, doanhNghiepId) {
    return ajaxGet(`v1/admin/thong-ke/luong-xe-vao-mo/xuat-pdf?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}doanh-nghiep-id=${doanhNghiepId}`,1);
}