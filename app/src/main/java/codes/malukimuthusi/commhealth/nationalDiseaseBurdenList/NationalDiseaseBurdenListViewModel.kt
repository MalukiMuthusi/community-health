package codes.malukimuthusi.commhealth.nationalDiseaseBurdenList

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference

class NationalDiseaseBurdenListViewModel : ViewModel() {

    var clickedDiseasedRef: DocumentReference? = null
}