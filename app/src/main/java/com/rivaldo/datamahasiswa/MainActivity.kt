package com.rivaldo.datamahasiswa

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivaldo.datamahasiswa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            binding.rvData.visibility = View.VISIBLE
            if (binding.etMahasiswa.equals("")) Toast.makeText(
                baseContext,
                "Masukkan Nama / NIM / PT",
                Toast.LENGTH_SHORT
            ).show()
            else
                showListDataMahasiswa(binding.etMahasiswa.text.toString())

        }
    }

    private fun showListDataMahasiswa(name: String) {
        viewModel.getData(name)
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
        }
        viewModel.observeDataMahasiswa().observe(this) { mahasiswa ->
            binding.rvData.adapter = baseContext?.let {
                MainAdapter(
                    mahasiswa, it
                )
            }
        }
    }
}