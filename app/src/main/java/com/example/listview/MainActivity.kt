package com.example.listview

import User
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val userList = mutableListOf<User>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)
        val saveBTN = findViewById<Button>(R.id.saveBTN)
        val listViewLV = findViewById<ListView>(R.id.listViewLV)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listViewLV.adapter = adapter

        saveBTN.setOnClickListener{
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toIntOrNull()
            if (name.isNotEmpty() && age != null){
                val user = User(name, age)
                userList.add(user)

                adapter.add("${user.name}, ${user.age} лет")
                adapter.notifyDataSetChanged()

                editTextName.text.clear()
                editTextAge.text.clear()
            }else{
                Toast.makeText(this, "Введите корректные данные", Toast.LENGTH_LONG).show()
            }
        }

        listViewLV.setOnItemClickListener { _,_, position, _ ->
            userList.removeAt(position)
            adapter.remove(adapter.getItem(position))
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_exit ->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}