package codes.malukimuthusi.commhealth.dataModels

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.firebase.firestore.DocumentReference

data class Disease(
    val name: String? = "",
    val prevention: String? = "",
    val symptoms: String? = "",
    val treatment: String? = "",
    val url: String? = "",
    var diseaseReference: DocumentReference? = null
)

data class PhoneNumbers(
    val name: String? = "",
    val number: String? = ""
)