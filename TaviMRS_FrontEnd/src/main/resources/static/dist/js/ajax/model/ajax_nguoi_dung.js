async function nguoiDungFindByDoanhNghiep(doanhNghiepId, page = 1, size = 10) {
    return ajaxGet(`v1/admin/nguoi-dung/find-by-doanh-nghiep?doanh-nghiep-id=${doanhNghiepId}&page=${page}&size=${size}`,1);
}

async function nguoiDungSearch(tenDangNhap, idDoanhNghiep, trangThai, page = 1, size = 10) {
    return ajaxGet(`v1/admin/nguoi-dung/search-tcnd?ten-dang-nhap=${tenDangNhap}&id-doanh-nghiep=${idDoanhNghiep}&trang-thai=${trangThai}&page=${page}&size=${size}`,1)
}

async function nguoiDungFindAll(page = 1, size = 10) {
    return ajaxGet(`v1/admin/nguoi-dung/find-all?page=${page}&size=${size}`,1);
}

async function nguoiDungFindByMoIdAndChucVuAndTrangThai(page = 1, size = 10) {
    return ajaxGet(`v1/admin/nguoi-dung/search?page=${page}&size=${size}`, 1);
}

async function nguoiDungLogin(loginForm) {
    return ajaxPost(`v1/public/user/login`,loginForm,2);
}

async function thongTinNguoiDungFindById(ids) {
    return ajaxGet(`v1/public/user/find-by-ids?listIds=${ids}&integer=1999`,2)
}

async function updateNguoiDung(nguoiDung) {
    return ajaxPut(`v1/admin/nguoi-dung/update`,nguoiDung, 1);
}

async function register(registerForm) {
    return ajaxPost(`v1/public/user/register`, registerForm, 2);
}

async function uploadNguoiDung(nguoiDung, doanhNghiepId) {
    return ajaxPost(`v1/admin/nguoi-dung/upload?doanh-nghiep-id=${doanhNghiepId}`, nguoiDung, 1);
}

async function nguoiDungFindById(id) {
    return ajaxGet(`v1/admin/nguoi-dung/find-by-id?nguoi-dung-id=${id}`);
}

async function nguoiDungFindAllGroupDoanhnghiep() {
    return ajaxGet(`v1/admin/nguoi-dung/find-all-group-doanh-nghiep`);
}

function viewTrangThaiNguoiDung(nguoiDung) {
    let {trangThai} = nguoiDung;
    // return trangThai === 0 ? "Đang Hoạt động" ? trangThai === 1 : "Khóa tạm thời" : "Khóa vĩnh viễn";
    return TRANG_THAI_TAI_KHOAN[trangThai];
}


