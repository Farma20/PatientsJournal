package com.bignerdranch.android.patientsjournal

import android.location.Criteria
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

    //Гетеры и сетеры для уровней боли во время определенных действий
    fun setPainLevelDict(criteria: String, level: Int){
        patientsAnswer.painLevelDict[criteria] = level
    }
    fun getPainLevelDict(criteria: String): Int? {
        return patientsAnswer.painLevelDict[criteria]
    }

    //Гетеры и сетеры для комментария2 к уровню боли
    fun setPainComment2(comment: String){
        patientsAnswer.painLevelComment = comment
    }
    fun getPainComment2():String{
        return patientsAnswer.painLevelComment
    }

    //Гетеры и сетеры для изменения оценки состояния
    fun  setAssessmentConditionDict(criteria: String){
        patientsAnswer.assessmentConditionDict[criteria] =
            !patientsAnswer.assessmentConditionDict[criteria]!!
    }
    fun getAssessmentConditionDict(criteria: String): Boolean? {
        return patientsAnswer.assessmentConditionDict[criteria]
    }

    //Гетеры и сетеры для комментария3 к оценке состояния
    fun setPainComment3(comment: String){
        patientsAnswer.assessmentConditionComment = comment
    }
    fun getPainComment3():String{
        return patientsAnswer.assessmentConditionComment
    }

    //Геттеры и сеттеры для radioGroup1
    fun setRadioGroup1(text: String){
        patientsAnswer.questionFiveRadio = text
    }
    fun getRadioGroup1(): String{
        return patientsAnswer.questionFiveRadio
    }

    //Геттеры и сеттеры для radioGroup2
    fun setRadioGroup2(text: String){
        patientsAnswer.questionSixRadio = text
    }
    fun getRadioGroup2(): String{
        return patientsAnswer.questionSixRadio
    }

    //Геттеры и сеттеры для коментария к вопросу 7
    fun setQuestion7Comment(comment: String){
        patientsAnswer.questionSevenEditText = comment
    }
    fun getQuestion7Comment():String{
        return patientsAnswer.questionSevenEditText
    }

}