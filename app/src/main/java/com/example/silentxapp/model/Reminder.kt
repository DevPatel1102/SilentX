package com.example.silentxapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reminder(
    val title : String,
    val startTime : String,
    val endTime : String,
    val location : String,
    val mode : String,
    @PrimaryKey val id: Int? = null
){

}