package ua.kpi.its.lab.rest.entity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.*
import java.time.LocalDate
@Entity
@Table(name = "satellite")
data class Satellite(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val name: String,
    val country: String,
    val purpose: String,
    val launchDate: LocalDate,
    val height: Int,
    val weight: Int,
    val geoStationary: Boolean,
    @OneToMany(mappedBy = "satellite", cascade = [CascadeType.ALL], orphanRemoval = true)
    val way: Processor,
) : Comparable<Satellite> {
    override fun compareTo(satellite: Satellite): Int {
        val compare = name.compareTo(satellite.model)
        return if (compare != 0) compare else way.compareTo(satellite.way)
    }
}