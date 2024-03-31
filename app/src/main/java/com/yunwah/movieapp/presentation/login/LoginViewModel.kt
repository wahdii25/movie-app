package com.yunwah.movieapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunwah.movieapp.domain.usecase.appEntry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    private val username = "VVVBB"
    val hashedPassword = "\$2a\$10\$umjkYTBFZNBjHdNSx.3mVe/xSRLW77vrGr7N5hRtyxpXuLlHl17rO"

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.SaveAppEntry -> {
                saveUserEntry()
            }
        }
    }

    private fun saveUserEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }

    fun checkPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }

    fun checkUsername(username: String): Boolean {
        return username == this.username
    }
}