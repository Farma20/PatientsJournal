package com.bignerdranch.android.patientsjournal

import androidx.lifecycle.ViewModel

class MyJournalViewModel: ViewModel() {

    //переменная, хранящая в себе данные ответов
    val patientsAnswer = PatientsAnswer()

    //Фукнкция для смены режима программы
    fun changeProgramMode(id: Int){
        patientsAnswer.program = id
    }

}