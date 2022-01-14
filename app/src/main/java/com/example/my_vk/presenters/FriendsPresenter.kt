package com.example.my_vk.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.my_vk.R
import com.example.my_vk.models.FriendsModel
import com.example.my_vk.providers.FriendsProvider
import com.example.my_vk.views.FriendsView

@InjectViewState
class FriendsPresenter: MvpPresenter<FriendsView>(){
    fun loadFriends(){
        viewState.startLoading()
        FriendsProvider(presenter = this).loadFriends()
    }
    fun friendsLoaded(friendsList: List<FriendsModel>){
        viewState.endLoading()
        if(friendsList.isEmpty()){
            viewState.setupEmptyList()
            viewState.showError(textResource = R.string.friends_no_items)
        } else {
            viewState.setupFriendsList(friendsList = friendsList)
        }
    }

    fun showError(textResource: Int) {
        viewState.showError(textResource = textResource)
    }
}