package ph.quarck.github.com.nph

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : Activity() {

    lateinit var mapping: Map<String, String>
    lateinit var remainingLetters: MutableList<String>

    lateinit var letter: TextView
    lateinit var name: TextView
    lateinit var button: Button

    var state = 0
    var currentLetter: String = ""

    var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapping = mapOf<String, String>(
                "A" to "Alpha",
                "B" to "Bravo",
                "C" to "Charlie",
                "D" to "Delta",
                "E" to "Echo",
                "F" to "Foxtrot",
                "G" to "Golf",
                "H" to "Hotel",
                "I" to "India",
                "J" to "Juliet",
                "K" to "Kilo",
                "L" to "Lima",
                "M" to "Mike",
                "N" to "November",
                "O" to "Oscar",
                "P" to "Papa",
                "Q" to "Quebec",
                "R" to "Romeo",
                "S" to "Sierra",
                "T" to "Tango",
                "U" to "Uniform",
                "V" to "Victor",
                "W" to "Whiskey",
                "X" to "X-ray",
                "Y" to "Yankee",
                "Z" to "Zulu"
                )

        letter = findViewById(R.id.letter) as TextView
        name = findViewById(R.id.name) as TextView
        button = findViewById(R.id.button) as Button

        remainingLetters = mapping.keys.toMutableList()

        pickNextLetter()
        letter.text = currentLetter
        button.text = "Check"
        name.visibility = View.GONE
        state = STATE_INITIAL

        button.setOnClickListener {
            when (state) {
                STATE_INITIAL -> {
                    // Time to show the answer
                    button.text = "Next"
                    name.visibility = View.VISIBLE
                    name.text = mapping[currentLetter]
                    state = STATE_ANSWER

                }
                STATE_ANSWER -> {
                    if (remainingLetters.isNotEmpty()) {
                        button.text = "Check"
                        name.visibility = View.GONE
                        pickNextLetter()

                        letter.text = currentLetter
                        state = STATE_INITIAL
                    }
                    else {
                        button.text = "Reset"
                        name.visibility = View.VISIBLE
                        name.text = "ALL DONE!!"

                        state = STATE_END
                    }

                }
                STATE_END -> {
                    remainingLetters = mapping.keys.toMutableList()

                    pickNextLetter()
                    letter.text = currentLetter
                    button.text = "Check"
                    name.visibility = View.GONE
                    state = STATE_INITIAL
                }
            }

        }
    }

    fun pickNextLetter() {
        val nextIdx = random.nextInt(remainingLetters.size)
        currentLetter = remainingLetters[nextIdx];
        remainingLetters.removeAt(nextIdx)
    }



    override fun onResume() {
        super.onResume()
    }

    companion object {
        const val STATE_INITIAL = 0
        const val STATE_ANSWER = 1
        const val STATE_END = 2
    }
}
