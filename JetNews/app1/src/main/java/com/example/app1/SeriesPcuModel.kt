package com.example.app1



import org.json.JSONObject

fun jsonToCategory(catJson: JSONObject): SeriesPcuCategoryItem {
    val category = SeriesPcuCategoryItem()
    category.apply {
        json = catJson
        val list = catJson.optJSONArray("list")
        if (list != null && list.length() > 0) {
            for (i in 0 until list.length()) {
                childs.add(
                        jsonToCategory(list.getJSONObject(i)).apply {
                            parent = category
                        }
                )
            }
        }
        id = catJson.optString("id")
        title = catJson.optString("title")
        price = catJson.optString("price")?.toDoubleOrNull()?.let {
            "$${it}"
        }
        sort = catJson.optInt("sort")
        childType = catJson.optString("child_type")
        childTypeName = catJson.optString("child_type_name")
        productInfo = catJson.optString("product_info")
        thumbnail = catJson.optString("thumbnail")
        skuCode = catJson.optString("size")
        typeIcon = catJson.optString("type_icon")
        typeName = catJson.optString("type_name")
        groupId = catJson.optString("group_id")
    }
    return category

}

open class SeriesPcuCategoryItem {
    lateinit var json:JSONObject
    var thumbnail: String? = null
    var id: String? = null
    var title: String? = null
    var sort: Int = -1
    var price: String? = null
    var selected: Boolean = false
    var selectable: Boolean = true
    var childType: String = ""
    var childTypeName: String = ""
    var productInfo: String? = null
    var childs: MutableList<SeriesPcuCategoryItem> = ArrayList()
    var parent: SeriesPcuCategoryItem? = null
    var skuCode: String? = null
    var typeIcon: String? = null
    var typeName: String? = null
    var groupId:String? =null

    fun clearStateRecursive() {
        childs.forEach {
            it.selected = false
            it.clearStateRecursive()
        }
    }

}