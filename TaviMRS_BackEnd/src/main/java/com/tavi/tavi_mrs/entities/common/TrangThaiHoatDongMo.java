package com.tavi.tavi_mrs.entities.common;

public class TrangThaiHoatDongMo {
    public static final int CHUA_HOAT_DONG = 1;
    public static final int DANG_HOAT_DONG = 2;
    public static final int TAM_DUNG_HOAT_DONG = 3;
    public static final int DUNG_HOAT_DONG = 4;

    public static String getTrangThaiHoatDongMoAsString(int key) {
        switch (key) {
            case CHUA_HOAT_DONG: return "Chưa Hoạt Động";
            case DANG_HOAT_DONG: return "Đang Hoạt Động";
            case TAM_DUNG_HOAT_DONG: return "Tạm Dừng Hoạt Động";
            case DUNG_HOAT_DONG: return "Dừng Hoạt Động";
            default: return "Không Xác Định";
        }
    }

    public static boolean isTrangThaiHoatDongMo(int trangThaiHoatDong) {
        if (trangThaiHoatDong == 0
                || trangThaiHoatDong == CHUA_HOAT_DONG
                || trangThaiHoatDong == DANG_HOAT_DONG
                || trangThaiHoatDong == TAM_DUNG_HOAT_DONG
                || trangThaiHoatDong == DUNG_HOAT_DONG)
        {
            return true;
        } else return false;
    }

}
