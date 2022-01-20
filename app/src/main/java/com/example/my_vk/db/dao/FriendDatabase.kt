package com.example.my_vk.db.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.my_vk.db.model.Friend

@Database(
    entities = [
        Friend::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FriendDatabase: RoomDatabase() {
    abstract fun friendDao(): FriendDao

}