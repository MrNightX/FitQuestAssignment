package com.example.fitquest.ui.Workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitquest.R

class PresetRoutine : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preset_routine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize UI elements
        val titleTextView: TextView = view.findViewById(R.id.textViewTitleRoutine)
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)

        // Set an OnCheckedChangeListener to handle radio button selection
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton: RadioButton = view.findViewById(checkedId)
            // Handle radio button selection
            // For example, you can show a toast or perform an action based on the selected radio button
        }
    }
}
