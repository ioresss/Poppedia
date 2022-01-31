package tads.eaj.ufrn.poppedia.fragments.list

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListTouchListener(
    val context: Context, val recyclerView: RecyclerView, val listener: OnItemClickListener
) : RecyclerView.OnItemTouchListener {
    var myGestureDetector:GestureDetector = GestureDetector(context, object :
        GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            super.onSingleTapUp(e)
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            if (childView != null) {
                listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
            }
            return true
        }
        override fun onLongPress(e: MotionEvent) {
            super.onLongPress(e)
            val childView = recyclerView.findChildViewUnder(e.x, e.y)
            if (childView != null) {
                listener.onItemLongClick(childView,recyclerView.getChildAdapterPosition(childView))
            }
        }
    })

    interface OnItemClickListener{
        fun onItemClick(v: View, position:Int)
        fun onItemLongClick(v:View, position: Int)
    }


    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        myGestureDetector.onTouchEvent(e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("Not yet implemented")
    }
}