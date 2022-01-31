package tads.eaj.ufrn.poppedia.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*


@Entity(tableName = "celebrity")
@TypeConverters(Converter::class)
data class Celeb(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val birthDate: Date,
    val occupation: String,
    val description: String,
    val website:String
)