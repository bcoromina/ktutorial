class QueryBuilder {
    private val selectColumns = mutableListOf<String>()
    private var fromTable: String? = null
    private val whereClauses = mutableListOf<String>()

    fun select(vararg columns: String) {
        //out: means it is a covariant type so you can only read elements out of it
        val f: Array<out String> = columns

        selectColumns.addAll(columns)
    }

    fun from(table: String) {
        fromTable = table
    }

    fun where(condition: String) {
        whereClauses.add(condition)
    }

    override fun toString(): String {
        require(fromTable != null) { "FROM clause is required" }
        require(selectColumns.isNotEmpty()) { "SELECT clause must have at least one column" }

        return buildString {
            append("SELECT ")
            append(selectColumns.joinToString(", "))
            append(" FROM ")
            append(fromTable)
            if (whereClauses.isNotEmpty()) {
                append(" WHERE ")
                append(whereClauses.joinToString(" AND "))
            }
            append(";")
        }
    }
}

fun query(block: QueryBuilder.() -> Unit): QueryBuilder {
    return QueryBuilder().apply(block)
}

// Example usage>
fun main() {
    val query = query {
        select("id", "name")
        from("users")
        where("age > 18")
    }
    println(query) // Outputs: SELECT id, name FROM users WHERE age > 18;
}
