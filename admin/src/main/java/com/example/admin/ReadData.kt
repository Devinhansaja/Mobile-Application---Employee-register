package com.example.admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivityReadBinding
import com.google.firebase.database.*

class ReadData : AppCompatActivity() {

    private lateinit var binding: ActivityReadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchbutton.setOnClickListener {
            val searchNic: String = binding.searchnic.text.toString()
            if (searchNic.isNotEmpty()) {
                readData(searchNic)
            } else {
                Toast.makeText(this, "Please enter the Employee N.I.C", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(nic: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Employee-register")

        // Query to search for the NIC in the database
        val query: Query = databaseReference.orderByChild("nic").equalTo(nic)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (employeeSnapshot in dataSnapshot.children) {
                        val employeeName = employeeSnapshot.child("empolyeename").value.toString()
                        val employeeField = employeeSnapshot.child("employeefield").value.toString()
                        val number = employeeSnapshot.child("number").value.toString()

                        binding.reademployeename.text = employeeName
                        binding.reademployeefield.text = employeeField
                        binding.readnumber.text = number

                        Toast.makeText(this@ReadData, "Result Found", Toast.LENGTH_SHORT).show()
                        binding.searchnic.text.clear()
                        return
                    }
                } else {
                    Toast.makeText(this@ReadData, "Employee does not exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ReadData, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
