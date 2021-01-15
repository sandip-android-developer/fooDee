package com.webmyne.modak.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.modak.adapter.FavoriteAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.model.Item_Details
import com.webmyne.modak.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_dashbord.*
import kotlinx.android.synthetic.main.favorite_fragment.*


@SuppressLint("ValidFragment")
class FavoritesFragments(var activity: BaseActivity) : BaseFragment() {
    var itemCount: Int = 0
    val db: DatabaseHelper = DatabaseHelper(activity)
    var isLoading: Boolean = false
    var ItemDetailsDetails = ArrayList<Item_Details>()
    var favoriteadapter: FavoriteAdapter? = null
    var offset: Int = 0

    companion object {
        fun getInstance(baseActivity: BaseActivity): FavoritesFragments {
            return FavoritesFragments(baseActivity)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.webmyne.modak.R.layout.favorite_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initview()
        actionListner()

    }

    private fun actionListner() {
    }

    private fun initview() {
        var fooditemlink = ArrayList<String>()

        var totalCount = 0
        rvFavorite.layoutManager = LinearLayoutManager(context)

        if (db.getAllItemDetails().size > 0) {
            ItemDetailsDetails.addAll(db.getAllItemDetailsWIthChef(0))
        }

        println("<><><><ItemDetailsDetails---" + ItemDetailsDetails.size)
        println("<><><><ItemDetailsDetails db--" + db.getAllItemDetailsWIthChef(0).size)
        for (i in 0 until ItemDetailsDetails.size) {
            totalCount += ItemDetailsDetails.get(i).itemCount
        }

        if (db.getTotalItemCount() == 0) {
            activity.textAddItemCount.visibility = View.GONE
        } else {
            activity.textAddItemCount.visibility = View.VISIBLE
            activity.textAddItemCount.text = db.getTotalItemCount().toString()
        }
        favoriteadapter =
            FavoriteAdapter(activity, ItemDetailsDetails, object : FavoriteAdapter.onitemCount {
                override fun onitemcount() {
                    println("db.getTotalItemCount()--" + db.getTotalItemCount())
                    if (db.getTotalItemCount() == 0) {
                        activity.textAddItemCount.visibility = View.GONE
                    } else {
                        activity.textAddItemCount.visibility = View.VISIBLE
                        activity.textAddItemCount.text = db.getTotalItemCount().toString()
                    }
                }

            })
        rvFavorite.adapter = favoriteadapter

        rvFavorite.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItem = (rvFavorite.layoutManager as LinearLayoutManager)!!.getItemCount()
                val lastVisibleItem =
                    (rvFavorite.layoutManager as LinearLayoutManager)!!.findLastVisibleItemPosition()
                println("<><><><ItemDetailsDetails totalItem---" + totalItem)
                println("<><><><ItemDetailsDetails lastVisibleItem---" + lastVisibleItem)
                if (!isLoading && lastVisibleItem == totalItem - 1) {
                    isLoading = true;
                    println("<><><><ItemDetailsDetails2---" + ItemDetailsDetails.size)
                    showProgressView()
                    val mCountDownTimer: CountDownTimer
                    var i = 0

                    progressBar.progress = i
                    mCountDownTimer = object : CountDownTimer(5000, 1000) {

                        override fun onTick(millisUntilFinished: Long) {
                            Log.v("Log_tag", "Tick of Progress$i$millisUntilFinished")
                            i++
                            progressBar.progress = i * 100 / (5000 / 1000)
                            progressBar.visibility = View.VISIBLE

                        }

                        override fun onFinish() {
                            //Do what you want
                            i++
                            progressBar.visibility = View.INVISIBLE
                            loadMoreItems()
                            progressBar.progress = 100
                        }
                    }
                    mCountDownTimer.start()

                    // Scrolled to bottom. Do something here.

                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

    }

    private fun loadMoreItems() {
        hideProgressView()
        offset = offset + 10
        if (db.getAllItemDetails().size > 0) {
            ItemDetailsDetails.addAll(db.getAllItemDetailsWIthChef(offset))
        }
        println("<><><><ItemDetailsDetails1---" + ItemDetailsDetails.size)
        favoriteadapter!!.notifyDataSetChanged()
        isLoading = false;
        /*  if (ItemDetailsDetails.size==60){
              isLoading = false;
          }*/


    }

    override fun onResume() {
        super.onResume()
        ///initview()
    }

    fun showProgressView() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressView() {
        progressBar.visibility = View.INVISIBLE
    }

}
