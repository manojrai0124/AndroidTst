package com.manojrai.androidtst.ui.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manojrai.androidtst.R
import com.manojrai.androidtst.data.model.ForeCastDaily
import com.manojrai.androidtst.utils.common.DateUtils
import kotlinx.android.synthetic.main.item_daily.view.*

class ForecastAdapter(
    private var list: ArrayList<ForeCastDaily.Daily>
) : RecyclerView.Adapter<ForecastAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_daily, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount() = list.size

    fun setList(lis: List<ForeCastDaily.Daily>) {
        this.list = lis as ArrayList<ForeCastDaily.Daily>
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(data: ForeCastDaily.Daily) {
            itemView.tvDate.text = DateUtils.getDate(data.date)
            itemView.tvPressure.text = data.pressure.toString()
            itemView.tvHumidity.text = data.humidity.toString()
            itemView.tvDay.text = data.temp.day.toString()
            itemView.tvNight.text = data.temp.night.toString()
            itemView.tvMaxTemp.text = data.temp.max.toString()
            itemView.tvMinTemp.text = data.temp.min.toString()
        }
    }
}