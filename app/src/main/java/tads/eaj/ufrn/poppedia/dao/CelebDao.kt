package tads.eaj.ufrn.poppedia.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import tads.eaj.ufrn.poppedia.data.Celeb

@Dao
interface CelebDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCeleb(celeb: Celeb):Long

    @Query( "SELECT * FROM celebrity ORDER BY id")
    fun readAllCelebs():LiveData<List<Celeb>>

    @Query("SELECT * FROM celebrity WHERE id = :id")
    fun findCeleb(id:Long):LiveData<Celeb>

    @Update
    fun updateCeleb(celeb:Celeb)

    @Delete
    fun deleteCeleb(celeb: Celeb)

    @Query("DELETE FROM celebrity")
    fun deleteAllCelebs()
}