package codes.malukimuthusi.commhealth.recentActivity

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import codes.malukimuthusi.commhealth.R

class RecentActivityFragment : Fragment() {

    companion object {
        fun newInstance() = RecentActivityFragment()
    }

    private lateinit var viewModel: RecentActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recent_activity_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecentActivityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}