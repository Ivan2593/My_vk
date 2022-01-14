package com.example.my_vk.adapters

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.my_vk.R
import com.example.my_vk.activities.FriendsActivity
import com.example.my_vk.db.FriendViewModel
import com.example.my_vk.db.model.Friend
import com.example.my_vk.models.FriendsModel
import de.hdodenhof.circleimageview.CircleImageView
import com.squareup.picasso.Picasso

class FriendsAdapter(val f:FriendsActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var mFriendsList: ArrayList<FriendsModel> = ArrayList()
    private var mSourceList: ArrayList<FriendsModel> = ArrayList()
    private lateinit var mUserViewModel: FriendViewModel

    fun setupFriends(friendList: List<FriendsModel>){
        mSourceList.clear()
        mSourceList.addAll(friendList)
        filter(query = "")
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if ("${it.firstName} ${it.lastName}".contains(query, ignoreCase = true) || "${it.lastName} ${it.firstName}".contains(query, ignoreCase = true)) {
                mFriendsList.add(it)
            }
        }
        mFriendsList.sortBy { it.firstName }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)
        val mBtnAdd: Button = itemView.findViewById(R.id.btn_add)
        val mTxtUsername: TextView = itemView.findViewById(R.id.friend_txt_username)
        val mTxtCity: TextView = itemView.findViewById(R.id.friend_txt_city)
        mUserViewModel = ViewModelProvider(f).get(FriendViewModel::class.java)
        mBtnAdd.setOnClickListener {
            val user = Friend(
                itemView.tag as Int,
            mTxtUsername.text.toString()
        )
            mUserViewModel.addFriend(user)

        }
        return FriendsViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendsViewHolder) {
            holder.bind(friendModel =  mFriendsList[position])

        }
    }

    override fun getItemCount(): Int {
        return mFriendsList.count()
    }

    class FriendsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var mCivAvatar: CircleImageView = itemView.findViewById(R.id.friend_civ_avatar)
        private var mTxtUsername: TextView = itemView.findViewById(R.id.friend_txt_username)
        private var mImgOnline: View = itemView.findViewById(R.id.friend_img_online)
        @SuppressLint("SetTextI18n")
        fun bind(friendModel: FriendsModel) {
            friendModel.photo.let {
                    url ->
                Picasso.get().load(url).into(mCivAvatar)
            }
            itemView.tag = friendModel.id
            mTxtUsername.text = "${friendModel.firstName} ${friendModel.lastName}"
            if (friendModel.isOnline == 1) {
                mImgOnline.visibility = View.VISIBLE
            } else {
                mImgOnline.visibility = View.GONE
            }
        }

    }
}