async function nhatKyKhaiThacBaoCao(ngayDau = null, ngayCuoi = null, khoangSanIds = [0], huyenId = 0) {
    return ajaxGet(`v1/admin/thong-ke/tru-luong-khai-thac/?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}khoang-san-ids=${khoangSanIds}&huyen-id=${huyenId}`,1);
}

async function nhatKyKhaiThacBaoCaoExcel(ngayDau = null, ngayCuoi = null, khoangSanIds = [0], huyenId = 0) {
    return ajaxGet(`v1/admin/thong-ke/tru-luong-khai-thac/xuat-excel?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}khoang-san-ids=${khoangSanIds}&huyen-id=${huyenId}`,1);
}

async function nhatKyKhaiThacBaoCaoWord(ngayDau = null, ngayCuoi = null, khoangSanIds = [0], huyenId = 0) {
    return ajaxGet(`v1/admin/thong-ke/tru-luong-khai-thac/?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}khoang-san-ids=${khoangSanIds}&huyen-id=${huyenId}`,1);
}

async function nhatKyKhaiThacBaoCaoPDF(ngayDau = null, ngayCuoi = null, khoangSanIds = [0], huyenId = 0) {
    return ajaxGet(`v1/admin/thong-ke/tru-luong-khai-thac/xuat-pdf?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}khoang-san-ids=${khoangSanIds}&huyen-id=${huyenId}`,1);
}