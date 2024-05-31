package com.example.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.mainupload.setOnClickListener {
            val empolyeename = binding.uploadempolyeename.text.toString()
            val employeefield = binding.uploademployeefield.text.toString()
            val nic = binding.uploadnic.text.toString()
            val number = binding.uploadnumber.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Employee-register")
            val EmployeeData = EmployeeData(empolyeename, employeefield, nic, number)


            databaseReference.child(nic).setValue(EmployeeData).addOnSuccessListener {

                binding.uploadempolyeename.text.clear()
                binding.uploademployeefield.text.clear()
                binding.uploadnic.text.clear()
                binding.uploadnumber.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
                .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }


    }

}

