package tads.eaj.ufrn.poppedia.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tads.eaj.ufrn.poppedia.data.Celeb

@Database(entities = [Celeb::class], version = 1, exportSchema = false)
abstract class CelebDatabase: RoomDatabase() {

    abstract fun celebDao():CelebDao

    companion object{
        @Volatile
        private var INSTANCE:CelebDatabase?=null


        fun getDatabase(context: Context):CelebDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CelebDatabase::class.java,
                    "celebrity_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}