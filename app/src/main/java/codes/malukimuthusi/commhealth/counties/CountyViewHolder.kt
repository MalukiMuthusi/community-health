package codes.malukimuthusi.commhealth.counties

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import codes.malukimuthusi.commhealth.dataModels.County
import codes.malukimuthusi.commhealth.databinding.CountiesSingleItemBinding

class CountyViewHolder private constructor(
    private val binding: CountiesSingleItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(county: County, viewModel: CountiesViewModel) {
        binding.county = county

        binding.countyCard.setOnClickListener {

            viewModel.clickedCounty = county
            binding.root.findNavController()
                .navigate(CountiesFragmentDirections.actionCountiesFragmentToNationalDiseaseBurdenListFragment())
        }
    }

    companion object {
        fun from(parent: ViewGroup): CountyViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CountiesSingleItemBinding.inflate(inflater, parent, false)

            return CountyViewHolder(binding)
        }
    }
}