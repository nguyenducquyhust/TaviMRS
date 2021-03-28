//Ajax loai khoang san

async function provinceFindByCountry(id) {
    return ajaxGet(`v1/public/province/find-by-country?country-id=${id}`,2);
}

async function districtFindByProvince(id) {
    console.log("huyen");
    return ajaxGet(`v1/public/district/find-by-province?province-id=${id}`,2);
}

async function communeFindByDistrict(id) {
    console.log("xa");
    return ajaxGet(`v1/public/commune/find-by-district?district-id=${id}`,2);
}

function setListTinhHuyenXa(selector,rs) {
    if (rs.message === "found") {
        rs = rs.data;
        let viewSelect = rs.map(data => `<option value="${data.id}">${data.name}</option>`).join('');
        selector.html(viewSelect);
    }
}

//end ajax dia gioi