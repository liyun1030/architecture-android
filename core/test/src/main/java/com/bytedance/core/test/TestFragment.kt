package com.bytedance.core.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bytedance.core.test.databinding.CoreTestFragmentTestBinding as ViewBinding

open class TestFragment : Fragment() {
    private var binding: ViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ViewBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding!!) {
            arguments?.apply {
                // content
                val content = getString(ARGUMENTS_KEY_CONTENT)
                if (content != null) textView.text = content
                // contentResId
                val contentResId = getInt(ARGUMENTS_KEY_CONTENT_RES_ID, -1)
                if (contentResId != -1) textView.setText(contentResId)
            }
        }
    }

    companion object {
        private const val ARGUMENTS_KEY_CONTENT_RES_ID = "arguments_key_content_res_id"
        private const val ARGUMENTS_KEY_CONTENT = "arguments_key_content"

        fun Fragment.setArgs(contentResId: Int) {
            arguments = Bundle().apply {
                putInt(ARGUMENTS_KEY_CONTENT_RES_ID, contentResId)
            }
        }

        fun Fragment.setArgs(content: String) {
            arguments = Bundle().apply {
                putString(ARGUMENTS_KEY_CONTENT, content)
            }
        }
    }
}