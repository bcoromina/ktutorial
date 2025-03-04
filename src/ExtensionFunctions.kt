

// Concept 'Receiver': String is the receiver type and 'this'
// refers to the receiver object
fun String.shout(): String = this.uppercase() + "!!!"

fun main(){

    val list = listOf(1,2,3)
    list.show()
    println("hello".shout())
}

fun<T> List<T>.show(): String = this.joinToString()