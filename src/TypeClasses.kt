
//Type class definition
sealed interface Show<T>{
    fun show(a: T): String
}


// instances
object ShowInt : Show<Int> {
    override fun show(a: Int): String = "Show int: $a"
}

object ShowString: Show<String> {
    override fun show(a: String): String = "Show string: $a"
}


// Substitutes implicit resolution in scala
inline fun <reified T> showInstance(): Show<T>? = when (T::class) {
    Int::class -> ShowInt as Show<T>
    String::class -> ShowString as Show<T>
    else -> null
}

//Syntax
inline fun <reified T> printShow(value: T): String {
    val instance = showInstance<T>()
    return instance?.show(value) ?: "No Show instance found"
}

fun main(){
    println(printShow(3))

    println(printShow("Bernat"))
}