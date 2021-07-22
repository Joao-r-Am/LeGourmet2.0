package com.example.realtimedatabasekotlin

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.realtimedatabasekotlin.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(Email,senhaUser,userName)
            database.child(userName).setValue(User).addOnSuccessListener {

                binding.Email.text.clear()
                binding.senhaUser.text.clear()
                binding.userName.text.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()


            }
            lateinit var telaReadData: Button
            telaReadData = findViewById(R.id.bt_telaNav)
            telaReadData.setOnClickListener{
                val intent: Intent = Intent(applicationContext, NavegationActivity::class.java)
                startActivity(intent)
            }

        }

    }
}