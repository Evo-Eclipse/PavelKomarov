package com.example.pavelkomarov

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.pavelkomarov.databinding.ActivitySecondBinding
import com.example.pavelkomarov.fragments.NotificationsFragment
import com.example.pavelkomarov.fragments.ProjectsFragment
import com.example.pavelkomarov.fragments.StatisticsFragment

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root) // Изменено с binding.main на binding.root
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Настройка тулбара
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_projects)

        // По умолчанию показываем ProjectsFragment
        if (savedInstanceState == null) {
            replaceFragment(ProjectsFragment())
        }

        // Настройка BottomNavigationView
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_projects -> {
                    replaceFragment(ProjectsFragment())
                    supportActionBar?.title = getString(R.string.title_projects)
                    true
                }
                R.id.nav_statistics -> {
                    replaceFragment(StatisticsFragment())
                    supportActionBar?.title = getString(R.string.title_statistics)
                    true
                }
                R.id.nav_notifications -> {
                    replaceFragment(NotificationsFragment())
                    supportActionBar?.title = getString(R.string.title_notifications)
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
