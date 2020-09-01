package codes.malukimuthusi.commhealth.countyDiseaseBurden

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import codes.malukimuthusi.commhealth.dataModels.Disease
import codes.malukimuthusi.commhealth.databinding.DiseaseBurdenCountyFragmentBinding
import codes.malukimuthusi.commhealth.nationalDiseaseBurdenList.NationalDiseaseBurdenListViewModel
import codes.malukimuthusi.commhealth.nationalDiseaseBurdenList.NationalDiseaseBurdenViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.SnapshotParser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class DiseaseBurdenCountyFragment : Fragment() {

    companion object {
        fun newInstance() = DiseaseBurdenCountyFragment()
    }

    private val viewModel: NationalDiseaseBurdenListViewModel by activityViewModels()
    private lateinit var binding: DiseaseBurdenCountyFragmentBinding
    lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DiseaseBurdenCountyFragmentBinding.inflate(inflater, container, false)

        db = FirebaseFirestore.getInstance()
        val query = db.collection("fact-sheets")
            .orderBy("name", Query.Direction.DESCENDING)
            .limit(5)

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
            FirestoreRecyclerAdapter<Disease, CountyDiseaseBurdenViewHolder>(
                firestoreRecyclerOptions
            ) {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): CountyDiseaseBurdenViewHolder {
                return CountyDiseaseBurdenViewHolder.from(parent)
            }

            override fun onBindViewHolder(
                holder: CountyDiseaseBurdenViewHolder,
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