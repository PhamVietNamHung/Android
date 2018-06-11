package com.example.ibm_t440p.ureminder.Activity.Activity.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.ibm_t440p.ureminder.Activity.Activity.Adapter.Gao1Adapter;
import com.example.ibm_t440p.ureminder.Activity.Activity.Adapter.Gao3Adapter;
import com.example.ibm_t440p.ureminder.Activity.Activity.Model.SanPham;
import com.example.ibm_t440p.ureminder.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Gao3 extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<SanPham> arrayList = new ArrayList<>() ;
    Gao3Adapter gao3Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gao3);
        AnhXa();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        gao3Adapter = new Gao3Adapter(this,arrayList);
        recyclerView.setAdapter(gao3Adapter);
        SanPhamTask task = new SanPhamTask();
        task.execute();

    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbargao1);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewGao1);

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
            gao3Adapter.notifyDataSetChanged();
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
                URL url = new URL("http://ugao-server.herokuapp.com/api/producttypes/5b17a73930ce0c00149a18f2");
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
                builder.replace(0,40,"");
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
