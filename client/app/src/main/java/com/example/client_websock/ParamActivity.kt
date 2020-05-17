package com.example.client_websock

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.param_activity.*

class ParamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.param_activity)


        findViewById<EditText>(R.id.xG1).setText(sPref.getString("xG1", ""))
        findViewById<EditText>(R.id.xGA1).setText(sPref.getString("xGA1", ""))
        findViewById<EditText>(R.id.xGD1).setText(sPref.getString("xGD1", ""))
        findViewById<EditText>(R.id.GDxGD1).setText(sPref.getString("GDxGD1", ""))
        findViewById<EditText>(R.id.xGSh1).setText(sPref.getString("xGSh1", ""))
        findViewById<EditText>(R.id.xGASh1).setText(sPref.getString("xGASh1", ""))
        findViewById<EditText>(R.id.xG901).setText(sPref.getString("xG901", ""))
        findViewById<EditText>(R.id.xGA901).setText(sPref.getString("xGA901", ""))
        findViewById<EditText>(R.id.xG2).setText(sPref.getString("xG2", ""))
        findViewById<EditText>(R.id.xGA2).setText(sPref.getString("xGA2", ""))
        findViewById<EditText>(R.id.xGD2).setText(sPref.getString("xGD2", ""))
        findViewById<EditText>(R.id.GDxGD2).setText(sPref.getString("GDxGD2", ""))
        findViewById<EditText>(R.id.xGSh2).setText(sPref.getString("xGSh2", ""))
        findViewById<EditText>(R.id.xGASh2).setText(sPref.getString("xGASh2", ""))
        findViewById<EditText>(R.id.xG902).setText(sPref.getString("xG902", ""))
        findViewById<EditText>(R.id.xGA902).setText(sPref.getString("xGA902", ""))

        findViewById<Button>(R.id.btn_test).setOnClickListener {
            val xG1 = 41.98
            val xGA1 = 26.98
            val xGD1 = 15.08
            val GDxGD1 = 7.92
            val xGSh1 = 0.13
            val xGASh1 = 0.08
            val xG901 = 1.71
            val xGA901 = 1.1
            val xG2 = 30.1
            val xGA2 = 41.74
            val xGD2 = -11.64
            val GDxGD2 = -0.36
            val xGSh2 = 0.1
            val xGASh2 = 0.14
            val xG902 = 1.21
            val xGA902 = 1.68

            val editor = sPref.edit()
            editor.putString("xG1", xG1.toString())
            editor.putString("xGA1", xGA1.toString())
            editor.putString("xGD1", xGD1.toString())
            editor.putString("GDxGD1", GDxGD1.toString())
            editor.putString("xGSh1", xGSh1.toString())
            editor.putString("xGASh1", xGASh1.toString())
            editor.putString("xG901", xG901.toString())
            editor.putString("xGA901", xGA901.toString())
            editor.putString("xG2", xG2.toString())
            editor.putString("xGA2", xGA2.toString())
            editor.putString("xGD2", xGD2.toString())
            editor.putString("GDxGD2", GDxGD2.toString())
            editor.putString("xGSh2", xGSh2.toString())
            editor.putString("xGASh2", xGASh2.toString())
            editor.putString("xG902", xG902.toString())
            editor.putString("xGA902", xGA902.toString())
            editor.apply()

            findViewById<EditText>(R.id.xG1).setText(sPref.getString("xG1", ""))
            findViewById<EditText>(R.id.xGA1).setText(sPref.getString("xGA1", ""))
            findViewById<EditText>(R.id.xGD1).setText(sPref.getString("xGD1", ""))
            findViewById<EditText>(R.id.GDxGD1).setText(sPref.getString("GDxGD1", ""))
            findViewById<EditText>(R.id.xGSh1).setText(sPref.getString("xGSh1", ""))
            findViewById<EditText>(R.id.xGASh1).setText(sPref.getString("xGASh1", ""))
            findViewById<EditText>(R.id.xG901).setText(sPref.getString("xG901", ""))
            findViewById<EditText>(R.id.xGA901).setText(sPref.getString("xGA901", ""))
            findViewById<EditText>(R.id.xG2).setText(sPref.getString("xG2", ""))
            findViewById<EditText>(R.id.xGA2).setText(sPref.getString("xGA2", ""))
            findViewById<EditText>(R.id.xGD2).setText(sPref.getString("xGD2", ""))
            findViewById<EditText>(R.id.GDxGD2).setText(sPref.getString("GDxGD2", ""))
            findViewById<EditText>(R.id.xGSh2).setText(sPref.getString("xGSh2", ""))
            findViewById<EditText>(R.id.xGASh2).setText(sPref.getString("xGASh2", ""))
            findViewById<EditText>(R.id.xG902).setText(sPref.getString("xG902", ""))
            findViewById<EditText>(R.id.xGA902).setText(sPref.getString("xGA902", ""))

        }
    }

    override fun onBackPressed() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.save -> {
                val editor = sPref.edit()
                editor.putString("xG1", findViewById<EditText>(R.id.xG1).text.toString())
                editor.putString("xGA1", findViewById<EditText>(R.id.xGA1).text.toString())
                editor.putString("xGD1", findViewById<EditText>(R.id.xGD1).text.toString())
                editor.putString("GDxGD1", findViewById<EditText>(R.id.GDxGD1).text.toString())
                editor.putString("xGSh1", findViewById<EditText>(R.id.xGSh1).text.toString())
                editor.putString("xGASh1", findViewById<EditText>(R.id.xGASh1).text.toString())
                editor.putString("xG901", findViewById<EditText>(R.id.xG901).text.toString())
                editor.putString("xGA901", findViewById<EditText>(R.id.xGA901).text.toString())
                editor.putString("xG2", findViewById<EditText>(R.id.xG2).text.toString())
                editor.putString("xGA2", findViewById<EditText>(R.id.xGA2).text.toString())
                editor.putString("xGD2", findViewById<EditText>(R.id.xGD2).text.toString())
                editor.putString("GDxGD2", findViewById<EditText>(R.id.GDxGD2).text.toString())
                editor.putString("xGSh2", findViewById<EditText>(R.id.xGSh2).text.toString())
                editor.putString("xGASh2", findViewById<EditText>(R.id.xGASh2).text.toString())
                editor.putString("xG902", findViewById<EditText>(R.id.xG902).text.toString())
                editor.putString("xGA902", findViewById<EditText>(R.id.xGA902).text.toString())
                editor.apply()
            }
            android.R.id.home -> {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}