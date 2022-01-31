package tads.eaj.ufrn.poppedia.dao

import androidx.lifecycle.LiveData
import tads.eaj.ufrn.poppedia.data.Celeb

class CelebRepository (private val celebDao: CelebDao){

    val readAllCelebs: LiveData<List<Celeb>> = celebDao.readAllCelebs()

    fun insertCeleb(celeb: Celeb){
        celebDao.insertCeleb(celeb)
    }

     fun findCeleb(id: Long): LiveData<Celeb> {
        return celebDao.findCeleb(id)
    }

    fun updateCeleb(celeb: Celeb){
        celebDao.updateCeleb(celeb)
    }

    fun deleteCeleb(celeb: Celeb){
        celebDao.deleteCeleb(celeb)
    }

    fun deleteAllCelebs(){
        celebDao.deleteAllCelebs()
    }
}