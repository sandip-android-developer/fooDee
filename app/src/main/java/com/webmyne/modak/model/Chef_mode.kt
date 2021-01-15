package com.webmyne.modak.model

import java.io.Serializable


open class Chef_mode : Serializable {
    var mode_id: Int = 0
    var Chef_mode_id: Int = 0
    var mode_Name: String = ""


    companion object {
        val TABLE_CHEF_MODE = "chef_mode"
        val COLUMN_MODE_ID = "mode_id"
        val COLUMN_CHEF_MODE_ID = "chef_mode_id"
        val COLUMN_MODE_NAME = "mode_name"

        val CREATE_CHEF_MODE_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_CHEF_MODE + "("
                        + COLUMN_MODE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_CHEF_MODE_ID + " INTEGER NOT NULL,"
                        + COLUMN_MODE_NAME + " TEXT" +
                        ")"
                )
    }
}
