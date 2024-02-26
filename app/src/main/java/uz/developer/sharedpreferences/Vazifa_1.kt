package uz.developer.sharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import uz.developer.sharedpreferences.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
class Vazifa_1 : AppCompatActivity() {

    private val sharedPrefFile = "user_info"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userList: ArrayList<User>
    private lateinit var gson: Gson

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vazifa1)

        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        gson = Gson()
        userList = retrieveUserList()

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonShow = findViewById<Button>(R.id.buttonShow)

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Malumotni to`ldiring!!", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(name, phone)
                userList.add(user)

                saveUserList(userList)

                Toast.makeText(this, "Ma`lumot qo`shildi", Toast.LENGTH_LONG).show()
            }
        }

        buttonShow.setOnClickListener {
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
