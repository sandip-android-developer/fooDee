package com.webmyne.modak.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.webmyne.modak.model.*
import com.webmyne.modak.model.Chef_Details.Companion.COLUMN_CHEF_AVERAGE_RATING
import com.webmyne.modak.model.Chef_Details.Companion.COLUMN_CHEF_CUISINE_TYPE
import com.webmyne.modak.model.Chef_Details.Companion.COLUMN_CHEF_DESCRIPTION
import com.webmyne.modak.model.Chef_Details.Companion.COLUMN_CHEF_ID
import com.webmyne.modak.model.Chef_Details.Companion.COLUMN_CHEF_IMAGE
import com.webmyne.modak.model.Chef_Details.Companion.COLUMN_CHEF_NAME
import com.webmyne.modak.model.Chef_Details.Companion.CREATE_CHEF_DETAILS_TABLE
import com.webmyne.modak.model.Chef_Details.Companion.TABLE_CHEF_DETAILS
import com.webmyne.modak.model.Chef_Food_Type.Companion.COLUMN_CHEF_FOOD_TYPE_ID
import com.webmyne.modak.model.Chef_Food_Type.Companion.COLUMN_FOOD_TYPE_ID
import com.webmyne.modak.model.Chef_Food_Type.Companion.COLUMN_FOOD_TYPE_NAME
import com.webmyne.modak.model.Chef_Food_Type.Companion.CREATE_CHEF_FOOD_TYPE_TABLE
import com.webmyne.modak.model.Chef_Food_Type.Companion.TABLE_CHEF_FOOD_TYPE
import com.webmyne.modak.model.Chef_Language_Spoken.Companion.COLUMN_CHEF_LANGUAGE_ID
import com.webmyne.modak.model.Chef_Language_Spoken.Companion.COLUMN_LANGUAGE_ID
import com.webmyne.modak.model.Chef_Language_Spoken.Companion.COLUMN_LANGUAGE_NAME
import com.webmyne.modak.model.Chef_Language_Spoken.Companion.CREATE_CHEF_LANGUAGE_SPOKEN_TABLE
import com.webmyne.modak.model.Chef_Language_Spoken.Companion.TABLE_CHEF_LANGUAGE_SPOKEN
import com.webmyne.modak.model.Chef_Review.Companion.COLUMN_CHEF_REVIEW_ID
import com.webmyne.modak.model.Chef_Review.Companion.COLUMN_CHEF_REVIEW_USER_DESC
import com.webmyne.modak.model.Chef_Review.Companion.COLUMN_CHEF_REVIEW_USER_IMAGE
import com.webmyne.modak.model.Chef_Review.Companion.COLUMN_CHEF_REVIEW_USER_NAME
import com.webmyne.modak.model.Chef_Review.Companion.COLUMN_CHEF_REVIEW_USER_RATING
import com.webmyne.modak.model.Chef_Review.Companion.CREATE_CHEF_REVIEW_TABLE
import com.webmyne.modak.model.Chef_Review.Companion.TABLE_CHEF_REVIEW
import com.webmyne.modak.model.Chef_mode.Companion.COLUMN_CHEF_MODE_ID
import com.webmyne.modak.model.Chef_mode.Companion.COLUMN_MODE_ID
import com.webmyne.modak.model.Chef_mode.Companion.COLUMN_MODE_NAME
import com.webmyne.modak.model.Chef_mode.Companion.CREATE_CHEF_MODE_TABLE
import com.webmyne.modak.model.Chef_mode.Companion.TABLE_CHEF_MODE
import com.webmyne.modak.model.Item_Details.Companion.COLUMN_ITEM_CHEF_ID
import com.webmyne.modak.model.Item_Details.Companion.COLUMN_ITEM_CITY
import com.webmyne.modak.model.Item_Details.Companion.COLUMN_ITEM_COUNT
import com.webmyne.modak.model.Item_Details.Companion.COLUMN_ITEM_DESCRIPTION
import com.webmyne.modak.model.Item_Details.Companion.COLUMN_ITEM_DISTANCE
import com.webmyne.modak.model.Item_Details.Companion.COLUMN_ITEM_ID
import com.webmyne.modak.model.Item_Details.Companion.COLUMN_ITEM_IMAGE
import com.webmyne.modak.model.Item_Details.Companion.COLUMN_ITEM_NAME
import com.webmyne.modak.model.Item_Details.Companion.COLUMN_ITEM_PRICE
import com.webmyne.modak.model.Item_Details.Companion.CREATE_ITEM_DETAILS_TABLE
import com.webmyne.modak.model.Item_Details.Companion.TABLE_ITEM_DETAILS
import com.webmyne.modak.model.Item_Review.Companion.COLUMN_ITEM_ID_REVIEW
import com.webmyne.modak.model.Item_Review.Companion.COLUMN_ITEM_REVIEW_ID
import com.webmyne.modak.model.Item_Review.Companion.COLUMN_ITEM_REVIEW_USER_DESC
import com.webmyne.modak.model.Item_Review.Companion.COLUMN_ITEM_REVIEW_USER_ID
import com.webmyne.modak.model.Item_Review.Companion.COLUMN_ITEM_REVIEW_USER_IMAGE
import com.webmyne.modak.model.Item_Review.Companion.COLUMN_ITEM_REVIEW_USER_NAME
import com.webmyne.modak.model.Item_Review.Companion.COLUMN_ITEM_REVIEW_USER_RATING
import com.webmyne.modak.model.Item_Review.Companion.CREATE_ITEM_REVIEW_TABLE
import com.webmyne.modak.model.Item_Review.Companion.TABLE_ITEM_REVIEW
import com.webmyne.modak.model.ResponsPojo.User_Favorite
import com.webmyne.modak.model.ResponsPojo.User_Favorite.Companion.COLUMN_FAVORITE_ID
import com.webmyne.modak.model.ResponsPojo.User_Favorite.Companion.COLUMN_ITEM_ID_FAVORITE
import com.webmyne.modak.model.ResponsPojo.User_Favorite.Companion.COLUMN_ITEM_IMAGE_FAVORITE
import com.webmyne.modak.model.ResponsPojo.User_Favorite.Companion.COLUMN_USER_ID_FAVORITE
import com.webmyne.modak.model.ResponsPojo.User_Favorite.Companion.CREATE_USER_FAVORITE_TABLE
import com.webmyne.modak.model.ResponsPojo.User_Favorite.Companion.TABLE_USER_FAVORITE
import com.webmyne.modak.model.User.Companion.COLUMN_USER_EMAIL
import com.webmyne.modak.model.User.Companion.COLUMN_USER_ID
import com.webmyne.modak.model.User.Companion.COLUMN_USER_IMAGE
import com.webmyne.modak.model.User.Companion.COLUMN_USER_Mobile
import com.webmyne.modak.model.User.Companion.COLUMN_USER_NAME
import com.webmyne.modak.model.User.Companion.COLUMN_USER_PASSWORD
import com.webmyne.modak.model.User.Companion.CREATE_USER_TABLE
import com.webmyne.modak.model.User.Companion.TABLE_FOOD_USER
import com.webmyne.modak.model.User_Address.Companion.COLUMN_ADDRESS_CITY
import com.webmyne.modak.model.User_Address.Companion.COLUMN_ADDRESS_COUNTRY
import com.webmyne.modak.model.User_Address.Companion.COLUMN_ADDRESS_DEFAULT
import com.webmyne.modak.model.User_Address.Companion.COLUMN_ADDRESS_ID
import com.webmyne.modak.model.User_Address.Companion.COLUMN_ADDRESS_SHORT_ADDRESS
import com.webmyne.modak.model.User_Address.Companion.COLUMN_ADDRESS_STATE
import com.webmyne.modak.model.User_Address.Companion.COLUMN_ADDRESS_TYPE
import com.webmyne.modak.model.User_Address.Companion.COLUMN_ADDRESS_ZIPCODE
import com.webmyne.modak.model.User_Address.Companion.COLUMN_USER_ID_ADDRESS
import com.webmyne.modak.model.User_Address.Companion.CREATE_ADDRESS_TABLE
import com.webmyne.modak.model.User_Address.Companion.TABLE_ADDRESS
import com.webmyne.modak.model.User_PaymentInfo.Companion.COLUMN_CARD_ID
import com.webmyne.modak.model.User_PaymentInfo.Companion.COLUMN_PAYMEMENT_ISDEFAULT
import com.webmyne.modak.model.User_PaymentInfo.Companion.COLUMN_PAYMENT_CARDNUMBER
import com.webmyne.modak.model.User_PaymentInfo.Companion.COLUMN_PAYMENT_CARD_TYPE_IMAGE
import com.webmyne.modak.model.User_PaymentInfo.Companion.COLUMN_PAYMENT_CARD_TYPE_NAME
import com.webmyne.modak.model.User_PaymentInfo.Companion.COLUMN_PAYMENT_EXPIRYMONTH
import com.webmyne.modak.model.User_PaymentInfo.Companion.COLUMN_PAYMENT_EXPIRYYEAR
import com.webmyne.modak.model.User_PaymentInfo.Companion.COLUMN_PAYMENT_HOLDERNAME
import com.webmyne.modak.model.User_PaymentInfo.Companion.COLUMN_USER_ID_CARD
import com.webmyne.modak.model.User_PaymentInfo.Companion.CREATE_PAYMENTINFO_TABLE
import com.webmyne.modak.model.User_PaymentInfo.Companion.TABLE_CARD_INFO
import java.sql.DriverManager.println
import java.util.*


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 2
        private val DATABASE_NAME = "UserManager.db"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USER_TABLE)
        db?.execSQL(CREATE_ADDRESS_TABLE)
        db?.execSQL(CREATE_PAYMENTINFO_TABLE)
        db?.execSQL(CREATE_ITEM_DETAILS_TABLE)
        db?.execSQL(CREATE_CHEF_DETAILS_TABLE)
        db?.execSQL(CREATE_CHEF_REVIEW_TABLE)
        db?.execSQL(CREATE_ITEM_REVIEW_TABLE)
        db?.execSQL(CREATE_CHEF_FOOD_TYPE_TABLE)
        db?.execSQL(CREATE_CHEF_MODE_TABLE)
        db?.execSQL(CREATE_CHEF_LANGUAGE_SPOKEN_TABLE)
        db?.execSQL(CREATE_USER_FAVORITE_TABLE)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + CREATE_USER_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_ADDRESS_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_PAYMENTINFO_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_ITEM_DETAILS_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_CHEF_DETAILS_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_CHEF_REVIEW_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_ITEM_REVIEW_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_CHEF_FOOD_TYPE_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_CHEF_MODE_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_CHEF_LANGUAGE_SPOKEN_TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_USER_FAVORITE_TABLE)
        onCreate(db)
    }

    fun insertUserFoodData(user: User): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.username)
        values.put(COLUMN_USER_EMAIL, user.useremail)
        values.put(COLUMN_USER_Mobile, user.usermobile)
        values.put(COLUMN_USER_PASSWORD, user.userpassword)
        values.put(COLUMN_USER_IMAGE, user.userImage)
        val id = db.insert(TABLE_FOOD_USER, null, values)
        db.close()
        return id
    }

    fun InsertUserAddress(userAddress: User_Address) {
        val db = this.writableDatabase
        val valuesAddress = ContentValues()
        valuesAddress.put(COLUMN_USER_ID_ADDRESS, userAddress.userId)
        valuesAddress.put(COLUMN_ADDRESS_TYPE, userAddress.adressType)
        valuesAddress.put(COLUMN_ADDRESS_SHORT_ADDRESS, userAddress.address1)
        valuesAddress.put(COLUMN_ADDRESS_CITY, userAddress.city)
        valuesAddress.put(COLUMN_ADDRESS_STATE, userAddress.state)
        valuesAddress.put(COLUMN_ADDRESS_COUNTRY, userAddress.country)
        valuesAddress.put(COLUMN_ADDRESS_ZIPCODE, userAddress.zipcode)
        db.insert(TABLE_ADDRESS, null, valuesAddress)
    }

    fun InsertUserCardInfo(cardinfo: User_PaymentInfo) {
        val db = this.writableDatabase
        val valuesAddress = ContentValues()

        valuesAddress.put(COLUMN_USER_ID_CARD, cardinfo.userId)
        valuesAddress.put(COLUMN_PAYMENT_CARD_TYPE_NAME, cardinfo.cardTypeName)
        valuesAddress.put(COLUMN_PAYMENT_CARD_TYPE_IMAGE, cardinfo.cardTypeImage)
        valuesAddress.put(COLUMN_PAYMENT_HOLDERNAME, cardinfo.holderName)
        valuesAddress.put(COLUMN_PAYMENT_CARDNUMBER, cardinfo.cardNumber)
        valuesAddress.put(COLUMN_PAYMENT_EXPIRYMONTH, cardinfo.cardexpiryMonth)
        valuesAddress.put(COLUMN_PAYMENT_EXPIRYYEAR, cardinfo.cardExpiryYear)
        db.insert(TABLE_CARD_INFO, null, valuesAddress)
    }

    fun InsertItemDetails(itemDetails: Item_Details) {
        println("Cooming##")
        val db = this.writableDatabase
        val valuesitemDetails = ContentValues()
        valuesitemDetails.put(COLUMN_ITEM_CHEF_ID, itemDetails.itemChefId)
        valuesitemDetails.put(COLUMN_ITEM_IMAGE, itemDetails.ItemImage)
        valuesitemDetails.put(COLUMN_ITEM_CITY, itemDetails.ItemCity)
        valuesitemDetails.put(COLUMN_ITEM_NAME, itemDetails.ItemName)
        valuesitemDetails.put(COLUMN_ITEM_PRICE, itemDetails.ItemPrice)
        valuesitemDetails.put(COLUMN_ITEM_DISTANCE, itemDetails.itemDistance)
        valuesitemDetails.put(COLUMN_ITEM_COUNT, itemDetails.itemCount)
        valuesitemDetails.put(COLUMN_ITEM_DESCRIPTION, itemDetails.ItemDescription)

        db.insert(TABLE_ITEM_DETAILS, null, valuesitemDetails)

       /* if (CheckChefIdExistItemDetailsTable(itemDetails.itemChefId)) {

        } else {
            db.insert(TABLE_ITEM_DETAILS, null, valuesitemDetails)
        }*/

    }

    fun InsertChefDetailsList(chefDetails: Chef_Details) {
        println("CoomingChef##")
        val db = this.writableDatabase
        val valuesChefDetails = ContentValues()
        valuesChefDetails.put(COLUMN_CHEF_IMAGE, chefDetails.ChefImage)
        valuesChefDetails.put(COLUMN_CHEF_AVERAGE_RATING, chefDetails.ChefAverageRating)
        valuesChefDetails.put(COLUMN_CHEF_NAME, chefDetails.ChefName)
        valuesChefDetails.put(COLUMN_CHEF_CUISINE_TYPE, chefDetails.ChefCuisineTye)
        valuesChefDetails.put(COLUMN_CHEF_DESCRIPTION, chefDetails.ChefDescription)

        db.insert(TABLE_CHEF_DETAILS, null, valuesChefDetails)
    }

    fun InsertChefLanguageSpoken(chefLanguageSpoken: Chef_Language_Spoken) {

        val db = this.writableDatabase
        val valuesCheflanguageSpoken = ContentValues()
        valuesCheflanguageSpoken.put(COLUMN_CHEF_LANGUAGE_ID, chefLanguageSpoken.Chef_language_id)
        valuesCheflanguageSpoken.put(COLUMN_LANGUAGE_NAME, chefLanguageSpoken.Langauge_Name)
        if (CheckChefIdExistInLanguageSpokenTable(chefLanguageSpoken.Chef_language_id) > 3) {

        } else {
            db.insert(TABLE_CHEF_LANGUAGE_SPOKEN, null, valuesCheflanguageSpoken)
        }

    }

    fun InsertChefMode(chefMode: Chef_mode) {

        val db = this.writableDatabase
        val valuesChefMode = ContentValues()
        valuesChefMode.put(COLUMN_CHEF_MODE_ID, chefMode.Chef_mode_id)
        valuesChefMode.put(COLUMN_MODE_NAME, chefMode.mode_Name)
        if (CheckChefIdExistInModeTable(chefMode.Chef_mode_id) > 3) {

        } else {
            db.insert(TABLE_CHEF_MODE, null, valuesChefMode)
        }

    }

    fun InsertChefFoodType(chefFoodType: Chef_Food_Type) {
        println("Chef_food_type_id---" + chefFoodType.Chef_food_type_id)
        val db = this.writableDatabase
        val valuesChefFoodType = ContentValues()
        valuesChefFoodType.put(COLUMN_CHEF_FOOD_TYPE_ID, chefFoodType.Chef_food_type_id)
        valuesChefFoodType.put(COLUMN_FOOD_TYPE_NAME, chefFoodType.food_type_Name)
        if (CheckChefIdExistInFoodTypeTable(chefFoodType.Chef_food_type_id) > 3) {

        } else {
            db.insert(TABLE_CHEF_FOOD_TYPE, null, valuesChefFoodType)
        }

    }

    fun InsertItemReviewDetails(itemReview: Item_Review) {
        println("CoomingChef##")
        val db = this.writableDatabase
        val valuesItemReview = ContentValues()
        valuesItemReview.put(COLUMN_ITEM_ID_REVIEW, itemReview.ItemIdReview)
        valuesItemReview.put(COLUMN_ITEM_REVIEW_USER_ID, itemReview.ItemReviewUserId)
        valuesItemReview.put(COLUMN_ITEM_REVIEW_USER_IMAGE, itemReview.ItemReviewUserImage)
        valuesItemReview.put(COLUMN_ITEM_REVIEW_USER_NAME, itemReview.ItemReviewUserName)
        valuesItemReview.put(COLUMN_ITEM_REVIEW_USER_DESC, itemReview.ItemReviewUserDesc)
        valuesItemReview.put(COLUMN_ITEM_REVIEW_USER_RATING, itemReview.ItemReviewUserRating)

        db.insert(TABLE_ITEM_REVIEW, null, valuesItemReview)
    }

    fun InsertChefReviewDetails(chefReview: Chef_Review) {
        println("CoomingChef##")
        val db = this.writableDatabase
        val valuesChefReview = ContentValues()
        valuesChefReview.put(COLUMN_CHEF_REVIEW_USER_IMAGE, chefReview.ChefReviewUserImage)
        valuesChefReview.put(COLUMN_CHEF_REVIEW_USER_NAME, chefReview.ChefReviewUserName)
        valuesChefReview.put(COLUMN_CHEF_REVIEW_USER_DESC, chefReview.ChefReviewUserDesc)
        valuesChefReview.put(COLUMN_CHEF_REVIEW_USER_RATING, chefReview.ChefReviewUserRating)

        db.insert(TABLE_CHEF_REVIEW, null, valuesChefReview)
    }
    fun InsertUserFavorite(userFavorite: User_Favorite) {
        val db = this.writableDatabase
        val valuesUserFavorite = ContentValues()
        valuesUserFavorite.put(COLUMN_USER_ID_FAVORITE, userFavorite.FavoriteUseruserId)
        valuesUserFavorite.put(COLUMN_ITEM_ID_FAVORITE, userFavorite.FavoriteItemId)
        valuesUserFavorite.put(COLUMN_ITEM_IMAGE_FAVORITE, userFavorite.FavoriteItemImage)

        db.insert(TABLE_USER_FAVORITE, null, valuesUserFavorite)
    }

    fun getAllDetailsUser(): MutableList<User> {
        val userList = ArrayList<User>()
        val selectQuery = "SELECT  * FROM $TABLE_FOOD_USER"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var userDetails = User()
                userDetails.userId = cursor!!.getInt(cursor.getColumnIndex(COLUMN_USER_ID))
                userDetails.username = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME))
                userDetails.useremail =
                    cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL))
                userDetails.usermobile =
                    cursor.getString(cursor.getColumnIndex(COLUMN_USER_Mobile))
                userDetails.userpassword =
                    cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD))
                userDetails.userImage =
                    cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_IMAGE))
                userList.add(userDetails)
            } while (cursor!!.moveToNext())
        }
        return userList
    }

    fun getAllDetailsUserByEmailId(userEmailId: String, userID: Long): MutableList<User> {
        val userList = ArrayList<User>()
        var selectQuery=""
        if (CheckUserIdExistInCardInfoTable(userID) && CheckUserIdExistInAddressTable(userID))
        {

            selectQuery  =
                "SELECT  * FROM $TABLE_FOOD_USER" + " INNER JOIN  $TABLE_ADDRESS INNER JOIN $TABLE_CARD_INFO ON " +
                        COLUMN_USER_ID + " = " + COLUMN_USER_ID_ADDRESS + " AND " +
                        COLUMN_USER_ID + " = " + COLUMN_USER_ID_CARD + " WHERE " + COLUMN_USER_EMAIL + " = '" + userEmailId +"'"
        }
        else if (CheckUserIdExistInAddressTable(userID))
        {


            selectQuery  =
                "SELECT  * FROM $TABLE_FOOD_USER" + " INNER JOIN  $TABLE_ADDRESS  ON " +
                        COLUMN_USER_ID + " = " + COLUMN_USER_ID_ADDRESS + " WHERE " + COLUMN_USER_EMAIL + " = '" + userEmailId +"'"

        }
        else if (CheckUserIdExistInCardInfoTable(userID))
        {
            selectQuery  =
                "SELECT  * FROM $TABLE_FOOD_USER" + " INNER JOIN   $TABLE_CARD_INFO ON " +
                        COLUMN_USER_ID + " = " + COLUMN_USER_ID_CARD + " WHERE " + COLUMN_USER_EMAIL + " = '" + userEmailId +"'"
        }
        else
        {
            selectQuery  =
                "SELECT  * FROM $TABLE_FOOD_USER" + " WHERE " + COLUMN_USER_EMAIL + " = '" + userEmailId + "'"
        }

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var userDetails = User()
                userDetails.userId = cursor!!.getInt(cursor.getColumnIndex(COLUMN_USER_ID))
                userDetails.username = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME))
                userDetails.useremail =
                    cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL))
                userDetails.usermobile =
                    cursor.getString(cursor.getColumnIndex(COLUMN_USER_Mobile))
                userDetails.userpassword =
                    cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD))
                userDetails.userImage =
                    cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_IMAGE))

                if (CheckUserIdExistInAddressTable(userID))
                {
                    userDetails.addressId=cursor.getInt(cursor.getColumnIndex(COLUMN_ADDRESS_ID))
                    userDetails.address1=cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_SHORT_ADDRESS))
                    userDetails.adressType=cursor.getInt(cursor.getColumnIndex(COLUMN_ADDRESS_TYPE))
                    userDetails.city=cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_CITY))
                    userDetails.state=cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_STATE))
                    userDetails.country=cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_COUNTRY))
                    userDetails.zipcode=cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_ZIPCODE))
                }
                if (CheckUserIdExistInCardInfoTable(userID))
                {
                    userDetails.cardId=cursor.getInt(cursor.getColumnIndex(COLUMN_CARD_ID))
                    userDetails.cardTypeName =
                        cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_CARD_TYPE_NAME))
                    userDetails.cardTypeImage =
                        cursor.getBlob(cursor.getColumnIndex(COLUMN_PAYMENT_CARD_TYPE_IMAGE))
                    userDetails.holderName=cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_HOLDERNAME))
                    userDetails.cardNumber=cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_CARDNUMBER))
                    userDetails.cardexpiryMonth=cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_EXPIRYMONTH))
                    userDetails.cardExpiryYear=cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_EXPIRYYEAR))
                }

                userList.add(userDetails)
            } while (cursor!!.moveToNext())
        }
        return userList
    }


    fun getUserAddressByUserId(userId: Int): MutableList<User_Address> {
        val addressList = ArrayList<User_Address>()
        val selectQuery = "SELECT  * FROM $TABLE_ADDRESS"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var address = User_Address()
                address.userId = cursor!!.getInt(cursor.getColumnIndex(COLUMN_USER_ID_ADDRESS))
                address.addressId = cursor.getInt(cursor.getColumnIndex(COLUMN_ADDRESS_ID))
                address.adressType =
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ADDRESS_TYPE))
                address.address1 =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_SHORT_ADDRESS))
                address.city = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_CITY))
                address.state = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_STATE))
                address.country =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_COUNTRY))
                address.zipcode =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_ZIPCODE))
                addressList.add(address)
            } while (cursor!!.moveToNext())


        }
        return addressList
    }

    fun getUserCardByUserId(userId: Int): MutableList<User_PaymentInfo> {
        val cardList = ArrayList<User_PaymentInfo>()
        val selectQuery = "SELECT  * FROM $TABLE_CARD_INFO" + " WHERE " + COLUMN_USER_ID_CARD + " = '" + userId + "'"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor!!.moveToFirst()) {
            do {

                var payment_info = User_PaymentInfo()
                payment_info.cardId = cursor.getInt(cursor.getColumnIndex(COLUMN_CARD_ID))
                payment_info.cardTypeName =
                    cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_CARD_TYPE_NAME))
                payment_info.cardTypeImage =
                    cursor.getBlob(cursor.getColumnIndex(COLUMN_PAYMENT_CARD_TYPE_IMAGE))
                payment_info.holderName =
                    cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_HOLDERNAME))
                payment_info.cardexpiryMonth =
                    cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_EXPIRYMONTH))
                payment_info.cardExpiryYear =
                    cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_EXPIRYYEAR))
                payment_info.cardNumber =
                    cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENT_CARDNUMBER))
                payment_info.isDefault =
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PAYMEMENT_ISDEFAULT)) > 0
                cardList.add(payment_info)
            } while (cursor.moveToNext())

        }

        return cardList
    }

    fun getAllItemDetails(): MutableList<Item_Details> {
        val itemDetailsList = ArrayList<Item_Details>()
        val selectQuery = "SELECT  * FROM $TABLE_ITEM_DETAILS"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var itemDetails = Item_Details()
                itemDetails.itemId = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_ID))
                itemDetails.ItemImage = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_IMAGE))
                itemDetails.ItemName = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME))
                itemDetails.ItemPrice = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PRICE))

                itemDetails.itemDistance =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_DISTANCE))
                itemDetails.itemCount = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_COUNT))
                itemDetails.ItemDescription =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_DESCRIPTION))

                itemDetailsList.add(itemDetails)
            } while (cursor!!.moveToNext())


        }
        return itemDetailsList
    }

    fun getAllItemReviewDetails(): MutableList<Item_Review> {
        val itemReviewList = ArrayList<Item_Review>()
        val selectQuery = "SELECT  * FROM $TABLE_ITEM_REVIEW"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var itemReviewDetails = Item_Review()
                itemReviewDetails.ItemReviewId =
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_REVIEW_ID))
                itemReviewDetails.ItemIdReview =
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_ID_REVIEW))
                itemReviewDetails.ItemReviewUserId =
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_REVIEW_USER_ID))
                itemReviewDetails.ItemReviewUserImage =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_REVIEW_USER_IMAGE))
                itemReviewDetails.ItemReviewUserName =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_REVIEW_USER_NAME))
                itemReviewDetails.ItemReviewUserDesc =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_REVIEW_USER_DESC))
                itemReviewDetails.ItemReviewUserRating =
                    cursor.getFloat(cursor.getColumnIndex(COLUMN_ITEM_REVIEW_USER_RATING))

                itemReviewList.add(itemReviewDetails)
            } while (cursor!!.moveToNext())


        }
        return itemReviewList
    }

    fun getAllChefReviewDetails(): MutableList<Chef_Review> {
        val chefReviewList = ArrayList<Chef_Review>()
        val selectQuery = "SELECT  * FROM $TABLE_CHEF_REVIEW"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var chefReviewDetails = Chef_Review()
                chefReviewDetails.ChefReviewId =
                    cursor.getInt(cursor.getColumnIndex(COLUMN_CHEF_REVIEW_ID))
                chefReviewDetails.ChefReviewUserImage =
                    cursor.getBlob(cursor.getColumnIndex(COLUMN_CHEF_REVIEW_USER_IMAGE))
                chefReviewDetails.ChefReviewUserName =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_REVIEW_USER_NAME))
                chefReviewDetails.ChefReviewUserDesc =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_REVIEW_USER_DESC))
                chefReviewDetails.ChefReviewUserRating =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_REVIEW_USER_RATING))

                chefReviewList.add(chefReviewDetails)
            } while (cursor!!.moveToNext())


        }
        return chefReviewList
    }

    fun getChefLanguageSpoken(chef_id: Int): MutableList<Chef_Language_Spoken> {
        val chefLanguageSpokenList = ArrayList<Chef_Language_Spoken>()
        val selectQuery =
            "SELECT * FROM " + TABLE_CHEF_LANGUAGE_SPOKEN + " WHERE " + COLUMN_CHEF_LANGUAGE_ID + " = '" + chef_id + "'"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var chefLanguageSpoken: Chef_Language_Spoken = Chef_Language_Spoken()
                chefLanguageSpoken.langauge_id =
                    cursor.getInt(cursor.getColumnIndex(COLUMN_LANGUAGE_ID))
                chefLanguageSpoken.Langauge_Name =
                    cursor.getString(cursor.getColumnIndex(COLUMN_LANGUAGE_NAME))

                chefLanguageSpokenList.add(chefLanguageSpoken)
            } while (cursor!!.moveToNext())
        }
        return chefLanguageSpokenList
    }

    fun getChefFoodType(chef_id: Int): MutableList<Chef_Food_Type> {
        val chefFoodTypeList = ArrayList<Chef_Food_Type>()
        val selectQuery =
            "SELECT * FROM " + TABLE_CHEF_FOOD_TYPE + " WHERE " + COLUMN_CHEF_FOOD_TYPE_ID + " = '" + chef_id + "'"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var chefFoodType: Chef_Food_Type = Chef_Food_Type()
                chefFoodType.food_type_id =
                    cursor.getInt(cursor.getColumnIndex(COLUMN_FOOD_TYPE_ID))
                chefFoodType.food_type_Name =
                    cursor.getString(cursor.getColumnIndex(COLUMN_FOOD_TYPE_NAME))
                chefFoodTypeList.add(chefFoodType)
            } while (cursor!!.moveToNext())
        }
        return chefFoodTypeList
    }

    fun getChefMode(chef_id: Int): MutableList<Chef_mode> {
        val chefModeList = ArrayList<Chef_mode>()
        val selectQuery =
            "SELECT * FROM " + TABLE_CHEF_MODE + " WHERE " + COLUMN_CHEF_MODE_ID + " = '" + chef_id + "'"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var chefMode: Chef_mode = Chef_mode()
                chefMode.mode_id = cursor.getInt(cursor.getColumnIndex(COLUMN_MODE_ID))
                chefMode.mode_Name = cursor.getString(cursor.getColumnIndex(COLUMN_MODE_NAME))

                chefModeList.add(chefMode)
            } while (cursor!!.moveToNext())
        }
        return chefModeList
    }

    fun getItemCountByItemID(itemId: Int): Int {
        var itemCount: Int = 0
        val selectQuery =
            "SELECT * FROM " + TABLE_ITEM_DETAILS + " WHERE " + COLUMN_ITEM_ID + " = '" + itemId + "'"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return 0
        }

        if (cursor.moveToFirst()) {
            do {
                itemCount = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_COUNT))
            } while (cursor!!.moveToNext())


        }
        return itemCount
    }

    fun getTotalItemCount(): Int {
        var itemCount: Int = 0
        val selectQuery = "SELECT * FROM " + TABLE_ITEM_DETAILS

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return 0
        }

        if (cursor.moveToFirst()) {
            do {
                itemCount = itemCount + cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_COUNT))
            } while (cursor!!.moveToNext())


        }
        return itemCount
    }

    fun getAllItemDetailsWIthChef(index:Int): MutableList<Item_Details> {
        val itemDetailsList = ArrayList<Item_Details>()
        val selectQuery =
            "SELECT  * FROM $TABLE_ITEM_DETAILS " + " INNER JOIN $TABLE_CHEF_DETAILS ON " + COLUMN_ITEM_CHEF_ID + " = " + COLUMN_CHEF_ID +" LIMIT 10 offset " +
                    index

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var itemDetails = Item_Details()
                itemDetails.itemId = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_ID))
                itemDetails.itemChefId = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_CHEF_ID))
                itemDetails.ItemImage = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_IMAGE))
                itemDetails.ItemName = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME))
                itemDetails.ItemPrice = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PRICE))

                itemDetails.itemDistance =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_DISTANCE))
                itemDetails.itemCount = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_COUNT))
                itemDetails.ItemDescription =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_DESCRIPTION))

                itemDetails.ChefName = cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_NAME))
                itemDetails.chef_id = cursor.getInt(cursor.getColumnIndex(COLUMN_CHEF_ID))
                itemDetails.ChefAverageRating =
                    cursor.getFloat(cursor.getColumnIndex(COLUMN_CHEF_AVERAGE_RATING))
                itemDetails.ChefImage = cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_IMAGE))
                itemDetails.ChefCuisineTye =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_CUISINE_TYPE))
                itemDetails.ChefDescription =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_DESCRIPTION))

                itemDetailsList.add(itemDetails)
            } while (cursor!!.moveToNext())


        }
        return itemDetailsList
    }

    fun getAllItemDetailGreaterThanZero(): MutableList<Item_Details> {
        val itemDetailsList = ArrayList<Item_Details>()
        val selectQuery =
            "SELECT  * FROM $TABLE_ITEM_DETAILS" + " INNER JOIN $TABLE_CHEF_DETAILS ON " + COLUMN_ITEM_CHEF_ID + " = " + COLUMN_CHEF_ID

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var itemDetails = Item_Details()
                itemDetails.itemId = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_ID))
                itemDetails.itemChefId = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_CHEF_ID))
                itemDetails.ItemImage = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_IMAGE))
                itemDetails.ItemName = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME))
                itemDetails.ItemPrice = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PRICE))

                itemDetails.itemDistance =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_DISTANCE))
                itemDetails.itemCount = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_COUNT))
                itemDetails.ItemDescription =
                    cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_DESCRIPTION))

                itemDetails.ChefName = cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_NAME))
                itemDetails.chef_id = cursor.getInt(cursor.getColumnIndex(COLUMN_CHEF_ID))
                itemDetails.ChefAverageRating =
                    cursor.getFloat(cursor.getColumnIndex(COLUMN_CHEF_AVERAGE_RATING))
                itemDetails.ChefImage = cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_IMAGE))
                itemDetails.ChefCuisineTye =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_CUISINE_TYPE))
                itemDetails.ChefDescription =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_DESCRIPTION))
                if (itemDetails.itemCount>0)
                {
                    itemDetailsList.add(itemDetails)
                }


            } while (cursor!!.moveToNext())


        }
        return itemDetailsList
    }

    fun getAllChefList(): MutableList<Chef_Details> {
        val itemDetailsList = ArrayList<Chef_Details>()
        val selectQuery = "SELECT  * FROM $TABLE_CHEF_DETAILS"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var cheflist = Chef_Details()
                cheflist.chef_id = cursor.getInt(cursor.getColumnIndex(COLUMN_CHEF_ID))
                cheflist.ChefName = cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_NAME))
                cheflist.ChefAverageRating =
                    cursor.getFloat(cursor.getColumnIndex(COLUMN_CHEF_AVERAGE_RATING))
                cheflist.ChefImage = cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_IMAGE))
                cheflist.ChefCuisineTye =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_CUISINE_TYPE))
                cheflist.ChefDescription =
                    cursor.getString(cursor.getColumnIndex(COLUMN_CHEF_DESCRIPTION))

                itemDetailsList.add(cheflist)
            } while (cursor!!.moveToNext())

        }
        return itemDetailsList
    }

    fun getAllItemUserFavorite(userId:Int): MutableList<User_Favorite> {
        val itemfavoriteList = ArrayList<User_Favorite>()
        val selectQuery = "SELECT  * FROM $TABLE_USER_FAVORITE" + " WHERE " + COLUMN_USER_ID_FAVORITE + " = '" + userId + "'"

        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                var userFavorite:User_Favorite= User_Favorite()

                userFavorite.favoriteId = cursor.getInt(cursor.getColumnIndex(COLUMN_FAVORITE_ID))
                userFavorite.FavoriteUseruserId = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID_FAVORITE))
                userFavorite.FavoriteItemId = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_ID_FAVORITE))
                userFavorite.FavoriteItemImage = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_IMAGE_FAVORITE))
                itemfavoriteList.add(userFavorite)
            } while (cursor!!.moveToNext())

        }
        return itemfavoriteList
    }

    fun CheckChefIdExistItemDetailsTable(chef_id: Int): Boolean {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_ITEM_DETAILS + " WHERE " + COLUMN_ITEM_CHEF_ID + " = '" + chef_id + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return exist
    }

    fun CheckFaviriteIdExist(itemId: Int): Boolean {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_USER_FAVORITE + " WHERE " + COLUMN_ITEM_ID_FAVORITE + " = '" + itemId + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return exist
    }

    fun CheckChefIdExistInLanguageSpokenTable(chef_id: Int): Int {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_CHEF_LANGUAGE_SPOKEN + " WHERE " + COLUMN_CHEF_LANGUAGE_ID + " = '" + chef_id + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return clientCur.count
    }

    fun CheckChefIdExistInModeTable(chef_id: Int): Int {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_CHEF_MODE + " WHERE " + COLUMN_CHEF_MODE_ID + " = '" + chef_id + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return clientCur.count
    }

    fun CheckChefIdExistInFoodTypeTable(chef_id: Int): Int {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_CHEF_MODE + " WHERE " + COLUMN_CHEF_MODE_ID + " = '" + chef_id + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return clientCur.count
    }
    fun CheckUserIdExistInAddressTable(userId: Long): Boolean {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_ADDRESS + " WHERE " + COLUMN_USER_ID_ADDRESS + " = '" + userId + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return exist
    }
    fun CheckUserIdExistInCardInfoTable(userId: Long): Boolean {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_CARD_INFO + " WHERE " + COLUMN_USER_ID_CARD + " = '" + userId + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return exist
    }

    fun updateAddressTableByAddressId(userAddress: User_Address) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ADDRESS_ID, userAddress.addressId)
        contentValues.put(COLUMN_ADDRESS_TYPE, userAddress.adressType)
        contentValues.put(COLUMN_ADDRESS_SHORT_ADDRESS, userAddress.address1)
        contentValues.put(COLUMN_ADDRESS_CITY, userAddress.city) // EmpModelClass Name
        contentValues.put(COLUMN_ADDRESS_STATE, userAddress.state)
        contentValues.put(COLUMN_ADDRESS_COUNTRY, userAddress.country)
        contentValues.put(COLUMN_ADDRESS_DEFAULT, userAddress.defaultAddress)
        contentValues.put(COLUMN_ADDRESS_ZIPCODE, userAddress.zipcode)
        db.update(
            TABLE_ADDRESS,
            contentValues,
            COLUMN_ADDRESS_ID + " = " + userAddress.addressId,
            null
        )
        db.close() // Closing database connection
    }

    fun updateUserDetailsTable(user: User) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_USER_ID, user.userId)
        contentValues.put(COLUMN_USER_NAME, user.username) // EmpModelClass Name
        contentValues.put(COLUMN_USER_IMAGE, user.userImage)

        db.update(TABLE_FOOD_USER, contentValues, User.COLUMN_USER_ID + " = " + user.userId, null)

        db.close() // Closing database connection
    }

    fun updateItemCountByItemId(itemDetails: Item_Details) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ITEM_COUNT, itemDetails.itemCount)
        db.update(
            TABLE_ITEM_DETAILS,
            contentValues,
            COLUMN_ITEM_ID + " = " + itemDetails.itemId,
            null
        )
        println("select**" + contentValues)
        db.close() // Closing database connection
    }

    fun updateDefaultPaymentOption(cardId: Int) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_PAYMEMENT_ISDEFAULT, true)
        db.update(
            TABLE_CARD_INFO,
            contentValues,
            COLUMN_CARD_ID + " = " + cardId,
            null
        )
        println("select**" + contentValues)
        db.close() // Closing database connection
    }

    fun deleteUser(user: User): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_ID, user.userId)
        val success = db.delete(TABLE_FOOD_USER, "id=" + user.userId, null)

        db.close() // Closing database connection
        return success
    }
    fun deleteUserFavoriteItem(userItemIdFavorite: Int): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_USER_FAVORITE, COLUMN_ITEM_ID_FAVORITE + " = " + userItemIdFavorite, null)

        db.close() // Closing database connection
        return success
    }


    fun isEmailIdAlreadyExist(email: String): Boolean {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_FOOD_USER + " WHERE " + COLUMN_USER_EMAIL + " = '" + email + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return exist
    }

    fun isPhoneAlreadyExist(email: String): Boolean {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_FOOD_USER + " WHERE " + COLUMN_USER_Mobile + " = '" + email + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return exist
    }

    fun isUserAlreadyExist(email: String, password: String): Boolean {
        val db = this.writableDatabase
        val clientCur = db.rawQuery(
            "SELECT * FROM " + TABLE_FOOD_USER + " WHERE " + COLUMN_USER_EMAIL + " = '" + email +
                    "' AND " + COLUMN_USER_PASSWORD + " = '" + password + "'",
            null
        )
        val exist = clientCur.count > 0
        clientCur.close()
        return exist

    }

    fun getUserData(email: String): List<String> {
        val db = this.readableDatabase
        var c: Cursor
        var list = ArrayList<String>()
        c = db.rawQuery("SELECT * from $TABLE_FOOD_USER where $COLUMN_USER_EMAIL = '$email'", null)
        var pass: String = ""
        var name: String = ""
        var phn: String = ""
        if (c.moveToFirst()) {
            do {
                list.add(c.getString(c.getColumnIndex(COLUMN_USER_PASSWORD)))
                list.add(c.getString(c.getColumnIndex(COLUMN_USER_NAME)))
                list.add(c.getString(c.getColumnIndex(COLUMN_USER_Mobile)))


            } while (c.moveToNext())
        }
        return list
    }

    fun updateCurrentPassword(password: String, email: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_USER_PASSWORD, password) // EmpModelClass Name
        db.update(
            TABLE_FOOD_USER,
            contentValues,
            User.COLUMN_USER_EMAIL + " = '" + email + "'",
            null
        )
        db.close()

    }

}