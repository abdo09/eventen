package net.tinat.group.eventen.ui.user.signup

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import net.tinat.group.eventen.base.BaseViewModel

class SignUpFragmentViewModel(
    private val auth: FirebaseAuth
) : BaseViewModel() {
    var isUserCreated = MutableLiveData<Boolean>()
    var uId = MutableLiveData<String>()

    fun createUser(email: String?, password: String?) {
        showLoading.value = true
        try {
            email?.let { e_mail ->
                password.let { password ->
                    auth.createUserWithEmailAndPassword(e_mail, password ?: "")
                        .addOnCompleteListener { task ->
                            showLoading.value = false
                            isUserCreated.postValue(task.isSuccessful)
                            if (task.isSuccessful){
                                uId.postValue(auth.currentUser?.uid)
                            }
                        }

                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

}