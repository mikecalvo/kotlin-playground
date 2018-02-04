import static junit.framework.Assert.assertEquals

class WordCountGroovy {
  static Map<String, Integer> count(String text) {
    def response = new HashMap<String, Integer>()
    text.split("[[\\.\\?\\!\\,\\;\\(\\)]*\\s]+").each { String word ->
      if (!word) {
        return
      }
      def lowerCase = word.toLowerCase()
      response[lowerCase] = response[lowerCase]?.plus(1) ?: 1
    }

    response
  }

  public static void main(String[] args) {
    def start = System.currentTimeMillis()
    assertEquals([a: 2, b: 1], count("a b a"))
    assertEquals([a: 2, b: 3], count("a     b a  b b"))
    assertEquals([a: 2, b: 1], count("a.  a; b,"))
    assertEquals([a: 2], count("a!  A? "))
    assertEquals([:], count(""))
    assertEquals(["mike's": 4], count("mike's Mike's MIKE'S, mike's!"))
    assertEquals([a: 5], count("a\ta\ta\ta\t\t\ta"))
    assertEquals([a: 2], count("(a)?(a)"))

    def total = System.currentTimeMillis() - start
    println "Total time: ${total}"

  }
}