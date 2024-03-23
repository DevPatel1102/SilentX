package com.example.silentxapp.util

sealed class UiEvent {
    data class Navigate(val route : String) : UiEvent()
}