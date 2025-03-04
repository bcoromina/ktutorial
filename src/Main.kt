

// naive
fun twoSum(nums: IntArray, target: Int): IntArray {

    fun checkNumber(a: Int): Int? {
        var result: Int? = null
        for(j in nums.indices){
            result = if((nums[a] + nums[j]) == target && j != a) j else result
        }
        return result
    }

    val result = IntArray(2)

    for(i in nums.indices){
        val secondIndex = checkNumber(i)
        secondIndex?.let { sInd ->
            result[0] = i
            result[1] = sInd
        }
    }

    return result
}

// Time complexity O(n)
// but adds O(n) in space complexity
fun twoSum2(nums: IntArray, target: Int): IntArray {
    var complements = mutableMapOf<Int,Int>()
    for(i in nums.indices){
        val complement = target - nums[i]
        complements.get(complement)?.let{
            return intArrayOf(i, it)
        }
        complements.put(nums[i], i)
    }
    throw Exception("Not Found")
}

fun quickSort(nums: IntArray) {

    fun sortPivot(baseIndex: Int, pivotIndex: Int) {
        if(baseIndex >= pivotIndex) return

        val pivot = nums[pivotIndex]
        var i = baseIndex -1
        var j = baseIndex

        fun swap(a: Int, b: Int){
            if(nums[a] == nums[b]) return
            val aux = nums[b]
            nums[b] = nums[a]
            nums[a] = aux
        }

        while(j < pivotIndex){
            if(nums[j] < pivot){
                i += 1
                swap(i,j)
            }
            j += 1
        }
        swap(i + 1 , pivotIndex)

        sortPivot(baseIndex, i) // Sort left

        sortPivot(i + 2, pivotIndex) // Sort right
    }
    sortPivot(0, nums.size -1)
}


class ListNode(var value: Int) {
    var next: ListNode? = null
}

fun add(a: Int?, b: Int?): Int = (a ?: 0) + (b ?: 0)

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    fun add(a: ListNode?, b: ListNode?, prev: Int): ListNode?{
        if(a != null || b != null){
            val v = (a?.value ?: 0) + (b?.value ?: 0) + prev
            val res = ListNode(v % 10)

            val nextToAdd = v / 10

            if(a?.next != null || b?.next == null){
                res.next = add(a?.next, b?.next, v / 10)
            }

            if(a?.next == null && b?.next == null && nextToAdd > 0){
                res.next = ListNode(nextToAdd)
            }

            return res
        }else
            return null
    }
    return add(l1, l2, 0)
}


fun longestPalindrome(s: String): String {
    fun isPalindromic(str: String): Boolean = str.reversed() == str

    fun checkPalindrome(length: Int): String? {
        if(length > s.length) return null

        var ini = 0
        var end = length - 1
        var p: String? = null

        while(ini < s.length && end < s.length){
            val sub = s.substring(ini, end+1)
            if(isPalindromic(sub)){
                if(sub.length > (p?.length ?: 0) ){
                    p = sub
                }
            }
            ini += 1
            end += 1
        }

        return p
    }

    var result: String? = null
    for(length in s.length downTo 1){
        val p = checkPalindrome(length)
        if((p?.length ?: 0) > (result?.length ?: 0)){
            result = p
        }
    }
    return result!!
}


fun main() {

    println(longestPalindrome("cbbd"))

//    val r = addTwoNumbers(ListNode(2), ListNode(5))
//    println(r)

//    println(twoSum2( nums = intArrayOf(2,45,11,7), target = 9).joinToString())
//    println(twoSum2( nums = intArrayOf(3,3), target = 6).joinToString())
//    val arr = intArrayOf(5,3,7,6,9,2,1,8,4)
//    quickSort(arr)
//    println(arr.joinToString())
}