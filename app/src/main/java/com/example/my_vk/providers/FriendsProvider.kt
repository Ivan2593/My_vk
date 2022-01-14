package com.example.my_vk.providers

import com.example.my_vk.R
import com.example.my_vk.models.FriendsModel
import com.example.my_vk.models.VKFriendsRequest
import com.example.my_vk.presenters.FriendsPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException

class FriendsProvider(var presenter: FriendsPresenter) {
    fun loadFriends() {
        VK.execute(VKFriendsRequest(), object : VKApiCallback<List<FriendsModel>> {

            override fun fail(error: VKApiExecutionException) {
                presenter.showError(textResource = R.string.friends_error_loading)
            }

            override fun success(result: List<FriendsModel>) {
                presenter.friendsLoaded(result)
            }
        })

    }

}