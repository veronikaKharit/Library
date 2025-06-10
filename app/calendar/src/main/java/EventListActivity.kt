import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.hse.calendar.R
import java.text.SimpleDateFormat
import java.util.*

class EventListActivity : AppCompatActivity() {

    private lateinit var monthNameTextView: TextView
    private lateinit var eventContainer: LinearLayout
    private lateinit var currentMonth: Calendar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_calendar)

        monthNameTextView = findViewById(R.id.MonthName)
        eventContainer = findViewById(R.id.eventContainer)

        currentMonth = Calendar.getInstance()

        val prevMonthButton = findViewById<ImageButton>(R.id.imageButton1)
        val nextMonthButton = findViewById<ImageButton>(R.id.imageButton2)

        prevMonthButton.setOnClickListener {
            currentMonth.add(Calendar.MONTH, -1)
            loadEvents()
        }

        nextMonthButton.setOnClickListener {
            currentMonth.add(Calendar.MONTH, 1)
            loadEvents()
        }

        loadEvents()
    }

    private fun loadEvents() {
        val repository = EventRepository()
        val events = repository.getEventsForMonth(currentMonth)

        // Обновляем название месяца
        val monthName = getMonthName(currentMonth)
        monthNameTextView.text = monthName

        // Очищаем контейнер перед добавлением новых данных
        eventContainer.removeAllViews()

        // Добавляем события
        if (events.isEmpty()) {
            val noEventsTextView = TextView(this)
            noEventsTextView.text = "Запланированных мероприятий нет"
            eventContainer.addView(noEventsTextView)
        } else {
            for (event in events) {
                val eventView = createEventCard(event)
                eventContainer.addView(eventView)
            }
        }
    }

    private fun createEventCard(event: Event): LinearLayout {
        val eventLayout = LinearLayout(this)
        eventLayout.orientation = LinearLayout.VERTICAL
        eventLayout.setPadding(16, 16, 16, 16)
        eventLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // Заголовок события
        val titleTextView = TextView(this)
        titleTextView.text = event.title
        titleTextView.textSize = 18f

        // Описание события
        val descriptionTextView = TextView(this)
        descriptionTextView.text = event.description
        descriptionTextView.textSize = 14f

        // Время и место
        val timePlaceTextView = TextView(this)
        timePlaceTextView.text = "${event.timeStart} - ${event.timeFinish} | ${event.place}"
        timePlaceTextView.textSize = 12f

        // Собираем карточку
        eventLayout.addView(titleTextView)
        eventLayout.addView(descriptionTextView)
        eventLayout.addView(timePlaceTextView)

        eventLayout.setOnClickListener {
            val intent = Intent(this, ItemEventActivity::class.java)
            intent.putExtra("eventId", event.id)
            startActivity(intent)
        }

        return eventLayout
    }

    private fun getMonthName(calendar: Calendar): String {
        val format = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        return format.format(calendar.time)
    }
}
