package com.tavi.tavi_mrs_front_end.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = {"/","/dang-nhap"}, method = RequestMethod.GET)
    public String login() {
        return "dang-nhap";
    }

    @RequestMapping(value = {"/danh-sach-doanh-nghiep"}, method = RequestMethod.GET)
    public String danhSachDoanhNghiep() {
        return "danh-sach-doanh-nghiep";
    }

    @RequestMapping(value = {"/thong-tin-doanh-nghiep"}, method = RequestMethod.GET)
    public String thongTinDoanhNghiep() {
        return "thong-tin-doanh-nghiep";
    }

    @RequestMapping(value = {"/danh-sach-xe-cho-duyet"}, method = RequestMethod.GET)
    public String danhSachXeChoDuyet() {
        return "danh-sach-xe-cho-duyet";
    }

    @RequestMapping(value = {"/tong-hop-danh-sach-doanh-nghiep"}, method = RequestMethod.GET)
    public String tongHopDanhSachDoanhNghiep() {
        return "tong-hop-danh-sach-doanh-nghiep";
    }

    @RequestMapping(value = {"/tong-hop-tru-luong-khai-thac"}, method = RequestMethod.GET)
    public String tongHopTruLuongKhaiThac() {
        return "tong-hop-tru-luong-khai-thac";
    }

    @RequestMapping(value = {"/thong-tin-xe-van-chuyen"}, method = RequestMethod.GET)
    public String thongTinXeVanChuyen() {
        return "thong-tin-xe-van-chuyen";
    }

    @RequestMapping(value = {"/thong-tin-mo"}, method = RequestMethod.GET)
    public String thongTinMo() {
        return "thong-tin-mo";
    }

    @RequestMapping(value = {"/thong-tin-giay-phep-khai-thac"}, method = RequestMethod.GET)
    public String thongTinGiayPhepKhaiThac() {
        return "thong-tin-giay-phep-khai-thac";
    }

    @RequestMapping(value = {"/thong-tin-thiet-bi"}, method = RequestMethod.GET)
    public String thongTinThietBi() {
        return "thong-tin-thiet-bi";
    }

    @RequestMapping(value = {"/thong-tin-nguoi-dung"}, method = RequestMethod.GET)
    public String thongTinNguoiDung() {
        return "thong-tin-nguoi-dung";
    }

    @RequestMapping(value = {"/thong-tin-thu-tuc"}, method = RequestMethod.GET)
    public String thongTinThuTuc() {
        return "thong-tin-thu-tuc";
    }

    @RequestMapping(value = {"/bao-cao-tong-hop-vi-pham"}, method = RequestMethod.GET)
    public String baoCaoTongHopViPham() { return "bao-cao-tong-hop-vi-pham"; }

    @RequestMapping(value = {"/bao-cao-luong-xe"}, method = RequestMethod.GET)
    public String baoCaoLuongXe() { return "bao-cao-luong-xe"; }

    @RequestMapping(value = {"/hinh-anh-luu-tru"}, method = RequestMethod.GET)
    public String hinhAnhLuuTru() { return "hinh-anh-luu-tru"; }

    @RequestMapping(value = {"/chi-tiet-hinh-anh-luu-tru"}, method = RequestMethod.GET)
    public String chiTietHinhAnhLuuTru() { return "chi-tiet-hinh-anh-luu-tru"; }

    @RequestMapping(value = {"/hinh-anh-vi-pham"}, method = RequestMethod.GET)
    public String hinhAnhViPham() { return "hinh-anh-vi-pham"; }

    @RequestMapping(value = {"/chi-tiet-vi-pham"}, method = RequestMethod.GET)
    public String chiTietHinhAnhViPham() { return "chi-tiet-vi-pham"; }

    @RequestMapping(value = {"/xem-truc-tuyen"}, method = RequestMethod.GET)
    public String xemTruTuyen() { return "xem-truc-tuyen"; }

    @RequestMapping(value = {"/tra-cuu-thong-bao"}, method = RequestMethod.GET)
    public String traCuuThongBao() { return "tra-cuu-thong-bao"; }

    @RequestMapping(value = {"/tra-cuu-nguoi-dung"}, method = RequestMethod.GET)
    public String traCuuNguoiDung() { return "tra-cuu-nguoi-dung"; }

    @RequestMapping(value = {"/tra-cuu-thiet-bi"}, method = RequestMethod.GET)
    public String traCuuThietBi() { return "tra-cuu-thiet-bi"; }

    @RequestMapping(value = {"/tra-cuu-xe-van-chuyen"}, method = RequestMethod.GET)
    public String traCuuXeVanChuyen() { return "tra-cuu-xe-van-chuyen"; }

    @RequestMapping(value = {"/tra-cuu-giay-phep-khai-thac"}, method = RequestMethod.GET)
    public String traCuuGiayPhepKhaiThac() { return "tra-cuu-giay-phep-khai-thac"; }

    @RequestMapping(value = {"/tra-cuu-doanh-nghiep"}, method = RequestMethod.GET)
    public String traCuuDoanhNghiep() { return "tra-cuu-doanh-nghiep"; }

    @RequestMapping(value = {"/tra-cuu-mo"}, method = RequestMethod.GET)
    public String traCuuMo() { return "tra-cuu-mo"; }

    @RequestMapping(value = {"/gui-thong-bao"}, method = RequestMethod.GET)
    public String guiThongBao() { return "gui-thong-bao"; }

    @RequestMapping(value = {"/nhan-bao-cao"}, method = RequestMethod.GET)
    public String nhanBaoCao() { return "nhan-bao-cao"; }

    @RequestMapping(value = {"/man-hinh-theo-doi"}, method = RequestMethod.GET)
    public String manHinhTheoDoi() { return "man-hinh-theo-doi"; }

    @RequestMapping(value = {"/bieu-do-khoang-san"}, method = RequestMethod.GET)
    public String banDoKhoangSan() { return "bieu-do-khoang-san"; }

    @RequestMapping(value = {"/chi-tiet-bao-cao"}, method = RequestMethod.GET)
    public String chiTietBaoCao() { return "chi-tiet-bao-cao"; }

    @RequestMapping(value = {"/chi-tiet-man-hinh-theo-doi"}, method = RequestMethod.GET)
    public String chiTietManHinhTheoDoi() { return "chi-tiet-man-hinh-theo-doi"; }

    @RequestMapping(value = {"/chi-tiet-xe-van-chuyen-khoang-san"}, method = RequestMethod.GET)
    public String chiTietXeVanChuyenKhoangSan() { return "chi-tiet-xe-van-chuyen-khoang-san"; }
}
