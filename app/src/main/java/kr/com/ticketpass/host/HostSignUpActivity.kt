package kr.com.ticketpass.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivitySignupBinding
import kr.com.ticketpass.viewmodel.SignupViewModel


class HostSignUpActivity : AppCompatActivity() {
    private val viewModel: SignupViewModel by lazy {
        ViewModelProvider(this).get(SignupViewModel::class.java)
    }
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        changeFragment()
    }

    private fun changeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.host_sign_up_container, HostSignUpEmailFragment())
            .addToBackStack(null)
            .commit()
    }
}