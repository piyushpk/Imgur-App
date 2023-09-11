package com.hspl.imgur_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hspl.imgur_app.R
import com.hspl.imgur_app.databinding.CellDashboardGridBinding
import com.hspl.imgur_app.databinding.CellDashboardListBinding
import com.hspl.imgur_app.model.DashboardModel
import com.hspl.imgur_app.utils.AppUtils
import com.squareup.picasso.Picasso

class DashboardAdapter(private val layoutManager: GridLayoutManager? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType { LIST, GRID }

    private var mList = ArrayList<DashboardModel>()

    @SuppressLint("NotifyDataSetChanged")
    // To set searched list to the adapter
    fun setTicketList(movieList: List<DashboardModel>) {
        this.mList = movieList as ArrayList<DashboardModel>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.LIST.ordinal) {
            GridView(CellDashboardGridBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            ListView(CellDashboardListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ViewType.LIST.ordinal) {
            (holder as GridView).bind(mList[position])
        } else {
            (holder as ListView).bind(mList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) {
            ViewType.GRID.ordinal
        } else {
            ViewType.LIST.ordinal
        }
    }

    override fun getItemCount() = mList.size

    inner class ListView(private val cellDashboardListBinding: CellDashboardListBinding) :
        RecyclerView.ViewHolder(cellDashboardListBinding.root) {
        fun bind(dashboardModel: DashboardModel) {
            cellDashboardListBinding.txtTitle.text = dashboardModel.title
            cellDashboardListBinding.txtDate.text = AppUtils.convertDateTime(dashboardModel.datetime)
            cellDashboardListBinding.txtTotal.text = dashboardModel.images_count
            try {
                Picasso.get()
                    .load(dashboardModel.imagesModel[0].link)
                    .placeholder(R.drawable.ic_action_logo)
                    .into(cellDashboardListBinding.imageView)
            } catch (_: Exception) {

            }
        }
    }

    inner class GridView(private val cellDashboardGridBinding: CellDashboardGridBinding) :
        RecyclerView.ViewHolder(cellDashboardGridBinding.root) {
        fun bind(dashboardModel: DashboardModel) {
            cellDashboardGridBinding.txtTitle.text = dashboardModel.title
            cellDashboardGridBinding.txtDate.text = AppUtils.convertDateTime(dashboardModel.datetime)
            cellDashboardGridBinding.txtTotal.text = dashboardModel.images_count
            try {
                cellDashboardGridBinding.imageView.visibility = VISIBLE
                Picasso.get()
                    .load(dashboardModel.imagesModel[0].link)
                    .placeholder(R.drawable.ic_action_logo)
                    .into(cellDashboardGridBinding.imageView)
            } catch (_: Exception) {

            }
        }
    }
}