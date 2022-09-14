import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul

private val scope = MainScope()

val App = FC<Props> {
    var list by useState(emptyList<Item>())

    useEffectOnce {
        scope.launch {
            list = getItemList()
        }
    }

    h1 {
        +"Item List"
    }
    ul {
        list.sortedByDescending(Item::priority).forEach { item ->
            li {
                key = item.toString()
                onClick = {
                    scope.launch {
                        deleteItem(item)
                        list = getItemList()
                    }
                }
                +"[${item.priority}] ${item.item}"
            }
        }
    }
    inputComponent {
        onSubmit = { input ->
            val item = Item(input.replace("!", ""), input.count { it == '!' })
            scope.launch {
                addItem(item)
                list = getItemList()
            }
        }
    }
}
