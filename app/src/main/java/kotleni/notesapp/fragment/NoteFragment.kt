package kotleni.notesapp.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import kotleni.notesapp.databinding.FragmentNoteBinding
import kotleni.notesapp.viewmodel.NoteViewModel


class NoteFragment: Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        binding.toolbar.setNavigationIcon(kotleni.notesapp.R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        viewModel.getCurrentNote().observe(this) {
            binding.field.setText(it.text.toString())
        }

        viewModel.loadNote(arguments!!.getInt("uid"))

        binding.field.addTextChangedListener {
            viewModel.updateNote(it.toString())
        }

        binding.removeBtn.setOnClickListener {
            viewModel.removeNote()
            finish()
        }
    }

    private fun finish() {
        requireActivity().supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out
            )
            setReorderingAllowed(true)
            val fragment = MainFragment()
            replace(kotleni.notesapp.R.id.container, fragment)
        }
    }
}