async function bieuDoTongHop(ngayDau = null, ngayCuoi = null) {
    return ajaxGet(`v1/admin/bieu-do/bieu-do-tong-hop?${ngayDau === null ? '' : `ngay-dau=${ngayDau}&`}${ngayCuoi === null ? '' : `ngay-cuoi=${ngayCuoi}&`}`,1);
}