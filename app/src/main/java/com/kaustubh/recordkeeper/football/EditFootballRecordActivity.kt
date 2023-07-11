package com.kaustubh.recordkeeper.football

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaustubh.recordkeeper.databinding.ActivityEditFootballRecordBinding
import com.kaustubh.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditFootballRecordActivity : AppCompatActivity() {

    private lateinit var binding:ActivityEditFootballRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditFootballRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val record= intent.getStringExtra("Record")

        title="$record Record"
    }
}