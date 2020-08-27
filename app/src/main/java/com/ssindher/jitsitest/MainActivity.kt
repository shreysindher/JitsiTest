package com.ssindher.jitsitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupJitsi()
//        buttonJoin.setOnClickListener { launchJitsi() }
        buttonJoin.setOnClickListener { gotoCustomJitsi() }
    }

    private fun setupJitsi() {
        val serverUrl: URL = try {
            URL("https://meet.jit.si")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            throw RuntimeException("Invalid server URL!")
        }

        val defaultOptions = JitsiMeetConferenceOptions.Builder()
            .setServerURL(serverUrl)
            .setWelcomePageEnabled(false)
            .build()
        JitsiMeet.setDefaultConferenceOptions(defaultOptions)
    }

    private fun launchJitsi() {
        val roomId = editTextId.text.toString()
        if (roomId.isNotBlank()) {
            val options = JitsiMeetConferenceOptions.Builder()
                .setRoom(roomId)
                .build()

            JitsiMeetActivity.launch(this, options)
        }
    }

    private fun gotoCustomJitsi() {
        startActivity(Intent(this, VideoActivity::class.java))
    }

}