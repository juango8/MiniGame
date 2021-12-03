package com.diana.minigame

import android.widget.ImageButton

fun checkForMatch(position1: Int, position2: Int, cards: List<MemoryCard>): Boolean {
    return if (cards[position1].image == cards[position2].image) {
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        true
    } else false
}

fun restoreCards(cards: List<MemoryCard>) {
    for (card in cards) {
        if (!card.isMatched) {
            card.isFaceUp = false
        }
    }
}

fun updateViews(cards: List<MemoryCard>, buttons: List<ImageButton>) {
    cards.forEachIndexed { index, card ->
        val button = buttons[index]
        if (card.isMatched) {
            button.alpha = 0.1f
        }
        button.setImageResource(if (card.isFaceUp) card.image else R.drawable.descarga)
    }
}

fun checkFinish(cards: List<MemoryCard>): Boolean {
    for (card in cards) {
        if (!card.isMatched) {
            return false
        }
    }
    return true
}


data class MemoryCard(
    val image: Int,
    val backImage: Int = R.drawable.ic_launcher_background,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)