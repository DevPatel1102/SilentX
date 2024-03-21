package com.example.silentxapp.viewModel

import com.example.silentxapp.model.Reminder

sealed class ReminderListEvent {

    data class OnDeleteReminderClick(val reminder: Reminder) : ReminderListEvent()
    data class OnReminderClick(val reminder: Reminder) : ReminderListEvent()
    object OnAddReminderClick : ReminderListEvent()
}