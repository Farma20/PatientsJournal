package com.bignerdranch.android.patientsjournal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

const val PROGRAM_SELECTION_TAG = "programSelection"
const val REQUEST_PROGRAM = 0


class MyJournalFragment: Fragment(), ProgramSelectionDialogFragment.Callbacks {

    private lateinit var programSelectionButton: TextView

    //Ленивая инициализация ViewModel
    private val myJournalViewModel: MyJournalViewModel by lazy {
        ViewModelProvider(this)[MyJournalViewModel::class.java]
    }

    //определение интерфейса обратного вызова из ProgramSelectionDialogFragment
    override fun onProgramSelected(idButton: Int) {
        myJournalViewModel.patientsAnswer.program = idButton
        updateUI()
    }


    //инициализация фрагмента с активацией layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_myjournal, container, false)

        programSelectionButton = view.findViewById(R.id.program_selected_button) as TextView

        return view
    }

    //запуск фрагмента. Фрагмент отрисовывается на экране
    override fun onStart() {
        super.onStart()

        //слушатель на кнопку выбора режима
        programSelectionButton.setOnClickListener{
            //открытие фрагмента диалогового окна при нажатии
            ProgramSelectionDialogFragment.newInstance(myJournalViewModel.patientsAnswer.program)
                .apply {
                    setTargetFragment(this@MyJournalFragment, REQUEST_PROGRAM)

                    show(this@MyJournalFragment.requireFragmentManager(), PROGRAM_SELECTION_TAG)
                }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(){
        val programAnswer = myJournalViewModel.patientsAnswer.program + 1
        programSelectionButton.text = programAnswer.toString()
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