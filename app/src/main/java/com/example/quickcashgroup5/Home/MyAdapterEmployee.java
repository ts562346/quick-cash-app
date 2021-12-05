package com.example.quickcashgroup5.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcashgroup5.JobDetails.JobStatusEmployeeActivity;
import com.example.quickcashgroup5.R;

import java.util.ArrayList;

public class MyAdapterEmployee extends RecyclerView.Adapter<MyAdapterEmployee.MyViewHolderEmployee> {

    ArrayList<DataModelDashboard> dataHolder;
    String TAG = "MyViewHolder";
    ArrayList<String> key = new ArrayList<String>();


    public MyAdapterEmployee(ArrayList<DataModelDashboard> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public MyViewHolderEmployee onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_dashboard, parent, false);
        return new MyViewHolderEmployee(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderEmployee holder, int position) {
        key.add(dataHolder.get(position).getKey());
        holder.payment.setText(dataHolder.get(position).getStatus());
        holder.title.setText(dataHolder.get(position).getJobTitle());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class MyViewHolderEmployee extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView payment, title;
        ImageButton imageButton;
        Context context;
        public MyViewHolderEmployee(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            title = itemView.findViewById(R.id.title);
            payment = itemView.findViewById(R.id.locationLabel);


            imageButton = itemView.findViewById(R.id.imageView);
            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            showPopupMenu(view);
        }

        public void showPopupMenu (View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
            popupMenu.inflate((R.menu.mymenu_employeedashboard));
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.status:
                    Log.d(TAG, "onMenuItemClick: details" + getAdapterPosition());

                    Intent intent = new Intent(context, JobStatusEmployeeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Key", key.get(getAdapterPosition()));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    return true;

            }
            return false;
        }
    }
}
