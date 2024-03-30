package com.example.silentxapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.silentxapp.ui.add_edit_reminder.AddEditReminderScreen
import com.example.silentxapp.util.Routes
import com.example.silentxapp.ui.reminder_list.ReminderScreen
import com.example.silentxapp.ui.theme.SilentXAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SilentXAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = Routes.Reminder_List
                ){
                    composable(Routes.Reminder_List){
                        ReminderScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                    composable(
                        route = Routes.Add_Edit_Reminder + "?reminderId={reminderId}",
                        arguments = listOf(
                            navArgument(name = "reminderId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        AddEditReminderScreen(onPopBackStack = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}
