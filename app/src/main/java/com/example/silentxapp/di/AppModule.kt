package com.example.silentxapp.di

import android.app.Application
import androidx.room.Room
import com.example.silentxapp.repository.ReminderRepository
import com.example.silentxapp.repository.ReminderRepositoryImpl
import com.example.silentxapp.room.ReminderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideReminderDatabase(app : Application) : ReminderDatabase {
        return  Room.databaseBuilder(
            app,
            ReminderDatabase::class.java,
            "Reminder_DB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideReminderRepository(db : ReminderDatabase) : ReminderRepository {
        return ReminderRepositoryImpl(db.reminderDAO)
    }
}