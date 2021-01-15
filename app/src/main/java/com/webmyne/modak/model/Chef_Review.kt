package com.webmyne.modak.model


open class Chef_Review {
    var ChefReviewId: Int = 0
    var ChefReviewUserImage:ByteArray?=null
    var ChefReviewUserName:String=""
    var ChefReviewUserDesc:String=""
    var ChefReviewUserRating:String=""


    companion object {
        val TABLE_CHEF_REVIEW = "chef_review"
        val COLUMN_CHEF_REVIEW_ID = "chef_review_id"
        val COLUMN_CHEF_REVIEW_USER_IMAGE = "chef_review_user_image"
        val COLUMN_CHEF_REVIEW_USER_NAME = "chef_review_user_name"
        val COLUMN_CHEF_REVIEW_USER_DESC = "chef_review_user_desc"
        val COLUMN_CHEF_REVIEW_USER_RATING = "chef_review_user_rating"


        val CREATE_CHEF_REVIEW_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_CHEF_REVIEW + "("
                        + COLUMN_CHEF_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_CHEF_REVIEW_USER_IMAGE + " BLOB,"
                        + COLUMN_CHEF_REVIEW_USER_NAME + " TEXT,"
                        + COLUMN_CHEF_REVIEW_USER_DESC + " TEXT,"
                        + COLUMN_CHEF_REVIEW_USER_RATING + " TEXT" +
                        ")"
                )
    }
}
