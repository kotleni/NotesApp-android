package kotleni.notesapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotleni.notesapp.database.NoteEntity
import kotleni.notesapp.databinding.ItemNotepreviewBinding

class NotesListAdapter(val itemClickHandler: (note: NoteEntity) -> Unit): RecyclerView.Adapter<NotesListAdapter.MyHolder>() {
    class MyHolder(val binding: ItemNotepreviewBinding): RecyclerView.ViewHolder(binding.root) {}

    private var list: List<NoteEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemNotepreviewBinding.inflate(LayoutInflater.from(parent.context))
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        // fixme: stupid code
        holder.binding.text.text = if(list[position].text!!.isEmpty()) "It's note is empty..." else list[position].text
        if(list[position].text!!.isEmpty()) holder.binding.text.setTextColor(Color.parseColor("#4C4C4C"))

        holder.binding.root.setOnClickListener {
            itemClickHandler.invoke(list[position])
        }
    }

    fun updateList(it: List<NoteEntity>) {
        list = it
        notifyDataSetChanged()
    }
}