package com.rodrigoguerrero.recipes.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.rodrigoguerrero.recipes.R
import com.rodrigoguerrero.recipes.adapters.RecipeAdapter
import com.rodrigoguerrero.recipes.databinding.ActivityLoginBinding
import com.rodrigoguerrero.recipes.viewmodels.LoginViewModel
import com.rodrigoguerrero.recipes.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityLoginBinding
    private val recipesAdapter = RecipeAdapter()

    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        viewModel.isUsernameValid.observe(this, Observer {
            if (!it) {
                binding.emailInputLayout.error = getString(R.string.invalid_email)
                binding.loginButton.isEnabled = false
            } else {
                binding.emailInputLayout.error = ""
                binding.loginButton.isEnabled = true
            }
        })

        viewModel.isPasswordValid.observe(this, Observer {
            if (!it) {
                binding.passwordInputLayout.error = getString(R.string.invalid_password)
                binding.loginButton.isEnabled = false
            } else {
                binding.passwordInputLayout.error = ""
                binding.loginButton.isEnabled = true
            }
        })
    }

    private fun init() {
        with(binding) {
            emailEditText.addTextChangedListener(afterTextChanged = {
                viewModel.validateUsername(it.toString())
            })
            passwordEditText.addTextChangedListener(afterTextChanged = {
                viewModel.validatePassword(it.toString())
            })

            loginButton.setOnClickListener {
                viewModel.login(emailEditText.text.toString(), this@LoginActivity)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}