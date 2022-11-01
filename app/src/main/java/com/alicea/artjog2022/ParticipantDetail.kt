package com.alicea.artjog2022

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ParticipantDetail : AppCompatActivity() {
    private var title: String = "Artist Detail"
    private var list: ArrayList<Participant> = arrayListOf()
    private var artistId: Int = 0

    companion object {
        const val PARTICIPANT_POSITION = "participant_position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participant_detail)
        setActionBarTitle(title)

        val position = intent.getIntExtra(PARTICIPANT_POSITION, 0)

        val tvName: TextView = findViewById(R.id.tv_detail_participant_name)
        val tvCreation: TextView = findViewById(R.id.tv_detail_participant_creation)
        val tvBio: TextView = findViewById(R.id.tv_detail_participant_bio)
        val imgPhoto: ImageView = findViewById(R.id.img_detail_participant)

        list.addAll(ParticipantsData.listData)
        val participant = list[position]

        artistId = participant.id
        tvName.text = participant.name
        tvCreation.text = participant.creation
        tvBio.text = participant.bio

        Glide.with(this)
            .load(participant.photo)
            .into(imgPhoto)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val url = "https://www.artjog.id/mmxxii/artist.php?id="
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url + artistId)
            )
        )

        return super.onOptionsItemSelected(item)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

}