package com.example.realtimedatabasekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NavegationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegation)

        lateinit var telaPesquisaReceita: Button
        telaPesquisaReceita = findViewById(R.id.bt_pesquisaSimples)
        telaPesquisaReceita.setOnClickListener{
            val intent: Intent = Intent(applicationContext, ReadData::class.java)
            startActivity(intent)
        }

        lateinit var telaAddReceita: Button
        telaAddReceita = findViewById(R.id.bt_addReceita)
        telaAddReceita.setOnClickListener{
            val intent: Intent = Intent(applicationContext, UpdateData::class.java)
            startActivity(intent)
        }

    }
}