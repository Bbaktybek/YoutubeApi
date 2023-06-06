package com.example.myhomework4.model

import android.os.Build.ID
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myhomework4.base.BaseActivity
import com.example.myhomework4.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun setUI() {
        super.setUI()
        getExtra()
    }

    private fun getExtra() {
        val getIntent =
            intent.getStringExtra(ID)
        Toast.makeText(this, getIntent, Toast.LENGTH_SHORT).show()
    }
}