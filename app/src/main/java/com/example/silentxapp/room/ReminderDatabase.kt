package com.example.silentxapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.silentxapp.model.Reminder

@Database(
    entities = [Reminder::class],
    version = 1
)
abstract class ReminderDatabase : RoomDatabase() {

    abstract val reminderDAO: ReminderDAO

}