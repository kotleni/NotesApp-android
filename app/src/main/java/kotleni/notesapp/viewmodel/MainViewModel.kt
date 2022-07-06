package kotleni.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotleni.notesapp.App
import kotleni.notesapp.database.AppDatabase
import kotleni.notesapp.database.NoteEntity
import kotleni.notesapp.storage.DatabaseStorage
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class MainViewModel: ViewModel() {
    // coroutine
    private val io = CoroutineScope(Dispatchers.IO)
    private val main = CoroutineScope(Dispatchers.Main)

    private val databaseStorage: DatabaseStorage = DatabaseStorage()
    private var notesList: MutableLiveData<List<NoteEntity>> = MutableLiveData()

    fun getNotesList(): LiveData<List<NoteEntity>> {
        return notesList
    }

    fun loadNotes() = main.launch {
        val notes = withContext(Dispatchers.IO) {
           databaseStorage.getAllNotes()
        }
        notesList.value = notes
    }

    fun newNote() = io.launch {
        databaseStorage.addNote(
            NoteEntity(
                uid = databaseStorage.getNextId(),
                text = ""
            )
        )
        loadNotes()
    }

    fun removeNote(noteEntity: NoteEntity) = io.launch {
        databaseStorage.removeNote(noteEntity)
        loadNotes()
    }
}