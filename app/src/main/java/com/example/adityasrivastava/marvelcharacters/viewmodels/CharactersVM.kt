package com.example.adityasrivastava.marvelcharacters.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.adityasrivastava.marvelcharacters.dialogs.LoadingDialog
import com.example.adityasrivastava.marvelcharacters.models.Response
import com.example.adityasrivastava.marvelcharacters.network.Keys
import com.example.adityasrivastava.marvelcharacters.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * CharactersVM
 */
class CharactersVM(application: Application) : AndroidViewModel(application) {
    /**
     * Get Character
     *
     * @param context
     * @param offset
     * @param limit
     * @param apiKey
     * @param hash
     * @param timeStamp
     */
    fun getCharacters(
        context: Context,
        offset: Int, limit: Int = 10, apiKey: String = Keys.API_KEY_VAL, hash: String = Keys.HASH_VAL,
        timeStamp: Int = Keys.TIME_STAMP_VAL
    ): MutableLiveData<Response> {
        val loadingDialog = LoadingDialog(context)
        loadingDialog.show()
        val mutableLiveData = MutableLiveData<Response>()
        val disposable = RetrofitClient.retrofitInterface.getMarvelCharacters(apiKey, hash, timeStamp, offset, limit)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                mutableLiveData.value = it
                loadingDialog.dismiss()
            }, {
                loadingDialog.dismiss()
                mutableLiveData.value = null
                Log.e("Error", it.toString())
                Toast.makeText(this@CharactersVM.getApplication(), "Network Error", Toast.LENGTH_LONG).show()
            })
        return mutableLiveData
    }
}