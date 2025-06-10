interface EventInterface {
    fun getEventList(): List<Event>  // Получение списка событий
    fun getEventById(id: Int): Event?  // Получение события по ID
    fun addEvent(event: Event)  // Добавление нового события
    fun updateEvent(event: Event)  // Обновление существующего события
    fun deleteEvent(id: Int)  // Удаление события
}
