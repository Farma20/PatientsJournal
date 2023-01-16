package com.bignerdranch.android.patientsjournal

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class ProgramSelectionDialogFragment:DialogFragment() {

    private val programNumbers = arrayOf(
        "Программа 1",
        "Программа 2",
        "Программа 3",
        "Программа 4",
        "Программа 5"
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //вызов диалогового окна
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выберите режим")
            builder.setSingleChoiceItems(programNumbers, -1){ _, _ ->}
            builder.setPositiveButton("OK"){ _, _ ->}
            builder.create()
        }?: throw java.lang.IllegalStateException("Activity not bee null")
    }

companion object{
    fun newInstance(): ProgramSelectionDialogFragment{
        return ProgramSelectionDialogFragment()
    }
}
}