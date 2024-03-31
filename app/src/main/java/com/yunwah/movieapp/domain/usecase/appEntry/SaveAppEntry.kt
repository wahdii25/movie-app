package com.yunwah.movieapp.domain.usecase.appEntry

import com.yunwah.movieapp.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localUserManger: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }

}