package codes.malukimuthusi.commhealth.nationalDiseaseBurdenList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import codes.malukimuthusi.commhealth.dataModels.Disease
import codes.malukimuthusi.commhealth.databinding.NationalDiseaseBurdenListFragmentBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class NationalDiseaseBurdenListFragment : Fragment() {

    companion object {
        fun newInstance() =
            NationalDiseaseBurdenListFragment()
    }

    private lateinit var viewModel: NationalDiseaseBurdenListViewModel
    private lateinit var binding: NationalDiseaseBurdenListFragmentBinding
    lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NationalDiseaseBurdenListFragmentBinding.inflate(inflater, container, false)

        db = FirebaseFirestore.getInstance()
        val query = db.collection("fact-sheets")
            .limit(50)

        val firestoreRecyclerOptions = FirestoreRecyclerOptions.Builder<Disease>()
            .setQuery(query, Disease::class.java)
            .setLifecycleOwner(this)
            .build()

        val firestoreRecyclerAdapter = object :
            FirestoreRecyclerAdapter<Disease, NationalDiseaseBurdenViewHolder>(
                firestoreRecyclerOptions
            ) {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): NationalDiseaseBurdenViewHolder {
                return NationalDiseaseBurdenViewHolder.from(parent)
            }

            override fun onBindViewHolder(
                holder: NationalDiseaseBurdenViewHolder,
                position: Int,
                model: Disease
            ) {
                holder.bind(model)

            }
        }

        binding.recyclerView.adapter = firestoreRecyclerAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

}