async function giayPhepTheoLoaiThuTucFindByLoaiThuTuc(id) {
    return ajaxGet(`v1/admin/giay-phep-theo-loai-thu-tuc/find-by-loai-thu-tuc?loai-thu-tuc-id=${id}`);
}