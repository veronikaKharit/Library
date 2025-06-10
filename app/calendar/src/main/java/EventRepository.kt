import java.util.Calendar

class EventRepository : EventInterface {

    private val events = mutableListOf<Event>()

    override fun getEventList(): List<Event> = events

    override fun getEventById(id: Int): Event? {
        return events.find { it.id == id }
    }

    override fun addEvent(event: Event) {
        events.add(event)
    }

    override fun updateEvent(event: Event) {
        val index = events.indexOfFirst { it.id == event.id }
        if (index != -1) {
            events[index] = event
        }
    }

    override fun deleteEvent(id: Int) {
        events.removeIf { it.id == id }
    }

    fun getEventsForMonth(month: Calendar): List<Event> {
        return events.filter {
            val calendar = Calendar.getInstance().apply { time = it.date }
            calendar.get(Calendar.MONTH) == month.get(Calendar.MONTH) &&
                    calendar.get(Calendar.YEAR) == month.get(Calendar.YEAR)
        }
    }
}
