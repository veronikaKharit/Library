import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load  // импортируем Coil
import org.hse.calendar.R

class EventAdapter(
    private var events: List<Event>,
    private val onEventClick: (Event) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDateTimeStart: TextView = view.findViewById(R.id.tvDateTimeStart)
        val tvDateTimeFinish: TextView = view.findViewById(R.id.tvDateTimeFinish)
        val ivPreview: ImageView = view.findViewById(R.id.ivPreview)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val tvPlace: TextView = view.findViewById(R.id.tvplace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_event_list, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.tvTitle.text = event.title
        holder.tvDateTimeStart.text = event.timeStart
        holder.tvDateTimeFinish.text = event.timeFinish
        holder.tvDescription.text = event.description
        holder.tvPlace.text = event.place

        // Если imageUrl не пустой, загружаем изображение, иначе не делаем ничего
        if (!event.imageUrl.isNullOrEmpty()) {
            holder.ivPreview.load(event.imageUrl)
        } else {
            holder.ivPreview.setImageDrawable(null)  // Очистить изображение
        }

        holder.itemView.setOnClickListener {
            onEventClick(event)
        }
    }

    fun updateEvents(newEvents: List<Event>) {
        events = newEvents
        notifyDataSetChanged()
    }
}
