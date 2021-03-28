async function giayPhepTheoThuTucFindByThuTuc(id) {
    return ajaxGet(`v1/admin/giay-phep-theo-thu-tuc/find-by-thu-tuc?thu-tuc-id=${id}`);
}