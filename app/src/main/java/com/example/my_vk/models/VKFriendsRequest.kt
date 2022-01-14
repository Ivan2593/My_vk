package com.example.my_vk.models


import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKFriendsRequest(uid: Int = 0) : VKRequest<List<FriendsModel>>("friends.get") {
    init {
        if (uid != 0) {
            addParam("user_id", uid)
        }
        addParam("fields", " photo_100, online")
    }

    override fun parse(r: JSONObject): List<FriendsModel> {
        val users = r.getJSONObject("response").getJSONArray("items")
        val result = ArrayList<FriendsModel>()
        for (i in 0 until users.length()) {
            result.add(FriendsModel.parse(users.getJSONObject(i)))
        }
        return result
    }
}