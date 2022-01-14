package com.example.picturematch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var gambar: List<pictureCard>
    private var indexOfSingleSelectedCard: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar

        actionBar!!.title="PICTURE MATCH" //title pada appbar
        actionBar.setDisplayHomeAsUpEnabled(true)


        val images = mutableListOf(R.drawable.boy, R.drawable.chef, R.drawable.king, R.drawable.man2)
        //jadikan gambar ada 2 supaya boleh buat pair
        images.addAll(images)
        // jadi kan random
        images.shuffle()

        var imageButton1 = findViewById<ImageButton>(R.id.imageButton1) //declare image button
        var imageButton2 = findViewById<ImageButton>(R.id.imageButton2)
        var imageButton3 = findViewById<ImageButton>(R.id.imageButton3)
        var imageButton4 = findViewById<ImageButton>(R.id.imageButton4)
        var imageButton5 = findViewById<ImageButton>(R.id.imageButton5)
        var imageButton6 = findViewById<ImageButton>(R.id.imageButton6)
        var imageButton7 = findViewById<ImageButton>(R.id.imageButton7)
        var imageButton8 = findViewById<ImageButton>(R.id.imageButton8)

        buttons = listOf(imageButton1, imageButton2, imageButton3, imageButton4, imageButton5,
            imageButton6, imageButton7, imageButton8)

        gambar = buttons.indices.map { index ->
            pictureCard(images[index])
        }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Log.i(TAG, "button clicked!!")
                // Update models
                updateModels(index)
                // Update the UI
                updateViews()
            }
        }
    }

    //gambar ketika tertutup
    private fun updateViews() {
        gambar.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.1f
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else R.drawable.gift)
        }
    }

    //gambar yang dipilih tidak sama
    private fun updateModels(position: Int) {
        val Gambar = gambar[position]
        // Error check
        if (Gambar.isFaceUp) {
            Toast.makeText(this, "Invalid card!", Toast.LENGTH_SHORT).show()
            return
        }

        // 3 kemungkinan
        if (indexOfSingleSelectedCard == null) {
            // 0 atau 2 gambar yang dipilih
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            // 1 gambar sahaja yang tekan
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        Gambar.isFaceUp = !Gambar.isFaceUp //satu gambar flip
    }

    //untuk kembalikan gambar tertutup atau opening game
    private fun restoreCards() {
        for (Gambar in gambar) {
            if (!Gambar.isMatched) {
                Gambar.isFaceUp = false
            }
        }
    }
    //gambar sama
    private fun checkForMatch(position1: Int, position2: Int) {
        if (gambar[position1].identifier == gambar[position2].identifier) {
            Toast.makeText(this, "Picture Match!!", Toast.LENGTH_SHORT).show()
            gambar[position1].isMatched = true
            gambar[position2].isMatched = true
        }
    }
}