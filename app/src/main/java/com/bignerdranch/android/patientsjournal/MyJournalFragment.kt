package com.bignerdranch.android.patientsjournal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

const val PROGRAM_SELECTION_TAG = "programSelection"
const val LEVEL_PAIN_TAG = "level_pain_tag"
const val REQUEST_PROGRAM = 0
val criteriaStr = listOf<String>("siting", "staying", "walking", "sleeping")
val criteriaAssessmentStr = listOf<String>("siting", "staying", "doing")


class MyJournalFragment:
    Fragment(),
    ProgramSelectionDialogFragment.Callbacks,
    LevelPainSelectorDialogFragment.Callbacks{

    //Объявление переменных
    private lateinit var programSelectionButton: TextView
    private lateinit var percentBarTextView: TextView
    private lateinit var painSeekBar: SeekBar
    private lateinit var painCommentEditText: EditText
    private lateinit var sitingPainSelectionButton: TextView
    private lateinit var stayingPainSelectionButton: TextView
    private lateinit var walkingPainSelectionButton: TextView
    private lateinit var sleepingPainSelectionButton: TextView
    private lateinit var painComment2EditText: EditText
    private lateinit var assessmentCondition1CheckBox: CheckBox
    private lateinit var assessmentCondition2CheckBox: CheckBox
    private lateinit var assessmentCondition3CheckBox: CheckBox
    private lateinit var assessmentConditionEditText: EditText
    private lateinit var radio1Group: RadioGroup
    private lateinit var radio2Group: RadioGroup
    private lateinit var comment7EditText: EditText
    private lateinit var nextButton: Button

    private lateinit var hostActivity: MainActivity

    //Ленивая инициализация ViewModel
    private val myJournalViewModel: MyJournalViewModel by lazy {
        ViewModelProvider(this)[MyJournalViewModel::class.java]
    }

    //определение интерфейса обратного вызова из ProgramSelectionDialogFragment
    override fun onProgramSelected(idButton: Int) {
        myJournalViewModel.patientsAnswer.program = idButton
        updateUI()
    }

    //определение интерфейса обратного вызова из LevelPainSelectionDialogFragment
    override fun onPainSelected(criteria: Int, id: Int) {
        myJournalViewModel.setPainLevelDict(criteriaStr[criteria], id)
        updateUI()
        println("$criteria, $id")
    }


    //инициализация фрагмента с активацией layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_myjournal, container, false)

        //инициализация переменных
        programSelectionButton = view.findViewById(R.id.program_selected_button) as TextView
        percentBarTextView = view.findViewById(R.id.pain_percents_bar) as TextView
        painSeekBar = view.findViewById(R.id.pain_seek_bar) as SeekBar
        painCommentEditText = view.findViewById(R.id.pain_comments) as EditText
        sitingPainSelectionButton = view.findViewById(R.id.sitting_selected_button) as TextView
        stayingPainSelectionButton = view.findViewById(R.id.standing_selected_button) as TextView
        walkingPainSelectionButton = view.findViewById(R.id.walking_selected_button) as TextView
        sleepingPainSelectionButton = view.findViewById(R.id.sleeping_selected_button) as TextView
        painComment2EditText = view.findViewById(R.id.pain_comments_2) as EditText
        assessmentCondition1CheckBox = view.findViewById(R.id.assessmentConditionCheckBox1) as CheckBox
        assessmentCondition2CheckBox = view.findViewById(R.id.assessmentConditionCheckBox2) as CheckBox
        assessmentCondition3CheckBox = view.findViewById(R.id.assessmentConditionCheckBox3) as CheckBox
        assessmentConditionEditText = view.findViewById(R.id.assessmentConditionComment) as EditText
        radio1Group = view.findViewById(R.id.radio_group_1) as RadioGroup
        radio2Group = view.findViewById(R.id.radio_group_2) as RadioGroup
        comment7EditText = view.findViewById(R.id.question7_comment) as EditText
        nextButton = view.findViewById(R.id.buttonNext) as Button

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

        //Слушатели EditText
        painCommentEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                myJournalViewModel.setPainComment(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        painComment2EditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                myJournalViewModel.setPainComment2(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {  }

        } )
        assessmentConditionEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                myJournalViewModel.setPainComment3(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {  }
        })
        comment7EditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                myJournalViewModel.setQuestion7Comment(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {  }
        })

        val question3Buttons = listOf<TextView>(
            sitingPainSelectionButton,
            stayingPainSelectionButton,
            walkingPainSelectionButton,
            sleepingPainSelectionButton
        )
        //Слушатели на кнопки третьего вопроса
        for (i in question3Buttons.indices){
            question3Buttons[i].setOnClickListener{
                //открытие фрагмента диалогового окна при нажатии
                val criteria: Int? = myJournalViewModel.getPainLevelDict(criteriaStr[i])
                if (criteria != null) {
                    LevelPainSelectorDialogFragment.newInstance(i, criteria)
                        .apply {
                            setTargetFragment(this@MyJournalFragment, REQUEST_PROGRAM)

                            show(this@MyJournalFragment.requireFragmentManager(), LEVEL_PAIN_TAG)
                        }
                }
            }
        }

        //Отслеживание нажатия на чекбоксы
        val assessmentConditions = listOf<CheckBox>(
            assessmentCondition1CheckBox,
            assessmentCondition2CheckBox,
            assessmentCondition3CheckBox
        )
        for(i in assessmentConditions.indices){
           assessmentConditions[i].setOnClickListener{
               myJournalViewModel.setAssessmentConditionDict(criteriaAssessmentStr[i])
               println(myJournalViewModel.getAssessmentConditionDict(criteriaAssessmentStr[i]))
           }
        }

        //Слушатели на radioGroup
        radio1Group.setOnCheckedChangeListener{_, checkedId ->
            view?.findViewById<RadioButton>(checkedId).apply {
                this?.let { myJournalViewModel.setRadioGroup1(it.text as String) }
            }
        }
        radio2Group.setOnCheckedChangeListener{_, checkedId ->
            view?.findViewById<RadioButton>(checkedId).apply {
                this?.let { myJournalViewModel.setRadioGroup2(it.text as String) }
            }
        }

        //Слушатель на nextButton
        nextButton.setOnClickListener{
            hostActivity.onFragmentSelected(BodyEditorFrontFragment.newInstance())
        }
    }

    //Функция сбора данных из бд
    private fun getData(): String {
        return "1) Эффективная программа: ${myJournalViewModel.getProgramMode()}\n" +
                "2) Процентное снижение боли: ${myJournalViewModel.getPainLevel()}\n"+
                "3) Комментарий 1: ${myJournalViewModel.getPainComment()}\n" +
                "4) Уровни боли: \n" +
                "\tСидя: ${myJournalViewModel.getPainLevelDict(criteriaStr[0])}\n"+
                "\tСтоя: ${myJournalViewModel.getPainLevelDict(criteriaStr[1])}\n"+
                "\tПри ходьбе: ${myJournalViewModel.getPainLevelDict(criteriaStr[2])}\n"+
                "\tВо время сна: ${myJournalViewModel.getPainLevelDict(criteriaStr[3])}\n" +
                "5) Комментарий 2: ${myJournalViewModel.getPainComment2()}\n" +
                "6) Оценка состояния:\n" +
                "\t Дольше сидеть: ${myJournalViewModel.getAssessmentConditionDict(
                    criteriaAssessmentStr[0])}\n" +
                "\t Дольше идти/стоять: ${myJournalViewModel.getAssessmentConditionDict(
                    criteriaAssessmentStr[1])}\n" +
                "\t Улучшения: ${myJournalViewModel.getAssessmentConditionDict(
                    criteriaAssessmentStr[2])}\n" +
                "7) Комментарий 3: ${myJournalViewModel.getPainComment3()}\n" +
                "8) Ощущения от стимуляции: ${myJournalViewModel.getRadioGroup1()}\n" +
                "9) Эффективность программы: ${myJournalViewModel.getRadioGroup2()}\n" +
                "10) Коментарий 4: ${myJournalViewModel.getQuestion7Comment()}"
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(){

        //Обновление представления первого вопроса
        var programAnswer = myJournalViewModel.getProgramMode()
        if(programAnswer != -1){
            programAnswer++
            programSelectionButton.text = programAnswer.toString()
        }

        //Обновление представления третьего вопроса
        val question3Buttons = listOf<TextView>(
            sitingPainSelectionButton,
            stayingPainSelectionButton,
            walkingPainSelectionButton,
            sleepingPainSelectionButton
        )
        for(i in question3Buttons.indices){
            question3Buttons[i].text = myJournalViewModel.getPainLevelDict(criteriaStr[i]).toString()
        }

    }

    //Отлавливание перехода из MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    //Вызов фрагмента извне
    companion object{
        fun newInstance():MyJournalFragment{
            return MyJournalFragment()
        }
    }
}