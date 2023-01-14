package com.bignerdranch.android.patientsjournal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MyJournalFragment: Fragment() {

    //инициализация фрашмента с активацией layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_myjournal, container, false)
        return view
    }

    //запуск фрагмента. Фрагмент отрисовывается на экране
    override fun onStart() {
        super.onStart()
    }

    //Отлавливание перехода из MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    //Вызов фрагмента извне
    companion object{
        fun newInstance():MyJournalFragment{
            return MyJournalFragment()
        }
    }
}