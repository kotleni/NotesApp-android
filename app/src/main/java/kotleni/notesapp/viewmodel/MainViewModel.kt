package kotleni.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotleni.notesapp.App
import kotleni.notesapp.database.AppDatabase
import kotleni.notesapp.database.NoteEntity
import kotleni.notesapp.storage.DatabaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainViewModel: ViewModel() {
    // coroutine
    private val io = CoroutineScope(Dispatchers.IO)

    private val databaseStorage: DatabaseStorage = DatabaseStorage()
    private var notesList: MutableLiveData<List<NoteEntity>> = MutableLiveData()

    fun getNotesList(): LiveData<List<NoteEntity>> {
        return notesList
    }

    fun loadNotes() = io.launch {
        notesList.value = databaseStorage.getAllNotes()
    }

    fun newNote() = io.launch {
        databaseStorage.addNote(
            NoteEntity(
                uid = databaseStorage.getNextId(),
                text = ""
            )
        )
    }

    fun removeNote(noteEntity: NoteEntity) = io.launch {
        databaseStorage.removeNote(noteEntity)
    }
}