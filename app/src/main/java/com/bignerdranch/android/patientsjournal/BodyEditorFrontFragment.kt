package com.bignerdranch.android.patientsjournal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class BodyEditorFrontFragment: Fragment() {

    private lateinit var hostActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_body_editor, container, false)


        return view
    }


    override fun onStart() {
        super.onStart()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = context as MainActivity
    }

    companion object{
        fun newInstance(): BodyEditorFrontFragment{
            return BodyEditorFrontFragment()
        }
    }
}