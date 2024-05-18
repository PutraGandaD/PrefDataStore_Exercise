package com.putragandad.datastoreexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.putragandad.datastoreexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // connect viewmodel
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory((application as MainApplication).dataStore)
        )[MainViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            mainViewModel.saveLoginStatus(true)
        }

        mainViewModel.readLoginStatus().observe(this) { status ->
            val tvLoginStatus = binding.tvLoginStatus
            if(status) {
                tvLoginStatus.text = "You're signed in"
            } else {
                tvLoginStatus.text = "You're not signed in"
            }
        }
    }
}