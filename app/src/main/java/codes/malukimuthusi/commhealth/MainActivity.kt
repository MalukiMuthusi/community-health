package codes.malukimuthusi.commhealth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import codes.malukimuthusi.commhealth.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.fragment))

        binding.bottomNavigation.selectedItemId = R.id.basicFormFragment

        auth = FirebaseAuth.getInstance()

        navItemSelected()

        bottomItemReselected()

        login()

    }

    private fun navItemSelected() {
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
    }

    private fun bottomItemReselected() {
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

    private fun login() {
        if (auth.currentUser == null) {
            startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(
                        listOf(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.PhoneBuilder()
                                .setWhitelistedCountries(listOf("+254"))
                                .setDefaultNumber("ke", "01508577")
                                .build()
                        )
                    )
                    .build(),
                RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {


            } else {
                if (response == null) {
                    // user cancelled
                }
            }
        }
    }

    companion object {
        const val RC_SIGN_IN = 8967
    }

}