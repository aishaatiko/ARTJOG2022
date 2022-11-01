package com.alicea.artjog2022

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class CreationDetail : AppCompatActivity() {

    private var titleBar: String = "Creation Detail"
    private var list: ArrayList<Creation> = arrayListOf()
    private var position: Int = 0

    companion object {
        const val CREATION_POSITION = "creation_position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creation_detail)
        setActionBarTitle(titleBar)

        position = intent.getIntExtra(CREATION_POSITION, 0)

        val tvTitle: TextView = findViewById(R.id.tv_detail_creation_title)
        val tvArtist: TextView = findViewById(R.id.tv_detail_participant_creation)
        val tvMaterial: TextView = findViewById(R.id.tv_creation_material)
        val tvDimension: TextView = findViewById(R.id.tv_creation_dimension)
        val tvDescription: TextView = findViewById(R.id.tv_detail_creation_description)
        val imgPhoto: ImageView = findViewById(R.id.img_detail_creation)

        list.addAll(CreationsData.listData)
        val creation = list[position]

        tvTitle.text = creation.title
        tvArtist.text = creation.artist
        tvMaterial.text = creation.material
        tvDimension.text = creation.dimension
        tvDescription.text = creation.description

        Glide.with(this)
            .load(creation.photo)
            .into(imgPhoto)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        val menuItem = menu.findItem(R.id.action_artist_detail)
        menuItem.title = getString(R.string.artist_detail)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val participantDetailIntent = Intent(this@CreationDetail, ParticipantDetail::class.java)
        participantDetailIntent.putExtra(ParticipantDetail.PARTICIPANT_POSITION, position)
        startActivity(participantDetailIntent)

        return super.onOptionsItemSelected(item)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

}