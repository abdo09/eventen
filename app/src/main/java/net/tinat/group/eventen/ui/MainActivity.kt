package net.tinat.group.eventen.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportActivity
import net.tinat.group.eventen.utils.fadeIn
import net.tinat.group.eventen.utils.fadeOut

class MainActivity : BaseSupportActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomNavigationVisibility()

        setUpNavigation()

    }

    @SuppressLint("RestrictedApi")
    fun setBottomNavigationVisibility() {
        // get the reference of the bottomNavigationView and set the visibility.
        when (NavHostFragment.findNavController(evenTenNavHostFragment).currentDestination?.id) {
            R.id.homeFragment -> {
                bottom_app_bar.fadeIn(250, View.VISIBLE)
                fab_layout.fadeIn(250, View.VISIBLE)
            }
            R.id.ticketsFragment -> {
                bottom_app_bar.fadeIn(250, View.VISIBLE)
                fab_layout.fadeIn(250, View.VISIBLE)
            }
            R.id.activitiesFragment -> {
                bottom_app_bar.fadeIn(250, View.VISIBLE)
                fab_layout.fadeIn(250, View.VISIBLE)
            }
            R.id.profileFragment -> {
                bottom_app_bar.fadeIn(250, View.VISIBLE)
                fab_layout.fadeIn(250, View.VISIBLE)
            }
            R.id.splash_screen -> {
                bottom_app_bar.fadeOut(250, View.GONE)
                fab_layout.fadeOut(250, View.GONE)
            }
            R.id.onboarding_viewpager -> {
                bottom_app_bar.fadeOut(250, View.GONE)
                fab_layout.fadeOut(250, View.GONE)
            }
            R.id.loginFragment -> {
                bottom_app_bar.fadeOut(250, View.GONE)
                fab_layout.fadeOut(250, View.GONE)
            }
            R.id.signUpFragment -> {
                bottom_app_bar.fadeOut(250, View.GONE)
                fab_layout.fadeOut(250, View.GONE)
            }
        }
    }

    @SuppressLint("RestrictedApi")
    fun setBottomNavigationVisibility(visibility: Int) {

        // get the reference of the bottomNavigationView and set the visibility.
        if (visibility == View.VISIBLE){
            bottom_app_bar.fadeIn(250, View.VISIBLE)
            fab_layout.fadeIn(250, View.VISIBLE)
            fab.fadeIn(250, View.VISIBLE)
        } else if (visibility == View.GONE){
            fab.fadeOut(250, View.GONE)
            bottom_app_bar.fadeOut(250, View.GONE)
            fab_layout.fadeOut(250, View.GONE)
        }

    }

    private fun setUpNavigation() {
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        fab.setOnClickListener {

        }

        bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.navigation_home -> {

                    findNavController(R.id.evenTenNavHostFragment).navigate(R.id.homeFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_activities -> {
                    findNavController(R.id.evenTenNavHostFragment).navigate(R.id.activitiesFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_tickets -> {
                    findNavController(R.id.evenTenNavHostFragment).navigate(R.id.ticketsFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_profile -> {
                    findNavController(R.id.evenTenNavHostFragment).navigate(R.id.profileFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_hidden -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener false

        })
    }


}