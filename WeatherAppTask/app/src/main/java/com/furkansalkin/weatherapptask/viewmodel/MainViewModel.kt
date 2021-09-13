package com.furkansalkin.weatherapptask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkansalkin.weatherapptask.model.ConsolidatedWeather
import com.furkansalkin.weatherapptask.service.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val weatherAPIService = WeatherAPIService()
    private val disposable = CompositeDisposable()

    val weather_data = MutableLiveData<ConsolidatedWeather>()
    val weather_error = MutableLiveData<Boolean>()
    val weather_load = MutableLiveData<Boolean>()

    fun refreshData(cityName: String){
        getDataFromAPI()

    }

    private fun getDataFromAPI() {
        weather_load.value = true
        disposable.add(
            weatherAPIService.getDataService()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ConsolidatedWeather>(){
                    override fun onSuccess(t: ConsolidatedWeather) {
                        weather_data.value = t
                        weather_error.value = false
                        weather_load.value = false
                    }

                    override fun onError(e: Throwable) {
                        weather_error.value = true
                        weather_load.value = false
                    }

                }))


    }
}