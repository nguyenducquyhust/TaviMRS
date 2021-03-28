async function loaiViPhamFindAll() {
    return ajaxGet(`v1/admin/loai-vi-pham/find-all`,1);
}

function setListLoaiViPham(selector) {
    loaiViPhamFindAll().then(rs => {
        if(rs.result === "found") {
            rs = rs.data;
            let viewSelect = "<option value='0'>Tất cả</option>"
            viewSelect += rs.map(item => `<option value=${item.idLoaiViPham}>${item.viPham}</option>`).join('');
            selector.html(viewSelect);
        }
    }).catch(err => {
        console.log(err)
    })
}