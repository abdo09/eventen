package net.tinat.group.eventen.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import net.tinat.group.eventen.data.paths.FireStoreConfig

class CategoryRepository(
    private val fireStore: FirebaseFirestore,
    private val authRepository: AuthRepository
) {

    fun getCategoryList(): Query? {
        return if (authRepository.isUserLogged())
            fireStore.collection(FireStoreConfig.CATEGORY)
        else null

    }

}