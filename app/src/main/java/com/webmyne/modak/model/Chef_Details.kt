package com.webmyne.modak.model

import java.io.Serializable


open class Chef_Details : Serializable {
    var chef_id:Int=0
    var ChefName: String = ""
    var ChefImage: String = ""
    var ChefAverageRating:Float=0f
    var ChefCuisineTye:String=""
    var ChefDescription:String=""

    companion object {
        val TABLE_CHEF_DETAILS = "table_chef_details"
        val COLUMN_CHEF_ID = "chef_id"
        val COLUMN_CHEF_IMAGE = "chef_image"
        val COLUMN_CHEF_NAME = "chef_name"
        val COLUMN_CHEF_AVERAGE_RATING = "chef_average_rating"
        val COLUMN_CHEF_CUISINE_TYPE = "chef_cuisine_type"
        val COLUMN_CHEF_DESCRIPTION = "chef_description"

        val CREATE_CHEF_DETAILS_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_CHEF_DETAILS + "("
                        + COLUMN_CHEF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_CHEF_IMAGE + " TEXT,"
                        + COLUMN_CHEF_AVERAGE_RATING + " INTEGER,"
                        + COLUMN_CHEF_CUISINE_TYPE + " TEXT,"
                        + COLUMN_CHEF_DESCRIPTION + " TEXT,"
                        + COLUMN_CHEF_NAME + " TEXT" +
                        ")"
                )
    }
}
