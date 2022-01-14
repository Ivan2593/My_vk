package com.example.my_vk.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav")
data class Friend(
    @PrimaryKey val id: Int,
    val name: String)