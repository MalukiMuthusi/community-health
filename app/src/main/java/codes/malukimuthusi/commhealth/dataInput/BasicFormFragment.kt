package codes.malukimuthusi.commhealth.dataInput

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import codes.malukimuthusi.commhealth.R
import codes.malukimuthusi.commhealth.dataModels.PatientEntry
import codes.malukimuthusi.commhealth.databinding.BasicFormFragmentBinding
import com.google.firebase.firestore.FirebaseFirestore

class BasicFormFragment : Fragment() {


    companion object {
        fun newInstance() = BasicFormFragment()
    }

    private lateinit var viewModel: BasicFormViewModel
    lateinit var binding: BasicFormFragmentBinding
    lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasicFormFragmentBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.genderInputText.setAdapter(it)
        }

        binding.floatingActionBar.setOnClickListener {
            if (validateInputs()) {
                submit()
            }
        }

        return binding.root
    }

    private fun submit() {
        val age = binding.ageLayout.editText?.text.toString()
        val gender = binding.genderLayout.editText?.text.toString()
        val locality = binding.localityLayout.editText?.text.toString()
        val illnessDuration = binding.illnessDurationLayout.editText?.text.toString()
        val otherConditions = binding.othersTextLayout.editText?.text.toString()
        val preexistingConditions = mutableMapOf<String, Boolean>()

        when {
            binding.checkboxDiabetes.isChecked -> {
                preexistingConditions["Diabetes"] = true
            }

            binding.hypertension.isChecked -> {
                preexistingConditions["Hypertension"] = true
            }

            binding.cancer.isChecked -> {
                preexistingConditions["Cancer"] = true
            }

            binding.otherBloodDisorders.isChecked -> {
                preexistingConditions["Blood Disorders"] = true
            }
        }

        val newPatientRecord = PatientEntry(
            age,
            gender,
            locality,
            illnessDuration,
            otherConditions,
            preexistingConditions
        )

        val newRecordRef = db.collection("patient-records").document()

        newRecordRef.set(newPatientRecord)
            .addOnCompleteListener {
                Toast.makeText(requireContext(), "Created new Patient Record", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(BasicFormFragmentDirections.actionBasicFormFragmentSelf())
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Failed to create new Patient Record",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
    }

    private fun validateInputs(): Boolean {
        return true
    }

}