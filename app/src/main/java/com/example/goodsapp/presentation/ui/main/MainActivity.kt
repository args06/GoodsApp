package com.example.goodsapp.presentation.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.goodsapp.R
import com.example.goodsapp.databinding.ActivityMainBinding
import com.example.goodsapp.domain.model.User
import com.example.goodsapp.presentation.ui.auth.LoginActivity
import com.example.goodsapp.presentation.ui.main.assets.AssetsFragment
import com.example.goodsapp.presentation.ui.main.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.isLoggedIn.collect { isLoggedIn ->
                    when (isLoggedIn) {
                        true -> setupMainScreen()
                        false -> navigateToLoginScreen()
                        null -> {}
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.currentUser.collect { user ->
                    user?.let { updateUserUI(it) }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.isLoggedOut.collect { isLoggedOut ->
                    if (isLoggedOut == true) {
                        navigateToLoginScreen()
                    }
                }
            }
        }

        setupBottomNavigation()

    }

    private fun setupMainScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHome -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.navigationAsset -> {
                    replaceFragment(AssetsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToLoginScreen() {
        LoginActivity.start(this@MainActivity)
        finish()
    }

    private fun updateUserUI(user: User) {
        binding.userDetail.apply {
            tvName.text = user.username
            tvEmail.text = user.email

            btnLogout.setOnClickListener {
                mainViewModel.logout()
            }
        }

    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}