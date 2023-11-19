package uz.os3ketchup.myhelper.domain

data class Order(
    var id: String = "",
    var uId: String = "",
    var list: List<Product> = listOf(),
    var time: String = "",
    var toUId: String = "",
    var totalPrice:String = ""
)