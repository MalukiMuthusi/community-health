package codes.malukimuthusi.commhealth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import codes.malukimuthusi.commhealth.databinding.SignUpFragmentBinding
import java.io.FileDescriptor
import java.io.IOException

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
        const val PICK_IMAGE = 8956
    }

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: SignUpFragmentBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)

        binding.selectImageButton.setOnClickListener {
            pickImage()
        }

        binding.submitButton.setOnClickListener {
            submit()
        }

        return binding.root
    }

    private fun pickImage() {
        val intent = Intent(
            Intent.ACTION_OPEN_DOCUMENT
        ).apply {
            type = "image/*"
        }

        startActivityForResult(intent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                binding.profileImage.colorFilter = null
                binding.profileImage.setImageURI(uri)

                // save the image uri
                sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString(MainActivity.PROFILE_IMAGE_URI, uri.toString())
                    apply()
                }
            }
        }
    }

    private fun submit() {
        val name = binding.nameText.text.toString()
        val location = binding.locationText.text.toString()

        with(sharedPref.edit()) {
            putString(MainActivity.SHARED_PREF_NAME, name)
            putString(MainActivity.SHARED_PREF_LOCATION, location)
            apply()
        }

        findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToRecentActivityFragment())
    }


}