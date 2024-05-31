package com.example.admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpdate.setOnClickListener {
            val referencenic = binding.referencenic.text.toString()
            val updateempolyeename = binding.updateempolyeename.text.toString()
            val updateemployeefield = binding.updateemployeefield.text.toString()
            val updatenumber = binding.updatenumber.text.toString()

            if (referencenic.isNotEmpty()) {
                updateData(referencenic, updateempolyeename, updateemployeefield, updatenumber)
            } else {
                Toast.makeText(this, "Please enter the Employee N.I.C", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateData(nic: String, employeename: String, employeefield: String, number: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Employee-register")

        // First, delete the existing data for the given NIC
        databaseReference.child(nic).removeValue().addOnSuccessListener {
            // After deletion, write the new data
            val employeeData = mapOf<String, String>(
                "empolyeename" to employeename,
                "employeefield" to employeefield,
                "number" to number,
                "nic" to nic
            )

            databaseReference.child(nic).setValue(employeeData).addOnSuccessListener {
                binding.updateempolyeename.text.clear()
                binding.updateemployeefield.text.clear()
                binding.referencenic.text.clear()
                binding.updatenumber.text.clear()

                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Delete Old Data", Toast.LENGTH_SHORT).show()
        }
    }
}
