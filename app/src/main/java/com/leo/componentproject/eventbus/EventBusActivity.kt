package com.leo.componentproject.eventbus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.leo.componentproject.R
import kotlinx.android.synthetic.main.activity_rx_bus.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val TAG = "EventBusActivity"
    }

    override fun onClick(v: View?) {
        when (v) {
            button -> {

            }
            button2 -> {
                EventBus.getDefault().post(EventBusMessage(name = "aaa"))
            }
            button3 -> {
                EventBus.getDefault().post(EventBusMessage(name = "bbb"))
            }
            button4 -> {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        setContentView(R.layout.activity_rx_bus)
        button.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    @Subscribe
    fun eat(message: EventBusMessage) {
        textView.text = "普通发送${message.name}"
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun eatMore(message: EventBusMessage) {
        textView.text ="异步"
        Log.d(TAG,"Thread Name:"+Thread.currentThread())
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    data class EventBusMessage(val name: String)
}
