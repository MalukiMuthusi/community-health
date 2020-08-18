package codes.malukimuthusi.commhealth.dataModels

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment

data class Disease(
    val name: String? = "",
    val prevention: String? = "",
    val symptoms: String? = "",
    val treatment: String? = "",
    val url: String? = ""
)

data class PhoneNumbers(
    val name: String? = "",
    val number: String? = ""
)