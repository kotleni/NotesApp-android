package kotleni.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotleni.notesapp.database.NoteEntity
import kotleni.notesapp.storage.DatabaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel: ViewModel() {
    // coroutine
    private val io = CoroutineScope(Dispatchers.IO)
    private val main = CoroutineScope(Dispatchers.Main)

    private val databaseStorage: DatabaseStorage = DatabaseStorage()
    private var currentNote: MutableLiveData<NoteEntity> = MutableLiveData()

    fun getCurrentNote(): LiveData<NoteEntity> {
        return currentNote
    }

    fun updateNote(text: String) = main.launch {
        currentNote.value!!.text = text
        withContext(Dispatchers.IO) {
            databaseStorage.updateNote(currentNote.value!!)
        }
    }

    fun loadNote(uid: Int) = main.launch {
        val note = withContext(Dispatchers.IO) {
            databaseStorage.getNoteByUid(uid)
            //databaseStorage.getAllNotes()[uid]
        }
        if(note != null) {
            currentNote.value = note
        } else {
            error("Note is null, in: loadNote")
        }
    }

    fun removeNote() = io.launch {
        databaseStorage.removeNote(currentNote.value!!)
    }
}