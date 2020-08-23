package codes.malukimuthusi.commhealth.counties

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import codes.malukimuthusi.commhealth.R
import codes.malukimuthusi.commhealth.call.PhoneNumberViewHolder
import codes.malukimuthusi.commhealth.dataModels.County
import codes.malukimuthusi.commhealth.dataModels.PhoneNumbers
import codes.malukimuthusi.commhealth.databinding.CountiesFragmentBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class CountiesFragment : Fragment() {

    companion object {
        fun newInstance() =
            CountiesFragment()
    }

    private lateinit var binding: CountiesFragmentBinding
    lateinit var db: FirebaseFirestore
    private val viewModel: CountiesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountiesFragmentBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()

        val query = db.collection("counties")

        val firestoreRecyclerOptions = FirestoreRecyclerOptions.Builder<County>()
            .setQuery(query, County::class.java)
            .setLifecycleOwner(this)
            .build()

        val firestoreAdapter = object :
            FirestoreRecyclerAdapter<County, CountyViewHolder>(firestoreRecyclerOptions) {
            override fun onBindViewHolder(
                holder: CountyViewHolder,
                position: Int,
                model: County
            ) {
                holder.bind(model, viewModel)
            }

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): CountyViewHolder {
                return CountyViewHolder.from(parent)
            }
        }

        binding.recyclerView.adapter = firestoreAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

}