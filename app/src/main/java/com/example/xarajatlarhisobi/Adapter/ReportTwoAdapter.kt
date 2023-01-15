package com.example.movieapproomsql.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.RvItemBinding
import com.example.xarajatlarhisobi.databinding.RvTwoItemBinding

class ReportTwoAdapter(var list: ArrayList<Report>) :
    RecyclerView.Adapter<ReportTwoAdapter.Vh>() {

    inner class Vh(var rvTwoItemBinding: RvTwoItemBinding) : RecyclerView.ViewHolder(rvTwoItemBinding.root) {

        fun onBind(report: Report, position: Int) {

            rvTwoItemBinding.objectNameTwo.text = report.objectName
           // rvTwoItemBinding.productNameTwo.text = report.produvtName
            rvTwoItemBinding.productPriceTwo.text = report.productPrice

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(RvTwoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int {

        return list.size

    }


}