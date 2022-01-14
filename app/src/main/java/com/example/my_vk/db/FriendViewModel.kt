package com.example.my_vk.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.my_vk.db.dao.FriendDatabase
import com.example.my_vk.db.model.Friend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FriendViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Friend>>
    private val repository: FriendRepository

    init {
        val friendDao = FriendDatabase.getDatabase(application).friendDao()
        repository = FriendRepository(friendDao)
        readAllData = repository.readAllData
    }
    
    fun addFriend(friend: Friend){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFriend(friend)
        }
    }

    fun deleteUser(friend: Friend){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(friend)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }
}