package com.example.add_delate_update_app

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.add_delate_update_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val usersMap = mutableMapOf<String, usersInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    var addCounter = 0
    var removedCounter = 0
    var currentUsers = 0

    private fun init() {
        binding.addBtn.setOnClickListener {
            if (checkFields()) {
                addUsers()
            }
        }
        binding.removeBtn.setOnClickListener {
            if (checkFields()) {
                removeUsers()
            }
        }
        binding.updateBtn.setOnClickListener {
            if (checkFields()) {
                updateUser()
            }
        }
        binding.profileBtn.setOnClickListener {
            if (checkFields()) {
                if (usersMap.isNotEmpty()) {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.putExtra(
                        "FIRSTNAME",
                        usersMap[binding.emailEditText.text.toString()]?.firstName ?: "No Info"
                    )
                    intent.putExtra(
                        "LASTNAME",
                        usersMap[binding.emailEditText.text.toString()]?.lastName ?: "No Info"
                    )
                    intent.putExtra(
                        "AGE",
                        usersMap[binding.emailEditText.text.toString()]?.age ?: "No Info"
                    )
                    intent.putExtra(
                        "EMAIL",
                        usersMap[binding.emailEditText.text.toString()]?.eMail ?: "No Info"
                    )
                    startActivity(intent)
                } else if (usersMap.isEmpty()) {
                    Toast.makeText(this, "No Profiles To View", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun addUsers() {
        val email = binding.emailEditText.text.toString()
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val age = binding.ageEditText.text.toString()
        val newUser = usersInfo(firstName, lastName, email, age)
        if (!isEmailValid(email)) {
            Toast.makeText(this, "E-mail Is Not Valid !", Toast.LENGTH_SHORT).show()
        } else if (usersMap.containsKey(email)) {
            Toast.makeText(this, "User already exists !", Toast.LENGTH_SHORT).show()
            binding.stateTv.setTextColor(Color.parseColor("#FF0000"))
        } else {
            usersMap[email] = newUser
            Toast.makeText(this, "User added successfully !", Toast.LENGTH_SHORT).show()
            binding.stateTv.setTextColor(Color.parseColor("#00ff00"))
            addCounter += 1
            binding.addedTv.text = "added: $addCounter"

            currentUsers += 1
            binding.currUsersTv.text = "Current Users: $currentUsers"
        }
    }
    private fun removeUsers() {
        val email = binding.emailEditText.text.toString()
        if (usersMap.containsKey(email)) {
            usersMap.remove(email)
            removedCounter += 1
            binding.removedTv.text = "removed: $removedCounter"

            currentUsers -= 1
            binding.currUsersTv.text = "Current Users: $currentUsers"
            Toast.makeText(this, "User removed successfully", Toast.LENGTH_SHORT).show()
            binding.stateTv.setTextColor(Color.parseColor("#00ff00"))
        } else {
            Toast.makeText(this, "User doesn't exist !", Toast.LENGTH_SHORT).show()
            binding.stateTv.setTextColor(Color.parseColor("#FF0000"))
        }
    }
    private fun checkFields(): Boolean {
        val email = binding.emailEditText.text.toString()
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val age = binding.ageEditText.text.toString()
        var ifEmpty = true
        when {
            firstName.isEmpty() -> {
                Toast.makeText(this, "First Name is empty", Toast.LENGTH_SHORT).show()
                binding.stateTv.setTextColor(Color.parseColor("#FF0000"))
                ifEmpty = false
            }
            lastName.isEmpty() -> {
                Toast.makeText(this, "Last Name is empty", Toast.LENGTH_SHORT).show()
                binding.stateTv.setTextColor(Color.parseColor("#FF0000"))
                ifEmpty = false
            }
            age.isEmpty() -> {
                Toast.makeText(this, "Age is empty", Toast.LENGTH_SHORT).show()
                binding.stateTv.setTextColor(Color.parseColor("#FF0000"))
                ifEmpty = false
            }
            email.isEmpty() -> {
                Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show()
                binding.stateTv.setTextColor(Color.parseColor("#FF0000"))
                ifEmpty = false
            }
        }
        return ifEmpty
    }
    private fun updateUser() {
        val email = binding.emailEditText.text.toString()
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val age = binding.ageEditText.text.toString()
        if (usersMap.containsKey(email)) {
            usersMap[email]!!.firstName = firstName
            usersMap[email]!!.lastName = lastName
            usersMap[email]!!.age = age
            Toast.makeText(this, "User Info Updated Successfully !", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "User doesn't exist !", Toast.LENGTH_SHORT).show()
        }
    }
    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}