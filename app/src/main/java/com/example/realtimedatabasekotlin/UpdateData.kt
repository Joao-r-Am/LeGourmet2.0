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

            val userName = binding.userName.text.toString()
            val Email = binding.Email.text.toString()

            updateData(userName,Email)

        }

    }

    private fun updateData(userName: String, Email: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>(
                "firstName" to userName,
                "lastName" to Email,
        )

        database.child(userName).updateChildren(user).addOnSuccessListener {

            binding.userName.text.clear()
            binding.Email.text.clear()
            Toast.makeText(this,"Successfuly Updated",Toast.LENGTH_SHORT).show()


        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }}
}