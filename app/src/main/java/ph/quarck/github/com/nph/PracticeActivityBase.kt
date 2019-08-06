package ph.quarck.github.com.nph

import android.os.Bundle
import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.TextView

import java.util.*

abstract class PracticeActivityBase : Activity() {

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
        setContentView(R.layout.activity_practice)

        getWindow().setNavigationBarColor(getResources().getColor(android.R.color.black));

        mapping = CreateMapping()

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

    protected abstract fun CreateMapping(): Map<String, String>

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
