package net.tinat.group.eventen.ui.bottom_tabs.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import net.tinat.group.eventen.base.BaseViewModel
import net.tinat.group.eventen.data.repository.CategoryRepository

class HomeFragmentViewModel(private val categoryRepository: CategoryRepository) : BaseViewModel() {

    private var categoriesList: MutableLiveData<List<String>>? = MutableLiveData()

    fun getCategories(): MutableLiveData<List<String>>? {

        showLoading.value = true
        val listOfCategories = arrayListOf<String>()
        categoryRepository.getCategoryList()?.get()?.addOnSuccessListener { documents ->
            showLoading.value = false
            for (document in documents) {
                listOfCategories.add(document.id)
                Log.d("GDSGFGASG", document.getString("nameEn").toString())
            }
            categoriesList?.value = listOfCategories
        }
            ?.addOnFailureListener {
                showLoading.value = false
            }

        return categoriesList
    }

}