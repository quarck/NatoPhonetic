package ph.quarck.github.com.nph

class MorseNumbers : PracticeActivityBase() {
    override fun CreateMapping(): Map<String, String> {
        return mapOf<String, String>(
                "0" to "-----",
                "1" to ".----",
                "2" to "..---",
                "3" to "...--",
                "4" to "....-",
                "5" to ".....",
                "6" to "-....",
                "7" to "--...",
                "8" to "---..",
                "9" to "----."
        )
    }
}
