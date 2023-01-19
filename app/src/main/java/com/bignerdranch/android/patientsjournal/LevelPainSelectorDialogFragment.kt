package com.bignerdranch.android.patientsjournal

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
private const val ID_CRITERIA_TAG = "criteria"
private const val ID_PAIN_LEVEL_TAG = "pain_level"

class LevelPainSelectorDialogFragment: DialogFragment() {
    private val painLevel = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

    interface Callbacks{
        fun onPainSelected(criteria: Int, id: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Достаем отправленный данный из родительского фрагмента
        var criteria = arguments?.getInt(ID_CRITERIA_TAG) as Int
        var idCriteria = arguments?.getInt(ID_PAIN_LEVEL_TAG) as Int

        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выберите уровень боли")
            builder.setSingleChoiceItems(painLevel, idCriteria){ _, id ->
                idCriteria = id
            }
            builder.setPositiveButton("OK"){ _, _ ->
                targetFragment?.let{fragment ->
                    (fragment as LevelPainSelectorDialogFragment.Callbacks)
                        .onPainSelected(criteria, idCriteria)
                }
            }
            builder.create()
        }?: throw java.lang.IllegalStateException("Activity not bee null")
    }

    companion object{
        fun newInstance(criteria: Int, id: Int): LevelPainSelectorDialogFragment{
            val args = Bundle().apply {
                putInt(ID_CRITERIA_TAG, criteria)
                putInt(ID_PAIN_LEVEL_TAG, id)
            }

            return LevelPainSelectorDialogFragment().apply {
                arguments = args
            }
        }
    }
}