package kr.com.ticketpass.host

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ActivityHostMainBinding
import kr.com.ticketpass.viewmodel.HostMainViewModel

class HostMainActivity : AppCompatActivity() {
    private val viewModel: HostMainViewModel by lazy {
        ViewModelProvider(this).get(HostMainViewModel::class.java)
    }

    private lateinit var binding: ActivityHostMainBinding
    private lateinit var adapter: HostMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_host_main)
        binding.viewModel = viewModel

        binding.hostMainAddBtn.setOnClickListener {
            startActivity(Intent(this, HostEventManageActivity::class.java))
        }

        initRecyclerView()
        setLivedataObserver()
        viewModel.getTicketList()
    }

    private fun initRecyclerView() {
        adapter = HostMainAdapter(this, mutableListOf(), binding.hostMainList)
        binding.hostMainList.run {
            layoutManager =
                LinearLayoutManager(this@HostMainActivity, RecyclerView.VERTICAL, false)
            adapter = this@HostMainActivity.adapter
        }
    }

    private fun setLivedataObserver() {
        viewModel.getTicketSuccess.observe(this, Observer {
            adapter.addList(viewModel.allTicketList)
        })
    }

}