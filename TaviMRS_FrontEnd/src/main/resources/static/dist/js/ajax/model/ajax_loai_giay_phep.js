async function loaiGiayPhepFindAll() {
    return ajaxGet(`v1/admin/loai-giay-phep/find-all`,1);
}

async function loaiGiayPhepFindAllTCGP(page = 1, size = 99999) {
    return ajaxGet(`v1/admin/loai-giay-phep/find-all-tcgp?page=${page}&size=${size}`,1);
}

async function setListLoaiGiayPhep(selector) {
    //khoangSanSearch('%',0, 1, 999).
    let arrLoaiGiayPhep = [];
    await loaiGiayPhepFindAll().then(rs => {
        if(rs.result === "found") {
            rs = rs.data;
            arrLoaiGiayPhep = rs;
            let viewSelect = rs.map(item => `<option value=${item.idLoaiGiayPhep}>${item.loaiGiayPhep}</option>`).join('');
            $(selector).html(viewSelect);
        }
    }).catch(err => {
        console.log(err)
    })
    return arrLoaiGiayPhep;
}

function setListLGP(selector) {
    loaiGiayPhepFindAllTCGP(1,99999).then(rs => {
        if(rs.result === "found") {
            rs = rs.data.currentElements;
            let viewSelect = "<option value=0>Tất Cả</option>";
            viewSelect += rs.map(item => `<option value=${item.idLoaiGiayPhep}>${item.loaiGiayPhep}</option>`).join('');
            selector.html(viewSelect);
        }
    }).catch(err => {
        console.log(err)
    })
}