package com.room.omdb

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val context = this
        val tilMain = findViewById<TextInputLayout>(R.id.til_main)
        val btnSearch = findViewById<Button>(R.id.btn_search)
        val rvMain = findViewById<RecyclerView>(R.id.rv_main)
        val etMain = findViewById<EditText>(R.id.et_main)

        val appRepository = AppRepository()

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = MoviesAdapter(context, mutableListOf(), appRepository)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        btnSearch.setOnClickListener {
            val searchString = etMain.text.toString()
            lifecycleScope.launch {
                val response = withContext(Dispatchers.IO) {
                    appRepository.getMovies(searchString)
                }
                if (response?.movies != null) {
                    rvMain.adapter = MoviesAdapter(context, response.movies, appRepository)
                }
            }

            etMain.clearFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(etMain.windowToken, 0)
        }

        tilMain.setEndIconOnClickListener {
            etMain.text.clear()
        }

    }
}