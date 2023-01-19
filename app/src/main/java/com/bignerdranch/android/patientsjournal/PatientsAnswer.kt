package com.bignerdranch.android.patientsjournal

data class PatientsAnswer(
    var program: Int = -1,
    var painBar:Int = 0,
    var painComment:String = "",
    var painLevelDict: MutableMap<String, Int> = mutableMapOf(
        "siting" to 0,
        "staying" to 0,
        "walking" to 0,
        "sleeping" to 0
        )
    )