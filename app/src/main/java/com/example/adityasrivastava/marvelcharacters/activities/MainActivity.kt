package com.example.adityasrivastava.marvelcharacters.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adityasrivastava.marvelcharacters.R
import com.example.adityasrivastava.marvelcharacters.adapters.CharacterAdapter
import com.example.adityasrivastava.marvelcharacters.models.Response
import com.example.adityasrivastava.marvelcharacters.models.ResultsItem
import com.example.adityasrivastava.marvelcharacters.viewmodels.CharactersVM
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MainActivity
 */
class MainActivity : AppCompatActivity() {
    /**
     *CharactersVM
     */
    lateinit var charactersVM: CharactersVM
    /**
     * cAdapter
     */
    private lateinit var cAdapter: CharacterAdapter
    /**
     * Offset
     */
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting Initial Title
        title = "0 ".plus(resources.getString(R.string.marvel_characters))

        //Initializing ViewModel
        charactersVM = ViewModelProviders.of(this).get(CharactersVM::class.java)

        //Initializing RecyclerView
        rv_character_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //Initializing Adapter
        cAdapter = CharacterAdapter(this)

        //Binding Adapter to RecyclerView
        rv_character_list.adapter = cAdapter

        //Getting Response For 1st Time
        charactersVM.getCharacters(this, offset = offset).observe(this, observer)

        //Pagination in RecyclerView
        rv_character_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)
                (rv.layoutManager as LinearLayoutManager).apply {
                    if (this.findLastCompletelyVisibleItemPosition() == cAdapter.list.size.minus(1)) {
                        offset+=10
                        charactersVM.getCharacters(this@MainActivity, offset = offset)
                            .observe(this@MainActivity, observer)
                    }
                }
            }
        })
    }

    /**
     * Observer
     */
    var observer = Observer<Response> {

        if (it!=null){
            tv_no_character.visibility = View.GONE
            cAdapter.list.addAll(it.data?.results as ArrayList<ResultsItem>)
            cAdapter.notifyDataSetChanged()
            title = cAdapter.list.size.toString().plus(" ${resources.getString(R.string.marvel_characters)}")
        }else{
            tv_no_character.visibility = View.VISIBLE
        }
    }
}