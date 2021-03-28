async function thongBaoFindAll(page = 1, size = 10) {
    return ajaxGet(`v1/admin/thong-bao/find-all?page=${page}&size=${size}`,1);
}

async function thongBaoFindById(id) {
    return ajaxGet(`v1/admin/thong-bao/find-by-id?id=${id}`);
}

// async function thongBaoFindByNgayAndTieuDe(ngayDau = null, ngayCuoi = null,tieuDe = "", page = 1, size = 10) {
//     return ajaxGet(`v1/admin/thong-bao/search?ngay-dau=${ngayDau}&ngay-cuoi=${ngayCuoi}&tieu-de=${tieuDe}&page=${page}&size=${size}`,1);
// }

async function thongBaoFindByNgayAndTieuDe(ngayDau = null, ngayCuoi = null, tieuDe = "", page = 1, size = 10) {
    return ajaxGet(`v1/admin/thong-bao/search?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}tieu-de=${tieuDe}&page=${page}&size=${size}`,1);
}

async function updateThongBao(thongBao) {
    return ajaxPut(`v1/admin/thong-bao/update`,thongBao,1);
}

async function thongBaoSend(listId = [], fileDinhKemList = [], hinhThucGui = 3, noiDung = "", soLuongDonViNhan = 0, tieuDe = "", yeuCauPhanHoi = true) {
    let data = {listId, fileDinhKemList, hinhThucGui, noiDung, soLuongDonViNhan, tieuDe, yeuCauPhanHoi};
    return ajaxPost(`v1/admin/thong-bao/send`,data,1);
}