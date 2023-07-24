package com.example.cryptocurrencydemo.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencydemo.R
import com.example.cryptocurrencydemo.databinding.ActivityMainBinding
import com.example.cryptocurrencydemo.presentation.adapter.CoinsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: CoinsAdapter
    var _state = mutableStateOf(CoinListState())
    private lateinit var state: State<CoinListState>



    private val viewModel: CoinListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        observeCoins()
         /*state = viewModel.state
        _state = viewModel._state
        observeCoinsState(_state)*/
    }

    private fun observeCoinsState(_state: MutableState<CoinListState>) {

        //if(viewModel.state.value.coins.isNotEmpty()){
        binding.progressBar.visibility = View.GONE
        binding.coinList.visibility = View.VISIBLE
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        val divider =
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ContextCompat.getDrawable(
                baseContext, R.drawable.custom_divider
            )!!
        )
        binding.coinList.addItemDecoration(divider)
        binding.coinList.setLayoutManager(layoutManager)
        rvAdapter = CoinsAdapter(_state.value.coins)
        binding.coinList.adapter = rvAdapter
        binding.tvHello.text = "${_state.value.coins.size}"
        //}

        if (_state.value.error.isNotBlank()) {
            Toast.makeText(this, "${viewModel.state.value.error}", Toast.LENGTH_SHORT).show()
        }

        if (_state.value.isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun observeCoins() {
        viewModel.coins.observe(this) {
            binding.progressBar.visibility = View.GONE
            binding.coinList.visibility = View.VISIBLE
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
            val divider =
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            divider.setDrawable(
                ContextCompat.getDrawable(
                    baseContext, R.drawable.custom_divider
                )!!
            )
            binding.coinList.addItemDecoration(divider)
            binding.coinList.setLayoutManager(layoutManager)
            rvAdapter = CoinsAdapter(it)
            binding.coinList.adapter = rvAdapter
            binding.tvHello.text = "${viewModel.coins.value?.size}"
        }
    }
}