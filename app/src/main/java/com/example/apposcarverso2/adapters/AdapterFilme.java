package com.example.apposcarverso2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apposcarverso2.R;
import com.example.apposcarverso2.model.Filme;

import java.util.List;

public class AdapterFilme extends RecyclerView.Adapter<AdapterFilme.MyViewHolder> {

    private final Context mContext;
    private final List<Filme> filmesList;
    private final OnNoteListener mOnNoteListener;

    public AdapterFilme(Context mContext, List<Filme> filmesList, OnNoteListener onNoteListener) {
        this.mContext = mContext;
        this.filmesList = filmesList;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public AdapterFilme.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.filme_item, parent, false);
        return new MyViewHolder(view, mOnNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFilme.MyViewHolder holder, int position) {

        holder.textViewNome.setText(filmesList.get(position).getNome());
        holder.textViewGenero.setText(filmesList.get(position).getGenero());

        //Pegando imagens usando Glide library

        Glide.with(mContext)
                .load(filmesList.get(position).getPoster())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return filmesList.size();
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView textViewNome;
        final TextView textViewGenero;
        final ImageView imageView;
        final OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.textViewNomeFilme);
            textViewGenero = itemView.findViewById(R.id.textViewGeneroFilme);
            imageView = itemView.findViewById(R.id.imageViewFilme);

            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

}
