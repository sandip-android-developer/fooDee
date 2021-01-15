package com.webmyne.modak.model.ResponsPojo


open class User_Favorite {
    var favoriteId: Int = 0
    var FavoriteUseruserId: Int = 0
    var FavoriteItemId: Int = 0
    var FavoriteItemImage: String = ""


    companion object {
        val TABLE_USER_FAVORITE = "table_favorite"
        val COLUMN_FAVORITE_ID = "favorite_id"
        val COLUMN_USER_ID_FAVORITE = "user_id_favorite"
        val COLUMN_ITEM_ID_FAVORITE = "item_id_favorite"
        val COLUMN_ITEM_IMAGE_FAVORITE = "item_image_favorite"

        val CREATE_USER_FAVORITE_TABLE = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_USER_FAVORITE + "("
                        + COLUMN_FAVORITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_USER_ID_FAVORITE + " INTEGER NOT NULL,"
                        + COLUMN_ITEM_ID_FAVORITE + " INTEGER NOT NULL,"
                        + COLUMN_ITEM_IMAGE_FAVORITE + " TEXT" +
                        ")"
                )
    }
}
