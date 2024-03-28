package com.example.silentxapp.ui.add_edit_reminder

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.silentxapp.R
import com.example.silentxapp.util.UiEvent
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditReminderScreen (
    onNavigate : (UiEvent : UiEvent.Navigate) -> Unit,
    viewModel: AddEditReminderViewModel = hiltViewModel()
){

    val main_mode = remember {
        mutableStateOf("Select Mode")
    }
    val showTimeStart = remember {
        mutableStateOf(false)
    }
    val showTimeEnd = remember {
        mutableStateOf(false)
    }
    val start_hour = remember {
        mutableStateOf("12")
    }
    val start_minute = remember {
        mutableStateOf("00")
    }
    val end_hour = remember {
        mutableStateOf("12")
    }
    val end_minute = remember {
        mutableStateOf("00")
    }
    val dropDownStatus = remember {
        mutableStateOf(false)
    }
    val select_mode_color = remember {
        mutableStateOf(Color.Gray)
    }
    val select_mode_dropIcon = remember {
        mutableStateOf(R.drawable.dropdown_arrowdown)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Menu")
                    }
                },
                title = {
                    Text(
                        text = "New Remainder",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(start = 45.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.topBar_bg),
                    navigationIconContentColor = Color.White,
                ),
                modifier = Modifier.padding(bottom = 1.dp)
            )
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = colorResource(id = R.color.topBar_bg))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 150.dp)
                ) {
                    TextField(
                        value = viewModel.title,
                        onValueChange = { title ->
                            viewModel.onEvent(AddEditReminderEvent.OnTitleChange(title))
                        },
                        Modifier
                            .width(330.dp)
                            .height(65.dp),
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                text = "Title",
                                fontSize = 25.sp,
                                color = Color.Gray
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorResource(id = R.color.grey),
                            unfocusedContainerColor = colorResource(id = R.color.grey),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            focusedLabelColor = colorResource(id = R.color.focused_label_color)
                        ),
                        textStyle = TextStyle.Default.copy(fontSize = 25.sp)
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {

                        Card(shape = RoundedCornerShape(10.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .width(130.dp)
                                    .height(60.dp)
                                    .background(colorResource(id = R.color.grey)),
                            ) {
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text(
                                    text = start_hour.value,
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(bottom = 1.dp), color = Color.White
                                )
                                Spacer(modifier = Modifier.padding(3.dp))
                                Text(
                                    text = ":",
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(bottom = 1.dp), color = Color.White
                                )
                                Spacer(modifier = Modifier.padding(3.dp))
                                Text(
                                    text = start_minute.value,
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(bottom = 1.dp), color = Color.White
                                )
                                IconButton(onClick = {
                                    showTimeStart.value = !showTimeStart.value
                                }) {
                                    Icon(
                                        Icons.Default.ArrowDropDown,
                                        contentDescription = "Time",
                                        modifier = Modifier.size(30.dp),
                                        tint = Color.White
                                    )
                                }
                                if (showTimeStart.value) {
                                    TimePicker(start_hour, start_minute, showTimeStart)
                                }
                                viewModel.onEvent(AddEditReminderEvent.OnStartTimeChange("${start_hour.value}:${start_minute.value}"))
                            }
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = "To",
                            fontSize = 25.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.padding(10.dp))

                        Card(shape = RoundedCornerShape(10.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .width(130.dp)
                                    .height(60.dp)
                                    .background(colorResource(id = R.color.grey)),
                            ) {
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text(
                                    text = end_hour.value,
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(bottom = 1.dp), color = Color.White
                                )
                                Spacer(modifier = Modifier.padding(3.dp))
                                Text(
                                    text = ":",
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(bottom = 1.dp), color = Color.White
                                )
                                Spacer(modifier = Modifier.padding(3.dp))
                                Text(
                                    text = end_minute.value,
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(bottom = 1.dp), color = Color.White
                                )

                                IconButton(onClick = {
                                    showTimeEnd.value = !showTimeEnd.value
                                }) {
                                    Icon(
                                        Icons.Default.ArrowDropDown,
                                        contentDescription = "Time",
                                        modifier = Modifier.size(30.dp),
                                        tint = Color.White
                                    )
                                }
                                if (showTimeEnd.value) {
                                    TimePicker(end_hour, end_minute, showTimeEnd)
                                }
                                viewModel.onEvent(AddEditReminderEvent.OnEndTimeChange("${end_hour.value}:${end_minute.value}"))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(15.dp))

                    Card(
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start, modifier = Modifier
                                .background(
                                    colorResource(id = R.color.grey)
                                )
                                .width(330.dp)
                                .height(60.dp)
                        ) {
                            Spacer(modifier = Modifier.padding(start = 20.dp))

                            Text(
                                text = "Location",
                                fontSize = 25.sp,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.padding(start = 165.dp))

                            IconButton(onClick = { }) {
                                Icon(
                                    Icons.Default.LocationOn,
                                    contentDescription = "Time",
                                    modifier = Modifier.size(30.dp),
                                    tint = Color.White
                                )
                            }
                            viewModel.onEvent(AddEditReminderEvent.OnLocationChange("3,Washington DC,USA"))
                        }
                    }

                    Spacer(modifier = Modifier.padding(15.dp))


                    Card(
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(330.dp)
                                .height(60.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .background(
                                        colorResource(id = R.color.grey)
                                    )
                                    .width(330.dp)
                                    .height(60.dp)
                            ) {

                                Text(
                                    text = main_mode.value,
                                    fontSize = 25.sp,
                                    color = select_mode_color.value,
                                    modifier = Modifier.padding(start = 20.dp)
                                )

                                Spacer(modifier = Modifier.padding(start = 125.dp))

                                IconButton(onClick = {
                                    Log.d("dropState", dropDownStatus.value.toString())
                                    dropDownStatus.value = !dropDownStatus.value
                                    select_mode_dropIcon.value = R.drawable.dropdown_arrowup
                                }) {
                                    Icon(
                                        painter = painterResource(id = select_mode_dropIcon.value),
                                        contentDescription = "DropDownIcon",
                                        modifier = Modifier.size(30.dp),
                                        tint = Color.White
                                    )
                                }
                                viewModel.onEvent(AddEditReminderEvent.OnModeChange(main_mode.value))
                            }
                        }
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier
                                .padding(start = 12.dp)
                        ) {
                            DropdownMenu(
                                expanded = dropDownStatus.value, onDismissRequest = {
                                    dropDownStatus.value = false
                                    select_mode_dropIcon.value = R.drawable.dropdown_arrowdown
                                }, modifier = Modifier
                                    .width(305.dp)
                                    .background(
                                        colorResource(id = R.color.off_white),
                                        shape = RoundedCornerShape(
                                            bottomStart = 10.dp,
                                            bottomEnd = 10.dp
                                        )
                                    )
                            ) {
                                DropdownMenuItem(
                                    text = { Text(text = "Ring", fontSize = 20.sp) },
                                    onClick = {
                                        dropDownStatus.value = false
                                        select_mode_color.value = Color.White
                                        main_mode.value = "Ring"
                                        select_mode_dropIcon.value =
                                            R.drawable.dropdown_arrowdown
                                    })
                                DropdownMenuItem(
                                    text = { Text(text = "Vibrate", fontSize = 20.sp) },
                                    onClick = {
                                        dropDownStatus.value = false
                                        select_mode_color.value = Color.White
                                        main_mode.value = "Vibrate"
                                        select_mode_dropIcon.value =
                                            R.drawable.dropdown_arrowdown
                                    })
                                DropdownMenuItem(
                                    text = { Text(text = "Silent", fontSize = 20.sp) },
                                    onClick = {
                                        dropDownStatus.value = false
                                        select_mode_color.value = Color.White
                                        main_mode.value = "Silent"
                                        select_mode_dropIcon.value =
                                            R.drawable.dropdown_arrowdown
                                    })
                            }
                        }

                    }

                    Spacer(modifier = Modifier.padding(40.dp))

                    OutlinedButton(
                        onClick = {
                            viewModel.onEvent(AddEditReminderEvent.OnSaveReminderClick)
                        },
                        modifier = Modifier.width(200.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "SAVE",
                            fontSize = 30.sp,
                            modifier = Modifier.padding(2.dp),
                            color = Color.White
                        )
                    }
                }
            }
        },
        containerColor = colorResource(id = R.color.off_white)
    )
}

@Composable
fun TimePicker(
    selectedHour: MutableState<String>,
    selectedMinute: MutableState<String>,
    state: MutableState<Boolean>
) {
    var pickedDate = remember {
        mutableStateOf(LocalDate.now())
    }
    val formattedDate = remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate.value.atStartOfDay())
        }
    }
    var pickedTime = remember {
        mutableStateOf(LocalTime.NOON)
    }

    val formattedTime = remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("HH:mm")
                .format(LocalDateTime.of(pickedDate.value, pickedTime.value))
        }
    }

    val timeDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        timeDialogState.show()
        Log.d("time", formattedTime.toString())
    }

    MaterialDialog(
        dialogState = timeDialogState,
        onCloseRequest = { state.value = false },
        buttons = {
            positiveButton(text = "Ok") {
                Log.d("time", formattedTime.toString())
                selectedHour.value = formattedTime.value.split(":")[0]
                selectedMinute.value = formattedTime.value.split(":")[1]
                state.value = false
            }
            negativeButton(text = "Cancel") {
                state.value = false
            }
        }
    ) {
        timepicker(
            initialTime = LocalTime.NOON,
            title = "Pick a Time"
        ) {
            pickedTime.value = it
        }
    }
}