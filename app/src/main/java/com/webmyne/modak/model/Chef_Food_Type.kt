package com.webmyne.modak.model

import java.io.Serializable


open class Chef_Food_Type : Serializable {
    var food_type_id: Int = 0
    var Chef_food_type_id: Int = 0
    var food_type_Name: String = ""


    companion object {
        val TABLE_CHEF_FOOD_TYPE = "chef_food_type"
        val COLUMN_FOOD_TYPE_ID = "food_type_id"
        val COLUMN_CHEF_FOOD_TYPE_ID = "chef_food_type_id"
        val COLUMN_FOOD_TYPE_NAME = "food_type_name"

        val CREATE_CHEF_FOOD_TYPE_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_CHEF_FOOD_TYPE + "("
                        + COLUMN_FOOD_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_CHEF_FOOD_TYPE_ID + " INTEGER NOT NULL,"
                        + COLUMN_FOOD_TYPE_NAME + " TEXT" +
                        ")"
                )
    }
}
