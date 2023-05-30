package ua.kpi.its.lab.rest.entity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.*

@Entity
@Table(name="processor")
data class Processor(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    var name: String,
    var manufactured: String,
    val cores: Int,
    val clockSpeed: Double,
    val socket: String,
    val productionDate: String,
    val mmxSupport: Boolean,
    @ManyToOne
    @JoinColumn(name = "satellite_id")
    val satellite: Satellite
) : Comparable<Processor> {
    override fun compareTo(processor: Processor): Int {
        val compare = name.compareTo(processor.name)
        return if (compare != 0) compare else name.compareTo(processor.name) + manufactured.compareTo(processor.manufactured)
    }
}