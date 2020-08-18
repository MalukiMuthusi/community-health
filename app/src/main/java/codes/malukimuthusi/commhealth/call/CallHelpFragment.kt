package codes.malukimuthusi.commhealth.call

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import codes.malukimuthusi.commhealth.R
import codes.malukimuthusi.commhealth.dataModels.PhoneNumbers
import codes.malukimuthusi.commhealth.databinding.CallHelpFragmentBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class CallHelpFragment : Fragment() {

    companion object {
        fun newInstance() = CallHelpFragment()
    }

    private val viewModel: CallHelpViewModel by viewModels()
    private lateinit var binding: CallHelpFragmentBinding
    lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CallHelpFragmentBinding.inflate(inflater, container, false)

        db = FirebaseFirestore.getInstance()

        val query = db.collection("referral-numbers")


        val firestoreRecyclerOptions = FirestoreRecyclerOptions.Builder<PhoneNumbers>()
            .setQuery(query, PhoneNumbers::class.java)
            .setLifecycleOwner(this)
            .build()

        val firestoreAdapter = object :
            FirestoreRecyclerAdapter<PhoneNumbers, PhoneNumberViewHolder>(firestoreRecyclerOptions) {
            override fun onBindViewHolder(
                holder: PhoneNumberViewHolder,
                position: Int,
                model: PhoneNumbers
            ) {
                holder.bind(model)
            }

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): PhoneNumberViewHolder {
                return PhoneNumberViewHolder.from(parent)
            }
        }

        binding.recyclerView.adapter = firestoreAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        return binding.root
    }
}