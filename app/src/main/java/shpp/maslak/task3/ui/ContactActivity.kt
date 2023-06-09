package shpp.maslak.task3.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import shpp.maslak.task3.R
import shpp.maslak.task3.databinding.ActivityContactBinding
import shpp.maslak.task3.ui.base.BaseActivity

class ContactActivity : BaseActivity<ActivityContactBinding>(ActivityContactBinding::inflate)
{

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.appbar)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }




}



