package com.example.quoteandjet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quoteandjet.Models.Quote
import com.example.quoteandjet.ViewModelFactories.MainViewModelFactory
import com.example.quoteandjet.ViewModels.MainViewModel
import com.example.quoteandjet.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycle.addObserver(LifeCycleObserverClass())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)

        setControls()
    }

    private fun setControls() {
        binding.tvQuote.text = viewModel.getQuote().text
        binding.tvAuthor.text = viewModel.getQuote().author

        bindObservables()

        binding.fbtnShare.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        binding.btnPrevious.setOnClickListener(this)
    }

    private fun bindObservables() {
        viewModel.quoteIndex.observe(this, Observer {
            binding.tvQuote.text = viewModel.getQuote().text
            binding.tvAuthor.text = viewModel.getQuote().author
        })
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.fbtnShare -> {
                val quote: Quote = viewModel.getQuote()
                val shareIntent: Intent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                shareIntent.putExtra(Intent.EXTRA_TEXT,quote.text)
                startActivity(shareIntent)
            }
            R.id.btnNext -> viewModel.getNextQuote()
            R.id.btnPrevious -> viewModel.getPreviousQuote()
        }
    }

}