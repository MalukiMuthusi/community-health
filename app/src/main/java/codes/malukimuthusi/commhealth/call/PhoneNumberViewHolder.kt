package codes.malukimuthusi.commhealth.call

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import codes.malukimuthusi.commhealth.dataModels.PhoneNumbers
import codes.malukimuthusi.commhealth.databinding.CallHelpFragmentBinding
import codes.malukimuthusi.commhealth.databinding.PhoneNumberSingleLayoutBinding

class PhoneNumberViewHolder private constructor(
    private val binding: PhoneNumberSingleLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(phoneNumber: PhoneNumbers) {
        binding.phoneNumber = phoneNumber
    }

    companion object {
        fun from(parent: ViewGroup): PhoneNumberViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = PhoneNumberSingleLayoutBinding.inflate(layoutInflater, parent, false)
            return PhoneNumberViewHolder(binding)
        }
    }
}