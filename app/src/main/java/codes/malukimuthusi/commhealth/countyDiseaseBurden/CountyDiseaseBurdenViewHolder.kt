package codes.malukimuthusi.commhealth.countyDiseaseBurden

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import codes.malukimuthusi.commhealth.dataModels.Disease
import codes.malukimuthusi.commhealth.databinding.NationalDiseaseBurdenSingleItemBinding
import codes.malukimuthusi.commhealth.nationalDiseaseBurdenList.NationalDiseaseBurdenListFragmentDirections
import codes.malukimuthusi.commhealth.nationalDiseaseBurdenList.NationalDiseaseBurdenListViewModel


class CountyDiseaseBurdenViewHolder private constructor(
    private val binding: NationalDiseaseBurdenSingleItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(diseaseName: Disease, viewModel: NationalDiseaseBurdenListViewModel) {
        binding.disease = diseaseName

        binding.diseaseName.setOnClickListener {
            viewModel.clickedDiseasedRef = diseaseName.diseaseReference
            binding.root.findNavController()
                .navigate(DiseaseBurdenCountyFragmentDirections.actionDiseaseBurdenCountyFragmentToDiseaseFragment())
        }
    }

    companion object {
        fun from(parent: ViewGroup): CountyDiseaseBurdenViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = NationalDiseaseBurdenSingleItemBinding.inflate(inflater, parent, false)
            return CountyDiseaseBurdenViewHolder(binding)
        }
    }
}