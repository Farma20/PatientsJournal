package com.bignerdranch.android.patientsjournal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MenuPatientsFragment: Fragment() {

    private lateinit var hostActivity: MainActivity

    private lateinit var name:TextView
    private lateinit var status:TextView
    private lateinit var nextJournalTime:TextView

    private lateinit var journalButtonOne1: ImageView
    private lateinit var journalButtonOne2: TextView

    private val menuPatientsViewModel: MenuPatientsViewModel by lazy {
        ViewModelProvider(this)[MenuPatientsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_patients_menu, container, false)

        name = view.findViewById(R.id.name_patient_text) as TextView
        status = view.findViewById(R.id.status_patient_text) as TextView
        nextJournalTime = view.findViewById(R.id.time_patient_text) as TextView

        journalButtonOne1 = view.findViewById(R.id.imageViewButtonJournal) as ImageView
        journalButtonOne2 = view.findViewById(R.id.imageViewButtonJournalText) as TextView

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        name.text = "${menuPatientsViewModel.getUserFirstName()} ${menuPatientsViewModel.getUserSecondName()}"
        status.text = menuPatientsViewModel.getUserStatus()
        nextJournalTime.text = menuPatientsViewModel.getNextJournalTime()

        for(button in listOf(journalButtonOne1, journalButtonOne2)){
            button.setOnClickListener{
                hostActivity.onFragmentSelected(MyJournalFragment.newInstance())
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        hostActivity = context as MainActivity

    }

    companion object{
        fun newInstance(): MenuPatientsFragment{
            return MenuPatientsFragment()
        }
    }
}