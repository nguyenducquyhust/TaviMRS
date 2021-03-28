// Ajax Mo

async function moFindAll(page = 1, size = 99999) {
    return ajaxGet(`v1/admin/mo/find-all?page=${page}&size=${size}`,1);
}

async function moFindByDoanhNghiep(doanhNghiepId, page = 1, size = 10) {
    return ajaxGet(`v1/admin/mo/find-by-doanh-nghiep?doanh-nghiep-id=${doanhNghiepId}&page=${page}&size=${size}`,1);
}


async function moFindByKhoangSanAndThongTinDoanhNghiep(ten = "" ,listKhoangSanIds = [0], huyenId = 0, page = 1, size = 10) {
    return ajaxGet(`v1/admin/mo/find-by-khoang-san-and-thong-tin-doanh-nghiep?ten=${ten}&khoang-san-ids=${listKhoangSanIds}&tinh-id=24&huyen-id=${huyenId}&page=${page}&size=${size}`,1);
}

async function moFindById(id) {
    return ajaxGet(`v1/admin/mo/find-by-id?id=${id}`,1);
}

async function updateMo(mo) {
    return ajaxPut(`v1/admin/mo/update`,mo,1);
}

function viewTrangThaiMo(mo) {
    return mo.trangThaiKetNoi ? "Kết nối" : "Không kết nối";
}
// function setListMo(selector) {
//     //khoangSanSearch('%',0, 1, 999).
//     moFindAll(1,9999999).then(rs => {
//         if(rs.result === "found") {
//         rs = rs.data.currentElements;
//         // let viewSelect = "<option value='0'>Tất cả</option>";
//         // viewSelect += rs.map(item => `<option value=${item.idKhoangSan}>${item.tenKhoangSan}</option>`).join('');
//         // selector.html(viewSelect);
//         let viewSelect = "<option value='0'>Tất cả</option>";
//         rs.map( data => {
//             viewSelect += `<option value=${data.idMo}>${data.diaChi}</option>`;
//     })
//         selector.html(viewSelect);
//     }
// }).catch(err => {
//         console.log(err)
//     })
// }
function setListMo(selector) {
    moFindAll(1,99999).then(rs => {
        if(rs.result === "found") {
            rs = rs.data.currentElements;
            let viewSelect = "<option value=0>Tất Cả</option>";
            viewSelect += rs.map(item => `<option value=${item.idMo}>${item.diaChi}</option>`).join('');
            selector.html(viewSelect);
        }
    }).catch(err => {
        console.log(err)
    })
}
// End Ajax Mo