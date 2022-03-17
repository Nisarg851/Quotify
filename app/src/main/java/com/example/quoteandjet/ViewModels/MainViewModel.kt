package com.example.quoteandjet.ViewModels

import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModel
import com.example.quoteandjet.Models.Quote
import com.google.gson.Gson
import java.io.InputStream
import java.nio.charset.StandardCharsets.UTF_8

@Suppress("UNCHECKED_CAST")
class MainViewModel(val context: Context): ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var quoteIndex = 1

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
        return emptyArray<Quote>()
    }

    fun getQuote() = quoteList.get(quoteIndex)

    fun getNextQuote() = quoteList.get(++quoteIndex)

    fun getPreviousQuote() = quoteList.get(--quoteIndex)
}