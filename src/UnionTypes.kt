
package kt.training.uniontypes

sealed interface Employee

//Data classes exposes equals and copy methods
data class Developer(val name: String, val age: Int): Employee{
    fun plusAge(a: Int)= copy(age = age + a)
}
data class Manager(val name: String, val age: Int, val devs: List<Developer>): Employee

fun getDeveloper(v: Boolean): Developer? = if(v) Developer("Bernat", 44) else null

fun getNumDevelopers(empl: Employee): Int {
    return when(empl){
        is Developer -> 0
        is Manager -> empl.devs.size
    }
}

fun getName(empl: Employee): String =
    when(empl){
        is Developer -> empl.name
        is Manager -> empl.name
    }

fun main() {
    val dev: Developer? = getDeveloper(true)

    println("Dev name: ${dev?.name}")

    val bernat = Developer("bernat", 44)
    bernat.plusAge(4)
    val (name, age) = Developer("bernat", 44) // Deconstruction

}

