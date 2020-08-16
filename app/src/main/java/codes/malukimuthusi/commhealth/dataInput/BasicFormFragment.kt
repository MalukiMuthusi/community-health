package codes.malukimuthusi.commhealth.dataInput

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import codes.malukimuthusi.commhealth.R
import codes.malukimuthusi.commhealth.databinding.BasicFormFragmentBinding

class BasicFormFragment : Fragment() {


    companion object {
        fun newInstance() = BasicFormFragment()
    }

    private lateinit var viewModel: BasicFormViewModel
    lateinit var binding: BasicFormFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasicFormFragmentBinding.inflate(inflater, container, false)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.genderInputText.setAdapter(it)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BasicFormViewModel::class.java)
        // TODO: Use the ViewModel
    }

}