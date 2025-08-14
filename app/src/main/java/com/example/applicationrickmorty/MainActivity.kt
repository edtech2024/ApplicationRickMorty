package com.example.applicationrickmorty

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.example.applicationrickmorty.presentation.fragment.DetailFragment
import com.example.applicationrickmorty.presentation.fragment.MainFragment

class MainActivity : AppCompatActivity(),
    MainFragment.OnItemOpenListener,
    DetailFragment.OnItemCloseListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // using toolbar as ActionBar

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        switchHamburger()

        if (savedInstanceState == null) {
            // Let's first dynamically add a fragment into a frame container
            transactionToMainFragment()
        }

    }

    private fun switchHamburger(){
        toggle.isDrawerIndicatorEnabled = true // Чтобы получить значок гамбургера, установим индикатор
    }

    private fun switchBack(){
        toggle.isDrawerIndicatorEnabled = false

        toggle.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        toggle.setToolbarNavigationClickListener() {

            onBackPressed()

            switchHamburger()
        }
    }

    override fun onOpenItem(open: Bundle) {

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentDetail: DetailFragment = DetailFragment.newInstance(
            open.getInt(applicationContext.getString(R.string.id)),
            open.getString(applicationContext.getString(R.string.type)),
            open.getString(applicationContext.getString(R.string.name)),
            open.getString(applicationContext.getString(R.string.gender)),
            open.getString(applicationContext.getString(R.string.status)),
            open.getString(applicationContext.getString(R.string.image)),
            open.getString(applicationContext.getString(R.string.species)),
            open.getString(applicationContext.getString(R.string.created)),
            open.getString(applicationContext.getString(R.string.episode)),
            open.getString(applicationContext.getString(R.string.location)),
            open.getString(applicationContext.getString(R.string.origin)),
            open.getString(applicationContext.getString(R.string.url)),
        )
        fragmentTransaction.replace(R.id.container, fragmentDetail) // контейнер в активити
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        switchBack()
    }

    // Now we can define the action to take in the activity when the fragment event fires
    // This is implementing the `OnItemCreateUpdateListener` interface methods
    override fun onCloseItem(){
        transactionToMainFragment()
    }

    private fun transactionToMainFragment(){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentMain: MainFragment = MainFragment.newInstance()
        fragmentTransaction.replace(R.id.container, fragmentMain) // контейнер в активити
        fragmentTransaction.commit()

        switchHamburger()
    }

}