package com.bignerdranch.android.patientsjournal

import androidx.lifecycle.ViewModel

class MenuPatientsViewModel: ViewModel() {
    var user = UserData()

    fun getUserFirstName():String{
        return user.firstName
    }

    fun getUserSecondName():String{
        return user.secondName
    }

    fun getUserStatus():String{
        return user.userStatus
    }

    fun getNextJournalTime():String{
        return user.nextJournalTime
    }
}