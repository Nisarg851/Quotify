package com.example.quoteandjet.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quoteandjet.models.Quote

@Dao
interface QuoteDAO {
    @Insert
    fun insertQuote(quote: Quote)

    @Insert
    fun insertAllQuotesFrom(quoteList: List<Quote>)

    @Query("SELECT * FROM Quotes WHERE id = :id")
    fun getQuote(id: Int): LiveData<Quote>

    @Query("SELECT * FROM Quotes")
    fun getAllQuotes(): LiveData<List<Quote>>

    @Query("DELETE FROM Quotes WHERE id = :id")
    suspend fun deleteQuotesWithId(id: Int)
}