package com.bignerdranch.android.patientsjournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //скрытие шапки приложения
        supportActionBar?.hide()

        //Проверка на наличие фрагмента в контейнере
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        //Транзакция начального фрагмента в activity_main.xml
        if(currentFragment == null){
            val fragment = MyJournalFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
}