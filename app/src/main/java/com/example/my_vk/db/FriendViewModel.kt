package com.example.my_vk.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.my_vk.App
import com.example.my_vk.db.dao.FriendDatabase
import com.example.my_vk.db.model.Friend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FriendViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Friend>>
    private val repository: FriendRepository
    @Inject
    lateinit var friend:FriendDatabase

    init {
        (application.applicationContext as App).appComponent.inject(this)

        val friendDao = friend.friendDao()
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