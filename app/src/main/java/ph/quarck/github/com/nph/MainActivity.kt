package ph.quarck.github.com.nph

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWindow().setNavigationBarColor(getResources().getColor(android.R.color.black));


        (findViewById(R.id.buttonNato) as Button).setOnClickListener {
            startActivity(Intent(this, NatoPhonetic::class.java))
        }
        (findViewById(R.id.buttonMorseLetters) as Button).setOnClickListener {
            startActivity(Intent(this, MorseLetters::class.java))
        }
        (findViewById(R.id.buttonMorseNumbers) as Button).setOnClickListener {
            startActivity(Intent(this, MorseNumbers::class.java))

        }
    }
}
