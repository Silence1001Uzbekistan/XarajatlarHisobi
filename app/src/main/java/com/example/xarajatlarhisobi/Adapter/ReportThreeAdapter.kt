package com.example.movieapproomsql.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappviewbindingandcache.Cache.MySharedPreferenceType
import com.example.xarajatlarhisobi.Models.ObjectMinus
import com.example.xarajatlarhisobi.databinding.RvThreeItemBinding

class ReportThreeAdapter(var list: ArrayList<ObjectMinus>) :
    RecyclerView.Adapter<ReportThreeAdapter.Vh>() {

    inner class Vh(var rvThreeItemBinding: RvThreeItemBinding) : RecyclerView.ViewHolder(rvThreeItemBinding.root) {

        fun onBind(objectMinus: ObjectMinus, position: Int) {

            //rvThreeItemBinding.objectNameTwo.text = objectMinus.objectName
            rvThreeItemBinding.userNameTwo.text = objectMinus.giveName
            rvThreeItemBinding.productPriceTwo.text = "${objectMinus.cash} ${MySharedPreferenceType.productTypeCache}"
            rvThreeItemBinding.textDate.text = objectMinus.dateName

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(RvThreeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int {

        return list.size

    }


}