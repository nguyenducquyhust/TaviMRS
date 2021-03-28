async function cameraFindAll(page = 1, size = 4) {
    return ajaxGet(`v1/admin/camera/find-all?page=${page}&size=${size}`,1);
}

async function cameraFindByDoanhNghiep(doanhNghiepId, page = 1, size = 4) {
    return ajaxGet(`v1/admin/camera/find-by-doanh-nghiep?doanh-nghiep-id=${doanhNghiepId}&page=${page}&size=${size}`,1);
}

async function cameraFindByMo(moId, page = 1, size = 16) {
    return ajaxGet(`v1/admin/camera/find-by-mo?mo-id=${moId}&page=${page}&size=${size}`,1);
}

