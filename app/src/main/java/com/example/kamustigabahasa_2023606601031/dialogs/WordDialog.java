package com.example.kamustigabahasa_2023606601031.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.example.kamustigabahasa_2023606601031.R;
import com.example.kamustigabahasa_2023606601031.models.Word;

public class WordDialog extends Dialog {

    private EditText etIndonesian, etEnglish, etKorean;
    private Button btnSave;
    private OnWordSaveListener listener;
    private Word word;

    public interface OnWordSaveListener {
        void onWordSaved(Word word);
    }

    public WordDialog(Context context, Word word, OnWordSaveListener listener) {
        super(context);
        this.word = word;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_word); // Layout XML untuk dialog

        // Inisialisasi komponen UI
        etIndonesian = findViewById(R.id.etIndonesian);
        etEnglish = findViewById(R.id.etEnglish);
        etKorean = findViewById(R.id.etKorean);
        btnSave = findViewById(R.id.btnSave);

        // Jika kata sudah ada, tampilkan datanya di EditText
        if (word != null) {
            etIndonesian.setText(word.getIndonesian());
            etEnglish.setText(word.getEnglish());
            etKorean.setText(word.getKorean());
        }

        // Aksi saat tombol Simpan diklik
        btnSave.setOnClickListener(v -> {
            String indonesian = etIndonesian.getText().toString().trim();
            String english = etEnglish.getText().toString().trim();
            String korean = etKorean.getText().toString().trim();

            if (!indonesian.isEmpty() && !english.isEmpty() && !korean.isEmpty()) {
                if (word == null) {
                    word = new Word(0, indonesian, english, korean);
                } else {
                    word = new Word(word.getId(), indonesian, english, korean);
                }

                listener.onWordSaved(word);
                dismiss();
            } else {
                new AlertDialog.Builder(getContext())
                        .setMessage("Please fill in all fields")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
}
