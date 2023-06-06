package com.example.myhomework4.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhomework4.BuildConfig
import com.example.myhomework4.base.BaseViewModel
import com.example.myhomework4.model.Playlists
import com.example.myhomework4.remote.ApiService
import com.example.myhomework4.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MainViewModel : BaseViewModel() {

    private val apiService: ApiService = RetrofitClient.create()

    fun playlists(): LiveData<Playlists> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()

        apiService.getPlaylists(
            BuildConfig.API_KEY,
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            "snippet,contentDetails",
        50
        )
            .enqueue(object : retrofit2.Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    print(t.stackTrace)
                }
            })

        return data
    }
}
