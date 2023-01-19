package com.bignerdranch.android.patientsjournal

import androidx.lifecycle.ViewModel

class MyJournalViewModel: ViewModel() {

    //переменная, хранящая в себе данные ответов
    val patientsAnswer = PatientsAnswer()

    //Гетеры и сетеры для режима программы
    fun setProgramMode(id: Int){
        patientsAnswer.program = id
    }
    fun getProgramMode(): Int {
        return patientsAnswer.program
    }

    //Гетеры и сетеры для уровня боли
    fun setPainLevel(percents: Int){
        patientsAnswer.painBar = percents
    }
    fun getPainLevel(): Int{
        return patientsAnswer.painBar
    }

    //Гетеры и сетеры для комментария к уровню боли
    fun setPainComment(comment: String){
        patientsAnswer.painComment = comment
    }
    fun getPainComment():String{
        return patientsAnswer.painComment
    }

}