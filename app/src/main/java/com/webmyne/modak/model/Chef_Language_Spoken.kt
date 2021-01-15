package com.webmyne.modak.model

import java.io.Serializable


open class Chef_Language_Spoken : Serializable {
    var langauge_id: Int = 0
    var Chef_language_id: Int = 0
    var Langauge_Name: String = ""


    companion object {
        val TABLE_CHEF_LANGUAGE_SPOKEN = "chef_language_spoken"
        val COLUMN_LANGUAGE_ID = "language_id"
        val COLUMN_CHEF_LANGUAGE_ID = "chef_language_id"
        val COLUMN_LANGUAGE_NAME = "language_name"

        val CREATE_CHEF_LANGUAGE_SPOKEN_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_CHEF_LANGUAGE_SPOKEN + "("
                        + COLUMN_LANGUAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_CHEF_LANGUAGE_ID + " INTEGER NOT NULL,"
                        + COLUMN_LANGUAGE_NAME + " TEXT" +
                        ")"
                )
    }
}
