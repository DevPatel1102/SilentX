package com.example.silentxapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.silentxapp.model.Reminder
import kotlinx.coroutines.flow.Flow


@Dao
interface ReminderDAO {

    @Query("SELECT * FROM Reminder ORDER BY id DESC")
    fun getReminder() : Flow<List<Reminder>>

    @Query("SELECT * FROM Reminder WHERE id = :id")
    suspend fun getReminderById(id : Int) : Reminder?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder : Reminder)

//    @Update
//    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder : Reminder)

    @Query("DELETE FROM Reminder")
    suspend fun deleteAllReminder()
}