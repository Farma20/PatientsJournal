package com.bignerdranch.android.patientsjournal

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

private const val ID_PROGRAM_TAG = "program_tag"

class ProgramSelectionDialogFragment:DialogFragment() {

    private val programNumbers = arrayOf(
        "Программа 1",
        "Программа 2",
        "Программа 3",
        "Программа 4",
        "Программа 5"
    )

    //интерфейс обратнго вызова для передачи данных в родительский фрагмент
    interface Callbacks{
        fun onProgramSelected(idButton: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //Достаем отправленный данный из родительского фрагмента
        var idButton = arguments?.getInt(ID_PROGRAM_TAG) as Int


        //вызов диалогового окна
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выберите режим")
            builder.setSingleChoiceItems(programNumbers, idButton){ _, id ->
                idButton = id
            }
            builder.setPositiveButton("OK"){ _, _ ->
                targetFragment?.let{fragment ->
                    (fragment as Callbacks).onProgramSelected(idButton)
                }
            }
            builder.create()
        }?: throw java.lang.IllegalStateException("Activity not bee null")
    }

companion object{
    fun newInstance(id: Int): ProgramSelectionDialogFragment{
        val args = Bundle().apply {
            putInt(ID_PROGRAM_TAG, id)
        }

        return ProgramSelectionDialogFragment().apply {
            arguments = args
        }
    }
}
}