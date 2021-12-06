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

import com.example.quickcashgroup5.EmployeeSelection.SelectEmployeeActivity;
import com.example.quickcashgroup5.JobDetails.JobDescriptionEmployerActivity;
import com.example.quickcashgroup5.PaymentManagement.PayEmployeeActivity;
import com.example.quickcashgroup5.R;

import java.util.ArrayList;

public class MyAdapterEmployer extends RecyclerView.Adapter<MyAdapterEmployer.MyViewHolderEmployer>  {

    ArrayList<DataModelDashboard> dataHolder;
    String TAG = "MyViewHolder";
    ArrayList<String> key = new ArrayList<String>();


    public MyAdapterEmployer(ArrayList<DataModelDashboard> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public MyViewHolderEmployer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_dashboard, parent, false);
        return new MyViewHolderEmployer(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderEmployer holder, int position) {
        key.add(dataHolder.get(position).getKey());
        holder.payment.setText(dataHolder.get(position).getStatus());
        holder.title.setText(dataHolder.get(position).getJobTitle());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class MyViewHolderEmployer extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView title, payment;
        ImageButton imageButton;
        Context context;
        public MyViewHolderEmployer(@NonNull View itemView) {
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
            TextView status = itemView.findViewById(R.id.locationLabel);
            String statusFetch = status.getText().toString();
            popupMenu.inflate((R.menu.mymenu_employerdashboard));

            if (statusFetch.equals("New")) {
                popupMenu.getMenu().removeItem(R.id.pay);
            } else if (statusFetch.equals("Ongoing")) {
                popupMenu.getMenu().removeItem(R.id.pay);
                popupMenu.getMenu().removeItem(R.id.select);
            }else if (statusFetch.equals("Completed")){
                popupMenu.getMenu().removeItem(R.id.select);
            }else if (statusFetch.equals("Complete")){
                popupMenu.getMenu().removeItem(R.id.select);
            } else {
                popupMenu.getMenu().removeItem(R.id.pay);
                popupMenu.getMenu().removeItem(R.id.select);
            }
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.details:
                    Log.d(TAG, "onMenuItemClick: details" + getAdapterPosition());
                    Intent intent =  new Intent(context, JobDescriptionEmployerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Key", key.get(getAdapterPosition()));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    return true;
                case R.id.select:
                    Log.d(TAG, "onMenuItemClick: select" + getAdapterPosition());
                    intent =  new Intent(context, SelectEmployeeActivity.class);
                    bundle = new Bundle();
                    bundle.putString("Key", key.get(getAdapterPosition()));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    return true;
                case R.id.pay:
                    Log.d(TAG, "onMenuItemClick: pay" + getAdapterPosition());
                    intent =  new Intent(context, PayEmployeeActivity.class);
                    bundle = new Bundle();
                    bundle.putString("Key", key.get(getAdapterPosition()));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    return true;
            }
            return false;
        }
    }
}