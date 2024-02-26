package uz.developer.sharedpreferences
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val sv = findViewById<Switch>(R.id.switch1)
        sv.setOnCheckedChangeListener { _, isChecked ->

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("changeBackgroundColor", isChecked)
            startActivity(intent)

        }
    }
}
