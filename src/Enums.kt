sealed interface Color
data object Red : Color
data object Green : Color
data object Blue : Color


fun printColor(c: Color): String =
    when(c){
        Red -> "Red"
        Blue -> "Blue"
        Green -> "Green"
    }


enum class EnumColor(val code: Int){
    RED(1), GREEN(2), BLUE(3)
}

fun printEnumColor(color: EnumColor): String =
    when(color){
        EnumColor.RED -> "RED"
        EnumColor.GREEN -> "GREEN"
        EnumColor.BLUE -> "BLUE"
    }


fun main(){
    println("My color is ${EnumColor.RED.code}")
}