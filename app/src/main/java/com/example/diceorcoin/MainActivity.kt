package com.example.diceorcoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**  Dice will be default   */
        val pagetitle: TextView = findViewById(R.id.Title)
        pagetitle.text = getString(R.string.Dice)
        val resultTextView1: TextView = findViewById(R.id.textView1)
        val resultTextView2: TextView = findViewById(R.id.textView2)
        resultTextView1.text = getString(R.string.resultHead, pagetitle.text, 1, "0")
        resultTextView2.text = getString(R.string.resultHead, pagetitle.text, 2, "0")
        val ansButton: Button = findViewById(R.id.button)
        ansButton.setOnClickListener { startFun() }

        /**  it will switch from dice to coin or vice versa   */
        val switchButton: Button = findViewById(R.id.Switch_btn)
        switchButton.setOnClickListener { switchBoard() }
    }
    /**  it will switch from dice to coin or vice versa   */
    private fun startFun() {
        val pagetitle: TextView = findViewById(R.id.Title)
        val ansButton: Button = findViewById(R.id.button)
        val title = pagetitle.text
        if (title == getString(R.string.Dice)) {
            ansButton.text = getString(R.string.Roll)
            rollDice()
        }else{
            ansButton.text = getString(R.string.Flip)
            flipCoin()
        }
    }


    /**  Update the screen after the call to roll or toss   */
    private fun resultUpdate(title: String, op1: String, op2: String) {
        val resultTextView1: TextView = findViewById(R.id.textView1)
        val resultTextView2: TextView = findViewById(R.id.textView2)
        resultTextView1.text = getString(R.string.result, title, 1, op1)
        resultTextView2.text = getString(R.string.result, title, 2, op2)
    }

    /**  display win!!! in the screen if both the values are same   */
    private fun winWin(op1: Int, op2: Int) {
        val winView: TextView = findViewById(R.id.Wintext)
        if (op1 == op2){
            winView.text = getString(R.string.Win)
        }else{
            winView.text = ""
        }
    }

    /**    Roll the dice and update the screen with the result     */
    private fun rollDice() {
        val pagetitle: TextView = findViewById(R.id.Title)
        pagetitle.text = getString(R.string.Dice)

        val dice1 = Dice(6)
        val diceRoll1 = dice1.roll()

        val dice2 = Dice(6)
        val diceRoll2 = dice2.roll()

        winWin(diceRoll1, diceRoll2)
        resultUpdate(getString(R.string.Dice),diceRoll1.toString(), diceRoll2.toString())
    }

    /**     flip the coin and update the screen with result     */
    private fun flipCoin() {
        val pagetitle: TextView = findViewById(R.id.Title)
        pagetitle.text = getString(R.string.Coin)

        val outcome: List<String> = listOf("Heads", "Tails")

        val coin1 = Coin()
        val coinFlip1 = coin1.flip()

        val coin2 = Coin()
        val coinFlip2 = coin2.flip()

        winWin(coinFlip1, coinFlip2)
        resultUpdate(getString(R.string.Coin),outcome[coinFlip1], outcome[coinFlip2])
    }

    /**     switches to toss or roll when click switch btn     */
    private fun switchBoard() {
        val pagetitle: TextView = findViewById(R.id.Title)
        val winView: TextView = findViewById(R.id.Wintext)
        val titletext = pagetitle.text
        winView.text = ""
        val resultTextView1: TextView = findViewById(R.id.textView1)
        val resultTextView2: TextView = findViewById(R.id.textView2)
        val ansButton: Button = findViewById(R.id.button)

        if (titletext == getString(R.string.Dice)) {
            pagetitle.text = getString(R.string.Coin)
            resultTextView1.text = getString(R.string.resultHead, pagetitle.text, 1, "NaN")
            resultTextView2.text = getString(R.string.resultHead, pagetitle.text, 2, "NaN")
            ansButton.text = getString(R.string.Flip)
        }else{
            pagetitle.text = getString(R.string.Dice)
            ansButton.text = getString(R.string.Roll)
            resultTextView1.text = getString(R.string.resultHead, pagetitle.text, 1, "0")
            resultTextView2.text = getString(R.string.resultHead, pagetitle.text, 2, "0")
        }
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}

class Coin {
    fun flip(): Int {
        return (0..1).random()
    }
}
