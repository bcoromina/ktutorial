

//fun solution(A: IntArray, K: Int): Boolean {
//    val n = A.size
//    for (i in 0 until n - 1) {
//        if (A[i] + 1 < A[i + 1])
//            return false // a number is missing
//    }
//    if (A[0] != 1 || A[n - 1] != K)
//        return false
//    else
//        return true
//}

//A[0] = 1
//A[1] = 1
//A[2] = 2
//A[3] = 3
//A[4] = 3
//import kotlin.math.ceil
//fun solution(A: IntArray): Int {
//
//    val avg = ceil(A.sum().toDouble() / A.size)
//
//    var steps = 0
//    for (num in A) {
//        steps += Math.abs(num - avg.toInt())
//    }
//
//    return steps
//}


import kotlin.math.abs

//fun solution(A: IntArray): Int {
//
//    fun calcForTarget(target: Int): Int{
//        var result = 0
//        for(a in A){
//            var rotation = target - a
//            if (rotation > 180) {
//                rotation -= 360  // Rotate counterclockwise
//            } else if (rotation < -180) {
//                rotation += 360  // Rotate clockwise
//            }
//            result += abs(rotation)
//        }
//        return result
//    }
//
//
//    val res = IntArray(A.size)
//    var i = 0
//    for(a in A){
//        res.set(i, calcForTarget(a))
//        i += 1
//    }
//    return res.min()
//}

fun solution(A: IntArray): Int {
    val n = A.size

    // Helper function to calculate rotation cost between one element and the target
    fun rotationCost(a: Int, target: Int): Int {
        var rotation = target - a
        if (rotation > 180) {
            rotation -= 360  // Rotate counterclockwise
        } else if (rotation < -180) {
            rotation += 360  // Rotate clockwise
        }
        return Math.abs(rotation)
    }

    // Calculate total rotation cost for the first element
    var totalCost = 0
    for (a in A) {
        totalCost += rotationCost(a, A[0])
    }

    var minCost = totalCost

    // Now calculate the cost for other target angles efficiently
    for (i in 1 until n) {
        // Adjust the total cost based on the previous target (A[i-1]) and the new target (A[i])
        var newCost = totalCost
        for (j in A.indices) {
            newCost -= rotationCost(A[j], A[i - 1])
            newCost += rotationCost(A[j], A[i])
        }
        minCost = minOf(minCost, newCost)
        totalCost = newCost
    }

    return minCost
}





fun main(){

    println(solution(intArrayOf(350, 0,0)))

   //println(normalizeAngle(370))


}