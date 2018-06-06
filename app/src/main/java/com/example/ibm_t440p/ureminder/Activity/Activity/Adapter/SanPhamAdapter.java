package com.example.ibm_t440p.ureminder.Activity.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibm_t440p.ureminder.Activity.Activity.Activity.SanPhamActivity;
import com.example.ibm_t440p.ureminder.Activity.Activity.Model.SanPham;
import com.example.ibm_t440p.ureminder.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.DataViewHolder> {
    public ArrayList<SanPham> getModel() {
        return arraysanpham;
    }

    public void setModel(ArrayList<SanPham> model) {
        this.arraysanpham = model;
    }

    Context context;
    ArrayList<SanPham> arraysanpham;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }



    @Override
    public SanPhamAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_san_pham, parent, false);

        DataViewHolder dataViewHolder = new DataViewHolder(itemView,context,this.arraysanpham);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(SanPhamAdapter.DataViewHolder holder, int position) {
        String tensanpham = arraysanpham.get(position).getTenSanPham();
        holder.txtTenSanPham.setText(tensanpham);
        String giasanpham = arraysanpham.get(position).getGiaSanPham();
        holder.txtGiaSanPham.setText("Giá: " + giasanpham + " VND");
        String anh = arraysanpham.get(position).getHinhAnh();
        Picasso.with(context).load(anh).into(holder.imgHinhAnh);
    }

    @Override
    public int getItemCount() {
        if(arraysanpham==null)return 0;
        Log.d("Adapter", "Size: "+ arraysanpham.size());
        return arraysanpham.size();
    }


    public static class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgHinhAnh;
        public TextView txtTenSanPham,txtGiaSanPham;
        Context ctv;
        private ArrayList<SanPham> models = new ArrayList<SanPham>();

        public DataViewHolder(View itemView, Context context, ArrayList<SanPham> arraysanpham) {
            super(itemView);
            this.ctv = context;
            this.models = arraysanpham;
            itemView.setOnClickListener(this);
            imgHinhAnh = (ImageView) itemView.findViewById(R.id.imgSanPham);
            txtGiaSanPham = (TextView) itemView.findViewById(R.id.txtGia);
            txtTenSanPham = (TextView) itemView.findViewById(R.id.txtTenSanPham);

        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            SanPham model = this.models.get(pos);
            Intent intent = new Intent(this.ctv, SanPhamActivity.class);
           intent.putExtra("HinhAnh",model.getHinhAnh());
           intent.putExtra("TenSanPham",model.getTenSanPham());
           intent.putExtra("GiaSanPham",model.getGiaSanPham());
            intent.putExtra("MoTa",model.getMoTa());
            this.ctv.startActivity(intent);


        }
    }
}
