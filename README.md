# FABottomBar


## Usage
### Add layout
```xml
<it.gcriscione.fabottombar.FABottomBar
        android:layout_alignParentBottom="true"
        android:id="@+id/fab_bottom_bar"
        android:layout_width="match_parent"
        android:layout_height="67dp"
        android:layout_gravity="bottom"
        app:fab_cradle_margin="4dp"
        app:fab_cradle_rounded_corner_radius="12dp"
        app:fab_size="60dp"
        app:itemTextColor="#fff"
        app:itemIconTint="@color/colorAccent"
        app:itemBackground="@null"
        app:menu="@menu/menu"
        app:background_color="@color/colorPrimary"
        />

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"
        app:fabCustomSize="64dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:backgroundTint="@color/colorAccent"
        app:maxImageSize="34dp"
        android:clickable="true"
        android:focusable="true"
        android:layout_centerHorizontal="true"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="35dp"
        app:elevation="10dp"
        />
```

### Use it in your Activity or Fragment
#### Initialization
```kotlin
  val faBottomBar = findViewById<FABottomBar>(R.id.fab_bottom_bar)
```

#### Add your FAB item 
```kotlin
faBottomBar.setFabItem(R.id.item_id)
```

#### Hide FAB
```kotlin
faBottomBar!!.hideFAB(findViewById(R.id.your_fab_id)
```

#### Show FAB
```kotlin
faBottomBar!!.showFAB(findViewById(R.id.your_fab_id)
```

#### Hide Item
```kotlin
faBottomBar.hideItem()
```

#### Show Item
```kotlin
faBottomBar.hideItem()
```

#### Set your OnNavigationItemSelectedListener
```kotlin
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
```            


## Gradle Dependency
- gradle project level
 ```gradle 
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
- gradle application level
```gradle 
dependencies {
  implementation 'com.github.Datalux:FABottomBar:0.1'
}
 ```
 
 


