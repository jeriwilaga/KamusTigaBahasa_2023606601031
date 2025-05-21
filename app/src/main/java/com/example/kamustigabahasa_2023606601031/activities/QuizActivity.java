package com.example.kamustigabahasa_2023606601031.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kamustigabahasa_2023606601031.R;
import com.example.kamustigabahasa_2023606601031.database.DatabaseHelper;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Cursor cursor;
    private String correctAnswer;
    private String currentQuestionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        dbHelper = new DatabaseHelper(this);

        // Inisialisasi elemen UI
        Button btnSubmit = findViewById(R.id.btnSubmit);
        EditText etAnswer = findViewById(R.id.etAnswer);
        TextView tvQuestion = findViewById(R.id.tvQuestion);

        // Load pertanyaan pertama
        loadNewQuestion(tvQuestion);

        // Aksi saat tombol Submit ditekan
        btnSubmit.setOnClickListener(v -> {
            String userAnswer = etAnswer.getText().toString().trim();

            if (userAnswer.isEmpty()) {
                Toast.makeText(QuizActivity.this, "Please enter an answer", Toast.LENGTH_SHORT).show();
            } else {
                if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                    Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, "Wrong! The correct answer is: " + correctAnswer, Toast.LENGTH_LONG).show();
                }

                // Load pertanyaan baru setelah menjawab
                loadNewQuestion(tvQuestion);
                etAnswer.setText(""); // Reset input jawaban
            }
        });
    }

    // Fungsi untuk menampilkan pertanyaan baru
    private void loadNewQuestion(TextView tvQuestion) {
        cursor = dbHelper.getAllWords();

        if (cursor == null || cursor.getCount() == 0) {
            tvQuestion.setText("No words available in the database.");
            correctAnswer = "";
            return;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(cursor.getCount());
        cursor.moveToPosition(randomIndex);

        // Random jenis pertanyaan (Indonesia -> English atau Korea)
        currentQuestionType = random.nextBoolean() ? "English" : "Korean";

        String indonesian = cursor.getString(1); // Kolom Bahasa Indonesia

        if (currentQuestionType.equals("English")) {
            tvQuestion.setText("Translate to English: " + indonesian);
            correctAnswer = cursor.getString(2); // Kolom Bahasa Inggris
        } else {
            tvQuestion.setText("Translate to Korean: " + indonesian);
            correctAnswer = cursor.getString(3); // Kolom Bahasa Korea
        }

        // Jangan lupa tutup cursor setelah selesai digunakan di aktivitas sesungguhnya
        // Tapi karena kita pakai satu cursor berulang, pastikan ditutup di onDestroy()
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
