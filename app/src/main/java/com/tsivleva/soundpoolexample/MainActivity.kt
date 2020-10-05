package com.tsivleva.soundpoolexample

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

const val SOUNDS_POOL_SIZE = 3

class MainActivity : AppCompatActivity() {
    private var soundPool: SoundPool? = null
    private var winID: Int? = null
    private var chpokID: Int? = null
    private var taDaID: Int? = null

    private var leftVolume = 1f
    private var rightVolume = 1f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soundPool = createSoundPool()
        chpokID = soundPool?.load(applicationContext, R.raw.chpok, 1)
        winID = soundPool?.load(applicationContext, R.raw.fanfare_win, 1)
        taDaID = soundPool?.load(applicationContext, R.raw.ta_da, 1)
        winBTN.setOnClickListener {
            soundPool?.play(winID ?: 0, leftVolume, rightVolume, 0, 0, 1f)
        }
        chpokBTN.setOnClickListener {
            soundPool?.play(chpokID ?: 0, leftVolume, rightVolume, 0, 0, 1f)
        }
        tadaBTN.setOnClickListener {
            soundPool?.play(taDaID ?: 0, leftVolume, rightVolume, 0, 0, 1f)
        }
    }

    private fun createSoundPool(): SoundPool {
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setLegacyStreamType(AudioManager.STREAM_MUSIC)
            .build()
        return SoundPool.Builder().setMaxStreams(SOUNDS_POOL_SIZE)
            .setAudioAttributes(attrs)
            .build()
    }
}