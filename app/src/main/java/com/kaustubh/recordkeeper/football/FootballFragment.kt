package com.kaustubh.recordkeeper.football

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaustubh.recordkeeper.databinding.FragmentFootballBinding

class FootballFragment : Fragment() {

    private lateinit var binding: FragmentFootballBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFootballBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.containerTopSpeed.setOnClickListener { launchFootballRecordScreen("Top Speed") }
        binding.containerMostGoals.setOnClickListener { launchFootballRecordScreen("Most Goals") }
        binding.containerLongestGoal.setOnClickListener { launchFootballRecordScreen("Longest Goal") }
    }

    private fun launchFootballRecordScreen(s: String) {
        var intent = Intent(context, EditFootballRecordActivity::class.java)
        intent.putExtra("Record", s)
        startActivity(intent)
    }
}