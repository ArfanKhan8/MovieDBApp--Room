package com.room.omdb

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    private val appRepository: AppRepository
): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(itemView: View): ViewHolder(itemView){
        val tvMovieTitle: TextView = itemView.findViewById(R.id.tv_title)
        val cvMovieItem: CardView = itemView.findViewById(R.id.cv_movie_item)
        val tvMovieYear: TextView = itemView.findViewById(R.id.tv_year)
        val ivMoviePoster: ImageView = itemView.findViewById(R.id.Iv_Image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int){
        val movie = movies[position]
        holder.tvMovieTitle.text = movie.title
        holder.tvMovieYear.text = movie.year
        Glide.with(holder.ivMoviePoster)
            .load(movie.poster)
            .into(holder.ivMoviePoster)

        holder.cvMovieItem.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    movie.imdbID?.let{id ->
                        val response = appRepository.getMovieDetails(id)
                        if (response != null){
                            val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                                putExtra("MOVIE", response)
                            }
                            context.startActivity(intent)

                        }
                    }
                }catch (e: Exception){
                    Log.d("logger", "${e}")
                }
            }
        }
    }


}