package com.stefsk

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.random.Random


class GameFragment : Fragment() {
    var satu =0
    var dua =0
    var tiga = 0
    var point = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null)
        {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text="$playerName's Turn"
            satu = Random.nextInt(1,99)
            dua = Random.nextInt(1,99)
            txtSoal.text = satu.toString() +  " + " + dua.toString()
            tiga = satu+dua
        }

        btnSubmit.setOnClickListener {
            val jawaban = txtAnswer.text.toString()
            if(tiga.toString() == jawaban)
            {
                satu = Random.nextInt(1,99)
                dua = Random.nextInt(1,99)
                txtSoal.text = satu.toString() +  " + " + dua.toString()
                tiga = satu+dua
                point++
            }
            else
            {
                val action1 = GameFragmentDirections.actionGameOver(point)
                Navigation.findNavController(it).navigate(action1)
            }
        }

        btnBack.setOnClickListener {
            val action = GameFragmentDirections.actionMainFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}