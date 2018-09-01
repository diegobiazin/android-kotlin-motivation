package com.diegobiazin.motivation.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.diegobiazin.motivation.R
import com.diegobiazin.motivation.mock.Mock
import com.diegobiazin.motivation.util.MotivationConstants
import com.diegobiazin.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mFilter: Int = MotivationConstants.PHRASE_FILTER.ALL
    private lateinit var mSecurityPreferences : SecurityPreferences
    private val mMock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)

        //Eventos
        setListeners()

        //inicializa
        handleFilter(R.id.imageAll)
        refreshPhrase()
        verifyUserName()
    }

    private fun verifyUserName() {
        textUserName.text = mSecurityPreferences.getStoreString(MotivationConstants.KEY.PERSON_NAME)
    }

    override fun onClick(view: View) {
        val id = view.id

        val listId = listOf(R.id.imageAll, R.id.imageSun, R.id.imageHappy)
        if (id in listId) {
            handleFilter(id)
        } else if (id == R.id.buttonNewPhrase) {
            refreshPhrase()
        }
    }

    private fun setListeners() {
        imageAll.setOnClickListener(this)
        imageSun.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        buttonNewPhrase.setOnClickListener(this)
    }

    private fun handleFilter(id: Int) {

        imageAll.setImageResource(R.drawable.ic_all_unselected)
        imageSun.setImageResource(R.drawable.ic_sun_unselected)
        imageHappy.setImageResource(R.drawable.ic_happy_unselected)

        if (id == R.id.imageAll) {
            mFilter = MotivationConstants.PHRASE_FILTER.ALL
            imageAll.setImageResource(R.drawable.ic_all_selected)
        } else if (id == R.id.imageSun) {
            mFilter = MotivationConstants.PHRASE_FILTER.SUN
            imageSun.setImageResource(R.drawable.ic_sun_selected)
        } else {
            mFilter = MotivationConstants.PHRASE_FILTER.HAPPY
            imageHappy.setImageResource(R.drawable.ic_happy_selected)
        }
    }

    private fun refreshPhrase() {
        textPhrase.text = mMock.getPhrase(mFilter)
    }

}
