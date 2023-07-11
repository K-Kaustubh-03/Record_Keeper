package com.kaustubh.recordkeeper.editrecord

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.kaustubh.recordkeeper.databinding.ActivityEditRecordBinding
import com.kaustubh.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    // we can just use binding for all classes as these are private, so they wont cause any problem

    private val screenData:ScreenData by lazy {

    }

    // as the code first went to onCreate block, we now have the context which we didn't have if we used late init because late init would have run before the onCreate block
    private val recordPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            "running",
            Context.MODE_PRIVATE
        )
    }

    private val record: String? by lazy { intent.getStringExtra("Distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        displayRecord()
    }

    private fun setupUI() {
        // setting dynamic toolbar
        title = "$record Record"

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
        binding.editTextRecord.setText(recordPreferences.getString("$record record", null))
        binding.editTextDate.setText(recordPreferences.getString("$record date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

//        val editor=runningPreferences.edit()
//        editor.putString("record",record)
//        editor.putString("date",date)
//        editor.apply()
        recordPreferences.edit {
            putString("$record record", record)
            putString("$record date", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("$record record")
            remove("$record date")
        }
    }

    data class ScreenData(
        val record:String,
        val sharedPreferencesName: String
        val recordFieldHint:String
    )

}