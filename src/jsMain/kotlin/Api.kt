import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.browser.window

val endpoint = window.location.origin

val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun getItemList(): List<Item> {
    return jsonClient.get(endpoint + Item.path).body()
}

suspend fun addItem(item: Item) {
    jsonClient.post(endpoint + Item.path) {
        contentType(ContentType.Application.Json)
        setBody(item)
    }
}

suspend fun deleteItem(item: Item) {
    jsonClient.delete(endpoint + Item.path + "/${item.id}")
}