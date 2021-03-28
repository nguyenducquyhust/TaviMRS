function videoLuuTruFindByViPhamAll(id) {
    return ajaxGet(`v1/admin/video-luu-tru/find-by-vi-pham?vi-pham-id=${id}`,1);
}