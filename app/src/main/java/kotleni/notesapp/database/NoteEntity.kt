package kotleni.notesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "text") var text: String?
)