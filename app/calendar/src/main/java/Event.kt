import java.util.Date

data class Event(
    val id: Int,
    val date: Date,///можно ли так?
    val timeStart: String,
    val timeFinish: String,
    val title: String,
    val place: String,
    val description: String,
    val preview: String,
    val imageUrl: String,
    val category: String,
    val bu: List<String>,
    val notifyPeople: List<String>,
    val daytype: String
)
