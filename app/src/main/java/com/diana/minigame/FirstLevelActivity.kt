package com.diana.minigame

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.diana.minigame.databinding.ActivityFirstLevelBinding

class FirstLevelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstLevelBinding

    private lateinit var images: MutableList<Int>
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private var indexOfSingleSelectedCard: Int? = null
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVariables()
        setListeners()
    }

    private fun setListeners() {
        buttons.forEachIndexed { index, imageButton ->
            imageButton.setOnClickListener {
                updateModels(index)
                updateViews(cards, buttons)
                if (checkFinish(cards)) {
                    val intent = Intent(this, SecondLevelActivity::class.java)
                    intent.putExtra("score", score)
                    startActivity(intent)
                }
            }
        }
        binding.buttonReboot.setOnClickListener {
            finish()
            startActivity(intent)
        }
        binding.buttonExit.setOnClickListener { finish() }

    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        if (card.isFaceUp) {
            Toast.makeText(this, "Movimiento invalido", Toast.LENGTH_SHORT).show()
            return
        }
        indexOfSingleSelectedCard = if (indexOfSingleSelectedCard == null) {
            restoreCards(cards)
            position
        } else {
            if (checkForMatch(indexOfSingleSelectedCard!!, position, cards)) {
                Toast.makeText(this, "Par encontrado!!", Toast.LENGTH_SHORT).show()
                score += 10
                binding.score.text = "Score: $score"
            }
            null
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun setVariables(){
        images = mutableListOf(R.drawable.casco, R.drawable.cleo, R.drawable.dog)
        images.addAll(images)
        images.shuffle()

        buttons = listOf(
            binding.image1,
            binding.image2,
            binding.image3,
            binding.image4,
            binding.image5,
            binding.image6,
        )
        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }
    }
}