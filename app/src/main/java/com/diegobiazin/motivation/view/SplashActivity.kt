package com.diegobiazin.motivation.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.diegobiazin.motivation.R
import com.diegobiazin.motivation.util.MotivationConstants
import com.diegobiazin.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurity: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurity = SecurityPreferences(this)

        buttonSave.setOnClickListener(this)

        verifyUserName()
    }

    private fun verifyUserName() {
        val userName = mSecurity.getStoreString(MotivationConstants.KEY.PERSON_NAME)
        if (userName != "")
            startActivity(Intent(this, MainActivity::class.java))

        editName.setText(userName)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSave)
            handleSave()
    }

    private fun handleSave() {
        val name: String = editName.text.toString()

        if (name == "")
            Toast.makeText(this, getString(R.string.informe_nome), Toast.LENGTH_LONG).show()
        else {
            mSecurity.storeString(MotivationConstants.KEY.PERSON_NAME, name)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Impede que seja possível voltar a esta activity
            //finish()
        }
    }
}
