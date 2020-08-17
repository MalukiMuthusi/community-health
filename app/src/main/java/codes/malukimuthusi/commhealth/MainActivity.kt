package codes.malukimuthusi.commhealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import codes.malukimuthusi.commhealth.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.selectedItemId = R.id.item_data_input

        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_activity -> {
                    true
                }
                R.id.item_call -> {
                    true
                }
                R.id.item_disease -> {
                    true
                }
                R.id.item_data_input -> {
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.item_activity -> {
                    true
                }
                R.id.item_call -> {
                    true
                }
                R.id.item_disease -> {
                    true
                }
                R.id.item_data_input -> {
                    true
                }
            }
        }

    }

}