package com.example.realtimedatabasekotlin

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.realtimedatabasekotlin.databinding.ActivityAdicionaReceitaBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdicionaReceita : AppCompatActivity() {

    private lateinit var binding : ActivityAdicionaReceitaBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var voltaNav: Button
        voltaNav = findViewById(R.id.bt_voltaNav)
        voltaNav.setOnClickListener{
            val intent: Intent = Intent(applicationContext, NavegationActivity::class.java)
            startActivity(intent)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityAdicionaReceitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.adicionarBtn.setOnClickListener {

            val nomeReceita: String = binding.nomeReceita.text.toString()
            val ingrediente = binding.ingrediente.text.toString()
            val descricao = binding.descricao.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Receita")
            val Receita = Receita(nomeReceita,ingrediente,descricao)
            database.child(nomeReceita).setValue(Receita).addOnSuccessListener {

                binding.nomeReceita.text.clear()
                binding.ingrediente.text.clear()
                binding.descricao.text.clear()

                Toast.makeText(this,"Receita salva com sucesso",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Falha ao salvar a receita",Toast.LENGTH_SHORT).show()


            }
        }
    }
}