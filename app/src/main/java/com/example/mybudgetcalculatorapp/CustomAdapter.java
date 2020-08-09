package com.example.mybudgetcalculatorapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList epargne_id, epargne_cap, epargne_taux, epargne_date, currency;

    CustomAdapter(Activity activity, Context context, ArrayList epargne_id, ArrayList epargne_cap, ArrayList epargne_taux, ArrayList currency,
                  ArrayList epargne_date){
        this.activity = activity;
        this.context = context;
        this.epargne_id = epargne_id;
        this.epargne_cap = epargne_cap;
        this.epargne_taux = epargne_taux;
        this.currency = currency;
        this.epargne_date = epargne_date;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.epargne_id_txt.setText(String.valueOf(epargne_id.get(position)));
        holder.epargne_cap_txt.setText(String.valueOf(epargne_cap.get(position)).concat(String.valueOf(currency.get(position)))  +" Capacité d'épargne");
        holder.epargne_taux_txt.setText(String.valueOf(epargne_taux.get(position)) + "% taux d'épargne");
        holder.epargne_date_txt.setText(String.valueOf(epargne_date.get(position)));

    }

    @Override
    public int getItemCount() {
        return epargne_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView epargne_id_txt, epargne_cap_txt, epargne_taux_txt, epargne_date_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            epargne_id_txt = itemView.findViewById(R.id.id_epargne_txt);
            epargne_cap_txt = itemView.findViewById(R.id.epargne_cap_txt);
            epargne_taux_txt = itemView.findViewById(R.id.epargne_taux_txt);
            epargne_date_txt = itemView.findViewById(R.id.epargne_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }

    }
}
