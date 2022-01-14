package com.example.my_vk.db

import androidx.lifecycle.LiveData
import com.example.my_vk.db.dao.FriendDao
import com.example.my_vk.db.model.Friend

class FriendRepository(private val friendDao: FriendDao) {
    val readAllData: LiveData<List<Friend>> = friendDao.readAllData()

    suspend fun addFriend(friend: Friend){
        friendDao.addFriend(friend)
    }

    suspend fun deleteUser(friend: Friend){
        friendDao.deleteUser(friend)
    }

    suspend fun deleteAllUsers(){
        friendDao.deleteAllUsers()
    }
}