package codes.malukimuthusi.commhealth.geoCategory

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import codes.malukimuthusi.commhealth.R
import codes.malukimuthusi.commhealth.databinding.GeoCategoryFragmentBinding

class GeoCategoryFragment : Fragment() {
    lateinit var binding: GeoCategoryFragmentBinding

    companion object {
        fun newInstance() =
            GeoCategoryFragment()
    }

    private lateinit var viewModel: GeoCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GeoCategoryFragmentBinding.inflate(inflater, container, false)

        binding.nationalSelectButton.setOnClickListener {
            findNavController().navigate(GeoCategoryFragmentDirections.actionGeoCategoryFragmentToDiseaseFragment())
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GeoCategoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}