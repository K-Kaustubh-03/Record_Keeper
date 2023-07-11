package com.kaustubh.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaustubh.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private lateinit var bindingRunning: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingRunning = FragmentRunningBinding.inflate(inflater, container, false)
        return bindingRunning.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences =
            requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)
        bindingRunning.textView5kmValue.text = runningPreferences.getString("5km record", null)
        bindingRunning.textView5kmDate.text = runningPreferences.getString("5km date", null)
        bindingRunning.textView10kmValue.text = runningPreferences.getString("10km record", null)
        bindingRunning.textView10kmDate.text = runningPreferences.getString("10km date", null)
        bindingRunning.textViewHalfMarathonValue.text =
            runningPreferences.getString("Half Marathon record", null)
        bindingRunning.textViewHalfMarathonDate.text =
            runningPreferences.getString("Half Marathon date", null)
        bindingRunning.textViewFullMarathonValue.text =
            runningPreferences.getString("Marathon record", null)
        bindingRunning.textViewFullMarathonDate.text =
            runningPreferences.getString("Marathon date", null)
    }

    private fun setupClickListeners() {
        bindingRunning.container5km.setOnClickListener { launchRunningRecordScreen("5km") }
        bindingRunning.container10km.setOnClickListener { launchRunningRecordScreen("10km") }
        bindingRunning.containerHalfMarathon.setOnClickListener { launchRunningRecordScreen("Half Marathon") }
        bindingRunning.containerFullMarathon.setOnClickListener { launchRunningRecordScreen("Marathon") }
    }

    private fun launchRunningRecordScreen(distance: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }
}