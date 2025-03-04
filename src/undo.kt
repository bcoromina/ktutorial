

sealed interface OperationType
data object Write: OperationType
data object Delete : OperationType

enum class OpType(val typeStr: String){
    Write("write"), Delete("delete")
}

fun fromString(s: String): OpType? =
    when(s){
        OpType.Write.typeStr -> OpType.Write
        OpType.Delete.typeStr -> OpType.Delete
        else -> null
    }



data class Operation(val type: OperationType, val text: String)

class Document(inputActions: List<Operation>) {
    private val operations = mutableListOf<Operation>()

    init {
        for(a in inputActions){
            operations.add(a)
        }
    }

    companion object{
        fun empty(): Document = Document(listOf())
    }

    private fun addAction(type: OperationType, text: String): Document  {
        operations.add(Operation(type, text))
        return Document(operations)
    }

    fun write(text: String) = addAction(Write, text)
    fun delete(text: String) = addAction(Delete, text)

    fun override(text: String) = delete(text).write(text)

    fun text(): String {
        tailrec fun aux(ops: List<Operation>, acc: String): String{
            when(ops){
                emptyList<Operation>() -> return acc
                else ->
                    when(ops.first().type){
                        Delete -> return aux(ops.drop(1), acc.substring(0, acc.length - ops.first().text.length))
                        Write ->  return aux(ops.drop(1), acc + ops.first().text)
                    }
            }
        }

        return aux(operations, "")
    }
}

fun main(){

    val document =
        Document.empty()
            .write("hello")
            .write(" world")
            .delete("d")
            .write(" hi")
            .override("ja")


    println(document.text())



}