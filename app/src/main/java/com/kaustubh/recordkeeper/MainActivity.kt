package com.kaustubh.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.kaustubh.recordkeeper.databinding.ActivityMainBinding
import com.kaustubh.recordkeeper.football.FootballFragment
import com.kaustubh.recordkeeper.running.RunningFragment

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // the first add fragment transaction is skipped because we are using the fragment container view to set the first fragment

//        supportFragmentManager.commit {
//            add(R.id.frame_content, RunningFragment())
//        }

        bindingMain.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.reset_running -> {
            Toast.makeText(this, "Running Record Reset Clicked!", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.reset_football -> {
            Toast.makeText(this, "Football Record Reset Clicked!", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.reset_all -> {
            Toast.makeText(this, "All Record Reset Clicked!", Toast.LENGTH_SHORT).show()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun onFootballClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, FootballFragment())
        }
        return true
    }

    private fun onRunningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_running -> onRunningClicked()
        R.id.nav_football -> onFootballClicked()
        else -> false
    }
}