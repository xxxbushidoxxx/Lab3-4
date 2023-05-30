package ua.kpi.its.lab.rest.svc.impl

import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.dto.SatelliteRequest
import ua.kpi.its.lab.rest.dto.SatelliteResponse
import ua.kpi.its.lab.rest.entity.Train
import ua.kpi.its.lab.rest.repository.SatelliteRepository
import ua.kpi.its.lab.rest.svc.SatelliteService
import org.springframework.security.access.prepost.PreAuthorize

@Service
class SatelliteServiceImpl(private val satelliteRepository: SatelliteRepository) : SatelliteService {
    @PreAuthorize("hasAuthority('EDITOR')")
    override fun createSatellite(satelliteRequest: SatelliteRequest): SatelliteResponse {
        val train = Train(model = satelliteRequest.name, manufacturer = satelliteRequest.manufactured)
        val savedHospital = satelliteRepository.save(train)
        return SatelliteResponse.fromEntity(savedHospital)
    }

    @PreAuthorize("permitAll()")
    override fun getSatelliteById(id: Long): SatelliteResponse {
        val hospital = satelliteRepository.findById(id).orElseThrow()
        return SatelliteResponse.fromEntity(hospital)
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    override fun updateSatellite(id: Long, satelliteRequest: SatelliteRequest): SatelliteResponse {
        val train = satelliteRepository.findById(id).orElseThrow()
        train.model = satelliteRequest.name
        train.manufacturer = satelliteRequest.manufactured
        val updatedTrain = satelliteRepository.save(train)
        return SatelliteResponse.fromEntity(updatedTrain)
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    override fun deleteSatellite(id: Long): Boolean {
        satelliteRepository.deleteById(id)
        return true
    }
}