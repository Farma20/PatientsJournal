package com.bignerdranch.android.patientsjournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //скрытие шапки приложения
        supportActionBar?.hide()

        //отключение темной темы
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //Проверка на наличие фрагмента в контейнере
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        //Транзакция начального фрагмента в activity_main.xml
        if(currentFragment == null){
            val fragment = MyJournalFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }

    //Функция для смены фрагмента
    fun onFragmentSelected(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}