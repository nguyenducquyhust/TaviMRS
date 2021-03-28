//Ajax loai khoang san

async function khoangSanSearch(ten = '%', nhomKhoangSanId = 0, page = 1, size = 10) {
    return ajaxGet(`v1/admin/khoang-san/search?ten=${ten}&nhom-khoang-san-id=${nhomKhoangSanId}&page=${page}&size=${size}`,1);
}

async function khoangSanFindAll() {
    return ajaxGet(`v1/admin/khoang-san/find-all`,1);
}

function setListKhoangSan(selector) {
    //khoangSanSearch('%',0, 1, 999).
    khoangSanFindAll().then(rs => {
        if(rs.result === "found") {
            rs = rs.data;
            let viewSelect = rs.map(item => `<option value=${item.idKhoangSan}>${item.tenKhoangSan}</option>`).join('');
            selector.html(viewSelect);
        }
    }).catch(err => {
        console.log(err)
    })
}

//end ajax loai khoang san