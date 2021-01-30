package net.tinat.group.eventen.ui.bottom_tabs.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import net.tinat.group.eventen.base.BaseViewModel
import net.tinat.group.eventen.data.repository.CategoryRepository

class HomeFragmentViewModel(private val categoryRepository: CategoryRepository) : BaseViewModel() {

    private var categoriesList: MutableLiveData<List<String>>? = MutableLiveData()

    @SuppressLint("LogNotTimber")
    fun getCategories(): MutableLiveData<List<String>>? {

        showLoading.value = true
        var categoriesListTemp: List<String?>

        categoryRepository.getCategoryList()?.addOnSuccessListener { documents ->
            showLoading.value = false
            for ( document in documents){
                Log.d("gfdSGDAFG", "${document.data}")
            }
        }
        

        return categoriesList
    }

}