package com.example.task_app2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var edName : EditText
    private lateinit var edMobile : EditText
    private lateinit var btnadd : Button
    private lateinit var btnshow : Button
    private lateinit var rc: RecyclerView
    //  private lateinit var layoutmanaget:RecyclerView.LayoutManager
    private var adapter: ShowData_Adapter? = null

    private lateinit var sQliteHelper: SQliteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initi()
        recyclerinti()
       // getalluser()
        sQliteHelper = SQliteHelper(this)
    }
    private fun recyclerinti() {
        rc.layoutManager = LinearLayoutManager(this)
        adapter = ShowData_Adapter()
        rc.adapter = adapter
    }


    private fun initi() {
        edName = findViewById<EditText>(R.id.edname)
        edMobile = findViewById<EditText>(R.id.edmobile)
        rc = findViewById<RecyclerView>(R.id.recycler)
    }

    fun showAllUsers(view: View) {
        getalluser()
//     val intent = Intent(this,ShowData_Activity::class.java)
//        startActivity(intent)
    }
    fun addPerson(view: View) {

        val  name = edName.text.toString()
        val mobile = edMobile.text.toString()

        if (name.isEmpty() || mobile.isEmpty()){
            Toast.makeText(this,"please enter required feald",Toast.LENGTH_LONG).show()
        }else{
            val  user = UserModel(name =name,mobile = mobile)
            val status = sQliteHelper.insertdata(user)

            //check insert success or not success

            if (status >-1){
                Toast.makeText(this,"user add.....",Toast.LENGTH_LONG).show()
                clearEditText()
              //  getalluser()
            }else{
                Toast.makeText(this,"Record not Saved",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getalluser(){
        val userdata = sQliteHelper.getAllusers()
       // Log.e("hiii","${userdata.size}")

        adapter?.additems(userdata)
    }
    private fun clearEditText() {
        edName.setText("")
        edMobile.setText("")
        edMobile.requestFocus()
    }
}