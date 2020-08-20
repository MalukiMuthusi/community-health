package codes.malukimuthusi.commhealth.nationalDiseaseBurdenList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import codes.malukimuthusi.commhealth.dataModels.Disease
import codes.malukimuthusi.commhealth.databinding.NationalDiseaseBurdenSingleItemBinding

class NationalDiseaseBurdenViewHolder private constructor(
    private val binding: NationalDiseaseBurdenSingleItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(diseaseName: Disease) {
        binding.disease = diseaseName

        binding.diseaseName.setOnClickListener {
            binding.root.findNavController()
                .navigate(NationalDiseaseBurdenListFragmentDirections.actionNationalDiseaseBurdenListFragmentToDiseaseFragment())
        }
    }

    companion object {
        fun from(parent: ViewGroup): NationalDiseaseBurdenViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = NationalDiseaseBurdenSingleItemBinding.inflate(inflater, parent, false)
            return NationalDiseaseBurdenViewHolder(binding)
        }
    }
}