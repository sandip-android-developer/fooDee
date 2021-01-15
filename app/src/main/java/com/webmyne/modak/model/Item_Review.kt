package com.webmyne.modak.model


open class Item_Review {
    var ItemReviewId: Int = 0
    var ItemIdReview: Int = 0
    var ItemReviewUserId: Int = 0
    var ItemReviewUserImage: String = ""
    var ItemReviewUserName: String = ""
    var ItemReviewUserDesc: String = ""
    var ItemReviewUserRating: Float = 0f


    companion object {
        val TABLE_ITEM_REVIEW = "item_review"
        val COLUMN_ITEM_REVIEW_ID = "item_review_id"
        val COLUMN_ITEM_ID_REVIEW = "item_id_review"
        val COLUMN_ITEM_REVIEW_USER_ID = "item_review_user_id"
        val COLUMN_ITEM_REVIEW_USER_IMAGE = "item_review_user_image"
        val COLUMN_ITEM_REVIEW_USER_NAME = "item_review_user_name"
        val COLUMN_ITEM_REVIEW_USER_DESC = "item_review_user_desc"
        val COLUMN_ITEM_REVIEW_USER_RATING = "item_review_user_rating"


        val CREATE_ITEM_REVIEW_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_ITEM_REVIEW + "("
                        + COLUMN_ITEM_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_ITEM_REVIEW_USER_ID + " INTEGER NOT NULL,"
                        + COLUMN_ITEM_ID_REVIEW + " INTEGER NOT NULL,"
                        + COLUMN_ITEM_REVIEW_USER_IMAGE + " TEXT,"
                        + COLUMN_ITEM_REVIEW_USER_NAME + " TEXT,"
                        + COLUMN_ITEM_REVIEW_USER_DESC + " TEXT,"
                        + COLUMN_ITEM_REVIEW_USER_RATING + " INTEGER" +
                        ")"
                )
    }
}
