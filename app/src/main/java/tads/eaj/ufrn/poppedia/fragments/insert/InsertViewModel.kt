package tads.eaj.ufrn.poppedia.fragments.insert

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tads.eaj.ufrn.poppedia.dao.CelebDatabase
import tads.eaj.ufrn.poppedia.dao.CelebRepository
import tads.eaj.ufrn.poppedia.data.Celeb



class InsertViewModel(application: Application) : AndroidViewModel(application) {



    private val repository : CelebRepository

    init {
        val celebDao = CelebDatabase.getDatabase(application).celebDao()
        repository = CelebRepository(celebDao)
    }

    fun insertCeleb(celeb:Celeb){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertCeleb(celeb)
        }
    }
}