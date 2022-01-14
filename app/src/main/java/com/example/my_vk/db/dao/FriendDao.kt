package com.example.my_vk.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.my_vk.db.model.Friend

@Dao
interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFriend(friend: Friend)

    @Delete
    suspend fun deleteUser(friend: Friend)

    @Query("DELETE FROM fav")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM fav ORDER BY id ASC")
    fun readAllData(): LiveData<List<Friend>>
}