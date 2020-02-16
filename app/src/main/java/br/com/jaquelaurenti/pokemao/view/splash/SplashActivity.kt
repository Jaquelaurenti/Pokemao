package br.com.jaquelaurenti.pokemao.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.jaquelaurenti.pokemao.R
import org.koin.android.viewmodel.ext.android.viewModel
import android.content.Intent
import br.com.jaquelaurenti.pokemao.view.main.MainActivity
import android.widget.Toast

class SplashActivity : AppCompatActivity() {
    val splashViewModel: SplashViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModel.checkHealth()
        splashViewModel.messageError.observe(this, Observer {
            if (it == "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish() }
            else {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
        }) }
}