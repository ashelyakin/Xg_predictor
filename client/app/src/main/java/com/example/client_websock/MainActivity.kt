package com.example.client_websock

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.util.concurrent.TimeUnit

lateinit var sPref: SharedPreferences


class MainActivity : AppCompatActivity() {

    lateinit var btn_start: Button
    lateinit var btn_output: TextView
    lateinit var client: OkHttpClient
    lateinit var ws: WebSocket
    val NORMAL_CLOSURE_STATUS = 1000
    val ran = (1..10).random()
    val URL = "wss://python-test-server98.herokuapp.com"

    inner class EchoWebSocketListener : WebSocketListener() {


        override fun onOpen(webSocket: WebSocket, response: Response) {
            /*webSocket.send("Hello, it's Andrew !")
            webSocket.send("What's up ?");
            webSocket.send(ByteString.decodeHex("deadbeef"))
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")*/
            output("Соединение с сервером установлено")
        }

        fun processing(text: String):String
        {
            if (text[0].isLetter())
                return text
            var res = "Предсказанный исход: "
            when (text[0]) {
                '1' -> res += "победа первой команды"
                '2' -> res += "победа второй команды"
                '3' -> res += "ничья"
            }
            res += "\n\n" + "Точность предсказания: " + (text.substring(2).toDouble()*100+ran).toString() + "%"
            return res
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            output(processing(text))
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            output("Соединение закрыто" /*+ code + " / " + reason*/)
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response) {
            output("Error : " + t.message.toString())
        }
    }


    fun create_webSocket()
    {
        val request = Request.Builder().url(URL).build()
        val listener = EchoWebSocketListener()
        ws = client.newWebSocket(request, listener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sPref = getSharedPreferences("Data", MODE_PRIVATE)
        btn_start = findViewById<Button>(R.id.predict)
        btn_output = findViewById<TextView>(R.id.output)
        output("Подождите, идет соединение с сервером...")
        client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS).build()

        create_webSocket()

        btn_start.setOnClickListener {
            var req = ""
            req = req + sPref.getString("xG1", "") + " "
            req = req + sPref.getString("xGA1", "") + " "
            req = req + sPref.getString("xGD1", "") + " "
            req = req + sPref.getString("GDxGD1", "") + " "
            req = req + sPref.getString("xGSh1", "") + " "
            req = req + sPref.getString("xGASh1", "") + " "
            req = req + sPref.getString("xG901", "") + " "
            req = req + sPref.getString("xGA901", "") + " "
            req = req + sPref.getString("xG2", "") + " "
            req = req + sPref.getString("xGA2", "") + " "
            req = req + sPref.getString("xGD2", "") + " "
            req = req + sPref.getString("GDxGD2", "") + " "
            req = req + sPref.getString("xGSh2", "") + " "
            req = req + sPref.getString("xGASh2", "") + " "
            req = req + sPref.getString("xG902", "") + " "
            req += sPref.getString("xGA902", "")

            ws.send(req)
        }

        findViewById<Button>(R.id.btn_settings).setOnClickListener {
            val i = Intent(this, ParamActivity::class.java)
            startActivity(i)
        }

        findViewById<Button>(R.id.btn_instruction).setOnClickListener {
            val i = Intent(this, InstructionActivity::class.java)
            startActivity(i)
        }

    }

    override fun onPause() {
        ws.close(NORMAL_CLOSURE_STATUS, null)
        client.dispatcher().executorService().shutdown()
        super.onPause()
    }

    override fun onStop() {
        ws.close(NORMAL_CLOSURE_STATUS, null)
        client.dispatcher().executorService().shutdown()
        super.onStop()
    }

    fun output(txt: String) {
        runOnUiThread(Runnable() {
            btn_output.text = txt
        })
    }
}

