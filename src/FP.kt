
enum class LogLevel(val prefix: String){
    Info("INFO"), Debug("DEBUG"), Error("Error")
}

val logs = listOf(
    "INFO: Server started",
    "ERROR: Database connection failed",
    "ERROR: Timeout exception",
    "ERROR: Database connection failed",
    "INFO: Health check passed",
    "ERROR: Null pointer exception",
    "DEBUG: Health check passed",
    "DEBUG: Health check passed",
    "DEBUG: Health check passed",
    "CRITICAL: Health check passed",
)

fun main(){




    val hundredMul = fun(x: Int): Int = 100 * x
    val tenMult = { x: Int -> x * 10 }

    fun applyFunc(n: Int, func: (Int) -> Int): Int = func(n)

    applyFunc(5, tenMult)
    applyFunc(8, {x -> x *56} )

    val adder = { a: Int, b: Int -> a + b }

    fun isError(str: String): Boolean = str.startsWith(LogLevel.Error.prefix)
    fun isDebug(str: String): Boolean = str.startsWith(LogLevel.Debug.prefix)
    fun isInfo(str: String): Boolean = str.startsWith(LogLevel.Info.prefix)

    fun getPrefix(str: String): LogLevel? =
        when {
            isError(str) -> LogLevel.Error
            isDebug(str) -> LogLevel.Debug
            isInfo(str)  -> LogLevel.Info
            else -> null
        }

    val result = logs
        .asSequence() // transformations will be lazy evaluated
        .filterNot { isError(it) }
        .flatMap { msg ->
            getPrefix(msg)?.let{  prefix -> listOf(prefix to msg) } ?: listOf()
        }
        .groupBy { e -> e.first }
        .mapValues { it.value.size }
        .toList()
        .sortedByDescending { it.second }

    println(result.take(1))
}