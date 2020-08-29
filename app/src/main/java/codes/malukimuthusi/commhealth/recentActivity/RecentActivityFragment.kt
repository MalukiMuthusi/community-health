package codes.malukimuthusi.commhealth.recentActivity

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import codes.malukimuthusi.commhealth.MainActivity
import codes.malukimuthusi.commhealth.databinding.RecentActivityFragmentBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class RecentActivityFragment : Fragment() {

    companion object {
        fun newInstance() = RecentActivityFragment()
    }

    private lateinit var viewModel: RecentActivityViewModel
    private lateinit var binding: RecentActivityFragmentBinding
    private lateinit var sharedPref: SharedPreferences
    lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecentActivityFragmentBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()
        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        countTodaysInteractions()
        countWeeklyInteractionss()
        countMontlyInteractions()

        val uri = sharedPref.getString(MainActivity.PROFILE_IMAGE_URI, "")
        if (!uri.isNullOrBlank()) {
            binding.imageProfile.setImageURI(Uri.parse(uri))
        }

        binding.nameText.text = sharedPref.getString(MainActivity.SHARED_PREF_NAME, "")
        binding.locationText.text = sharedPref.getString(MainActivity.SHARED_PREF_LOCATION, "")

        return binding.root
    }

    private fun countTodaysInteractions() {
        val yesterday = Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)
        db.collection("patient-records")
            .whereGreaterThanOrEqualTo("timeCreated", yesterday)
            .get()
            .addOnSuccessListener { documents ->
                val count = documents.size()
                binding.dailyInteractionsCount.text = count.toString()
            }
    }

    private fun countWeeklyInteractionss() {
        val weekAgo = Date(System.currentTimeMillis() - 6 * 24 * 60 * 60 * 1000)
        db.collection("patient-records")
            .whereGreaterThanOrEqualTo("timeCreated", weekAgo)
            .get()
            .addOnSuccessListener { documents ->
                val count = documents.size()
                binding.interactionsCount.text = count.toString()
            }
    }

    private fun countMontlyInteractions() {
        val l: Long = System.currentTimeMillis() - 14 * 24 * 60 * 60 * 1000
        val weekAgo = Date(l)
        db.collection("patient-records")
            .whereGreaterThanOrEqualTo("timeCreated", weekAgo)
            .get()
            .addOnSuccessListener { documents ->
                val count = documents.size()
                binding.interactionsCount.text = count.toString()
            }
    }

}