package com.project.eratani.features.heart_animation

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.project.eratani.R
import com.project.eratani.core.utils.constants.defaultHeartBeatDuration
import com.project.eratani.databinding.FragmentHeartAnimationBinding
import com.project.eratani.databinding.FragmentSearchBinding


class HeartAnimationFragment : Fragment() {
    private var _binding: FragmentHeartAnimationBinding? = null
    private val binding get() = _binding!!

    private lateinit var scaleXAnimator: ObjectAnimator
    private lateinit var scaleYAnimator: ObjectAnimator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        handleHeartAnimation()
    }

    private fun initData() {
        binding.speedLabel.text = "$defaultHeartBeatDuration ms"
        binding.speedSeekBar.progress = defaultHeartBeatDuration

        scaleXAnimator = ObjectAnimator.ofFloat(binding.ivHeart, "scaleX", 1f, 1.2f, 1f).apply {
            duration = defaultHeartBeatDuration.toLong()
            repeatCount = ObjectAnimator.INFINITE
        }
        scaleYAnimator = ObjectAnimator.ofFloat(binding.ivHeart, "scaleY", 1f, 1.2f, 1f).apply {
            duration = defaultHeartBeatDuration.toLong()
            repeatCount = ObjectAnimator.INFINITE
        }
    }

    private fun handleHeartAnimation() {
        scaleXAnimator.start()
        scaleYAnimator.start()

        binding.speedSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val duration = progress.toLong()
                scaleXAnimator.duration = duration
                scaleYAnimator.duration = duration
                binding.speedLabel.text = "${duration} ms"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeartAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}