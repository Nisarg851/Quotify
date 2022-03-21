package com.example.quoteandjet.viewModels

import android.content.Context
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quoteandjet.database.DatabaseModel
import com.example.quoteandjet.models.Quote
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.nio.charset.StandardCharsets.UTF_8

class MainViewModel(val context: Context): ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var quoteIndex: Int = 0

    // Live Datas
    private var _quote: MutableLiveData<Quote>
    val quote: LiveData<Quote>

    init {
        quoteList = loadQuotesFromAssets()

        val database: DatabaseModel = DatabaseModel.getDBInstance(context.applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            val _quoteList: List<Quote> = quoteList.map { Quote(id = null, text = it.text, author = it.author) }
            database.quoteDAO().insertAllQuotesFrom(_quoteList)
        }

        _quote = MutableLiveData(quoteList.get(0))
        quote = _quote
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

    fun getNextQuote(){
        if(quoteIndex < quoteList.size-1)
            ++quoteIndex
        _quote.value = quoteList.get(quoteIndex)
    }

    fun getPreviousQuote(){
        if(quoteIndex > 0)
            quoteIndex = quoteIndex - 1
        _quote.value = quoteList.get(quoteIndex)
    }
}