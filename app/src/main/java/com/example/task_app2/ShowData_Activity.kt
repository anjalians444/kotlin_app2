package com.example.task_app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShowData_Activity : AppCompatActivity() {
    private lateinit var sQliteHelper: SQliteHelper
    private lateinit var rc: RecyclerView
  //  private lateinit var layoutmanaget:RecyclerView.LayoutManager
    private var adapter: ShowData_Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
        inti()
        recyclerinti()
        sQliteHelper = SQliteHelper(this)
        getalluser()
    }

    private fun recyclerinti() {
      rc.layoutManager = LinearLayoutManager(this)
        adapter = ShowData_Adapter()
        rc.adapter = adapter
    }
    private fun getalluser(){
        val userdata = sQliteHelper.getAllusers()
        Log.e("hiii","${userdata.size}")
    }

    private fun inti() {
        rc = findViewById<RecyclerView>(R.id.recycler)
    }
}