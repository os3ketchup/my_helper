package uz.os3ketchup.myhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.os3ketchup.myhelper.di.DaggerAuthComponent

class MainActivity : AppCompatActivity() {

    val component by lazy {
        DaggerAuthComponent.create().inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}