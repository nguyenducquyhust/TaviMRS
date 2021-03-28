async function giayPhepKhaiThacFindByDoanhNghiep(doanhNghiepId, page = 1, size = 10) {
    return ajaxGet(`v1/admin/giay-phep-khai-thac/find-by-doanh-nghiep?doanh-nghiep-id=${doanhNghiepId}&page=${page}&size=${size}`,1);
}

async function giayPhepKhaiThacBaoCao(ngayDau = null, ngayCuoi = null, khoangSanIds = [0], huyenId = 0) {
    return ajaxGet(`v1/admin/thong-ke/doanh-nghiep-khai-thac/?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}khoang-san-ids=${khoangSanIds}&huyen-id=${huyenId}`,1);
}

async function giayPhepKhaiThacBaoCaoExcel(ngayDau = null, ngayCuoi = null, data) {
    return ajaxPost(`v1/admin/thong-ke/doanh-nghiep-khai-thac/xuat-exel?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}`, data,1);
}

async function giayPhepKhaiThacBaoCaoWord(ngayDau = null, ngayCuoi = null, data) {
    return ajaxPost(`v1/admin/thong-ke/doanh-nghiep-khai-thac/xuat-docx?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}`, data, 1);
}

async function giayPhepKhaiThacBaoCaoPDF(ngayDau = null, ngayCuoi = null, data) {
    return ajaxPost(`v1/admin/thong-ke/doanh-nghiep-khai-thac/xuat-pdf?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}`, data, 1);
}

async function giayPhepKhaiThacFindById(id) {
    return ajaxGet(`v1/admin/giay-phep-khai-thac/find-by-id?giay-phep-id=${id}`,1);
}

async function updateGiayPhepKhaiThac(giayPhepKhaiThac) {
    return ajaxPut(`v1/admin/giay-phep-khai-thac/update`, giayPhepKhaiThac,1);
}

function viewTrangThaiGiayPheoKhaiThac(giayPhep) {
    return HIEU_LUC[giayPhep.trangThai];
}

function viewThamQuyenCap(giayPhep) {
    return THAM_QUYEN[giayPhep.thamQuyenCap];
}

async function giayPhepKhaiThacFindAll(page = 1, size = 10) {
    return ajaxGet(`v1/admin/giay-phep-khai-thac/find-all?page=${page}&size=${size}`,1);
}

async function giayPhepKhaiThacSearch(idMo = 0, loaiGiayPhepId = 0, trangThai = -1, thamQuyenCap = -1, soQuyetDinh = "", page = 1, size = 10) {
    return ajaxGet(`v1/admin/giay-phep-khai-thac/search?id-mo=${idMo}&loai-giay-phep-id=${loaiGiayPhepId}&trang-thai=${trangThai}&tham-quyen-cap=${thamQuyenCap}&so-quyet-dinh=${soQuyetDinh}&page=${page}&size=${size}`,1);
}