package com.example.kamustigabahasa_2023606601031

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kamustigabahasa_2023606601031.activities.CRUDActivity
import com.example.kamustigabahasa_2023606601031.activities.QuizActivity
import com.example.kamustigabahasa_2023606601031.activities.TranslateActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnTranslate).setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    this,
                    TranslateActivity::class.java
                )
            )
        }

        findViewById<View>(R.id.btnCRUD).setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    this,
                    CRUDActivity::class.java
                )
            )
        }

        findViewById<View>(R.id.btnQuiz).setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    this,
                    QuizActivity::class.java
                )
            )
        }
    }
}