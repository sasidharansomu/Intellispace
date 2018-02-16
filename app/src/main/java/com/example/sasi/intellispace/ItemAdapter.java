package com.example.sasi.intellispace;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

/**
 * Created by hp on 06-02-2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    public int layoutid;
    public static ArrayList<CardAdapter> itemlist;
    public ItemAdapter(int layout , ArrayList<CardAdapter> list) {
        this.layoutid = layout;
        this.itemlist = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutid,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
           System.out.println("bow"+itemlist.get(position).getBuilding());
    }

    @Override
    public int getItemCount() {
        return itemlist.size()>0?itemlist.size():0;
    }

    static  class ViewHolder extends  RecyclerView.ViewHolder{

      public ViewHolder(View itemView) {
          super(itemView);

          System.out.println("fow "+itemlist.size());
      }
  }
}
