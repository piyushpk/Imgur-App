package com.hspl.imgur_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hspl.imgur_app.databinding.CellDashboardGridBinding
import com.hspl.imgur_app.databinding.CellDashboardListBinding
import com.hspl.imgur_app.model.DashboardModel

class DashboardAdapter(private val layoutManager: GridLayoutManager? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType { LIST, GRID }

    private var mList = ArrayList<DashboardModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTicketList(movieList: List<DashboardModel>) {
        this.mList = movieList as ArrayList<DashboardModel>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == ViewType.LIST.ordinal) {
            GridViewHolder(CellDashboardGridBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            ListViewHolder(CellDashboardListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
    }

    override fun getItemCount() = mList.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) {
            ViewType.GRID.ordinal
        } else {
            ViewType.LIST.ordinal
        }
    }

    class ListViewHolder(binding: CellDashboardListBinding) : RecyclerView.ViewHolder(binding.root)

    class GridViewHolder(binding: CellDashboardGridBinding) : RecyclerView.ViewHolder(binding.root)
}