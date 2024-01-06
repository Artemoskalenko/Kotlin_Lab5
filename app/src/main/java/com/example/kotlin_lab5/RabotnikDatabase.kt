package com.example.kotlin_lab5.ui.theme

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Rabotnik::class] , version = 1, exportSchema = true)
abstract class RabotnikDatabase: RoomDatabase() {
    abstract val dao: RabotnikDao
}
