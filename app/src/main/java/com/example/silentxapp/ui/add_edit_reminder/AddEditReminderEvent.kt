package com.example.silentxapp.ui.add_edit_reminder

sealed class AddEditReminderEvent {
    data class OnTitleChange(val title: String): AddEditReminderEvent()
    data class OnStartTimeChange(val startTime: String): AddEditReminderEvent()
    data class OnEndTimeChange(val endTime: String): AddEditReminderEvent()
    data class OnLocationChange(val location: String): AddEditReminderEvent()
    data class OnModeChange(val mode: String): AddEditReminderEvent()
    object OnSaveReminderClick: AddEditReminderEvent()
}