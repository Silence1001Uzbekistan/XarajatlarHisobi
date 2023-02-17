
package com.example.movieapproomsql.Adapter

import android.annotation.SuppressLint
import android.net.Uri
import com.example.xarajatlarhisobi.Dao.ReportDao
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xarajatlarhisobi.Models.Report
import com.example.xarajatlarhisobi.databinding.RvItemBinding

class ReportAdapter(var reportDao: ReportDao,var list: ArrayList<Report>, var onMyItemClickListener: OnMyItemClickListener) :
    RecyclerView.Adapter<ReportAdapter.Vh>() {

    inner class Vh(var rvItemBinding: RvItemBinding) : RecyclerView.ViewHolder(rvItemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(report: Report, position: Int) {


            rvItemBinding.nameRv.text = report.objectName
            rvItemBinding.productNameRv.text = report.produvtName
            rvItemBinding.productTypeRv.text = report.productType
          //  rvItemBinding.dateRv.text = report.productPrice

            rvItemBinding.showRv.text = report.productNumber
            rvItemBinding.lengthRv.text = report.productLength

          //  rvItemBinding.dateHome.text = report.productDate

            rvItemBinding.imageRV.setImageURI(Uri.parse(report.productImage))


            rvItemBinding.minusRv.setOnClickListener {

/*                if (report.productNumber!!.toInt() > 0) {

                    rvItemBinding.showRv.text = (report.productNumber!!.toInt() - 1).toString()
                    report.productNumber = (report.productNumber!!.toInt() - 1).toString()
                    reportDao.updateReport(report)

                }*/

                onMyItemClickListener.itemCLickChangeMinus(report, position)



            }

/*            rvItemBinding.plusRv.setOnClickListener {

                rvItemBinding.showRv.text = (report.productNumber!!.toInt() + 1).toString()
                report.productNumber = (report.productNumber!!.toInt() + 1).toString()
                reportDao.updateReport(report)

            }*/


            rvItemBinding.editRv.setOnClickListener {

                onMyItemClickListener.itemCLickChange(report, position)

            }

            rvItemBinding.deleteRv.setOnClickListener {

                onMyItemClickListener.itemClickDelete(report, position)

            }

            rvItemBinding.imageEye.setOnClickListener {

                onMyItemClickListener.itemClick(report)

            }


            rvItemBinding.showAloneRv.setOnClickListener {

                onMyItemClickListener.itemClickAlone(report)

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
        fun itemClickAlone(report: Report)
        fun itemCLickChange(report: Report, position: Int)
        fun itemClickDelete(report: Report, position: Int)
        fun itemCLickChangeMinus(report: Report, position: Int)
        fun itemCLickChangePlus(report: Report, position: Int)

    }

}