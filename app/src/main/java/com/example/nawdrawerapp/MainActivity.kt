package com.example.nawdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toogle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(com.example.nawdrawerapp.R.id.drawerLayout)

        toogle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open,
            R.string.close
        )

        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment(), it.title.toString())
                }
                R.id.message -> {
                    replaceFragment(MessageFragment(), it.title.toString())
                }
                R.id.settings -> {
                    replaceFragment(SettingsFragment(), it.title.toString())
                }
                R.id.login -> {
                    replaceFragment(LoginFragment(), it.title.toString())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}