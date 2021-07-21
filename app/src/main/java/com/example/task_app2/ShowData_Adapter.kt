package com.example.task_app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task_app2.ShowData_Adapter.*
import com.example.task_app2.ShowData_Adapter.ViewHolder as ViewHolder1

class ShowData_Adapter : RecyclerView.Adapter<ViewHolder1>() {

    private var userdata : ArrayList<UserModel> = ArrayList()

    fun additems(items : ArrayList<UserModel>){
        this.userdata = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.activity_show_data_adapter,parent,false)
    )


    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {
       val user = userdata[position]
        holder.bindView(user)
    }



    override fun getItemCount(): Int {
      return userdata.size
    }

    class ViewHolder (var view : View) : RecyclerView.ViewHolder(view){
        private var name = view.findViewById<TextView>(R.id.name)
        private var mobile = view.findViewById<TextView>(R.id.mobile)
        private var id = view.findViewById<TextView>(R.id.id)

        fun  bindView( user : UserModel){
            id.text = user.id.toString()
            name.text = user.name
            mobile.text = user.mobile
        }

    }

}