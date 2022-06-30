package com.example.add_delate_update_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.add_delate_update_app.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras

        if (extras != null){
            binding.firstNameTV.text = extras.getString("FIRSTNAME")
            binding.lastNameTV.text = extras.getString("LASTNAME")
            binding.ageTV.text = extras.getString("AGE")
            binding.emailTV.text = extras.getString("EMAIL")
        }

    }
}