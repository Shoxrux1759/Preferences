package uz.developer.sharedpreferences
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            findViewById<ConstraintLayout>(R.id.bac).setBackgroundColor(4294967295.toInt())
            startActivity(intent)
        }

        val changeColor = intent.getBooleanExtra("changeBackgroundColor", false)
        if (changeColor) {
            changeBackgroundColor(Color.GREEN)
        }
    }

    fun changeBackgroundColor(color: Int) {
        findViewById<ConstraintLayout>(R.id.bac).setBackgroundColor(color)
    }
}
