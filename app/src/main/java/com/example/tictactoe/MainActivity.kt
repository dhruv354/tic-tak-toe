package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), android.view.View.OnClickListener{
    lateinit var board : Array<Array<Button>>
    var Player = true
    var turnCount = 0
    var boardStatus = Array(3){IntArray(3)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val button1: Button? = findViewById()
        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        resetBtn.setOnClickListener {
            initializeBoardStatus()
            turnCount = 0
            Player = true
        }
        initializeBoardStatus()
    }

    private  fun initializeBoardStatus(){
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
        updateDisplay("Player X turn")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button1 -> {
                updateValue(row=0, col=0, player=Player)
            }
            R.id.button2 -> {
                updateValue(row=0, col=1, player=Player)
            }
            R.id.button3 -> {
                updateValue(row=0, col=2, player=Player)
            }
            R.id.button4 -> {
                updateValue(row=1, col=0, player=Player)
            }
            R.id.button5 -> {
                updateValue(row=1, col=1, player=Player)
            }
            R.id.button6 -> {
                updateValue(row=1, col=2, player=Player)
            }
            R.id.button7 -> {
                updateValue(row=2, col=0, player=Player)
            }
            R.id.button8 -> {
                updateValue(row=2, col=1, player=Player)
            }
            R.id.button9 -> {
                updateValue(row=2, col=2, player=Player)
            }
        }
        turnCount++
        Player = !Player
        if(Player){
            updateDisplay("Player X turn")
        }
        else{
            updateDisplay("Player O turn ")
        }
        if(turnCount == 9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun updateValue(row: Int, col: Int, player:Boolean){
        val text = if (player) "X" else "O"
        val value = if(player) 1 else 0
        board[row][col].apply{
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }

    private fun updateDisplay(s: String){
        displayTv.text = s
        if(s.contains("win")){
            disableButton()
        }
    }

    private fun checkWinner(){
        //check if horizontal rows are all same
        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2]){
//                disableButton()
                if(boardStatus[i][0] == 1){
                    updateDisplay("Player X wins")
                    return
                }
                else if(boardStatus[i][0] == 0){
                    updateDisplay("Player O wins")
                    return
                }
            }
        }
        //check all the columns
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i]){
//                disableButton()
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player X wins")
                    return
                }
                else if(boardStatus[0][i] == 0){
                    updateDisplay("Player O wins")
                    return
                }
            }
        }

        //check both the diagnals
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[2][2] == boardStatus[1][1]){
//            disableButton()
            if(boardStatus[0][0] == 1){
                updateDisplay("Player X wins")
                return
            }
            else if(boardStatus[0][0] == 0){
                updateDisplay("Player O wins")
                return
            }
        }

        if(boardStatus[2][0] == boardStatus[1][1] && boardStatus[2][0] == boardStatus[0][2]){
//            disableButton()
            if(boardStatus[1][1] == 1){
                updateDisplay("Player X wins")
                return
            }
            else if(boardStatus[1][1] == 0){
                updateDisplay("Player O wins")
                return
            }
        }
    }

    private fun disableButton(){
        for(i in board){
            for(button in i){
                button.isEnabled = false
            }
        }
    }
}