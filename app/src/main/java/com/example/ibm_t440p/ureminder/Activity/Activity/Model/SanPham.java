package com.example.ibm_t440p.ureminder.Activity.Activity.Model;

public class SanPham {
    public int IDSanPham;
    public String TenSanPham;
    public String MoTa;
    public String GiaSanPham;
    public String HinhAnh;

    public int getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(int IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public SanPham(){};

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
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

    public SanPham(int IDSanPham,String tenSanPham, String moTa, String giaSanPham, String hinhAnh) {
        this.IDSanPham = IDSanPham;
        TenSanPham = tenSanPham;
        MoTa = moTa;
        GiaSanPham = giaSanPham;
        HinhAnh = hinhAnh;
    }
}



