package com.example.myhomework4.ui

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.myhomework4.base.BaseActivity
import com.example.myhomework4.databinding.ActivityMainBinding
import com.example.myhomework4.model.DetailActivity
import com.example.myhomework4.model.Playlists
import com.example.myhomework4.ui.adapter.PlayListAdapter
import com.example.myhomework4.utils.ConnectionLiveData


class MainActivity() : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var adapter: PlayListAdapter
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun setUI() {
        super.setUI()
        adapter = PlayListAdapter(this::onClick)
        binding.recyclerView.adapter = adapter
    }

    override fun setupLiveData(){
        super.setupLiveData()
        viewModel.playlists().observe(this){
            binding.recyclerView.adapter = adapter
            adapter.addList(it.items!! as List<Playlists.Item>)
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.internetConnection.visibility = View.VISIBLE
                binding.noConnection.visibility = View.GONE
            } else {
                binding.internetConnection.visibility = View.GONE
                binding.noConnection.visibility = View.VISIBLE
                setupLiveData()
            }
        }
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater )
    }


    private fun onClick(item: Playlists.Item){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(ID, item.id)
        startActivity(intent)
    }


    companion object{
        const val ID = "ID"
    }
}