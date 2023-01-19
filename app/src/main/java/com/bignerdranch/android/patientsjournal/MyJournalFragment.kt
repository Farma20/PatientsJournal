package com.bignerdranch.android.patientsjournal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

const val PROGRAM_SELECTION_TAG = "programSelection"
const val REQUEST_PROGRAM = 0


class MyJournalFragment: Fragment(), ProgramSelectionDialogFragment.Callbacks {

    private lateinit var programSelectionButton: TextView
    private lateinit var percentBarTextView: TextView
    private lateinit var painSeekBar: SeekBar
    private lateinit var painCommentEditText: EditText
    private lateinit var sitingPainSelectionButton: TextView
    private lateinit var stayingPainSelectionButton: TextView
    private lateinit var walkingPainSelectionButton: TextView
    private lateinit var sleepingPainSelectionButton: TextView

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
        percentBarTextView = view.findViewById(R.id.pain_percents_bar) as TextView
        painSeekBar = view.findViewById(R.id.pain_seek_bar) as SeekBar
        painCommentEditText = view.findViewById(R.id.pain_comments) as EditText
        sitingPainSelectionButton = view.findViewById(R.id.sitting_selected_button) as TextView
        stayingPainSelectionButton = view.findViewById(R.id.standing_selected_button) as TextView
        walkingPainSelectionButton = view.findViewById(R.id.walking_selected_button) as TextView
        sleepingPainSelectionButton = view.findViewById(R.id.sleeping_selected_button) as TextView

        return view
    }

    //запуск фрагмента. Фрагмент отрисовывается на экране
    override fun onStart() {
        super.onStart()

        //Обновление интерфейса из локальной базы данных
        updateUI()

        //слушатель на кнопку выбора режима
        programSelectionButton.setOnClickListener{
            //открытие фрагмента диалогового окна при нажатии
            ProgramSelectionDialogFragment.newInstance(myJournalViewModel.patientsAnswer.program)
                .apply {
                    setTargetFragment(this@MyJournalFragment, REQUEST_PROGRAM)

                    show(this@MyJournalFragment.requireFragmentManager(), PROGRAM_SELECTION_TAG)
                }
        }

        percentBarTextView.text = "${myJournalViewModel.getPainLevel()}"
        //Слушатель на painSeekBar
        painSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val percents = progress * 5
                percentBarTextView.text = "$percents%"
                myJournalViewModel.setPainLevel(percents)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {    }
        })

        //Слушатель painEditText
        painCommentEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                myJournalViewModel.setPainComment(p0.toString())
                println(myJournalViewModel.getPainComment())
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        //Слушатели на кнопки третьего вопроса
        val question3Buttons = listOf<TextView>(
            sitingPainSelectionButton,
            stayingPainSelectionButton,
            walkingPainSelectionButton,
            sleepingPainSelectionButton
        )

        for (elem in question3Buttons){
            elem.setOnClickListener{
                //открытие фрагмента диалогового окна при нажатии
                ProgramSelectionDialogFragment.newInstance(myJournalViewModel.patientsAnswer.program)
                    .apply {
                        setTargetFragment(this@MyJournalFragment, REQUEST_PROGRAM)

                        show(this@MyJournalFragment.requireFragmentManager(), PROGRAM_SELECTION_TAG)
                    }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(){
        var programAnswer = myJournalViewModel.patientsAnswer.program
        if(programAnswer != -1){
            programAnswer++
            programSelectionButton.text = programAnswer.toString()
        }

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