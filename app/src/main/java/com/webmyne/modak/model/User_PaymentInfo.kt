package com.webmyne.modak.model

open class User_PaymentInfo {
    var userId: Int = 0
    var cardId: Int = 0
    var cardTypeName: String = ""
    var cardTypeImage:ByteArray?=null
    var holderName: String = ""
    var cardexpiryMonth: String = ""
    var cardExpiryYear: String = ""
    var cardNumber: String = ""
    var isDefault: Boolean = false

    companion object {
        val TABLE_CARD_INFO = "user_card_info"
        val COLUMN_CARD_ID = "card_id"
        val COLUMN_USER_ID_CARD = "user_id_card"
        val COLUMN_PAYMENT_CARD_TYPE_NAME = "card_type_name"
        val COLUMN_PAYMENT_CARD_TYPE_IMAGE = "card_type_image"
        val COLUMN_PAYMENT_HOLDERNAME = "card_holdernmae"
        val COLUMN_PAYMENT_CARDNUMBER = "card_number"
        val COLUMN_PAYMENT_EXPIRYMONTH = "card_expirymonth"
        val COLUMN_PAYMENT_EXPIRYYEAR = "card_expiryyear"
        val COLUMN_PAYMEMENT_ISDEFAULT = "isdefault"
        val CREATE_PAYMENTINFO_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_CARD_INFO + "("
                        + COLUMN_CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_USER_ID_CARD + " INTEGER NOT NULL,"
                        + COLUMN_PAYMENT_CARD_TYPE_NAME + " TEXT,"
                        + COLUMN_PAYMENT_CARD_TYPE_IMAGE + " BLOB,"
                        + COLUMN_PAYMENT_HOLDERNAME + " TEXT,"
                        + COLUMN_PAYMENT_CARDNUMBER + " TEXT,"
                        + COLUMN_PAYMENT_EXPIRYMONTH + " TEXT,"
                        + COLUMN_PAYMEMENT_ISDEFAULT + " BOOLEAN,"
                        + COLUMN_PAYMENT_EXPIRYYEAR + " TEXT"
                        + ")"
                )
    }

}

