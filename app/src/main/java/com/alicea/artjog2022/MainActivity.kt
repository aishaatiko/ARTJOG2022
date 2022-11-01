package com.alicea.artjog2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var listParticipants: ArrayList<Participant> = arrayListOf()
    private var listCreations: ArrayList<Creation> = arrayListOf()
    private var title: String = "ARTJOG 2022"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        recyclerView = findViewById(R.id.rv_gallery)
        recyclerView.setHasFixedSize(true)

        listParticipants.addAll(ParticipantsData.listData)
        listCreations.addAll(CreationsData.listData)
        showRecyclerCreationsGrid()

    }

    private fun showRecyclerParticipantsList() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val participantListAdapter = ParticipantListAdapter(listParticipants)
        recyclerView.adapter = participantListAdapter

        participantListAdapter.setOnItemClickCallback(object : ParticipantListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Participant, position: Int) {
                showSelectedParticipant(data, position)
            }
        })
    }

    private fun showRecyclerCreationsGrid() {
        recyclerView.layoutManager = GridLayoutManager(this,1)
        val creationGridAdapter = CreationGridAdapter(listCreations)
        recyclerView.adapter = creationGridAdapter

        creationGridAdapter.setOnItemClickCallback(object : CreationGridAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Creation, position: Int) {
                showSelectedCreation(data, position)
            }
        })
    }


    private fun showSelectedParticipant(data: Participant, position: Int) {
        Toast.makeText(this, "Profil " + data.name, Toast.LENGTH_SHORT).show()
        val participantDetailIntent = Intent(this@MainActivity, ParticipantDetail::class.java)
        participantDetailIntent.putExtra(ParticipantDetail.PARTICIPANT_POSITION, position)
        startActivity(participantDetailIntent)
    }

    private fun showSelectedCreation(data: Creation, position: Int) {
        Toast.makeText(this, data.title + " by " + data.artist, Toast.LENGTH_SHORT).show()
        val creationDetailIntent = Intent(this@MainActivity, CreationDetail::class.java)
        creationDetailIntent.putExtra(CreationDetail.CREATION_POSITION, position)
        startActivity(creationDetailIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_participant -> {
                title = "ARTJOG Participants"
                showRecyclerParticipantsList()
            }

            R.id.action_creation -> {
                title = "ARTJOG Gallery"
                showRecyclerCreationsGrid()
            }

            R.id.action_about_page -> {
                val aboutPageActivityIntent = Intent(this@MainActivity, AboutPageActivity::class.java)
                startActivity(aboutPageActivityIntent)
            }
        }

        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

}