package com.tavi.tavi_mrs.entities.common;

public class TrangThaiXuLy {
    public static final int CHUA_XU_LY = 1;
    public static final int DANG_XU_LY = 2;
    public static final int DA_XU_LY = 3;

    public static String getMoTaTrangThaiXuLy(int trangThaiXuLy) {
        switch (trangThaiXuLy) {
            case CHUA_XU_LY:
                return "Chưa xử lý";
            case DANG_XU_LY:
                return "Đang xử lý";
            case DA_XU_LY:
                return "Đã xử lý";
            default:
                return "Không xác định";
        }
    }
}
