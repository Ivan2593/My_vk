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

    companion object {
        @Volatile
        private var INSTANCE : FriendDatabase? = null

        fun getDatabase(context: Context): FriendDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FriendDatabase::class.java,
                    "fav"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}