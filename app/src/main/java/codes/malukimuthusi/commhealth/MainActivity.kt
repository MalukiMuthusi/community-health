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

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.fragment))

        binding.bottomNavigation.selectedItemId = R.id.basicFormFragment

        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.recentActivityFragment -> {
                    true
                }
                R.id.callHelpFragment -> {
                    true
                }
                R.id.geoCategoryFragment -> {
                    true
                }
                R.id.basicFormFragment -> {
//                    findNavController(R.id.fragment).navigate()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.recentActivityFragment -> {
                    true
                }
                R.id.callHelpFragment -> {
                    true
                }
                R.id.geoCategoryFragment -> {
                    true
                }
                R.id.basicFormFragment -> {
                    true
                }
            }
        }

    }

}