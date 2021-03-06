package com.example.realtimedatabasekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabasekotlin.databinding.ActivityReadReceitaBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadReceita : AppCompatActivity() {

    private lateinit var binding : ActivityReadReceitaBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadReceitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {

            val nomeReceita : String = binding.pesquisarReceita.text.toString()
            if  (nomeReceita.isNotEmpty()){

                readData(nomeReceita)

            }else{

                Toast.makeText(this,"Insira o nome de uma receita",Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun readData(nomeReceita: String) {

        database = FirebaseDatabase.getInstance().getReference("Receitas")
        database.child(nomeReceita).get().addOnSuccessListener {

            if (it.exists()){

                val nomeReceita = it.child("nomeReceita").value
                val ingrediente = it.child("ingrediente").value
                val descricao = it.child("descricao").value
                Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                binding.pesquisarReceita.text.clear()
                binding.rdNomeReceita.text = nomeReceita.toString()
                binding.rdIngrediente.text = ingrediente.toString()
                binding.rdDescricao.text = descricao.toString()

            }else{

                Toast.makeText(this,"Receita inexistente",Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener{

            Toast.makeText(this,"Falha",Toast.LENGTH_SHORT).show()


        }



    }
}