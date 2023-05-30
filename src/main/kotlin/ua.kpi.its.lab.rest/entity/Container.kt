import ua.kpi.its.lab.rest.entity.Satellite


interface Container<T> {
    fun add(element: T)

    fun remove(index: Int)

    fun update(index: Int, element: T)

    fun get(index: Int): T
    fun getAll(): List<T>
}

class SatelliteContainer : Container<Satellite> {
    private val satellites: MutableList<Satellite> = mutableListOf()

    override fun add(element: Satellite) {
        satellites.add(element)
    }

    override fun remove(index: Int) {
        satellites.removeAt(index)
    }

    override fun update(index: Int, element: Satellite) {
        satellites[index] = element
    }

    override fun get(index: Int): Satellite {
        return satellites[index]
    }

    override fun getAll(): List<Satellite> {
        return satellites
    }
}