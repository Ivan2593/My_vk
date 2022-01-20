package com.example.my_vk

import com.example.my_vk.db.FriendViewModel
import com.example.my_vk.di.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {
    fun inject(mFriendViewModel: FriendViewModel)
}