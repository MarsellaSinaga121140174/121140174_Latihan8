package com.example.latihan_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.latihan_8.database.MyApp
import com.example.latihan_8.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rvUser
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(emptyList())
        recyclerView.adapter = adapter

        Thread {
            val userDao = MyApp.database.userDao()
            val users = userDao.getAllUsers()
            runOnUiThread{
                adapter.setData(users)
            }
        }.start()
    }
}