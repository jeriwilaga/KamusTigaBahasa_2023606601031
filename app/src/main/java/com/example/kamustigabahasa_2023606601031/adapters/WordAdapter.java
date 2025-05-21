package com.example.kamustigabahasa_2023606601031.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kamustigabahasa_2023606601031.R;
import com.example.kamustigabahasa_2023606601031.models.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Word> wordList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEdit(Word word);
        void onDelete(Word word);
    }

    public WordAdapter(List<Word> wordList, Context context, OnItemClickListener listener) {
        this.wordList = wordList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = wordList.get(position);
        holder.tvIndonesian.setText(word.getIndonesian());
        holder.tvEnglish.setText(word.getEnglish());
        holder.tvKorean.setText(word.getKorean());

        holder.itemView.findViewById(R.id.btnEdit).setOnClickListener(v -> listener.onEdit(word));
        holder.itemView.findViewById(R.id.btnDelete).setOnClickListener(v -> listener.onDelete(word));
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndonesian, tvEnglish, tvKorean;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIndonesian = itemView.findViewById(R.id.tvIndonesian);
            tvEnglish = itemView.findViewById(R.id.tvEnglish);
            tvKorean = itemView.findViewById(R.id.tvKorean); // Pastikan id-nya benar di item_word.xml
        }
    }
}
