package com.kaustubh.recordkeeper.running

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.kaustubh.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRunningRecordBinding
    // we can just use binding for all classes as these are private, so they wont cause any problem

    // as the code first went to onCreate block, we now have the context which we didn't have if we used late init because late init would have run before the onCreate block
    private val runningPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            "running",
            Context.MODE_PRIVATE
        )
    }

    private val distance: String? by lazy { intent.getStringExtra("Distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        displayRecord()
    }

    private fun setupUI() {
        // setting dynamic toolbar
        title = "$distance Record"

        //        val applicationPreferences =
        //            androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        //        applicationPreferences.edit {
        //            putString("Some application data", "Some application value")
        //        }
        //
        //        val activityPreferences = getPreferences(Context.MODE_PRIVATE)
        //        activityPreferences.edit {
        //            putInt("Some activity data", 29)
        //        }
        //
        //        // here we can make files of names that we want (useful where we want to specifically clear/edit a particular division of the app)
        //        // we will be using this for our Record Keeper App
        //        val fileNamePreferences =
        //            getSharedPreferences("some shared preference file name", Context.MODE_PRIVATE)
        //        fileNamePreferences.edit {
        //            putBoolean("Some preference file name data", true)
        //        }

        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun displayRecord() {
        binding.editTextRecord.setText(runningPreferences.getString("$distance record", null))
        binding.editTextDate.setText(runningPreferences.getString("$distance date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

//        val editor=runningPreferences.edit()
//        editor.putString("record",record)
//        editor.putString("date",date)
//        editor.apply()
        runningPreferences.edit {
            putString("$distance record", record)
            putString("$distance date", date)
        }
    }

    private fun clearRecord() {
        runningPreferences.edit {
            remove("$distance record")
            remove("$distance date")
        }
    }
}