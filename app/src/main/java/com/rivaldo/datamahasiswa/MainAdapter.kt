package com.rivaldo.datamahasiswa

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rivaldo.datamahasiswa.databinding.ItemDataMahasiswaBinding
import com.rivaldo.datamahasiswa.response.MahasiswaItem
import com.rivaldo.datamahasiswa.utils.Constant.Companion.WEBVIEW_PDDIKTI_URL


class MainAdapter(private val listMahasiswa: List<MahasiswaItem>, private val context : Context) :
    RecyclerView.Adapter<MainAdapter.DataMahasiswaViewHolder>() {

    inner class DataMahasiswaViewHolder(itemView: View, val binding: ItemDataMahasiswaBinding) :
        RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataMahasiswaViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_data_mahasiswa, parent, false)
        val binding = ItemDataMahasiswaBinding.bind(view)
        return  DataMahasiswaViewHolder(view, binding)
    }

    override fun onBindViewHolder(holder: DataMahasiswaViewHolder, position: Int) {
        val item : MahasiswaItem = listMahasiswa[position]
        holder.binding.apply {
            val str = item.text.toString().split(",").toTypedArray()
            val namaNim = str[0].replace(')', ' ').split('(').toTypedArray()
            val nama = namaNim[0]
            val nim = namaNim[1].trim()

            tvName.text = nama
            tvNim.text = nim
            tvPt.text = str[1].removePrefix(" PT : ").lowercase().replaceFirstChar(Char::uppercase)
            tvProdi.text = str[2].removePrefix(" Prodi: ").lowercase().replaceFirstChar(Char::uppercase)
            layout.setOnClickListener {
                val url = WEBVIEW_PDDIKTI_URL + item.websiteLink
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
                context.startActivity(i)
            }
        }
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

}