package codes.malukimuthusi.commhealth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import codes.malukimuthusi.commhealth.databinding.ActivityMainBinding
import codes.malukimuthusi.commhealth.recentActivity.RecentActivityFragmentDirections
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
                    .setLogo(R.drawable.ic_baseline_healing_24)
                    .setAvailableProviders(
                        getAvailableProviders()
                    ).build(),
                RC_SIGN_IN
            )
        }
    }

    private fun getAvailableProviders(): List<AuthUI.IdpConfig> {
        return listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder()
                .setWhitelistedCountries(listOf("+254"))
                .setDefaultNumber("ke", "0790497466")
                .build()
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // show login success message
                Toast.makeText(this, "You are logged in", Toast.LENGTH_SHORT).show()

                findNavController(R.id.fragment).navigate(RecentActivityFragmentDirections.actionRecentActivityFragmentToSignUpFragment())

            } else {
                if (response == null) {
                    // user cancelled
                    Toast.makeText(this, "Failed to Log In", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val RC_SIGN_IN = 8967
        const val PROFILE_IMAGE_URI = "profile_image_uri"
        const val SHARED_PREF_NAME = "name_of_chw"
        const val SHARED_PREF_LOCATION = "location_of_chw"
    }

}