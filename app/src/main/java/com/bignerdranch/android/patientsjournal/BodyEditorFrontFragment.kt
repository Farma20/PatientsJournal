package com.bignerdranch.android.patientsjournal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

class BodyEditorFrontFragment: Fragment() {

    private lateinit var hostActivity: MainActivity

    private lateinit var left_hand: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_body_editor_front, container, false)

        left_hand = view.findViewById(R.id.left_hand) as ImageView

        return view
    }


    override fun onStart() {
        super.onStart()

        left_hand.setOnClickListener{
            it.alpha = if (it.alpha == 0.0f){
                1.0f
            }else{
                0.0f
            }
            println("alpha is 1 ${it.alpha}")
        }
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