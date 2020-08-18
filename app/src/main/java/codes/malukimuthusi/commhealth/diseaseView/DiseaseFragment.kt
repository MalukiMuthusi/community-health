package codes.malukimuthusi.commhealth.diseaseView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import codes.malukimuthusi.commhealth.dataModels.Disease
import codes.malukimuthusi.commhealth.databinding.DiseaseFragmentBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject


class DiseaseFragment : Fragment() {
    companion object {
        fun newInstance() =
            DiseaseFragment()
    }

    private val viewModel: DiseaseViewModel by viewModels()
    lateinit var db: FirebaseFirestore
    lateinit var binding: DiseaseFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DiseaseFragmentBinding.inflate(inflater, container, false)

        db = FirebaseFirestore.getInstance()

        db.collection("fact-sheets")
            .document("SKWPAnk9VtPyfX3N06jh")
            .get()
            .addOnSuccessListener { data ->
                val cho = data.toObject<Disease>()

                if (cho != null) {
                    binding.diseaseNameText.text = cho.name
                    binding.symptomsText.text = cho.symptoms
                    binding.preventionText.text = cho.prevention
                    binding.treatmentText.text = cho.treatment
                }
            }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}