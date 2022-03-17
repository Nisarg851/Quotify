package com.example.quoteandjet.ViewModels

import android.content.Context
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quoteandjet.Models.Quote
import com.google.gson.Gson
import java.io.InputStream
import java.nio.charset.StandardCharsets.UTF_8

class MainViewModel(val context: Context): ViewModel() {

//    private var quoteList: Array<Quote> = emptyArray()
//    private var quoteIndex = 1

    private var quoteList: Array<Quote> = emptyArray()

    private var _quoteIndex: MutableLiveData<Int> = MutableLiveData(0)
    val quoteIndex: LiveData<Int> = _quoteIndex

    init {
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote>{
        val inputStream: InputStream = context.assets.open("Quotes.json")
        val bufferSize: Int = inputStream.available()
        val buffer: ByteArray = ByteArray(bufferSize)
        inputStream.read(buffer)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            val json = String(buffer, UTF_8)
            val gson = Gson()
            return gson.fromJson(json, Array<Quote>::class.java)
        }
        return emptyArray()
    }

    fun getQuote() = quoteList.get(quoteIndex.value as Int)

    fun getNextQuote(){
        _quoteIndex.value = _quoteIndex.value?.plus(1)
    }

    fun getPreviousQuote(){
        _quoteIndex.value = _quoteIndex.value?.minus(1)
    }
}