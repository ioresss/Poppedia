package tads.eaj.ufrn.poppedia.fragments.edit

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tads.eaj.ufrn.poppedia.dao.CelebDatabase
import tads.eaj.ufrn.poppedia.dao.CelebRepository
import tads.eaj.ufrn.poppedia.data.Celeb
import tads.eaj.ufrn.poppedia.fragments.list.ListViewModel

class EditViewModel (application: Application, id:Long) : AndroidViewModel(application) {

    private var _eventUpdateCeleb= MutableLiveData<Boolean>(false)
    val eventUpdateCeleb:LiveData<Boolean>
        get() = _eventUpdateCeleb

    private val repository : CelebRepository
    lateinit var celeb:LiveData<Celeb>



    init {
        val celebDao = CelebDatabase.getDatabase(application).celebDao()
        repository = CelebRepository(celebDao)
        viewModelScope.launch {
            celeb = repository.findCeleb(id)
        }
    }



    fun updateCeleb(){
        _eventUpdateCeleb.value = true
        viewModelScope.launch (Dispatchers.IO) {
            repository.updateCeleb(celeb.value!!)
        }
    }

    class Factory(val application: Application, val id: Long) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ListViewModel::class.java)){
                return ListViewModel(application, id) as T
            }
            throw IllegalAccessException("Unknown View Model Class")
        }
    }
}