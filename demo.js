


//----------------cac ham ajax---------------//

    var inputMaTk, inputHoVaTen, inputTk, intputMk, inputEmail, inputSDT, inputDiaChi
        , inputNgaySinh, inputTGT, inputTGKH, inputTGHH, id,inputSearch;
    $(function () {

        inputMaTk = $("#inputMaTk");
        inputHoVaTen = $("#inputHoVaTen");
        inputTk = $("#inputTk");
        intputMk = $("#intputMk");
        inputEmail = $("#inputEmail");
        inputSDT = $("#inputSDT");
        inputDiaChi = $("#inputDiaChi");
        inputNgaySinh = $("#inputNgaySinh");
        inputTGT = $("#inputTGT");
        inputTGKH = $("#inputTGKH");
        inputTGHH = $("#inputTGHH");
        inputSearch = $("#inputSearch");

        // set danh sach tai khoan vao table
        thongTinTaiKhoan().then(result => {
            setDSTaiKhoan(result.data.currentElements);
        })
        //setDSTaiKhoan();
        //set 1 tai tai khoan dau tien xuong input
        setTaiKhoan(1);

        //khi click se lay ra tai khoan do
        $(document).on('click', '.chiTietTk ', function () {
            var currentRow = $(this).closest("tr");
            id = currentRow.find("td:eq(0)").attr("id"); // get current row 1st TD value
            console.log(id);
            $("#inputHoVaTen").focus();
            setTaiKhoan(id);
        });

        clickThemMoiTaiKhoan();
        clickCapNhapTaiKhoan();
        clickLamMoiInput();
        clickXoaTaiKhoan();
        clickKhoaTaiKhoan();
    });

//chi tiet ham set danh sach tai khoan
    function setDSTaiKhoan(listTaiKhoan) {
        let taiKhoanViews = ` `;
        let countkViews = ` `;
        //dem so tai khoan
        countAccount().then(count => {
            countkViews = ` <strong class="demtaikhoan">Tổng số tài khoản:<span>${count}</span></strong> `;
            $("#demTaiKhoan").html(countkViews);
        });
        let index = 0;
        listTaiKhoan.map((data) => {
            index = index + 1;
            taiKhoanViews += `<tr class="chiTietTk">
                <td id="${data.id}">${index}</td>
                 <td>${data.maTaiKhoan}</td>
                <td>${data.taiKhoan}</td>
                <td>${viewTrangThaiTaiKhoan(data)}</td>
               <td>
         <div class="chucnang">s
        <i class="fas fa-pen sua"></i>
        <i class="fas fa-lock" data-toggle="modal" data-target="#modal-change"></i>
        <i class="fas fa-trash-alt" data-toggle="modal" data-target="#modal-remove"></i>
        <i class="fas fa-print"></i>
        <i class="fas fa-history"></i>
        <i class="fas fa-user-lock"></i>
      </div>
    </td>
    <td>
     <img src="http://localhost:9999/api/v1/admin/tai-khoan/qrcode/${data.id}" width="100px" height="100px">
    </td>
            </tr>`;
        });
        $(".card-body tbody").html(taiKhoanViews);
    }

//set 1 tai khoan
    function setTaiKhoan(taiKhoanId) {
        taiKhoanFindById(taiKhoanId).then(rs => {
            let viewQR = ``;
            let viewAnhDaiDien = ``;
            if (rs.message === "found") {
                taiKhoan = rs.data;
                viewQR = `<img src="http://localhost:9999/api/v1/admin/tai-khoan/qrcode/${taiKhoanId}" width="100px" height="100px">`;
                $("#qrcode").html(viewQR);
                viewAnhDaiDien = `<img src="${taiKhoan.urlAnhDaiDien}" width="150px" height="150px">`;
                $("#anhdaidien").html(viewAnhDaiDien);
                inputMaTk.val(viewField(taiKhoan.maTaiKhoan));
                inputHoVaTen.val(viewField(taiKhoan.hoVaTen));
                intputMk.val(viewField(taiKhoan.matKhau));
                inputTk.val(viewField(taiKhoan.taiKhoan));
                inputEmail.val(viewField(taiKhoan.email));
                inputSDT.val(viewField(taiKhoan.soDienThoai));
                inputDiaChi.val(viewField(taiKhoan.diaChi));
                inputNgaySinh.val(taiKhoan.ngaySinh);
                inputTGT.val(taiKhoan.thoiGianKhoiTao);
                inputTGKH.val(taiKhoan.thoiGianKichHoat);
                inputTGHH.val(taiKhoan.thoiGianHetHan);
                if (taiKhoan.gioiTinh == 1) {
                    $('#gTNam').prop('checked', true);
                    $('#gTNu').prop('checked', false);
                } else if (taiKhoan.gioiTinh == 0) {
                    $('#gTNam').prop('checked', false);
                    $('#gTNu').prop('checked', true);
                } else {
                    $('#gTNam').prop('checked', false);
                    $('#gTNu').prop('checked', false);
                }

                if (taiKhoan.trangThai == 0) {
                    $('#kichHoat').prop('checked', true);
                    $('#khoa').prop('checked', false);
                } else if (taiKhoan.trangThai == 1) {
                    $('#kichHoat').prop('checked', false);
                    $('#khoa').prop('checked', true);
                } else {
                    $('#kichHoat').prop('checked', false);
                    $('#khoa').prop('checked', false);
                }
            }
        })
    }

//them moi
    function clickThemMoiTaiKhoan() {
        $("#themMoi").click(function () {
            //get data from input
            var maTaiKhoan = inputMaTk.val();
            var hoVaTen = inputHoVaTen.val();
            var taiKhoan = inputTk.val();
            var matKhau = intputMk.val();
            var email = inputEmail.val();
            var soDienThoai = inputSDT.val();
            var diaChi = inputDiaChi.val();
            var ngaySinh = inputNgaySinh.val();
            var thoiGianKhoiTao = inputTGT.val();
            var thoiGianKichHoat = inputTGKH.val();
            var thoiGianHetHan = inputTGHH.val();

            var nguoiDung = {
                maTaiKhoan: maTaiKhoan,
                hoVaTen: hoVaTen,
                taiKhoan: taiKhoan,
                matKhau: matKhau,
                email: email,
                soDienThoai: soDienThoai,
                diaChi: diaChi,
                ngaySinh: ngaySinh,
                thoiGianKhoiTao: thoiGianKhoiTao,
                thoiGianKichHoat: thoiGianKichHoat,
                thoiGianHetHan: thoiGianHetHan
            }

            console.log(nguoiDung);
            if ((regexUsername(taiKhoan) && regexEmail(email) && regexPassword(matKhau))) {
                themMoiTaiKhoan(nguoiDung).then(rs => {
                    if (rs.message == "khong the them moi") {
                        alert(rs.data);
                    } else if (rs.message == "them thanh cong") {
                        alert("thêm mới thành công");
                    } else {
                        alert("thêm mới thất bại");
                    }
                })
            } else {
                alert("Vui lòng nhập đúng định dạng và điền đầy đủ thông tin")
            }
        });
    }

//cap nhap tai khoan
    function clickCapNhapTaiKhoan() {
        $("#submit").click(function () {
            console.log("click update");
            //get data from input
            var maTaiKhoan = inputMaTk.val();
            maTaiKhoan = "Tk-00" + id;
            var hoVaTen = inputHoVaTen.val();
            var taiKhoan = inputTk.val();
            var matKhau = intputMk.val();
            var email = inputEmail.val();
            var soDienThoai = inputSDT.val();
            var diaChi = inputDiaChi.val();
            var ngaySinh = inputNgaySinh.val();
            var thoiGianKhoiTao = inputTGT.val();
            var thoiGianKichHoat = inputTGKH.val();
            var thoiGianHetHan = inputTGHH.val();
            var gioiTinh = $("input[name='gender']:checked").val();
            var trangThai = $("input[name='status']:checked").val();

            var nguoiDung = {
                id: id,
                maTaiKhoan: maTaiKhoan,
                hoVaTen: hoVaTen,
                taiKhoan: taiKhoan,
                matKhau: matKhau,
                email: email,
                soDienThoai: soDienThoai,
                diaChi: diaChi,
                ngaySinh: ngaySinh,
                gioiTinh: gioiTinh,
                thoiGianKhoiTao: thoiGianKhoiTao,
                thoiGianKichHoat: thoiGianKichHoat,
                thoiGianHetHan: thoiGianHetHan,
                trangThai: trangThai
            }
            console.log(nguoiDung);
            if (regexUsername(taiKhoan) && regexEmail(email) && regexPassword(matKhau)) {
                updateTaiKhoan(nguoiDung).then(rs => {
                    console.log(nguoiDung);
                    if (rs.message == "updated") {
                        alert("cập nhật thành công");
                    }
                });
            } else {
                alert("Vui lòng nhập đúng định dạng và điền đầy đủ thông tin");
            }
        });
    }


//lam moi o input
    function clickLamMoiInput() {
        $(".themMoi").click(function () {
            console.log("them  moi");
            $("#themMoi").toggle();
            inputMaTk.val("");
            inputHoVaTen.val("");
            inputTk.val("");
            intputMk.val("");
            inputEmail.val("");
            inputSDT.val("");
            inputDiaChi.val("");
            inputNgaySinh.val("");
            inputTGT.val("");
            inputTGKH.val("");
            inputTGHH.val("");
        });
    }

//xoa tai khoan
    function clickXoaTaiKhoan() {
        $("#confirm-yes").unbind("click").click(function () {
            xoaTaiKhoan(id);
            thongTinTaiKhoan().then(result => {
                setDSTaiKhoan(result.data.currentElements);
            })
        })
    }

//khoa tai khoan
    function clickKhoaTaiKhoan() {
        $("#change-yes").unbind("click").click(function () {
            khoaTaiKhoan(id);
            thongTinTaiKhoan().then(result => {
                setDSTaiKhoan(result.data.currentElements);
            })
        })
    }


    //tim kiem tai khoan
function timKiemTaiKhoan() {
    $("#find").on("click").click(function () {
        let search = inputSearch.val();
        timTaiKhoan(search).then(result => {
            setDSTaiKhoan(result.data.currentElements);
        })
    })
}









