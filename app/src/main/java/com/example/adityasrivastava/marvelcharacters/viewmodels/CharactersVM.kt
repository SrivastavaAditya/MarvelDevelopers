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

class CharactersVM(application: Application) : AndroidViewModel(application) {

    fun getCharacters(context: Context,
                      offset: Int, limit: Int = 10, apiKey: String = Keys.API_KEY_VAL, hash: String = Keys.HASH_VAL, timeStamp: Int = 1)
            : MutableLiveData<Response> {
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
                //it.printStackTrace()
                Log.e("error", it.toString())
                Toast.makeText(this@CharactersVM.getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            })
        return mutableLiveData
    }
}