package com.webmyne.modak.model

import java.io.Serializable

open class User:Serializable {
    var userId: Int = 0
    var username: String = ""
    var useremail: String = ""
    var usermobile: String = ""
    var userpassword: String = ""
    var userImage: ByteArray? = null
    

    //Address Table

    var addressId: Int = 0
    var adressType: Int = 0
    var address1: String = ""
    var city: String = ""
    var state: String = ""
    var country: String = ""
    var zipcode: String = ""

    //Card Table

    var cardId: Int = 0
    var cardTypeName: String = ""
    var cardTypeImage: ByteArray? = null
    var holderName: String = ""
    var cardexpiryMonth: String = ""
    var cardExpiryYear: String = ""
    var cardNumber: String = ""

    companion object {
        val TABLE_FOOD_USER = "fooduser"
        val COLUMN_USER_ID = "user_id"
        val COLUMN_USER_NAME = "user_name"
        val COLUMN_USER_EMAIL = "user_email"
        val COLUMN_USER_Mobile = "user_mobile"
        val COLUMN_USER_PASSWORD = "user_password"
        val COLUMN_USER_IMAGE = "user_image"


        val CREATE_USER_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_FOOD_USER + "("
                        + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_USER_NAME + " TEXT,"
                        + COLUMN_USER_EMAIL + " TEXT,"
                        + COLUMN_USER_Mobile + " TEXT,"
                        + COLUMN_USER_PASSWORD + " TEXT,"
                        + COLUMN_USER_IMAGE + " BLOB" + ")")
    }
}
