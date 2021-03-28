//Ajax doanh nghiep

async function doanhNghiepFindAll(page = 1, size = 999999) {
    return ajaxGet(`v1/admin/doanh-nghiep/find-all?page=${page}&size=${size}`,1);
}

async function doanhNghiepFindById(id) {
    return ajaxGet(`v1/admin/doanh-nghiep/find-by-id?id=${id}`,1);
}

async function thongTinDoanhNghiepFindById(id) {
    return ajaxGet(`v1/public/organization/find-by-id?id=${id}`,2);
}

async function updateThongTinDoanhNghiep(doanhNghiep) {
    return ajaxPut(`v1/admin/organization/update`, doanhNghiep, 2);
}

async function uploadThongTinDoanhNghiep(doanhNghiep, arrNN, location, xaId) {
    return ajaxPost(`v1/admin/organization/upload?career-id=${arrNN}&location=${location}&commune-id=${xaId}`, doanhNghiep, 2);
}

async function uploadDoanhNghiep(doanhNghiep) {
    return ajaxPost(`v1/admin/doanh-nghiep/upload`, doanhNghiep, 1);
}

async function updateCauHinh(doanhNghiep) {
    return ajaxPut(`v1/admin/doanh-nghiep/update-cau-hinh`, doanhNghiep, 1);
}

async function updateDoanhNghiep(doanhNghiep) {
    return ajaxPut(`v1/admin/doanh-nghiep/update`, doanhNghiep, 1);
}

function viewTrangThaiDoanhNghiep(doanhNghiep) {
    return TRANG_THAI_DOANH_NGHIEP[doanhNghiep.trangThaiPhanMem];
}

async function doanhNghiepFindByTenAndDiaChiAndTrangThaiPhanMem(tenDoanhNghiep= "", diaChi = "", trangThaiPhanMem, page = 1, size = 10) {
    return ajaxGet(`v1/admin/doanh-nghiep/search?ten-doanh-nghiep=${tenDoanhNghiep}&dia-chi=${diaChi}&trang-thai-phan-mem=${trangThaiPhanMem}&page=${page}&size=${size}`,1)
}

function setListDoanhNghiep(selector) {
    doanhNghiepFindAll().then(rs => {
        if(rs.result === "found") {
            rs = rs.data.currentElements;
            let viewSelect = "<option value=0>Tất Cả</option>";
            viewSelect += rs.map(item => `<option value=${item.idDoanhNghiep}>${item.tenDoanhNghiep}</option>`).join('');
            selector.html(viewSelect);
        }
    }).catch(err => {
        console.log(err)
    })
}

function setListDN(selector) {
    doanhNghiepFindAll(1,99999).then(rs => {
        if(rs.result === "found") {
            rs = rs.data.currentElements;
            let viewSelect = "<option value=0>Tất Cả</option>";
            viewSelect += rs.map(item => `<option value=${item.idDoanhNghiep}>${item.tenDoanhNghiep}</option>`).join('');
            selector.html(viewSelect);
        }
    }).catch(err => {
        console.log(err)
    })
}




//End ajax doanh nghiep