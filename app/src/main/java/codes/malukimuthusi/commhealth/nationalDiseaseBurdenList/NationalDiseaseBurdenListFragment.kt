package codes.malukimuthusi.commhealth.nationalDiseaseBurdenList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import codes.malukimuthusi.commhealth.dataModels.Disease
import codes.malukimuthusi.commhealth.databinding.NationalDiseaseBurdenListFragmentBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.SnapshotParser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class NationalDiseaseBurdenListFragment : Fragment() {

    companion object {
        fun newInstance() =
            NationalDiseaseBurdenListFragment()
    }

    private val viewModel: NationalDiseaseBurdenListViewModel by activityViewModels()
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
            .setQuery(query, object : SnapshotParser<Disease> {

                override fun parseSnapshot(snapshot: DocumentSnapshot): Disease {
                    val disease = snapshot.toObject(Disease::class.java)
                    if (disease != null) {
                        disease.diseaseReference = snapshot.reference
                        return disease
                    }
                    return Disease()
                }
            })
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
                holder.bind(model, viewModel)

            }
        }

        binding.recyclerView.adapter = firestoreRecyclerAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

}