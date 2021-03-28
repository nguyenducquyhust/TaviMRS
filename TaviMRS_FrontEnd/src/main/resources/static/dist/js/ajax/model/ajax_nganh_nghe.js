async function carrerFindAll() {
    return ajaxGet(`v1/public/career/find-all`,2);
}

async function carrerfindCarerrByOrganization(id) {
    return ajaxGet(`v1/public/organization-career/find-career-by-organization?organization-id=${id}`,2);
}

async function organizationCareerUpdateList(organizationId, careers) {
    return ajaxPut(`v1/admin/organization-career/update-list-career?organizationId=${organizationId}&careers=${careers}`,null, 2);
}


function setSelectCarrer(selector, idDoanhNghiep) {
    let result = [];
    carrerfindCarerrByOrganization(idDoanhNghiep).then(rs => {
        if(rs.message === "found") {
            rs = rs.data;
            result = rs;
            arrSelect = rs.map(data => {
                return data.id + "";
            })
            selector.val(arrSelect);
            selector.select2({templateSelection: formatSelection}).trigger("change");
        }
    }).catch(err => console.log(err));
}

async function setListCarrer(selector) {
    await carrerFindAll().then(rs => {
        if (rs.message === "found") {
            rs = rs.data;
            let viewSelect = rs.map(data =>  `<option value="${data.id}">${data.code}:&nbsp;${data.name}</option>`).join('');
            selector.html(viewSelect);
            selector.select2({templateSelection: formatSelection});
        }
    }).catch(err => {
        console.log(err)
    })
}

function formatSelection(val) {
    return val.text.split(":")[0].trim();
}

