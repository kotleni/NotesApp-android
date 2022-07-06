package kotleni.notesapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Query("SELECT * FROM noteentity")
    fun getAll(): List<NoteEntity>

    @Update
    fun update(entity: NoteEntity)

    @Insert
    fun insertAll(vararg users: NoteEntity)

    @Delete
    fun delete(user: NoteEntity)
}