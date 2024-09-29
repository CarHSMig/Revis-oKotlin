package com.example.truco

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nosScoreTextView: TextView
    private lateinit var elesScoreTextView: TextView

    private var nosScore = 0
    private var elesScore = 0
    private val maxScore = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nosScoreTextView = findViewById(R.id.nos_score)
        elesScoreTextView = findViewById(R.id.eles_score)

        findViewById<Button>(R.id.nos_plus).setOnClickListener {
            updateScore(true, 1)
        }

        findViewById<Button>(R.id.nos_truco).setOnClickListener {
            updateScore(true, 3)
        }

        findViewById<Button>(R.id.nos_minus).setOnClickListener {
            updateScore(true, -1)
        }

        findViewById<Button>(R.id.eles_plus).setOnClickListener {
            updateScore(false, 1)
        }

        findViewById<Button>(R.id.eles_truco).setOnClickListener {
            updateScore(false, 3) // Corrigido para +3 pontos
        }

        findViewById<Button>(R.id.eles_minus).setOnClickListener {
            updateScore(false, -1)
        }

        findViewById<Button>(R.id.reset_button).setOnClickListener {
            resetGame()
        }
    }

    private fun updateScore(isNos: Boolean, value: Int) {
        if (isNos) {
            nosScore = (nosScore + value).coerceIn(0, maxScore) // Atualizando a pontuação de "Nós"
            nosScoreTextView.text = nosScore.toString() // Atualizando a exibição
        } else {
            elesScore = (elesScore + value).coerceIn(0, maxScore) // Atualizando a pontuação de "Eles"
            elesScoreTextView.text = elesScore.toString() // Atualizando a exibição
        }
        checkWinner()
    }

    private fun checkWinner() {
        if (nosScore == maxScore) {
            showWinnerDialog("Nós ganhamos!")
        } else if (elesScore == maxScore) {
            showWinnerDialog("Eles ganharam!")
        }
    }

    private fun showWinnerDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Fim do jogo")
            .setMessage(message)
            .setPositiveButton("OK") { _, _ -> resetGame() }
            .setCancelable(false)
            .show()
    }

    private fun resetGame() {
        AlertDialog.Builder(this)
            .setTitle("Zerar")
            .setMessage("Tem certeza que deseja começar novamente a pontuação?")
            .setPositiveButton("OK") { _, _ ->
                nosScore = 0
                elesScore = 0
                nosScoreTextView.text = nosScore.toString()
                elesScoreTextView.text = elesScore.toString()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
