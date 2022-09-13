import kotlinx.serialization.Serializable

@Serializable
data class Item(val item: String, val priority: Int) {
    val id: Int = item.hashCode()

    companion object {
        const val path = "/items"
    }
}