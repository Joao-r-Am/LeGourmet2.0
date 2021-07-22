package com.example.realtimedatabasekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabasekotlin.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateData : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {

            val email = binding.email.text.toString()
            val userName = binding.userName.text.toString()
            val senhaUser = binding.senhaUser.text.toString()

            updateData(email,userName,senhaUser)

        }

    }

    private fun updateData(Email: String, userName: String, senhaUser: String) {

        database = FirebaseDatabase.getInstance().getReference("User")
        val user = mapOf<String,String>(
            "email" to Email,
            "userName" to userName,
            "senhaUser" to senhaUser,
        )

        database.child(userName).updateChildren(user).addOnSuccessListener {

            binding.email.text.clear()
            binding.userName.text.clear()
            binding.senhaUser.text.clear()
            Toast.makeText(this,"Usuário atualizado",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Falha ao atualizar",Toast.LENGTH_SHORT).show()

        }}
}