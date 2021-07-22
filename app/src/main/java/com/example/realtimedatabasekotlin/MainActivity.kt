package com.example.realtimedatabasekotlin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimedatabasekotlin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {

            val Email = binding.Email.text.toString()
            val senhaUser = binding.senhaUser.text.toString()
            val userName = binding.userName.text.toString()

            database = FirebaseDatabase.getInstance().getReference("User")
            val User = User(Email, senhaUser, userName)
            database.child(userName).setValue(User).addOnSuccessListener {

                binding.Email.text.clear()
                binding.senhaUser.text.clear()
                binding.userName.text.clear()

                fun validaCampos(Email: String?, senhaUser: String?, userName: String?) : Boolean {
                    if(Email!!.isEmpty()){
                    val emailValido = Email != null && Email.isNotBlank() && Email.contains("@")
                    val senhaValido = senhaUser != null && senhaUser.isNotBlank() && senhaUser.length >= 6
                    return emailValido && senhaValido
                    } else {
                        Email.setError("Campos invalidos")
                    }
                return false}

                Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this, "Falha ao cadastrar", Toast.LENGTH_SHORT).show()


            }

        }

        lateinit var telaNavegation: Button
        telaNavegation = findViewById(R.id.bt_telaNavAct)
        telaNavegation.setOnClickListener{
            val intent: Intent = Intent(applicationContext, NavegationActivity::class.java)
            startActivity(intent)
        }

    }
}

private fun String.setError(s: String) {
 return
}
