package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.helloworld.databinding.ActivityMainBinding
import java.time.Duration

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var countDownTimer: CountDownTimer
    private var timerDuration: Long = 60000
    private var pauseOffset: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTimer.text = "${(timerDuration/1000).toString()}"

        binding.startTimer.setOnClickListener{
            startTimer(pauseOffset)
        }

        binding.pauseTimer.setOnClickListener{
            pauseTimer()
        }

        binding.resetTimer.setOnClickListener{
            resetTimer()
        }
        binding.minutePicker.setOnClickListener {

        }

    }

    private fun doNothing() {

    }

    private  fun startTimer(pauseOffsetL: Long) {
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL,1000) {
            override fun onTick(millisUntillFinished: Long) {
                pauseOffset = timerDuration - millisUntillFinished
                binding.tvTimer.text =
                    (millisUntillFinished/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity,"PIZZA IS READY!",Toast.LENGTH_LONG).show()
            }
        }.start()

    }

    private fun pauseTimer(){
        countDownTimer.cancel()

    }

    private fun resetTimer(){
        countDownTimer.cancel()
        binding.tvTimer.text = "${(timerDuration/1000).toString()}"
        pauseOffset = 0
    }








}