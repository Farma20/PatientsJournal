package com.bignerdranch.android.patientsjournal

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

private const val ID_PAIN_LEVEL_TAG = "pain_level"

class LevelPainSelectorDialogFragment: DialogFragment() {
    private val painLevel = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Достаем отправленный данный из родительского фрагмента
        var idButton = arguments?.getInt(ID_PAIN_LEVEL_TAG) as Int

        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выберите режим")
            builder.setSingleChoiceItems(painLevel, idButton){ _, id ->
                idButton = id
            }
            builder.setPositiveButton("OK"){ _, _ ->
                targetFragment?.let{fragment ->
                    (fragment as ProgramSelectionDialogFragment.Callbacks).onProgramSelected(idButton)
                }
            }
            builder.create()
        }?: throw java.lang.IllegalStateException("Activity not bee null")
    }

    companion object{
        fun newInstance(id: Int): ProgramSelectionDialogFragment{
            val args = Bundle().apply {
                putInt(ID_PAIN_LEVEL_TAG, id)
            }

            return ProgramSelectionDialogFragment().apply {
                arguments = args
            }
        }
    }
}