package com.example.kamustigabahasa_2023606601031.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kamustigabahasa_2023606601031.R;
import com.example.kamustigabahasa_2023606601031.database.DatabaseHelper;

public class TranslateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        Spinner spinner = findViewById(R.id.spinnerLanguage);
        String[] languages = {"Indonesian", "English", "Japanese"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, languages);
        spinner.setAdapter(adapter);

        findViewById(R.id.btnTranslate).setOnClickListener(v -> {
            EditText editText = findViewById(R.id.editTextWord);
            String word = editText.getText().toString().trim();
            String targetLanguage = spinner.getSelectedItem().toString();

            if (!word.isEmpty()) {
                DatabaseHelper db = new DatabaseHelper(this);
                Cursor cursor = db.getAllWords();

                boolean found = false;
                while (cursor.moveToNext()) {
                    String indoWord = cursor.getString(1);
                    if (indoWord.equalsIgnoreCase(word)) {
                        found = true;
                        String translation = "";

                        switch (targetLanguage) {
                            case "English":
                                translation = cursor.getString(2);
                                break;
                            case "Japanese":
                                translation = cursor.getString(3);
                                break;
                            case "Indonesian":
                                translation = indoWord;
                                break;
                        }

                        Toast.makeText(this, "Translation: " + translation, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if (!found) {
                    Toast.makeText(this, "Word not found!", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
                db.close();
            } else {
                Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
