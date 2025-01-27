package com.example.admin

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener {
            val intent = Intent(
                this@MainActivity, UploadActivity::class.java
            )
            startActivity(intent)
        }

        binding.mainupdate.setOnClickListener {
            val intent = Intent(
                this@MainActivity, UpdateActivity::class.java
            )
            startActivity(intent)

        }

        binding.mainSearch.setOnClickListener {
            val intent = Intent(
                this@MainActivity, ReadData::class.java
            )
            startActivity(intent)

        }


    }

}