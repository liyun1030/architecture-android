package com.bytedance.core.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.core.test.databinding.CoreTestActivityTestBinding as ViewBinding

open class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            intent?.apply {
                // content
                val content = getStringExtra(ARGUMENTS_KEY_CONTENT)
                if (content != null) textView.text = content
                // contentResId
                val contentResId = getIntExtra(ARGUMENTS_KEY_CONTENT_RES_ID, -1)
                if (contentResId != -1) textView.setText(contentResId)
            }
        }
    }

    companion object {
        private const val ARGUMENTS_KEY_CONTENT_RES_ID = "arguments_key_content_res_id"
        private const val ARGUMENTS_KEY_CONTENT = "arguments_key_content"

        fun startActivity(activity: Activity, contentResId: Int, clazz: Class<*>) {
            activity.startActivity(
                Intent(activity.applicationContext, clazz)
                    .putExtra(ARGUMENTS_KEY_CONTENT_RES_ID, contentResId)
            )
        }

        fun startActivity(activity: Activity, content: String, clazz: Class<*>) {
            activity.startActivity(
                Intent(activity.applicationContext, clazz)
                    .putExtra(ARGUMENTS_KEY_CONTENT, content)
            )
        }
    }
}