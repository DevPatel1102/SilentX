package com.example.silentxapp.ui.reminder_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.silentxapp.repository.ReminderRepository
import com.example.silentxapp.util.Routes
import com.example.silentxapp.util.UiEvent
import com.example.silentxapp.ui.reminder_list.ReminderListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val repository: ReminderRepository
) : ViewModel(){

    val reminder = repository.getReminder()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent (event : ReminderListEvent){
        when(event){
            is ReminderListEvent.OnReminderClick ->{
//                sendUiEvent(UiEvent.Navigate(Routes.Add_Edit_Reminder + "?reminderId=${event.reminder.id}"))
            }
            is ReminderListEvent.OnAddReminderClick ->{
                sendUiEvent(UiEvent.Navigate(Routes.Add_Edit_Reminder))
            }
            is ReminderListEvent.OnDeleteReminderClick ->{

                viewModelScope.launch {
                    repository.deleteReminder(event.reminder)
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