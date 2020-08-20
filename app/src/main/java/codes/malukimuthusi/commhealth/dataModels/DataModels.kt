package codes.malukimuthusi.commhealth.dataModels

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ServerTimestamp
import java.util.*
import java.util.concurrent.locks.Condition

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

data class PatientEntry(
    val age: String? = "",
    val gender: String? = "",
    val locality: String? = "",
    val illnessDuration: String? = "",
    val otherConditions: String? = "",
    val preexistingCondition: MutableMap<String, Boolean>? = mutableMapOf(),
    @field:ServerTimestamp val timeCreated: Date? = null
)