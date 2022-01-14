package com.example.my_vk.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.my_vk.R
import com.example.my_vk.activities.FavouritesActivity
import com.example.my_vk.db.FriendViewModel
import com.example.my_vk.db.model.Friend
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FavouritesAdapter(val f:FavouritesActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mFriendsList: List<Friend> = listOf()
    private lateinit var mUserViewModel: FriendViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_favourite, parent, false)
        var mIdFriend: TextView = itemView.findViewById(R.id.friend_id)
        val mBtnDel: Button = itemView.findViewById(R.id.btn_del)
        mUserViewModel = ViewModelProvider(f).get(FriendViewModel::class.java)
        mBtnDel.setOnClickListener {
            mFriendsList.forEach {
                if (it.id.toString() == mIdFriend.text){
                    mUserViewModel.deleteUser(it)
                }
            }
        }
        return FavouritesAdapter.FavouritesViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavouritesViewHolder) {
            holder.bind(friendModel =  mFriendsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mFriendsList.count()
    }

    class FavouritesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var mCivAvatar: CircleImageView = itemView.findViewById(R.id.friend_civ_avatar)
        private var mTxtUsername: TextView = itemView.findViewById(R.id.friend_txt_username)
        private var mIdFriend: TextView = itemView.findViewById(R.id.friend_id)

        @SuppressLint("SetTextI18n")
        fun bind(friendModel: Friend) {
            Picasso.get().load(R.drawable.picture).into(mCivAvatar)
            mTxtUsername.text = friendModel.name
            mIdFriend.text = friendModel.id.toString()

        }
    }
    fun setData(user: List<Friend>){
        this.mFriendsList = user
        notifyDataSetChanged()
    }

}