package com.tavi.tavi_mrs.entities.common;

public class ThamQuyenCap {

    public static final int CAP_TRUNG_UONG = 0;
    public static final int CAP_TINH_THANH_PHO = 1;
    public static final int CAP_HUYEN = 2;

    public static String getThamQuyenCap(int thamQuyenCap) {
        switch (thamQuyenCap) {
            case ThamQuyenCap.CAP_TRUNG_UONG : {
                return "Cấp Trung Ương";
            }
            case CAP_TINH_THANH_PHO : {
                return "Cấp Tỉnh/Thành phố";
            }
            case CAP_HUYEN : {
                return "Cấp Huyện";
            }
            default: {
                return "Không xác định";
            }
        }
    }
}
