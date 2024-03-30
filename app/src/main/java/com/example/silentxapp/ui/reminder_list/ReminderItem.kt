package com.example.silentxapp.ui.reminder_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.silentxapp.model.Reminder
import com.example.silentxapp.R

@Composable
fun ReminderItem1(
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

    val gradientBrush1 = Brush.linearGradient(
        colors = listOf(
            Color(0xFF4A4A4A),
            Color(0x99141414)
        )
    )
    Card(
        modifier
            .width(380.dp)
            .padding(7.dp)
            .background(gradientBrush1, shape = RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp,gradientBrush)
    ) {

        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier.padding(
                    start = 15.dp,
                    end = 3.dp,
                    top = 10.dp,
                    bottom = 10.dp
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = reminder.title,
                        modifier= Modifier
                            .width(170.dp)
                            .height(32.dp),
                        fontSize = 25.sp,
                        color = colorResource(id = R.color.white),
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = reminder.startTime,
                        fontSize = 17.sp,
                        color =colorResource(id = R.color.text_color)
                    )

                    Spacer(modifier.width(3.dp))

                    Text(
                        text = "-",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.text_color)
                    )

                    Spacer(modifier.width(3.dp))

                    Text(
                        text = reminder.endTime,
                        fontSize = 17.sp,
                        color = colorResource(id = R.color.text_color)
                    )
                }

                Spacer(modifier.height(5.dp))

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = reminder.location,
                        fontSize = 17.sp,
                        modifier = Modifier
                            .width(230.dp)
                            .height(28.dp),
                        color = colorResource(id = R.color.white),
                        overflow = TextOverflow.Ellipsis
                    )

                    Image(
                        painter = painterResource(id = mode_logo),
                        contentDescription = "Mode",
                        modifier
                            .size(35.dp)
                            .padding(start = 15.dp),
                        alignment = Alignment.Center,
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                    )
                }
            }
            IconButton(onClick = {
                onEvent(ReminderListEvent.OnDeleteReminderClick(reminder))
            }) {
                Image(
                    Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier
                        .size(30.dp)
                        .padding(end = 5.dp),
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.delete_icon_color))
                )
            }

        }

    }
}