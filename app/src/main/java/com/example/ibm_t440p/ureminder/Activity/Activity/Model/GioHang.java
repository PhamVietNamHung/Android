package com.example.ibm_t440p.ureminder.Activity.Activity.Model;

public class GioHang {
    public int idsp;
    public String TenSanPham;
    public String GiaSanPham;
    public String HinhAnh;
    public int solong ;

    public GioHang(int idsp, String tenSanPham, String giaSanPham, String hinhAnh, int solong) {
        this.idsp = idsp;
        TenSanPham = tenSanPham;
        GiaSanPham = giaSanPham;
        HinhAnh = hinhAnh;
        this.solong = solong;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getSolong() {
        return solong;
    }

    public void setSolong(int solong) {
        this.solong = solong;
    }
}
