package com.project.eratani.features.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.project.eratani.R
import com.project.eratani.core.data.source.Resource
import com.project.eratani.core.utils.constants.listGender
import com.project.eratani.core.utils.constants.listStatusActive
import com.project.eratani.core.utils.showToast
import com.project.eratani.databinding.ActivityRegisterBinding
import com.project.eratani.features.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        setupPickGender()

        setupPickStatus()

        buttonEnabled(false)

        handleForms()

        handleButtonRegister()

        handleEnterButton()
    }

    private fun handleEnterButton() {
        binding.tvToRegister.setOnClickListener {
            val intentToDashboard = Intent(this, DashboardActivity::class.java)
            intentToDashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intentToDashboard)
        }
    }

    private fun checkForms(){
        binding.apply {
            val email = edRegisEmail.text.toString()
            val name = edRegisName.text.toString()
            val gender = edRegisGender.text.toString()
            val status = edRegisActiveStatus.text.toString()

            buttonEnabled(Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty() && name.isNotEmpty() && gender.isNotEmpty() && status.isNotEmpty())
        }
    }

    private fun handleForms() {
        binding.apply {
            edRegisName.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    checkForms()
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    checkForms()
                }

                override fun afterTextChanged(p0: Editable?) {
                    checkForms()
                }
            })

            edRegisName.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    checkForms()
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    checkForms()
                }

                override fun afterTextChanged(p0: Editable?) {
                    checkForms()
                }
            })

            edRegisGender.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    checkForms()
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    checkForms()
                }

                override fun afterTextChanged(p0: Editable?) {
                    checkForms()
                }
            })

            edRegisActiveStatus.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    checkForms()
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    checkForms()
                }

                override fun afterTextChanged(p0: Editable?) {
                    checkForms()
                }
            })
        }

    }

    private fun handleButtonRegister() {
        binding.btnRegister.setOnClickListener{
            val email = binding.edRegisEmail.text.toString()
            val name = binding.edRegisName.text.toString()
            val gender = binding.edRegisGender.text.toString()
            val status = binding.edRegisActiveStatus.text.toString()

            registerViewModel.registerUser(name, email, gender, status).observe(this){user ->
                when(user){
                    is Resource.Error ->{
                        showLoading(false)

                        Timber.tag("RegisterActivity").e("Error : ${user.message}")
                        showToast(user.message.toString())
                    }
                    is Resource.Loading ->{
                        showLoading(true)
                    }
                    is Resource.Message ->{
                        showLoading(false)

                        Timber.tag("RegisterActivity").e("Message : ${user.message}")
                    }
                    is Resource.Success ->{
                        showLoading(false)

                        showToast("Register success!")

                        val intentToDashboard = Intent(this, DashboardActivity::class.java)
                        intentToDashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intentToDashboard)
                    }
                }
            }
        }
    }

    private fun setupPickStatus() {
        val adapter = ArrayAdapter(this, R.layout.item_list_dropdown, listStatusActive)

        binding.edRegisActiveStatus.apply {
            setAdapter(adapter)
            addTextChangedListener (object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    binding.layoutActiveStatus.isHintEnabled = p0.isNullOrEmpty()
                }
            })
        }
    }

    private fun setupPickGender() {
        val adapter = ArrayAdapter(this, R.layout.item_list_dropdown, listGender)

        binding.edRegisGender.apply {
            setAdapter(adapter)
            addTextChangedListener (object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    binding.layoutGender.isHintEnabled = p0.isNullOrEmpty()
                }
            })
        }
    }

    private fun setupActionBar() {
        supportActionBar?.hide()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun buttonEnabled(isEnabled: Boolean) {
        binding.btnRegister.isEnabled = isEnabled
    }
}