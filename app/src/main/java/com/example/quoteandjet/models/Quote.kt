package com.example.quoteandjet.models

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Int?,
    @ColumnInfo(name = "Quote")
    val text: String,
    @ColumnInfo(name = "Author", defaultValue = "Anonymous")
    val author: String?
    )