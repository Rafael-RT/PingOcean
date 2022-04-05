package com.example.pingocean

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = Authorization()

        supportFragmentManager.beginTransaction().add(R.id.main, fragment).commit()

    }



}