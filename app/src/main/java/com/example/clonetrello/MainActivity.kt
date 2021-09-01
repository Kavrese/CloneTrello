 package com.example.clonetrello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.pager_date_single
import kotlinx.android.synthetic.main.activity_main.view.*

 class MainActivity : AppCompatActivity() {
    var singleMode = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rec_date_group.visibility = View.GONE

        initPager()
        initRec()
        initSearch()

        mode.setOnClickListener {
            switchMode()
        }
    }

     private fun initPager(){

         val listInterface: MutableList<OnNewPosition> = arrayListOf()

         pager_date_single.adapter = AdapterDatesSingle(HardCodeInfo.listModelCardDate, object: OnNewInterface{
             override fun new_interface(onNewPosition: OnNewPosition) {
                 listInterface.add(onNewPosition)
             }
         })

         pager_date_single.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
             override fun onPageSelected(position: Int) {
                 listInterface.forEach { onNewPosition: OnNewPosition -> onNewPosition.position(position)}
             }
         })
     }

     private fun initRec(){
         rec_date_group.apply {
             adapter = AdapterDatesGroup(HardCodeInfo.listModelCardDate, object: OnClickDateGroup{
                 override fun onClick(pos: Int) {
                     this@MainActivity.motion_swipe_dates.transitionToStart()
                     this@MainActivity.rec_date_group.scrollToPosition(0)
                     switchMode()
                     this@MainActivity.pager_date_single.setCurrentItem(pos, false)
                 }
             })
             layoutManager = GridLayoutManager(this@MainActivity, 2)
         }
     }

     private fun initSearch(){
         search.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
             return@OnKeyListener if (keyCode == KeyEvent.KEYCODE_ENTER){
                 initResultSearch(searchWorker(search.text.toString()))
                 true
             }else{
                 false
             }
         })
         clear_search.setOnClickListener {
             clearSearch()
         }
     }

     private fun clearSearch(){
         rec_search.visibility = View.GONE
         add_task.visibility = View.VISIBLE
         clear_search.visibility = View.GONE
         search.setText("")
     }

     private fun initResultSearch(listTask: List<ModelTask>){
         rec_search.visibility = View.VISIBLE
         rec_search.apply {
             layoutManager = LinearLayoutManager(this@MainActivity)
             adapter = AdapterTask(listTask)
         }
         add_task.visibility = View.GONE
         clear_search.visibility = View.VISIBLE
     }

     private fun searchWorker(worker: String): List<ModelTask>{
         val listTask: MutableList<ModelTask> = arrayListOf()
         HardCodeInfo.listModelCardDate.map { modelCardDate: ModelCardDate ->
             modelCardDate.list_task.filter { modelTask -> modelTask.from == worker }
         }.forEach { it: List<ModelTask> ->
             listTask.addAll(it)
         }
         return listTask
     }

     private fun switchMode(){
        singleMode = !singleMode
        if (singleMode) {
            mode.setImageResource(R.drawable.ic_mode_single)
            pager_date_single.visibility = View.VISIBLE
            rec_date_group.visibility = View.GONE
        }else {
            mode.setImageResource(R.drawable.ic_mode_group)
            pager_date_single.visibility = View.GONE
            rec_date_group.visibility = View.VISIBLE
        }
    }
}