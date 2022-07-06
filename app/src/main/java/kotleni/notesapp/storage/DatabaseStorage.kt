package kotleni.notesapp.storage

import androidx.room.Room
import kotleni.notesapp.App
import kotleni.notesapp.database.AppDatabase
import kotleni.notesapp.database.NoteEntity

class DatabaseStorage {
    companion object {
        const val NOTESDB_NAME = "notes-db"
    }

    private val db = Room.databaseBuilder(
        App.context,
        AppDatabase::class.java, NOTESDB_NAME
    ).build()

    fun getAllNotes(): List<NoteEntity> {
        val dao = db.notesDao()
        return dao.getAll()
    }

    fun getNoteByUid(uid: Int): NoteEntity? {
        val dao = db.notesDao()
        val all = dao.getAll()

        all.forEach {
            if(it.uid == uid)
                return it
        }

        return null
    }

    fun addNote(noteEntity: NoteEntity) {
        val dao = db.notesDao()
        dao.insertAll(noteEntity)
    }

    fun updateNote(noteEntity: NoteEntity) {
        val dao = db.notesDao()
        dao.update(noteEntity)
    }

    fun removeNote(noteEntity: NoteEntity) {
        val dao = db.notesDao()
        dao.delete(noteEntity)
    }

    fun getNextId(): Int {
        return getAllNotes().count()
    }
}