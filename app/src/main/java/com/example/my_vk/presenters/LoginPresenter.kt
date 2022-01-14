package com.example.my_vk.presenters

import android.content.Intent
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.my_vk.R
import com.example.my_vk.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback


@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {
    private lateinit var mHandler: Handler
    fun loginVk(requestCode: Int, resultCode: Int, data: Intent?) : Boolean {
        if (!VK.onActivityResult(requestCode = requestCode, resultCode = resultCode, data = data, callback = object :
                VKAuthCallback {

                override fun onLogin(token: VKAccessToken) {
                    viewState.openFriends()
                }

                override fun onLoginFailed(errorCode: Int) {
                    viewState.showError(textResource = R.string.login_error_credentials)
                }
            })){
            return false
        }
        return true
    }

}