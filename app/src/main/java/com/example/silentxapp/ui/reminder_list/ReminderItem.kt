package com.example.silentxapp.ui.reminder_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.silentxapp.model.Reminder
import com.example.silentxapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderItem(
    reminder: Reminder,
    onEvent: (ReminderListEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    var mode_logo by remember{
        mutableStateOf(R.drawable.time_logo)
    }
    if(reminder.mode=="Silent"){
        mode_logo = R.drawable.silent
    }
    else if(reminder.mode=="Vibrate"){
        mode_logo=R.drawable.vibrate
    }
    else{
        mode_logo=R.drawable.ring
    }

    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF3C3C3C),
            Color(0xFFA2A2A2)
        )
    )

    Card(
        modifier = Modifier
            .width(360.dp)
            .padding(7.dp)
            .background(gradientBrush, shape = RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 5.dp,
                    top = 10.dp,
                    bottom = 10.dp
                )
            ) {
                Row {
                    Card(
                        modifier = Modifier
                            .width(140.dp)
                            .height(30.dp)
                            .background(
                                Color(0xFFB8B8B8),
                                shape = RoundedCornerShape(5.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = reminder.title,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(start = 5.dp),
                                color = colorResource(id = R.color.black)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(7.dp))
                    Card(
                        modifier = Modifier
                            .width(140.dp)
                            .height(30.dp)
                            .background(
                                Color(0xFFB8B8B8),
                                shape = RoundedCornerShape(5.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )

                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.time_logo),
                                contentDescription = "Time",
                                modifier = Modifier.size(23.dp)
                            )
                            Spacer(modifier = Modifier.width(7.dp))

                            Text(
                                text = reminder.startTime,
                                fontSize = 17.sp,
                                color = colorResource(id = R.color.black)
                            )

                            Spacer(modifier = Modifier.width(3.dp))

                            Text(
                                text = "-",
                                fontSize = 20.sp,
                                color = colorResource(id = R.color.black)
                            )

                            Spacer(modifier = Modifier.width(3.dp))

                            Text(
                                text = reminder.endTime,
                                fontSize = 17.sp,
                                color = colorResource(id = R.color.black)
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
                Row {
                    Card(
                        modifier = Modifier
                            .width(35.dp)
                            .height(30.dp)
                            .background(
                                Color(0xFFB8B8B8),
                                shape = RoundedCornerShape(5.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = mode_logo),
                                contentDescription = "Mode",
                                modifier = Modifier.size(23.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(7.dp))
                    Card(
                        modifier = Modifier
                            .width(245.dp)
                            .height(30.dp)
                            .background(
                                Color(0xFFB8B8B8),
                                shape = RoundedCornerShape(5.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 3.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                Icons.Default.LocationOn,
                                contentDescription = "Location",
                                modifier = Modifier.size(23.dp)
                            )
                            Spacer(modifier = Modifier.width(7.dp))

                            Text(
                                text = reminder.location,
                                fontSize = 17.sp,
                                modifier = Modifier.padding(bottom = 2.dp),
                                color = colorResource(id = R.color.black)
                            )
                        }
                    }

                }
            }
            IconButton(onClick = {
                onEvent(ReminderListEvent.OnDeleteReminderClick(reminder))
            }) {
                Image(
                    Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.size(25.dp)
                )
            }

        }

    }

}