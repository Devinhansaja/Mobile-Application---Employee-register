package com.example.admin



import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.admin.databinding.ActivitySplashBinding
class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Delay for 3 seconds
        Handler().postDelayed({
            // Start MainActivity
            val intent = Intent(this@Splash, login::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}
