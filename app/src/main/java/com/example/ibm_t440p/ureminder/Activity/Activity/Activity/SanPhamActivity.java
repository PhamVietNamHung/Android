package com.example.ibm_t440p.ureminder.Activity.Activity.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.ibm_t440p.ureminder.Activity.Activity.Model.SanPham;
import com.example.ibm_t440p.ureminder.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.EventListener;

public class SanPhamActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    TextView tvTenSanPham;
    TextView tvGiaSanPham;
    TextView tvMoTa;
    ImageView imgHinhAnh;
    ScrollView scrollView;
    Spinner spinner;
    FloatingActionButton floatingActionButton;
    Button button;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);


        AnhXa();



        CatchEventSpiner();
         GetChiTietSanPham();
        actionBar();


    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetChiTietSanPham() {


        Picasso.with(SanPhamActivity.this).load(getIntent().getStringExtra("HinhAnh")).into(imgHinhAnh);
        tvTenSanPham.setText(getIntent().getStringExtra("TenSanPham"));
        tvGiaSanPham.setText("Giá: " + getIntent().getStringExtra("GiaSanPham") + " VND");
        tvMoTa.setText(getIntent().getStringExtra("MoTa"));
    }

    private void CatchEventSpiner(){
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }
    private  void AnhXa(){
        tvTenSanPham = (TextView) findViewById(R.id.textviewtenchitietsanpham);
        tvGiaSanPham = (TextView) findViewById(R.id.textviewgiasanpham);
        tvMoTa = (TextView) findViewById(R.id.textviewmotachitietsanpham);
        imgHinhAnh = (ImageView) findViewById(R.id.imageviewchitietsanpham);
        spinner = (Spinner) findViewById(R.id.spiner);
        scrollView =(ScrollView)findViewById(R.id.activity_chi_tiet_san_pham);
        button = (Button) findViewById(R.id.buttondamua);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarchitietsanpham);
    }
}
