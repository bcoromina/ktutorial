import kotlin.math.abs
import kotlin.random.Random
// classes are final by default so we have to use the open modifier if we want to extend them
open class Animal(val name: String) {
    open fun eat() = println("$name is eating...")

    companion object{
        // define properties and methods from for the Animal type, not for an instance
    }
}

class Dog(name: String) : Animal(name) {
    override fun eat() {
        println("A Dog is eating...")
        super.eat()
    }
    companion object{
        private fun genName(): String = "Dog-${abs((Random.nextInt() % 10))}"
        fun randomDog(): Dog = Dog(genName())
    }
}

abstract class WalkingAnimal(name: String) : Animal(name) {
    val hasLegs = true

    abstract fun walk():Unit
}


interface CanSwim{
    fun swim()
}


interface HasHair

// A class can inherit from another class and from multiple interfaces
class Cat(name: String) : WalkingAnimal(name), CanSwim, HasHair {
    override fun walk() {
        println("$name is walking like a cat")
    }

    override fun swim() {
        println("A swimming cat")
    }
}





fun main(){
    
    val e = arrayOf(Dog("p"), Dog("a"))

    val f: Array<Int> = arrayOf(1,2,4)
    val g: IntArray = intArrayOf(1,2,3)
    
    val d = Dog("titi")

    d.eat()

    //subtype polymorphism:
    val p: Animal = d // variable p receives a valid substitute of animal, a Dog
    p.eat()           // The code of the most specific type will be involved

    val cat = Cat("kiki")
    cat.walk()


    Dog.randomDog().eat()

}