package com.example.movieapproomsql.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.RvItemBinding

class ReportAdapter(var list: ArrayList<Report>, var onMyItemClickListener: OnMyItemClickListener) :
    RecyclerView.Adapter<ReportAdapter.Vh>() {

    inner class Vh(var rvItemBinding: RvItemBinding) : RecyclerView.ViewHolder(rvItemBinding.root) {

        fun onBind(report: Report, position: Int) {

            rvItemBinding.nameRv.text = report.objectName
            rvItemBinding.productNameRv.text = report.produvtName
            rvItemBinding.productTypeRv.text = report.productType
            rvItemBinding.dateRv.text = report.productPrice

            rvItemBinding.dateHome.text = report.productDate

            rvItemBinding.editRv.setOnClickListener {

                onMyItemClickListener.itemCLickChange(report, position)

            }

            rvItemBinding.deleteRv.setOnClickListener {

                onMyItemClickListener.itemClickDelete(report, position)

            }

            rvItemBinding.root.setOnClickListener {

                onMyItemClickListener.itemClick(report)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int {

        return list.size

    }


    interface OnMyItemClickListener {

        fun itemClick(report: Report)
        fun itemCLickChange(report: Report, position: Int)
        fun itemClickDelete(report: Report, position: Int)

    }

}