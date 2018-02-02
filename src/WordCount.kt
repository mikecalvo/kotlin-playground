import kotlin.test.assertEquals

fun countWords(text: String) : Map<String, Int> {
    val response = HashMap<String, Int>()
    val words = text.split("[[\\.\\?\\!\\,\\;\\(\\)]*\\s]+".toRegex())
    words.forEach({ word:String ->
        if (word.isNotBlank()) {
            val lowerCase = word.toLowerCase()
            response[lowerCase] = response[lowerCase]?.plus(1) ?: 1
        }
    })
    return response
}

fun main(args : Array<String>) {
    assertEquals(mapOf("a" to 2, "b" to 1), countWords("a b a"), "Simple example")
    assertEquals(mapOf("a" to 2, "b" to 3), countWords("a     b a  b b"), "Extra spaces")
    assertEquals(mapOf("a" to 2, "b" to 1), countWords("a.  a; b,"), "Punctuation")
    assertEquals(mapOf("a" to 2), countWords("a!  A? "), "Capitalization")
    assertEquals(mapOf(), countWords(""), "Empty string")
    assertEquals(mapOf("mike's" to 4), countWords("mike's Mike's MIKE'S, mike's!"), "Apostrophe OK")
    assertEquals(mapOf("a" to 5), countWords("a\ta\ta\ta\t\t\ta"), "Tabs")
    assertEquals(mapOf("a" to 2), countWords("(a)?(a)"), "Parentheses")
}