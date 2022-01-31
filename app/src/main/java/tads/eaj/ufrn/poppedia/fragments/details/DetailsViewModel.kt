package tads.eaj.ufrn.poppedia.fragments.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tads.eaj.ufrn.poppedia.dao.CelebDatabase
import tads.eaj.ufrn.poppedia.dao.CelebRepository
import tads.eaj.ufrn.poppedia.data.Celeb

class DetailsViewModel(application: Application) : AndroidViewModel(application) {



    private val repository : CelebRepository

    init {
        val celebDao = CelebDatabase.getDatabase(application).celebDao()
        repository = CelebRepository(celebDao)
    }

    fun findCeleb(id: Long): LiveData<Celeb> {
        return repository.findCeleb(id)
    }

    fun deleteCeleb(celeb: Celeb){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteCeleb(celeb)
        }
    }
}