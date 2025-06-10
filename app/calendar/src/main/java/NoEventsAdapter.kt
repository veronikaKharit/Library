import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.hse.calendar.R

class NoEventsAdapter : RecyclerView.Adapter<NoEventsAdapter.NoEventsViewHolder>() {

    inner class NoEventsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noEventsText: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoEventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.without_events, parent, false)
        return NoEventsViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: NoEventsViewHolder, position: Int) {
        holder.noEventsText.text = "Запланированных мероприятий нет"
    }
}
