package kt.training.generics

sealed interface Employee

//Data classes exposes equals and copy methods
data class Developer(val name: String, val age: Int): Employee

interface KVStore<K,V> {
    fun get(key: K): V?
    fun values(): List<V>
    fun <T> map(f: (V) -> T ): KVStore<K,T>
    fun put(key: K, value: V): KVStore<K,V>
}

class InMemoryKVStore<K,V> : KVStore<K,V> {

    init {
        println("New InMemoryKVStore created")
    }

    companion object{
        fun<K,V> empty():InMemoryKVStore<K,V> = InMemoryKVStore()
    }

    constructor(){
        store = mapOf()
    }
    constructor(s: Map<K, V>){
        store = s
    }

    private var store: Map<K, V> = mutableMapOf<K,V>()

    override fun get(key: K): V? = store.get(key)

    override fun values(): List<V> = store.values.toList()

    override fun <T> map(f: (V) -> T): InMemoryKVStore<K, T> {
        val mutableMap = mutableMapOf<K,T>()
        store.forEach { mutableMap.put(it.key, f(it.value)) }
        return InMemoryKVStore(mutableMap)
    }

    override fun put(key: K, value: V): KVStore<K, V> {
        val mutableMap = mutableMapOf<K, V>().apply { 
            putAll(store)
        }
        mutableMap.put(key, value)
        return InMemoryKVStore(mutableMap)
    }

}


fun main(){
  
//    val emptyStore = InMemoryKVStore.empty<String, Developer>()
//    val a: InMemoryKVStore<String, Developer> = InMemoryKVStore()
//
//    val entry: Pair<String, Developer> = "bernat" to Developer("bernat", 44)
//
//    val l: Pair<Int, String> = Pair(1, "hola")

    val store = InMemoryKVStore(
        mapOf(
            "bernat" to Developer("bernat", 44),
            "nuria" to Developer("nuria", 49),
            "nil" to Developer("nil", 11)
        )
    )

    val fullStore = store.put("pepe", Developer("pepe", 100))

    val result = fullStore.map { value -> "Age: ${value.age}" }.values()

    for(ageStr in result){
        println(ageStr)
    }

    println("Ages: ${result}")
}
