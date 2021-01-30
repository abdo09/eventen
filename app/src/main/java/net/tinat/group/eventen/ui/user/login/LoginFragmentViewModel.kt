package net.tinat.group.eventen.ui.user.login

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import net.tinat.group.eventen.base.BaseViewModel

class LoginFragmentViewModel(private val auth: FirebaseAuth) : BaseViewModel() {
    var isUserSignedIn = MutableLiveData<Boolean>()
    var uId = MutableLiveData<String>()

    fun signInUser(email: String?, password: String?) {
        showLoading.value = true

        try {
            email?.let { e_mail ->
                password.let { password ->

                    auth.signInWithEmailAndPassword(e_mail, password ?: "")
                        .addOnCompleteListener { task ->

                            showLoading.value = false

                            isUserSignedIn.postValue(task.isSuccessful)

                            if (task.isSuccessful) {
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