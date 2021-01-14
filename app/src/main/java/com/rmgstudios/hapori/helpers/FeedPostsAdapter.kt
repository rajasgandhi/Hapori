package com.rmgstudios.hapori.helpers

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


class FeedPostsAdapter(private var context: Context, posts: List<FeedData>) :
    RecyclerView.Adapter<FeedPostsAdapter.ViewHolder>() {

    // no Context reference needed—can get it from a ViewGroup parameter
    private var posts = ArrayList<FeedData>(posts)

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // no need for a LayoutInflater instance—
        // the custom view inflates itself
        val itemView = FeedPost(parent.context)

        return ViewHolder(itemView)
    }

    private fun dpToPx(dp: Float) : Int {
        val r: Resources = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            r.displayMetrics
        ).toInt()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getCustomView().setUpFeedPost(posts[position].postTitle, posts[position].postBody)
        holder.getCustomView().setOnClickListener {
            val i = Intent(context, PostExpanded::class.java)
            i.putExtra("POST_TITLE", posts[position].postTitle)
            i.putExtra("POST_BODY", posts[position].postBody)
            i.putExtra("POST_ID", posts[position].postID)
            context.startActivity(i)
        }
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val customView: FeedPost = v as FeedPost
        fun getCustomView(): FeedPost {
            return customView
        }
    }
}