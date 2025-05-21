package com.example.kamustigabahasa_2023606601031.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kamustigabahasa_2023606601031.R;
import com.example.kamustigabahasa_2023606601031.database.DatabaseHelper;
import com.example.kamustigabahasa_2023606601031.dialogs.WordDialog;
import com.example.kamustigabahasa_2023606601031.models.Word;
import com.example.kamustigabahasa_2023606601031.adapters.WordAdapter;

import java.util.ArrayList;
import java.util.List;

public class CRUDActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private WordAdapter adapter;
    private List<Word> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewWords);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        wordList = new ArrayList<>();

        loadData();

        adapter = new WordAdapter(wordList, this, new WordAdapter.OnItemClickListener() {
            @Override
            public void onEdit(Word word) {
                showEditDialog(word);
            }

            @Override
            public void onDelete(Word word) {
                dbHelper.deleteWord(word.getId());
                Toast.makeText(CRUDActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });

        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAddWord).setOnClickListener(v -> showAddDialog());
    }

    private void loadData() {
        wordList.clear();
        Cursor cursor = dbHelper.getAllWords();
        while (cursor.moveToNext()) {
            wordList.add(new Word(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }
        cursor.close();
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    private void showAddDialog() {
        WordDialog dialog = new WordDialog(this, null, word -> {
            dbHelper.insertWord(
                    word.getIndonesian(),
                    word.getEnglish(),
                    word.getKorean()
            );
            loadData();
        });
        dialog.show();
    }

    private void showEditDialog(Word word) {
        WordDialog dialog = new WordDialog(this, word, updatedWord -> {
            dbHelper.updateWord(
                    updatedWord.getId(),
                    updatedWord.getIndonesian(),
                    updatedWord.getEnglish(),
                    updatedWord.getKorean()
            );
            loadData();
        });
        dialog.show();
    }
}

