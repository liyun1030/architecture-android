package com.bytedance.core.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bytedance.core.test.databinding.CoreTestFragmentTestListBinding as ViewBinding

open class TestListFragment : Fragment() {
    private var binding: ViewBinding? = null
    private val count by lazy { arguments?.getInt(ARGUMENTS_KEY_COUNT) ?: 0 }

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
            recyclerView.adapter = TestListAdapter(count)
        }
    }

    companion object {
        private const val ARGUMENTS_KEY_COUNT = "arguments_key_count"

        fun Fragment.setArgs(count: Int) {
            arguments = Bundle().apply {
                putInt(ARGUMENTS_KEY_COUNT, count)
            }
        }
    }
}

class TestListAdapter(private val count: Int) : Adapter<TestListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TestListViewHolder(TextView(parent.context).apply {
            textSize = 20f
            setPadding(50)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        })

    override fun getItemCount() = count

    override fun onBindViewHolder(holder: TestListViewHolder, position: Int) {
        holder.bind(position)
    }
}

class TestListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textView = view as TextView
    fun bind(position: Int) {
        // 设置 item 数据
        textView.text = itemView.context.getString(R.string.core_test_item, position.toString())
    }
}