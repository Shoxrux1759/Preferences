package uz.developer.sharedpreferences

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class qoushish : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qoushish)
        val editText = findViewById<EditText>(R.id.name)
        val editText2 = findViewById<EditText>(R.id.phone)



    }
}