package net.tinat.group.eventen.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import net.tinat.group.eventen.R
import net.tinat.group.eventen.base.BaseSupportActivity
import net.tinat.group.eventen.databinding.ActivityMainBinding
import net.tinat.group.eventen.utils.fadeIn
import net.tinat.group.eventen.utils.fadeOut
import net.tinat.group.eventen.utils.navigationBarAndStatusBarColor

class HomeMainActivity : BaseSupportActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigationVisibility()

        setUpNavigation()

    }

    @SuppressLint("RestrictedApi")
    fun setBottomNavigationVisibility() {
        // get the reference of the bottomNavigationView and set the visibility.
        when (navController.currentDestination?.id) {
            R.id.homeFragment -> {
                binding.bottomAppBar.fadeIn(250, View.VISIBLE)
                binding.fabLayout.fadeIn(250, View.VISIBLE)
            }
            R.id.ticketsFragment -> {
                binding.bottomAppBar.fadeIn(250, View.VISIBLE)
                binding.fabLayout.fadeIn(250, View.VISIBLE)
            }
            R.id.activitiesFragment -> {
                binding.bottomAppBar.fadeIn(250, View.VISIBLE)
                binding.fabLayout.fadeIn(250, View.VISIBLE)
            }
            R.id.profileFragment -> {
                binding.bottomAppBar.fadeIn(250, View.VISIBLE)
                binding.fabLayout.fadeIn(250, View.VISIBLE)
            }
            R.id.splash_screen -> {
                binding.bottomAppBar.fadeOut(250, View.GONE)
                binding.fabLayout.fadeOut(250, View.GONE)
            }
            R.id.onboarding_viewpager -> {
                binding.bottomAppBar.fadeOut(250, View.GONE)
                binding.fabLayout.fadeOut(250, View.GONE)
            }
            R.id.loginFragment -> {
                binding.bottomAppBar.fadeOut(250, View.GONE)
                binding.fabLayout.fadeOut(250, View.GONE)
            }
            R.id.signUpFragment -> {
                binding.bottomAppBar.fadeOut(250, View.GONE)
                binding.fabLayout.fadeOut(250, View.GONE)
            }
        }
    }

    @SuppressLint("RestrictedApi")
    fun setBottomNavigationVisibility(visibility: Int) {

        // get the reference of the bottomNavigationView and set the visibility.
        if (visibility == View.VISIBLE) {
            binding.bottomAppBar.fadeIn(250, View.VISIBLE)
            binding.fabLayout.fadeIn(250, View.VISIBLE)
            binding.fab.fadeIn(250, View.VISIBLE)
        } else if (visibility == View.GONE) {
            binding.fab.fadeOut(250, View.GONE)
            binding.bottomAppBar.fadeOut(250, View.GONE)
            binding.fabLayout.fadeOut(250, View.GONE)
        }

    }

    @SuppressLint("ResourceType")
    private fun setUpNavigation() {



        binding.bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
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

            }
            return@OnNavigationItemSelectedListener false

        })
    }


}