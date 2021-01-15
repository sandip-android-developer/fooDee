package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.webmyne.modak.R
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.Chef_Details
import com.webmyne.modak.model.HomeDetails
import com.webmyne.modak.model.Item_Details
import com.webmyne.modak.model.Item_Review
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    var bundle = Bundle()
    private var progressStatus = 0
    private val handler = Handler()
    val db: DatabaseHelper = DatabaseHelper(this)

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, SplashActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Functions.hideStatusBar(this)
        setContentView(R.layout.activity_splash)
        setShowBackMessage(false)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
      InsertChefDetialsList()
       // InsertChefDetialsListLoop()
         InsertItemDetails()
        navigateToLogin()


    }

    private fun InsertChefDetialsListLoop() {

        for (i in 0 until 30) {

            var chefDetailslist = Chef_Details()
            chefDetailslist.ChefImage =
                "https://image.shutterstock.com/image-photo/head-shot-young-business-man-260nw-95520982.jpg"
            chefDetailslist.ChefName = "Chef Peter"
            chefDetailslist.ChefAverageRating = 3.5F
            chefDetailslist.ChefCuisineTye = "Punjab"
            chefDetailslist.ChefDescription =
                "Chef PeterChef Peter Chef Peter Chef Peter Chef Peter Chef Peter Chef Peter"
            db.InsertChefDetailsList(chefDetailslist)

        }

        var chefDetailsList = ArrayList<Chef_Details>()
        chefDetailsList.addAll(db.getAllChefList())
        for (i in 0 until 30) {
            var itemDetails = Item_Details()
            itemDetails.itemChefId = chefDetailsList.get(i).chef_id
            itemDetails.ItemImage =
                "https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/noodlesss.jpg"
            itemDetails.itemDistance = "10"
            itemDetails.ItemPrice = "1200"
            itemDetails.ItemName = "Pohe "+i
            itemDetails.ItemCity = "0"
            itemDetails.ItemDescription =
                "A dish’s name should clearly identify the dish so that guests don’t have to read the description in order to obtain this basic information. When customers can easily determine if they want to read further by just reading the name of the item, it saves them time. To achieve this level of clarity, you often must mention the specific item in the dish name. For instance, instead of writing “Joe’s Special” and then describing this mystery dish, you would write “Joe’s Lasagna Special,” which allows customers to quickly decide if they want more detail."
            db.InsertItemDetails(itemDetails)
        }
    }

    private fun InsertChefDetialsList() {
        var checfImage = ArrayList<String>()
        var chefName = ArrayList<String>()
        var chefAverageRating = ArrayList<String>()
        var CuisinName = ArrayList<String>()

        checfImage.add("https://image.shutterstock.com/image-photo/head-shot-young-business-man-260nw-95520982.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-late-thirties-white-260nw-123599890.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-portrait-asian-smiling-260nw-1041841363.jpg")
        checfImage.add("https://phowdimages.azureedge.net/cloud/pics/8137/p/c7bfc992b6614bf9a36057506e1bfc4c/1.jpg?preset=detailswithbefore")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-young-attractive-sexy-260nw-200475077.jpg")
        checfImage.add("https://www.wellbeingofwomen.org.uk/wp-content/uploads/2019/01/nina-portrait-passport-size.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-picture-businesswoman-brown-hair-260nw-250775908.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/photo-booth-portrait-260nw-189686696.jpg")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS28H_O1Fz3KCbe8izHd67oYEZ_40Q76IpCXeVO6R1av5PXIlNb&s")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUlqHdu2vcS5TSBrUEf4jDmumM4SUrYq1Lsv_DJV05atIwfNpg&s")


       /* checfImage.add("https://image.shutterstock.com/image-photo/head-shot-young-business-man-260nw-95520982.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-late-thirties-white-260nw-123599890.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-portrait-asian-smiling-260nw-1041841363.jpg")
        checfImage.add("https://phowdimages.azureedge.net/cloud/pics/8137/p/c7bfc992b6614bf9a36057506e1bfc4c/1.jpg?preset=detailswithbefore")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-young-attractive-sexy-260nw-200475077.jpg")
        checfImage.add("https://www.wellbeingofwomen.org.uk/wp-content/uploads/2019/01/nina-portrait-passport-size.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-picture-businesswoman-brown-hair-260nw-250775908.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/photo-booth-portrait-260nw-189686696.jpg")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS28H_O1Fz3KCbe8izHd67oYEZ_40Q76IpCXeVO6R1av5PXIlNb&s")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUlqHdu2vcS5TSBrUEf4jDmumM4SUrYq1Lsv_DJV05atIwfNpg&s")


        checfImage.add("https://image.shutterstock.com/image-photo/head-shot-young-business-man-260nw-95520982.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-late-thirties-white-260nw-123599890.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-portrait-asian-smiling-260nw-1041841363.jpg")
        checfImage.add("https://phowdimages.azureedge.net/cloud/pics/8137/p/c7bfc992b6614bf9a36057506e1bfc4c/1.jpg?preset=detailswithbefore")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-young-attractive-sexy-260nw-200475077.jpg")
        checfImage.add("https://www.wellbeingofwomen.org.uk/wp-content/uploads/2019/01/nina-portrait-passport-size.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-picture-businesswoman-brown-hair-260nw-250775908.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/photo-booth-portrait-260nw-189686696.jpg")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS28H_O1Fz3KCbe8izHd67oYEZ_40Q76IpCXeVO6R1av5PXIlNb&s")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUlqHdu2vcS5TSBrUEf4jDmumM4SUrYq1Lsv_DJV05atIwfNpg&s")


        checfImage.add("https://image.shutterstock.com/image-photo/head-shot-young-business-man-260nw-95520982.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-late-thirties-white-260nw-123599890.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-portrait-asian-smiling-260nw-1041841363.jpg")
        checfImage.add("https://phowdimages.azureedge.net/cloud/pics/8137/p/c7bfc992b6614bf9a36057506e1bfc4c/1.jpg?preset=detailswithbefore")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-young-attractive-sexy-260nw-200475077.jpg")
        checfImage.add("https://www.wellbeingofwomen.org.uk/wp-content/uploads/2019/01/nina-portrait-passport-size.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-picture-businesswoman-brown-hair-260nw-250775908.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/photo-booth-portrait-260nw-189686696.jpg")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS28H_O1Fz3KCbe8izHd67oYEZ_40Q76IpCXeVO6R1av5PXIlNb&s")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUlqHdu2vcS5TSBrUEf4jDmumM4SUrYq1Lsv_DJV05atIwfNpg&s")


        checfImage.add("https://image.shutterstock.com/image-photo/head-shot-young-business-man-260nw-95520982.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-late-thirties-white-260nw-123599890.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-portrait-asian-smiling-260nw-1041841363.jpg")
        checfImage.add("https://phowdimages.azureedge.net/cloud/pics/8137/p/c7bfc992b6614bf9a36057506e1bfc4c/1.jpg?preset=detailswithbefore")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-photo-young-attractive-sexy-260nw-200475077.jpg")
        checfImage.add("https://www.wellbeingofwomen.org.uk/wp-content/uploads/2019/01/nina-portrait-passport-size.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/passport-picture-businesswoman-brown-hair-260nw-250775908.jpg")
        checfImage.add("https://image.shutterstock.com/image-photo/photo-booth-portrait-260nw-189686696.jpg")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS28H_O1Fz3KCbe8izHd67oYEZ_40Q76IpCXeVO6R1av5PXIlNb&s")
        checfImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUlqHdu2vcS5TSBrUEf4jDmumM4SUrYq1Lsv_DJV05atIwfNpg&s")*/




        chefName.add("Chef Peter")
        chefName.add("Chef Sandip")
        chefName.add("Chef Gilbert ")
        chefName.add("Chef Jarvis")
        chefName.add("Chef Adam")
        chefName.add("Chef Bernard")
        chefName.add("Chef Elbert  ")
        chefName.add("Chef Dominick")
        chefName.add("Chef Clifford ")
        chefName.add("Chef Bertram")

        chefAverageRating.add("3.5")
        chefAverageRating.add("4")
        chefAverageRating.add("4.7")
        chefAverageRating.add("4.3")
        chefAverageRating.add("4.3")
        chefAverageRating.add("4.5")
        chefAverageRating.add("4.8")
        chefAverageRating.add("3")
        chefAverageRating.add("2.8")
        chefAverageRating.add("4")


        CuisinName.add("Panjab")
        CuisinName.add("Gujrat")
        CuisinName.add("North East")
        CuisinName.add("Panjab")
        CuisinName.add("Panjab")
        CuisinName.add("West Bengal")
        CuisinName.add("West Bengal")
        CuisinName.add("Orrisa")
        CuisinName.add("Jharkhand")
        CuisinName.add("South India")

        println("checfImagesize" + checfImage.size)
        var chefDescription: String = ""

        for (i in 0 until chefName.size) {

            if (i % 2 == 0) {
                chefDescription =
                    "Oversees a restaurant's kitchen by managing other members of the food preparation team, deciding what dishes to serve and adjusting orders to meet guests' requests. May assist in prep work, such as chopping vegetables, but more often will be involved in cooking specialty dishes. Chooses ingredients and designs a menu based on the seasonal availability of food items. Creates unique dishes that inspire guests to come back again and again to see what is new in the restaurant."

            } else {
                chefDescription =
                    "A restaurant menu with descriptions of food that truly engage the senses can entice people to come inside. In the same way, your chef job description must also engage and entice jobseekers. More specifically, the job responsibilities section of a job posting gives potential applicants a feel for what their day-to-day life would be like in the position, so it is crucial to write it well and catch the attention of the candidate you want.You want jobseekers to grasp the expectations and duties of the job at a glance. To do this, use bullet points. You should include at least six, but no more than eight, to make sure that you are giving a complete picture of the job without overwhelming applicants. Start each bullet point with a present-tense action verb so that candidates can begin to envision themselves in the role. Since a chef position can encompass a wide range of duties, from menu preparation to hiring and firing employees, make sure to include examples that cover any or all of those functions in your chef job description."
            }

            var chefDetailslist = Chef_Details()
            chefDetailslist.ChefImage = checfImage.get(i).toString()
            chefDetailslist.ChefName = chefName.get(i).toString()
            chefDetailslist.ChefAverageRating = chefAverageRating.get(i).toFloat()
            chefDetailslist.ChefCuisineTye = CuisinName.get(i)
            chefDetailslist.ChefDescription = chefDescription
            db.InsertChefDetailsList(chefDetailslist)
        }
    }

    private fun InsertItemDetails() {
        var fooditemlink = ArrayList<String>()

        var itemDistanceinMile = ArrayList<String>()
        var itemPrice = ArrayList<String>()

        var itemName = ArrayList<String>()



        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/noodlesss.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/springrolls.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/mushroom-rice_625x350_61424324920.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/veggies_625x350_71466071339.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/neerdosagallery.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/greendosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/onionravadosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/mysoremasaladosagallery.jpg")

        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/noodlesss.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/springrolls.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/mushroom-rice_625x350_61424324920.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/veggies_625x350_71466071339.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/neerdosagallery.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/greendosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/onionravadosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/mysoremasaladosagallery.jpg")


        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/noodlesss.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/springrolls.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/mushroom-rice_625x350_61424324920.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/veggies_625x350_71466071339.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/neerdosagallery.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/greendosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/onionravadosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/mysoremasaladosagallery.jpg")


        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/noodlesss.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/springrolls.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/mushroom-rice_625x350_61424324920.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/veggies_625x350_71466071339.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/neerdosagallery.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/greendosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/onionravadosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/mysoremasaladosagallery.jpg")


        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/noodlesss.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/springrolls.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/tofurice.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/mushroom-rice_625x350_61424324920.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/chinese-vegetarian/veggies_625x350_71466071339.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/neerdosagallery.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/greendosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/onionravadosa.jpg")
        fooditemlink.add("https://drop.ndtv.com/albums/COOKS/dosagallery/mysoremasaladosagallery.jpg")



        itemDistanceinMile.add("10")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("6")
        itemDistanceinMile.add("0.3")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("10")
        itemDistanceinMile.add("20")
        itemDistanceinMile.add("15")
        itemDistanceinMile.add("2")
        itemDistanceinMile.add("25")

        itemDistanceinMile.add("10")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("6")
        itemDistanceinMile.add("0.3")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("10")
        itemDistanceinMile.add("20")
        itemDistanceinMile.add("15")
        itemDistanceinMile.add("2")
        itemDistanceinMile.add("25")

        itemDistanceinMile.add("10")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("6")
        itemDistanceinMile.add("0.3")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("10")
        itemDistanceinMile.add("20")
        itemDistanceinMile.add("15")
        itemDistanceinMile.add("2")
        itemDistanceinMile.add("25")

        itemDistanceinMile.add("10")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("6")
        itemDistanceinMile.add("0.3")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("10")
        itemDistanceinMile.add("20")
        itemDistanceinMile.add("15")
        itemDistanceinMile.add("2")
        itemDistanceinMile.add("25")

        itemDistanceinMile.add("10")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("6")
        itemDistanceinMile.add("0.3")
        itemDistanceinMile.add("1")
        itemDistanceinMile.add("10")
        itemDistanceinMile.add("20")
        itemDistanceinMile.add("15")
        itemDistanceinMile.add("2")
        itemDistanceinMile.add("25")

        itemPrice.add("15")
        itemPrice.add("25")
        itemPrice.add("10")
        itemPrice.add("75")
        itemPrice.add("45")
        itemPrice.add("100")
        itemPrice.add("11")
        itemPrice.add("13")
        itemPrice.add("17")
        itemPrice.add("31")

        itemPrice.add("15")
        itemPrice.add("25")
        itemPrice.add("10")
        itemPrice.add("75")
        itemPrice.add("45")
        itemPrice.add("100")
        itemPrice.add("11")
        itemPrice.add("13")
        itemPrice.add("17")
        itemPrice.add("31")

        itemPrice.add("15")
        itemPrice.add("25")
        itemPrice.add("10")
        itemPrice.add("75")
        itemPrice.add("45")
        itemPrice.add("100")
        itemPrice.add("11")
        itemPrice.add("13")
        itemPrice.add("17")
        itemPrice.add("31")

        itemPrice.add("15")
        itemPrice.add("25")
        itemPrice.add("10")
        itemPrice.add("75")
        itemPrice.add("45")
        itemPrice.add("100")
        itemPrice.add("11")
        itemPrice.add("13")
        itemPrice.add("17")
        itemPrice.add("31")

        itemPrice.add("15")
        itemPrice.add("25")
        itemPrice.add("10")
        itemPrice.add("75")
        itemPrice.add("45")
        itemPrice.add("100")
        itemPrice.add("11")
        itemPrice.add("13")
        itemPrice.add("17")
        itemPrice.add("31")

        itemPrice.add("15")
        itemPrice.add("25")
        itemPrice.add("10")
        itemPrice.add("75")
        itemPrice.add("45")
        itemPrice.add("100")
        itemPrice.add("11")
        itemPrice.add("13")
        itemPrice.add("17")
        itemPrice.add("31")



        itemName.add("Kadai paneer")
        itemName.add("Katha meetha petha / halwakadoo")
        itemName.add("Mushroom do pyaza")
        itemName.add("Paneer butter masala")
        itemName.add("Rajma chaval")
        itemName.add("Chingri malai curry")
        itemName.add("Shondesh")
        itemName.add("Pongal")
        itemName.add("Puri bhaji")
        itemName.add("Masala Dosa With White souce")


        itemName.add("Kadai paneer")
        itemName.add("Katha meetha petha / halwakadoo")
        itemName.add("Mushroom do pyaza")
        itemName.add("Paneer butter masala")
        itemName.add("Rajma chaval")
        itemName.add("Chingri malai curry")
        itemName.add("Shondesh")
        itemName.add("Pongal")
        itemName.add("Puri bhaji")
        itemName.add("Masala Dosa With White souce")

        itemName.add("Kadai paneer")
        itemName.add("Katha meetha petha / halwakadoo")
        itemName.add("Mushroom do pyaza")
        itemName.add("Paneer butter masala")
        itemName.add("Rajma chaval")
        itemName.add("Chingri malai curry")
        itemName.add("Shondesh")
        itemName.add("Pongal")
        itemName.add("Puri bhaji")
        itemName.add("Masala Dosa With White souce")

        itemName.add("Kadai paneer")
        itemName.add("Katha meetha petha / halwakadoo")
        itemName.add("Mushroom do pyaza")
        itemName.add("Paneer butter masala")
        itemName.add("Rajma chaval")
        itemName.add("Chingri malai curry")
        itemName.add("Shondesh")
        itemName.add("Pongal")
        itemName.add("Puri bhaji")
        itemName.add("Masala Dosa With White souce")

        itemName.add("Kadai paneer")
        itemName.add("Katha meetha petha / halwakadoo")
        itemName.add("Mushroom do pyaza")
        itemName.add("Paneer butter masala")
        itemName.add("Rajma chaval")
        itemName.add("Chingri malai curry")
        itemName.add("Shondesh")
        itemName.add("Pongal")
        itemName.add("Puri bhaji")
        itemName.add("Masala Dosa With White souce")

        var homeDetails = ArrayList<HomeDetails>()
        var chefDetailsList = ArrayList<Chef_Details>()
        chefDetailsList.addAll(db.getAllChefList())
        println("fooditemlinkSize" + fooditemlink.size)
          var chefid:Int=-1
        if (db.getAllItemDetailsWIthChef(0).size>0)
        {

        }
        else {
            for (i in 0 until fooditemlink.size) {

                var itemDetails = Item_Details()

                if (i % 10 == 0) {
                    chefid = -1
                    chefid = chefid + 1
                    println("chefid--" + chefid)
                } else {
                    chefid = chefid + 1
                }
                println("chefid i--" + chefid + " " + i)
                itemDetails.itemChefId = chefDetailsList.get(chefid).chef_id
                itemDetails.ItemImage = fooditemlink.get(i).toString()

                itemDetails.itemDistance = itemDistanceinMile.get(i).toString()
                itemDetails.ItemPrice = itemPrice.get(i).toString()
                itemDetails.ItemName = itemName.get(i).toString()
                itemDetails.ItemCity = "0"
                itemDetails.ItemDescription =
                    "A dish’s name should clearly identify the dish so that guests don’t have to read the description in order to obtain this basic information. When customers can easily determine if they want to read further by just reading the name of the item, it saves them time. To achieve this level of clarity, you often must mention the specific item in the dish name. For instance, instead of writing “Joe’s Special” and then describing this mystery dish, you would write “Joe’s Lasagna Special,” which allows customers to quickly decide if they want more detail."

                db.InsertItemDetails(itemDetails)

            }
        }

        for (j in 0 until 5) {
            var itemReview: Item_Review = Item_Review()
            itemReview.ItemIdReview = 1
            itemReview.ItemReviewUserId = 2
            itemReview.ItemReviewUserImage = ""
            itemReview.ItemReviewUserName = "SANDIP"
            itemReview.ItemReviewUserDesc = "Wow,Its looks super tasty,i am gonna  try it!"
            itemReview.ItemReviewUserRating = 3f
            db.InsertItemReviewDetails(itemReview)
        }
        //homeDetails.addAll(homeDetails)
    }

    private fun navigateToLogin() {


        Thread(Runnable {
            while (progressStatus < 100) {
                progressStatus += 2
                handler.post(Runnable {
                    Progressbar.setProgress(progressStatus)

                })
                try {
                    // Sleep for 200 milliseconds.
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                if (progressStatus == 100) {
                    if (PrefUtils.isUserLoggedIn(this@SplashActivity)) {
                        DashboardActivity.launchActivity(this@SplashActivity)
                        //DrawerContentSlideActivity.launchActivity(this@SplashActivity)
                        finishAffinity()
                    } else {
                        LoginActivity.launchActivity(this@SplashActivity)
                        //  DashboardActivity.launchActivity(this@SplashActivity)
                        //DrawerContentSlideActivity.launchActivity(this@SplashActivity)
                        finishAffinity()
                    }

                }

            }
            /* else
             {

             }*/
        }).start()
        /* val longestDelay:Long=1200
         val longestDuration:Long=3000
         var oneTouchTimer = object : CountDownTimer(longestDelay+longestDuration+500,1000){
             override fun onFinish() {
                 if(PrefUtils.isUserLoggedIn(this@SplashActivity)){
                  DashboardActivity.launchActivity(this@SplashActivity)
                  //DrawerContentSlideActivity.launchActivity(this@SplashActivity)
                     finishAffinity()
                 }else {
                     LoginActivity.launchActivity(this@SplashActivity)
                    // DashboardActivity.launchActivity(this@SplashActivity)
                     //DrawerContentSlideActivity.launchActivity(this@SplashActivity)
                     finishAffinity()
                 }
             }
             override fun onTick(millisUntilFinished: Long) {
             }

         }
         oneTouchTimer.start()
 */
    }

}