package com.alicea.artjog2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AboutPageActivity : AppCompatActivity() {

    private var title: String = "AboutMe"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participant_detail)
        setActionBarTitle(title)

        val tvName: TextView = findViewById(R.id.tv_detail_participant_name)
        val tvCreation: TextView = findViewById(R.id.tv_detail_participant_creation)
        val tvBio: TextView = findViewById(R.id.tv_detail_participant_bio)
        val imgPhoto: ImageView = findViewById(R.id.img_detail_participant)

        tvName.text = getString(R.string.about_page_name)
        tvCreation.text = getString(R.string.about_page_email)
        tvBio.text = ""

        Glide.with(this)
            .load(R.drawable.foto_profil)
            .into(imgPhoto)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}