package com.webmyne.modak.model

import java.io.Serializable


open class Item_Details : Serializable {
    var itemId: Int = 0
    var itemChefId: Int = 0
    var ItemImage: String = ""
    var ItemCity: String = ""
    var ItemName: String = ""
    var ItemPrice: String = ""
    var itemDistance: String = ""
    var itemCount: Int = 0
    var ItemDescription: String = ""


    var chef_id: Int = 0
    var ChefName: String = ""
    var ChefImage: String = ""
    var ChefAverageRating: Float = 0f
    var ChefCuisineTye:String=""
    var ChefDescription:String=""

    companion object {
        val TABLE_ITEM_DETAILS = "table_item_details"
        val COLUMN_ITEM_ID = "item_id"
        val COLUMN_ITEM_CHEF_ID = "item_chef_id"
        val COLUMN_ITEM_IMAGE = "item_image"
        val COLUMN_ITEM_CITY = "item_city"
        val COLUMN_ITEM_NAME = "item_name"
        val COLUMN_ITEM_PRICE = "item_price"
        val COLUMN_ITEM_DISTANCE = "item_distance"
        val COLUMN_ITEM_DESCRIPTION = "item_discription"
        val COLUMN_ITEM_COUNT = "item_count"

        val CREATE_ITEM_DETAILS_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_ITEM_DETAILS + "("
                        + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_ITEM_CHEF_ID + " INTEGER NOT NULL,"
                        + COLUMN_ITEM_IMAGE + " TEXT,"
                        + COLUMN_ITEM_CITY + " TEXT,"
                        + COLUMN_ITEM_NAME + " TEXT,"
                        + COLUMN_ITEM_PRICE + " TEXT,"
                        + COLUMN_ITEM_COUNT + " INTEGER,"
                        + COLUMN_ITEM_DESCRIPTION + " TEXT,"
                        + COLUMN_ITEM_DISTANCE + " TEXT" +
                        ")"
                )
    }
}
