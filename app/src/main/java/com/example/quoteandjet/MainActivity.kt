package com.example.quoteandjet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quoteandjet.ViewModelFactories.MainViewModelFactory
import com.example.quoteandjet.models.Quote
import com.example.quoteandjet.viewModels.MainViewModel
import com.example.quoteandjet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycle.addObserver(LifeCycleObserverClass())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)

        setControls()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setControls() {
        binding.fbtnShare.setOnClickListener{
            val quote: Quote = viewModel.quote.value!!
            val shareIntent: Intent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,quote.text)
            startActivity(shareIntent)
        }
    }

}