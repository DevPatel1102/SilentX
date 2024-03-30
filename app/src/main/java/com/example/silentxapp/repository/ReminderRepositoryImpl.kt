package com.example.silentxapp.repository

import androidx.annotation.WorkerThread
import com.example.silentxapp.model.Reminder
import com.example.silentxapp.room.ReminderDAO
import kotlinx.coroutines.flow.Flow

class ReminderRepositoryImpl(
    private val dao: ReminderDAO
) : ReminderRepository {
    override fun getReminder(): Flow<List<Reminder>> {
        return dao.getReminder()
    }

    override suspend fun getReminderById(id: Int): Reminder? {
        return dao.getReminderById(id)
    }

    @WorkerThread
    override suspend fun insertReminder(reminder: Reminder) {
        dao.insertReminder(reminder)
    }

    @WorkerThread
    override suspend fun deleteReminder(reminder: Reminder) {
        dao.deleteReminder(reminder)
    }

    @WorkerThread
    override suspend fun deleteAllReminder() {
        dao.deleteAllReminder()
    }
}