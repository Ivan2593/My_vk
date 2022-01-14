package com.example.my_vk.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.my_vk.R
import com.example.my_vk.adapters.FavouritesAdapter
import com.example.my_vk.db.FriendViewModel

class FavouritesActivity : AppCompatActivity() {
    private lateinit var mAdapter: FavouritesAdapter
    private lateinit var mUserViewModel: FriendViewModel
    private lateinit var mFriendViewModel: FriendViewModel
    private lateinit var mRvFriends: RecyclerView
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        mRvFriends = findViewById(R.id.recycler_favourites)
        mUserViewModel = ViewModelProvider(this)[FriendViewModel::class.java]
        mAdapter = FavouritesAdapter(this)
        mRvFriends.adapter = mAdapter
        mRvFriends.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        mRvFriends.setHasFixedSize(true)
        mUserViewModel.readAllData.observe(this, { user ->
            mAdapter.setData(user)
        })



    }
}