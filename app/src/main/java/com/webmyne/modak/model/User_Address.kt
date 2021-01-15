package com.webmyne.modak.model


open class User_Address {
    var userId: Int = 0
    var addressId: Int = 0
    var defaultAddress:Boolean=false
    var adressType: Int = 0
    var address1: String = ""
    var city: String = ""
    var state: String = ""
    var country: String = ""
    var zipcode: String = ""

    companion object {
        val TABLE_ADDRESS = "table_address"
        val COLUMN_ADDRESS_TYPE = "adress_type"
        val COLUMN_ADDRESS_ID = "adress_id"
        val COLUMN_USER_ID_ADDRESS = "user_id_address"
        val COLUMN_ADDRESS_SHORT_ADDRESS = "address_short"
        val COLUMN_ADDRESS_CITY = "city"
        val COLUMN_ADDRESS_STATE = "state"
        val COLUMN_ADDRESS_COUNTRY = "country"
        val COLUMN_ADDRESS_ZIPCODE = "zipcode"
        val COLUMN_ADDRESS_DEFAULT = "address_default"

        val CREATE_ADDRESS_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_ADDRESS + "("
                        + COLUMN_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_USER_ID_ADDRESS + " INTEGER NOT NULL,"
                        + COLUMN_ADDRESS_TYPE + " INTEGER,"
                        + COLUMN_ADDRESS_SHORT_ADDRESS + " TEXT,"
                        + COLUMN_ADDRESS_CITY + " TEXT,"
                        + COLUMN_ADDRESS_STATE + " TEXT,"
                        + COLUMN_ADDRESS_COUNTRY + " TEXT,"
                        + COLUMN_ADDRESS_DEFAULT + " BOOLEAN,"
                        + COLUMN_ADDRESS_ZIPCODE + " TEXT" +
                        ")"
                )
    }
}
