package com.example.kotlin_lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.kotlin_lab5.ui.theme.KotlinLab5Theme
import com.example.kotlin_lab5.ui.theme.RabotnikDatabase
import com.example.kotlin_lab5.ui.theme.RabotnikScreen
import com.example.kotlin_lab5.ui.theme.RabotnikViewModel

@Suppress("UNCHECKED_CAST")

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            RabotnikDatabase::class.java,
            "rabotnik.db"
        ).build()
    }
    private val viewModel by viewModels<RabotnikViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return RabotnikViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinLab5Theme {
                val state by viewModel._state.collectAsState()
                RabotnikScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}