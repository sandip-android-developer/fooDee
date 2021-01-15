package com.webmyne.modak.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import com.google.android.gms.tasks.Tasks
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

open class PlacesAdapter(context: Context, resource: Int, private val mBounds: RectangularBounds) :
    ArrayAdapter<PlacesAdapter.PlaceAutocomplete>(context, resource), Filterable {
    var placesClient: PlacesClient? = null
    var mResultList: ArrayList<PlaceAutocomplete>? = null

    override fun getCount(): Int {
        return mResultList?.size!!
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                println("Addapter Comming 1")
                val results = FilterResults()
                if (constraint != null) {
                    Log.d(TAG, "Before Prediction")
                    mResultList = getPredictions(constraint)
                    Log.d(TAG, "After Prediction")
                    if (mResultList != null) {
                        Log.d(TAG, "After Prediction  78")
                        results.values = mResultList
                        results.count = mResultList!!.size
                        //notifyDataSetChanged()
                    }
                }
                println("results" + results.count)
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }

    init {
        placesClient = com.google.android.libraries.places.api.Places.createClient(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getView(position, convertView, parent)
        //TypefaceHelper.typeface(view)
        return view
    }

    override fun getItem(position: Int): PlaceAutocomplete {
        println("results**" + mResultList?.size)
        return mResultList?.get(position)!!
    }

    private fun getPredictions(constraint: CharSequence?): ArrayList<PlaceAutocomplete> {

        val resultList = ArrayList<PlaceAutocomplete>()

        // Create a new token for the autocomplete session. Pass this to FindAutocompletePredictionsRequest,
        // and once again when the user makes a selection (for example when calling fetchPlace()).
        val token = AutocompleteSessionToken.newInstance()


        // Use the builder to create a FindAutocompletePredictionsRequest.
        val request = FindAutocompletePredictionsRequest.builder()
            // Call either setLocationBias() OR setLocationRestriction().
            // .setLocationBias(bounds)
            .setLocationBias(mBounds)
            //.setCountry("au")
            //   .setTypeFilter(TypeFilter.ADDRESS)
            .setSessionToken(token)
            .setQuery(constraint.toString())
            .build()

        var autocompletePredictions = placesClient?.findAutocompletePredictions(request)

        // This method should have been called off the main UI thread. Block and wait for at most
        // 60s for a result from the API.
        try {
            Tasks.await<FindAutocompletePredictionsResponse>(autocompletePredictions!!, 60, TimeUnit.SECONDS)
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: TimeoutException) {
            e.printStackTrace()
        }

        if (autocompletePredictions!!.isSuccessful) {
            val findAutocompletePredictionsResponse = autocompletePredictions.result
            if (findAutocompletePredictionsResponse != null)
                for (prediction in findAutocompletePredictionsResponse.autocompletePredictions) {
                    Log.i(TAG, prediction.placeId)
                    Log.i(TAG, prediction.getPrimaryText(null).toString())

                    resultList.add(PlaceAutocomplete(prediction.placeId, prediction.getFullText(null).toString()))

                }
            return resultList
        } else {
            return resultList
        }


    }

    inner class PlaceAutocomplete internal constructor(var placeId: CharSequence?, var description: CharSequence?) {

        override fun toString(): String {
            return description.toString()
        }
    }

    companion object {

        private val TAG = "PlaceArrayAdapter"
    }

}