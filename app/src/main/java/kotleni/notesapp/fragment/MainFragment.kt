package kotleni.notesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotleni.notesapp.adapter.NotesListAdapter
import kotleni.notesapp.databinding.FragmentMainBinding
import kotleni.notesapp.viewmodel.MainViewModel

class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // setSupportActionBar(binding.toolbar)

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = NotesListAdapter() {
            // when clicked
        }

        viewModel.getNotesList().observe(this) {
            (binding.recycler.adapter as NotesListAdapter).updateList(it)
        }

        binding.addBtn.setOnClickListener {
            viewModel.newNote()
        }
    }
}