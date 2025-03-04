

fun<E,T> List<E>.fold(initial: T, operation: (E,T) -> T): T  =
    if(this.isEmpty())
        initial
    else
        this.drop(1).fold(operation(this.first(), initial), operation)



sealed class MyList<out T> {

    companion object{
        fun<E,T> myListOf(vararg elements: E): MyList<E> where T : E   {
            elements.reverse()
            val empty: MyList<E> = Empty
            return elements.toList().fold(empty, { e, lst -> Cons(e, lst)} )
        }
    }


    abstract fun <R> fold(initial: R, operation: (R, T) -> R): R

    fun <T> toString(l: MyList<T>): String {
        tailrec fun toStr(list: MyList<T>, acc: String): String =
            when(list){
                is Cons<T> -> {
                    val sep = if(list.tail == Empty) "" else ","
                    toStr(list.tail, acc +"${list.head}" + sep)
                }
                is Empty -> acc
            }

        return toStr(l, "[") + "]"
    }


}


data class Cons<T>(val head: T, val tail: MyList<T>) : MyList<T>(){
    override fun toString(): String = toString(this)
    override fun <R> fold(initial: R, operation: (R, T) -> R): R =
         tail.fold(operation(initial, head), operation)

}

object Empty : MyList<Nothing>() {
    override fun toString(): String = toString(this)
    override fun <R> fold(initial: R, operation: (R, Nothing) -> R): R = initial
}



fun main() {
    val l: MyList<Int> = Cons(1, Cons(2, Cons(3, Empty)))
    println(l.toString())
    println(l.fold(0, {x,y -> x + y}))

    val res = MyList.myListOf(4,5,6,7,8)
    println(res.toString())
}



