package shpp.maslak.task3.ui.activities

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import shpp.maslak.task3.databinding.ActivityAuthBinding
import shpp.maslak.task3.ui.base.BaseActivity

class AuthActivity:BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        onBackPressedDispatcher.addCallback(){
//            finish()
//        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return false
    }
}