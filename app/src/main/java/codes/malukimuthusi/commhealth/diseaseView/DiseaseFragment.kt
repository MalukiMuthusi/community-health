package codes.malukimuthusi.commhealth.diseaseView

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import codes.malukimuthusi.commhealth.R

class DiseaseFragment : Fragment() {

    companion object {
        fun newInstance() =
            DiseaseFragment()
    }

    private lateinit var viewModel: DiseaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.disease_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DiseaseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}