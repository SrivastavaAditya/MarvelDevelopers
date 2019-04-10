package com.example.adityasrivastava.marvelcharacters.activities

import android.os.Bundle
import android.view.View
import android.view.ViewManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adityasrivastava.marvelcharacters.R
import com.example.adityasrivastava.marvelcharacters.adapters.CharacterAdapter
import com.example.adityasrivastava.marvelcharacters.models.Response
import com.example.adityasrivastava.marvelcharacters.models.ResultsItem
import com.example.adityasrivastava.marvelcharacters.network.Keys
import com.example.adityasrivastava.marvelcharacters.viewmodels.CharactersVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var charactersVM: CharactersVM
    private lateinit var cAdapter: CharacterAdapter
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charactersVM = ViewModelProviders.of(this).get(CharactersVM::class.java)
        rv_character_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        cAdapter = CharacterAdapter(this)
        rv_character_list.adapter = cAdapter

        charactersVM.getCharacters(this, offset = offset).observe(this, observer)

        rv_character_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)
                (rv.layoutManager as LinearLayoutManager).apply {
                    if (this.findLastCompletelyVisibleItemPosition() == cAdapter.list.size.minus(1)) {
                        offset+=10
                        charactersVM.getCharacters(this@MainActivity, offset = offset).observe(this@MainActivity, observer)
                    }
                }
            }
        })
    }

    var observer = Observer<Response> {

        if (it!=null){
            cAdapter.list.addAll(it.data?.results as ArrayList<ResultsItem>)
            cAdapter.notifyDataSetChanged()
            title = cAdapter.list.size.toString().plus(" ${resources.getString(R.string.marvel_characters)}")
        }else{
            tv_no_character.visibility = View.VISIBLE
        }
    }
}
