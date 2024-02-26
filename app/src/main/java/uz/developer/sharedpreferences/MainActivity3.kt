package uz.developer.sharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.developer.sharedpreferences.models.User

class MainActivity3 : AppCompatActivity() {
    private val sharedPrefFile = "user_info"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userList: ArrayList<User>
    private lateinit var gson: Gson

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        sharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        gson = Gson()
        userList = retrieveUserList()

        var btn_show = findViewById<LinearLayout>(R.id.btn_show)
        var add_contact = findViewById<LinearLayout>(R.id.add_contact)
        var show = findViewById<Button>(R.id.show)
        var save = findViewById<Button>(R.id.save)
        var btn = findViewById<Button>(R.id.save_btn)

        var name = findViewById<EditText>(R.id.userName)
        var phone = findViewById<EditText>(R.id.userPhone)

        btn.setOnClickListener {
            val userName = name.text.toString()
            val userPhone = phone.text.toString()

            if (userName.isEmpty() || userPhone.isEmpty()) {
                Toast.makeText(this, "Foydalanuvchi ma`lumotlarni to`liq to`ldiring", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(userName, userPhone)
                userList.add(user)
                saveUserList(userList)
                name.text.clear()
                phone.text.clear()
                btn_show.visibility = View.VISIBLE
                add_contact.visibility = View.GONE
            }
        }

        save.setOnClickListener {
            btn_show.visibility = View.GONE
            add_contact.visibility = View.VISIBLE
        }

        show.setOnClickListener {
            val userInfo = StringBuilder()
            for (user in userList) {
                userInfo.append("Ism: ${user.uname}\nTelefon: ${user.uphone}\n\n")
            }
            Toast.makeText(this, userInfo.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun retrieveUserList(): ArrayList<User> {
        val json = sharedPreferences.getString("user_list", null)
        val type = object : TypeToken<ArrayList<User>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList()
    }

    private fun saveUserList(userList: ArrayList<User>) {
        val json = gson.toJson(userList)
        sharedPreferences.edit().putString("user_list", json).apply()
    }
}
