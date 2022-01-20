package com.example.my_vk.di

import android.content.Context
import androidx.room.Room
import com.example.my_vk.db.dao.FriendDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(val context: Context) {
    @Provides
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideDatabase(context: Context) : FriendDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                FriendDatabase::class.java,
                "fav"
            ).build()
    }
}