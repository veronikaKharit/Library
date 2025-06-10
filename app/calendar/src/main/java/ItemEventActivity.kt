import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.hse.calendar.R

class ItemEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_event)

        val eventId = intent.getIntExtra("eventId", -1)
        val event = EventRepository().getEventById(eventId)

        if (event != null) {
            findViewById<TextView>(R.id.tvTitle).text = event.title
            findViewById<TextView>(R.id.tvDate).text = event.date.toString() // Форматировать дату по необходимости
            findViewById<TextView>(R.id.tvDateTimeStart).text = event.timeStart
            findViewById<TextView>(R.id.tvDateTimeFinish).text = event.timeFinish
            findViewById<TextView>(R.id.tvDescription).text = event.description
            findViewById<TextView>(R.id.tvplace).text = event.place
        }
    }
}
