package tads.eaj.ufrn.poppedia.fragments.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tads.eaj.ufrn.poppedia.dao.CelebDatabase
import tads.eaj.ufrn.poppedia.dao.CelebRepository
import tads.eaj.ufrn.poppedia.data.Celeb

class ListViewModel(application: Application, id: Long) : AndroidViewModel(application) {
     val readAllCelebs : LiveData<List<Celeb>>

    private val repository: CelebRepository

    init {
        val celebDao = CelebDatabase.getDatabase(application).celebDao()
        repository = CelebRepository(celebDao)
        readAllCelebs = repository.readAllCelebs
    }

    fun deleteAllCelebs(){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteAllCelebs()
        }
    }


}