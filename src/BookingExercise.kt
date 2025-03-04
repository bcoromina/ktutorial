import kotlin.math.max
import kotlin.math.min

class Calendar() {
    private val ResSize = 100

    val reservations: IntArray = IntArray(ResSize)

    init {
        reservations.fill(0,0, ResSize)
    }

    fun addEvent(ini: Int, end: Int): Boolean {
        require(ini < end) { "Inconsistent interval"}
        require(ini < ResSize)
        require(end < ResSize)

        var permit = true
        var i = ini
        while(permit && i < end){
            if( reservations[i]  == 2){
                permit = false
            }else{
                reservations[i] += 1
            }
            i += 1
        }
        return permit
    }
}

class Interval private constructor(val ini: Int, val end: Int){
    fun contains(n: Int): Boolean = n in (ini + 1)..<end
    companion object{
        fun build(ini: Int, end: Int): Interval? =
            if(ini < end)
                Interval(ini, end)
            else null

    }
}

fun intersec(a: Interval, b: Interval): Interval? =
    if(b.contains(a.ini))
        Interval.build(a.ini, min(a.end, b.end))
    else if(b.contains(a.end))
        Interval.build(max(a.ini, b.ini), a.end)
    else if(a.contains(b.ini))
        Interval.build(b.ini, min(b.end, a.end))
    else if(a.contains(b.end))
        Interval.build(max(b.ini, a.ini), b.end)
    else
        null

data class Reservation(val interval: Interval, val count: Int)





fun main(){

    val calendar = Calendar()

    fun processEvent(ini: Int, end: Int): Boolean{
        val result = calendar.addEvent(ini,end)
        println("Interval [$ini, $end]]: $result")
        println("Array: ${calendar.reservations.take(20)}")
        return result
    }



    assert(processEvent(10, 20))
    assert(processEvent(15, 25))
    assert(!processEvent(12, 16))
    assert(processEvent(5, 10))
    

//    assert(intersec(Interval(10,20), Interval(12,16)) == Interval(12, 16))
//    assert(intersec(Interval(12,16), Interval(10,20)) == Interval(12, 16))
//
//    assert(intersec(Interval(15,25), Interval(10,20)) == Interval(15, 20))
//    assert(intersec(Interval(10,20), Interval(15,25) ) == Interval(15, 20))
//
//    assert(intersec(Interval(5,13), Interval(10,20)) == Interval(10, 13))
//    assert(intersec(Interval(10,20), Interval(5,13)) == Interval(10, 13))
//
//    assert(intersec(Interval(3,7), Interval(5,13)) == null)
//    assert(intersec(Interval(5,13), Interval(3,7)) == null)
//
//    assert(intersec(Interval(50,60), Interval(5,13)) == null)
//    assert(intersec(Interval(5,13), Interval(50,60)) == null)


}