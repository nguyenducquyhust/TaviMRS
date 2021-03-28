async function thuTucFindByDoanhNghiep(doanhNghiepId, page = 1, size = 10) {
    return ajaxGet(`v1/admin/thu-tuc/find-by-doanh-nghiep?doanh-nghiep-id=${doanhNghiepId}&page=${page}&size=${size}`,1);
}

async function thuTucFindBydId(id) {
    return ajaxGet(`v1/admin/thu-tuc/find-by-id?thu-tuc-id=${id}`,1);
}

async function updateThuTuc(thuTuc) {
    return ajaxPut(`v1/admin/thu-tuc/update`,thuTuc, 1);
}

function viewTrangThaiThuTuc(thuTuc) {
    let {giayPhepKhaiThac} = thuTuc;
    return giayPhepKhaiThac !== null ?  viewTrangThaiGiayPheoKhaiThac(giayPhepKhaiThac) : "";
}
