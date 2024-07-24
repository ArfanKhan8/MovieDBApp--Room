package com.room.omdb

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val movieDetails = intent.getSerializableExtra("MOVIE",MovieDetailsResponse::class.java)
        Log.d("logger","${movieDetails?.plot}")


        val imageView: ImageView = findViewById(R.id.imageView)
        val titleTextView: TextView = findViewById(R.id.tv_title_detail)
        val yearTextView: TextView = findViewById(R.id.tv_year_detail)
        val descTextView: TextView = findViewById(R.id.tv_desc_detail)
        val returnButton: Button = findViewById(R.id.btn_return)
        val seeMoreTextView: TextView = findViewById(R.id.tv_see_more)
        val extraDetailsLayout: LinearLayout = findViewById(R.id.extra_details_layout)

        movieDetails?.let {
            Glide.with(this).load(it.poster).into(imageView)
            titleTextView.text = it.title
            yearTextView.text = it.year
            descTextView.text = it.plot

            findViewById<TextView>(R.id.tv_rated).text = "Rated: ${it.rated}"
            findViewById<TextView>(R.id.tv_released).text = "Released: ${it.released}"
            findViewById<TextView>(R.id.tv_runtime).text = "Movie Length: ${it.runtime}"
            findViewById<TextView>(R.id.tv_genre).text = "Genre: ${it.genre}"
            findViewById<TextView>(R.id.tv_director).text = "Director: ${it.director}"
            findViewById<TextView>(R.id.tv_writer).text = "Writer: ${it.writer}"
            findViewById<TextView>(R.id.tv_actors).text = "Actors: ${it.actors}"
            findViewById<TextView>(R.id.tv_language).text = "Language: ${it.language}"
            findViewById<TextView>(R.id.tv_country).text = "Country: ${it.country}"
            findViewById<TextView>(R.id.tv_awards).text = "Awards: ${it.awards}"
            findViewById<TextView>(R.id.tv_ratings).text = "IMDB Rating: ${it.imdbRating}"
        }

        seeMoreTextView.setOnClickListener {
            if (extraDetailsLayout.visibility == LinearLayout.GONE) {
                extraDetailsLayout.visibility = LinearLayout.VISIBLE
                seeMoreTextView.text = "See less details"
            } else {
                extraDetailsLayout.visibility = LinearLayout.GONE
                seeMoreTextView.text = "See more details"
            }
        }

        returnButton.setOnClickListener {
            finish()
        }

    }
}