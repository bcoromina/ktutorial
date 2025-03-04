package kt.training.generics

import java.math.BigDecimal


sealed interface Stream<out A>{

    fun take(n: Int): List<A> {
        tailrec fun aux(acc: MutableList<A>, s: Stream<A>, nAcc: Int): List<A> {
            if (nAcc == 0) {
                return acc
            } else {
                when (s) {
                    is Empty -> return acc
                    is Cons<A> -> {
                        acc.add(s.e)
                        return aux(acc, s.next(), nAcc - 1)
                    }
                }
            }
        }

        return aux(mutableListOf(), this, n)
    }

    fun<B> map(f: (A) -> B): Stream<B> {
        fun aux(s: Stream<A>): Stream<B> =
            when(s){
                is Empty -> Empty
                is Cons<A> -> Cons(f(s.e), {aux(s.next())})
            }
        return aux(this)
    }
}

data object Empty : Stream<Nothing>
data class Cons<T>(val e: T, val next: () -> Stream<T>): Stream<T>



// Solution with type classes
sealed interface HasNext<A>{
    fun next(i: A): A
}

object IntHasNext: HasNext<Int>{
    override fun next(i: Int): Int = i+1
}

inline fun <reified T> getInstance(): HasNext<T>? = when (T::class) {
    IntHasNext::class -> IntHasNext as HasNext<T>
    else -> null
}

// Solution with type classes doesn't work because inline function cannot be recursive
//inline fun <reified T> from(n: T): Stream<T>  {
//    val hasNextInstance = getInstance<T>()
//    return Cons(n,{ from(hasNextInstance?.next(n) ?: throw Exception("Instance not found")) })
//}


val intScope = object : HasNext2<Int>{
    override fun Int.next(): Int = this + 1

}

//Scope
interface HasNext2<A>{ //Dispatcher receiver
    fun A.next(): A //receiver
}

fun<A> HasNext2<A>.from2(n: A): Stream<A> {
    return Cons(n, {from2(n.next())})
}

fun main(){

    with(intScope){
        from2(3)
    }


    //fun from(n: Int): Stream<Int> = Cons(n,{ from(n+1) })
    fun<T> from(n: T, next: (T) -> T): Stream<T> = Cons(n, { from(next(n), next) })

    println(from(10, {x  -> x +1 }).map{x -> x*2}.take(5))
}