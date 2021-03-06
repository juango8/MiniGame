package com.diana.minigame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diana.minigame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPlay.setOnClickListener { startPlay() }
        binding.buttonExit.setOnClickListener { exitGame() }
    }

    private fun startPlay(){
        val intent = Intent(this, FirstLevelActivity::class.java)
        startActivity(intent)
    }

    private fun exitGame(){
        onBackPressed()
    }

}