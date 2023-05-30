package ua.kpi.its.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.SatelliteRequest
import ua.kpi.its.lab.rest.dto.SatelliteResponse
import ua.kpi.its.lab.rest.svc.impl.SatelliteServiceImpl


@RestController
@RequestMapping("/api/satellites")
class SatelliteController {

    private final lateinit var satelliteService: SatelliteServiceImpl;

    @Autowired
    fun SatelliteController(satelliteService: SatelliteServiceImpl) {
        this.satelliteService = satelliteService;
    }

    @GetMapping("/{id}")
    fun getSatelliteById(@PathVariable Long id): ResponseEntity<SatelliteResponse>? {
        val satelliteResponse: SatelliteResponse = satelliteService.getSatelliteById(id);
        if (satelliteResponse == null) {
            println("Satellite with id " + id + " not found")
        }
        return ResponseEntity.ok(satelliteResponse);
    }

    @PostMapping("/")
    fun createSatellite(@Valid @RequestBody satelliteRequest: SatelliteRequest?): ResponseEntity<SatelliteResponse>? {
        val satelliteResponse: SatelliteResponse? = satelliteRequest?.let { satelliteService.createSatellite(it) }
        return ResponseEntity.status(HttpStatus.CREATED).body<SatelliteResponse>(satelliteResponse)
    }

    @PutMapping("/{id}")
    fun updateSatellite(
        @PathVariable id: Long,
        @Valid @RequestBody satelliteRequest: SatelliteRequest?
    ): ResponseEntity<SatelliteResponse>? {
        val satelliteResponse: SatelliteResponse? = satelliteRequest?.let { satelliteService.updateSatellite(id, it) }
        return ResponseEntity.ok<SatelliteResponse>(satelliteResponse)
    }

    @DeleteMapping("/{id}")
    fun deleteSatellite(@PathVariable id: Long): ResponseEntity<Void?>? {
        val isDeleted: Boolean = satelliteService.deleteSatellite(id)
        if (!isDeleted) {
            println("Satellite with id $id not found")
        }
        return ResponseEntity.noContent().build()
    }
}