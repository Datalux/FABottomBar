package it.gcriscione.fabottombar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val faBottomBar = findViewById<FABottomBar>(R.id.fab_bottom_bar)

        faBottomBar.setFabItem(R.id.b)
        faBottomBar.hideItem()

        faBottomBar.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.a ->
                        return@OnNavigationItemSelectedListener faBottomBar!!.hideFAB(findViewById(R.id.fab))
                    R.id.b ->
                        return@OnNavigationItemSelectedListener faBottomBar!!.showFAB(findViewById(R.id.fab))
                    R.id.c ->
                        return@OnNavigationItemSelectedListener faBottomBar!!.hideFAB(findViewById(R.id.fab))
                }
                false
            })

    }




}
