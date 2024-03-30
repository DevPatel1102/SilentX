package com.example.silentxapp.ui.add_edit_reminder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.silentxapp.model.Reminder
import com.example.silentxapp.repository.ReminderRepository
import com.example.silentxapp.util.Routes
import com.example.silentxapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditReminderViewModel @Inject constructor(
    private val repository: ReminderRepository,
    savedStateHandle  : SavedStateHandle
) : ViewModel() {

    var reminder by mutableStateOf<Reminder?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var startTime by mutableStateOf("")
        private set

    var endTime by mutableStateOf("")
        private set

    var location by mutableStateOf("")
        private set

    var mode by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val reminderId = savedStateHandle.get<Int>("reminderId")
        if (reminderId != -1){
            viewModelScope.launch {
               repository.getReminderById(reminderId!!)?.let { reminder ->
                   title=reminder.title
                   startTime=reminder.startTime
                   endTime=reminder.endTime
                   mode=reminder.mode
                   this@AddEditReminderViewModel.reminder = reminder
               }

            }
        }
    }

    fun onEvent(event: AddEditReminderEvent){
        when(event){
            is AddEditReminderEvent.OnTitleChange ->{
                title= event.title
            }
            is AddEditReminderEvent.OnStartTimeChange ->{
                startTime= event.startTime
            }
            is AddEditReminderEvent.OnEndTimeChange ->{
                endTime= event.endTime
            }
            is AddEditReminderEvent.OnLocationChange ->{
                location= event.location
            }
            is AddEditReminderEvent.OnModeChange ->{
                mode= event.mode
            }
            is AddEditReminderEvent.OnSaveReminderClick ->{
                viewModelScope.launch {
//                    if(title.isNotBlank() || startTime.isNotBlank() || endTime.isNotBlank() || location.isNotBlank() || mode.isNotBlank()){
//                        sendUiEvent()
//                    }
                    repository.insertReminder(
                        Reminder(
                            title = title,
                            startTime = startTime,
                            endTime = endTime,
                            location = location,
                            mode = mode,
                            id = reminder?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }

        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}