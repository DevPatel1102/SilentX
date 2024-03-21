package com.example.silentxapp.repository

import com.example.silentxapp.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository{

    fun getReminder() : Flow<List<Reminder>>

    suspend fun getReminderById(id : Int) : Reminder?

    suspend fun insertReminder(reminder : Reminder)

    suspend fun deleteReminder(reminder : Reminder)

    suspend fun deleteAllReminder()
}