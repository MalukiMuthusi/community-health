package codes.malukimuthusi.commhealth.recentActivity

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import codes.malukimuthusi.commhealth.MainActivity
import codes.malukimuthusi.commhealth.R
import codes.malukimuthusi.commhealth.databinding.RecentActivityFragmentBinding

class RecentActivityFragment : Fragment() {

    companion object {
        fun newInstance() = RecentActivityFragment()
    }

    private lateinit var viewModel: RecentActivityViewModel
    private lateinit var binding: RecentActivityFragmentBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecentActivityFragmentBinding.inflate(inflater, container, false)

        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        val uri = sharedPref.getString(MainActivity.PROFILE_IMAGE_URI, "")
        if (!uri.isNullOrBlank()) {
            binding.imageProfile.setImageURI(Uri.parse(uri))
        }

        binding.nameText.text = sharedPref.getString(MainActivity.SHARED_PREF_NAME, "")
        binding.locationText.text = sharedPref.getString(MainActivity.SHARED_PREF_LOCATION, "")

        return binding.root
    }

}