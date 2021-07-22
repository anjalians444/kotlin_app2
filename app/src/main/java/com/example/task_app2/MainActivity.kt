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
    //  private lateinit var layoutmanaget:RecyclerView.LayoutManager
   // private var adapter: ShowData_Adapter? = null

    private lateinit var sQliteHelper: SQliteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initi()
       // recyclerinti()
       // getalluser()
        sQliteHelper = SQliteHelper(this)
    }



    private fun initi() {
        edName = findViewById<EditText>(R.id.edname)
        edMobile = findViewById<EditText>(R.id.edmobile)
       // rc = findViewById<RecyclerView>(R.id.recycler)
    }

    fun showAllUsers(view: View) {
      //  getalluser()
     val intent = Intent(this,ShowData_Activity::class.java)
        startActivity(intent)
    }
    fun addPerson(view: View) {

        val  name = edName.text.toString()
        val mobile = edMobile.text.toString()
    //    var  vailid : String= "^[0-9]$"

      //  String number=entered_number.getText().toString();

//        if(edMobile.getText().toString().length <10 || mobile.length >13 || mobile.matches(vailid)==false  ) {
//           // Toast.makeText(MyDialog.this,"Please enter "+"\n"+" valid phone number",Toast.LENGTH_SHORT).show();
//            // am_checked=0;
//        }

        if (name.isEmpty() || name.length < 6 ) {
            edName.setError("please Valid 6 latter Name")
        }
            else if( mobile.isEmpty() || mobile.length != 10){
                edMobile.setError("please Valid 10 digits Number" )

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

//    private fun getalluser(){
//        val userdata = sQliteHelper.getAllusers()
//       // Log.e("hiii","${userdata.size}")
//
//        adapter?.additems(userdata)
//    }
    private fun clearEditText() {
        edName.setText("")
        edMobile.setText("")
        edMobile.requestFocus()
    }
}

//private fun String.matches(regex: String): Boolean {
//
//}
