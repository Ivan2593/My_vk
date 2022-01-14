package com.example.my_vk.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.my_vk.models.FriendsModel

@StateStrategyType (value = AddToEndSingleStrategy::class)
interface FriendsView : MvpView{
    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupFriendsList(friendsList: List<FriendsModel>)
    fun startLoading()
    fun endLoading()
}