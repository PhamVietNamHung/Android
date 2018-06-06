package com.example.ibm_t440p.ureminder.Activity.Activity.Activity;

import android.graphics.ColorSpace;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.ViewFlipper;

import com.example.ibm_t440p.ureminder.Activity.Activity.Adapter.SanPhamAdapter;
import com.example.ibm_t440p.ureminder.Activity.Activity.Model.GioHang;
import com.example.ibm_t440p.ureminder.Activity.Activity.Model.SanPham;
import com.example.ibm_t440p.ureminder.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    ListView listView;
    ArrayList<SanPham> arrayList = new ArrayList<>() ;
    SanPhamAdapter adapter;
    ScrollView scrollView;
    public static ArrayList<GioHang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Anhxa();
        ActionViewFlipper();


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerviewSanPham);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SanPhamAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
        // lấy dữ liệu task
        SanPhamTask task = new SanPhamTask();
        task.execute();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_dangnhap) {
            return true;
        }else if (id == R.id.action_dangki) {
            return true;
        }else if (id == R.id.action_gioithieu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.gao1) {

        } else if (id == R.id.gao2) {

        }else if (id == R.id.gao3) {

        }else if (id == R.id.profile) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /////////

    public  void Anhxa(){
        viewFlipper = (ViewFlipper) findViewById(R.id.viewlipper);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewSanPham);
        scrollView =(ScrollView)findViewById(R.id.activity_chi_tiet_san_pham);
        if(manggiohang != null){

        }else {
            manggiohang = new ArrayList<>();
        }
    }

    public  void ActionViewFlipper(){

         viewFlipper.setFlipInterval(3000);
         viewFlipper.setAutoStart(true);

    }
    class SanPhamTask extends AsyncTask<Void,Void,ArrayList<SanPham>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<SanPham> quanen) {
            super.onPostExecute(quanen);
            arrayList.clear();
            arrayList.addAll(quanen);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            arrayList.clear();
        }

        @Override
        protected ArrayList<SanPham> doInBackground(Void... params) {
            ArrayList<SanPham> ds = new ArrayList<>();
            try
            {
                URL url = new URL("https://ugao-server.herokuapp.com/api/products");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(),"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line!=null)
                {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                builder.replace(0,36,"");
                JSONArray jsonArray = new JSONArray(builder.toString());
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SanPham sanPham = new SanPham();
                    if(jsonObject.has("ProdName"))
                        sanPham.setTenSanPham(jsonObject.getString("ProdName"));
                    if(jsonObject.has("CurrentPrice"))
                        sanPham.setGiaSanPham(jsonObject.getString("CurrentPrice"));
                    if(jsonObject.has("Description"))
                        sanPham.setMoTa(jsonObject.getString("Description"));
                    if(jsonObject.has("PicLink"))
                    {
//                        String image = jsonObject.getString("UrlAnhBia");
//                        byte[] imageAsBytes = Base64.decode(image, Base64.DEFAULT);
                       sanPham.setHinhAnh(jsonObject.getString("PicLink"));
                    }
                    ds.add(sanPham);
                }
            }catch(Exception ex)
            {
                Log.e("Error",ex.toString());
            }
            return ds;
        }
    }

}
