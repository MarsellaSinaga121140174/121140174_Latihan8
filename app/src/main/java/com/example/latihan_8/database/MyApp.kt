package com.example.latihan_8.database

import android.app.Application
import androidx.room.Room

class MyApp : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this, AppDatabase::class.java, "my_database"
        ).build()

        Thread {
            val userDao = database.userDao()
            if(userDao.getAllUsers().isEmpty()) {
                userDao.insertUser(User(
                    username = "John Doe", email = "john@example.com"
                ))
                userDao.insertUser(User(
                    username = "Jane Smith", email = "jane@example.com"
                ))
                userDao.insertUser(User(
                    username = "Mike Johnson", email = "mike@example.com"
                ))
                userDao.insertUser(User(
                    username = "Upin Ipin", email = "opah@test.com"
                ))
            }
        }.start()
    }
}