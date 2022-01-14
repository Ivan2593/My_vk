package com.example.my_vk.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.my_vk.R
import com.example.my_vk.adapters.FriendsAdapter
import com.example.my_vk.db.FriendViewModel
import com.example.my_vk.models.FriendsModel
import com.example.my_vk.presenters.FriendsPresenter
import com.example.my_vk.views.FriendsView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.vk.api.sdk.VK

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    private lateinit var mAdapter: FriendsAdapter
    private lateinit var mCpvWait: CircularProgressView
    private lateinit var mTxtNoItems: TextView
    private lateinit var mRvFriends: RecyclerView
    private lateinit var mBtnLogout: Button
    private lateinit var mBtnAdd: Button
    private lateinit var mBtnFavourites: Button
    private lateinit var mFriendViewModel: FriendViewModel

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        mRvFriends = findViewById(R.id.recycler_friends)
        mTxtNoItems = findViewById(R.id.txt_friends_no_items)
        mCpvWait = findViewById(R.id.cpv_friends)
        mBtnLogout = findViewById(R.id.btn_logout_enter)
        mBtnFavourites = findViewById(R.id.btn_favourites)
        val mTxtSearch: EditText = findViewById(R.id.txt_friends_search)
        mTxtSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mAdapter.filter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        friendsPresenter.loadFriends()
        mAdapter = FriendsAdapter(this)
        mFriendViewModel = ViewModelProvider(this)[FriendViewModel::class.java]
        mRvFriends.adapter = mAdapter
        mRvFriends.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        mRvFriends.setHasFixedSize(true)
        mBtnLogout.setOnClickListener {
            VK.logout()
            mFriendViewModel.deleteAllUsers()
            startActivity(Intent(application, LoginActivity::class.java))
        }
        mBtnFavourites.setOnClickListener {
            startActivity(Intent(application, FavouritesActivity::class.java))
        }
    }

    override fun showError(textResource: Int) {
        mTxtNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        mRvFriends.visibility = View.GONE
        mTxtNoItems.visibility = View.VISIBLE
    }

    override fun setupFriendsList(friendsList: List<FriendsModel>) {
        mRvFriends.visibility = View.VISIBLE
        mTxtNoItems.visibility = View.GONE

        mAdapter.setupFriends(friendList = friendsList)
    }

    override fun startLoading() {
        mRvFriends.visibility = View.GONE
        mCpvWait.visibility = View.VISIBLE
        mTxtNoItems.visibility = View.GONE
    }

    override fun endLoading() {
        mCpvWait.visibility = View.GONE
    }
}