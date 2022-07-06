package kotleni.notesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotleni.notesapp.R
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

        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2) //LinearLayoutManager(requireContext())
        binding.recycler.adapter = NotesListAdapter() {
            requireActivity().supportFragmentManager.commit {
                setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
                setReorderingAllowed(true)
                val fragment = NoteFragment()
                fragment.arguments = Bundle().apply {
                    putInt("uid", it.uid)
                }
                replace(R.id.container,fragment)
            }
        }

        viewModel.loadNotes()

        viewModel.getNotesList().observe(this) {
            (binding.recycler.adapter as NotesListAdapter).updateList(it)
        }

        binding.addBtn.setOnClickListener {
            viewModel.newNote()
        }
    }
}