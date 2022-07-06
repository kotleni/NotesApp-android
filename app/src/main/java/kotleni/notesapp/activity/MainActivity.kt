package kotleni.notesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import kotleni.notesapp.R
import kotleni.notesapp.databinding.ActivityMainBinding
import kotleni.notesapp.databinding.FragmentMainBinding
import kotleni.notesapp.databinding.FragmentNoteBinding
import kotleni.notesapp.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<MainFragment>(R.id.container)
        }
    }
}