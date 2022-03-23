package top.learningman.mipush

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import top.learningman.mipush.utils.MessageAdapter.MessageHolder.Companion.fromRFC3339
import top.learningman.mipush.view.MessageDialog


class TranslucentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("TranslucentActivity", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translucent)

        val userID = intent.getStringExtra("userID")!!
        val long = intent.getStringExtra("long")!!
        val msgID = intent.getStringExtra("msgID")!!
        val title = intent.getStringExtra("title")!!
        val content = intent.getStringExtra("content")!!
        val time = intent.getStringExtra("createdAt")!!.fromRFC3339()

        val message = MessageDialog.Message(
            title,
            content,
            long,
            time,
            msgID,
            userID
        )
        MessageDialog.show(message, this) {
            finish()
        }
    }
}