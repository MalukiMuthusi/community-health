package codes.malukimuthusi.commhealth.dataInput

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import codes.malukimuthusi.commhealth.R

class BasicFormFragment : Fragment() {

    companion object {
        fun newInstance() = BasicFormFragment()
    }

    private lateinit var viewModel: BasicFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.basic_form_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BasicFormViewModel::class.java)
        // TODO: Use the ViewModel
    }

}