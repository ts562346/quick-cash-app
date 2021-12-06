package com.example.quickcashgroup5.home;

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

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.jobdetails.JobStatusEmployeeActivity;

import java.util.ArrayList;

/**
 * Adapter Employee RecyclerView
 */
public class MyAdapterEmployee extends RecyclerView.Adapter<MyAdapterEmployee.MyViewHolderEmployee> {

    ArrayList<DataModelDashboard> dataHolder;
    String tag = "MyViewHolder";
    ArrayList<String> key = new ArrayList<>();

    /**
     * Constructor for this class
     *
     * @param dataHolder
     */
    public MyAdapterEmployee(ArrayList<DataModelDashboard> dataHolder) {
        this.dataHolder = dataHolder;
    }

    /**
     * Runs when ViewHolders are created
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolderEmployee onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_dashboard, parent, false);
        return new MyViewHolderEmployee(view);

    }

    /**
     * Binds ViewHolder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolderEmployee holder, int position) {
        key.add(dataHolder.get(position).getKey());
        holder.payment.setText(dataHolder.get(position).getStatus());
        holder.title.setText(dataHolder.get(position).getJobTitle());
    }

    /**
     * Returns the count of the dataHolder
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    /**
     * Employee RecyclerView
     */
    class MyViewHolderEmployee extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView payment;
        TextView title;
        ImageButton imageButton;
        Context context;

        /**
         * Constructor of this
         *
         * @param itemView
         */
        public MyViewHolderEmployee(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            title = itemView.findViewById(R.id.title);
            payment = itemView.findViewById(R.id.locationLabel);


            imageButton = itemView.findViewById(R.id.imageView);
            imageButton.setOnClickListener(this);
        }

        /**
         * OnClick method for button
         *
         * @param view
         */
        @Override
        public void onClick(View view) {
            showPopupMenu(view);
        }

        /**
         * Popup menu
         *
         * @param view
         */
        public void showPopupMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate((R.menu.mymenu_employeedashboard));
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        /**
         * Runs when menu item is clicked
         *
         * @param menuItem
         * @return
         */
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.status) {
                Log.d(tag, "onMenuItemClick: details" + getAdapterPosition());

                Intent intent = new Intent(context, JobStatusEmployeeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Key", key.get(getAdapterPosition()));
                intent.putExtras(bundle);
                context.startActivity(intent);
                return true;
            } else {
                Log.d(tag, "onMenuItemClick: details");
            }
            return false;
        }
    }
}
