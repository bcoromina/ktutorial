

fun factorial(n: Int): Int {
    require(n >= 0) { "Number of elements must be non-negative!" }
    tailrec fun fact(n: Int, acc: Int): Int {
        return if(n == 1) acc else fact( n = n-1, acc = acc * n)
    }
    return fact(n, 1)
}


//fun fibonacci(n: Int): List<Int> {
//    require(n >= 0) { "Number of elements must be non-negative!" }
//    return when(n){
//        0 -> listOf()
//        1 -> listOf(0)
//        2 -> listOf(0, 1)
//        else -> {
//            val fibTail = fibonacci(n-1)
//            fibTail + (fibTail[n-2] + fibTail[n-3])
//        }
//    }
//}

tailrec fun fibonacci(n: Int, a: Int = 0, b: Int = 1, result: List<Int> = listOf()): List<Int> {
    return when {
        n <= 0 -> result
        n == 1 -> result + a
        n == 2 -> result + b
        else -> fibonacci(n - 1, b, a + b, result + a)
    }
}

fun main(){
    println("4! = ${factorial(4)}")
    println("Fibonacci: ${fibonacci(8)}")
}
