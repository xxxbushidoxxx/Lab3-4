package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.SatelliteRequest
import ua.kpi.its.lab.rest.dto.SatelliteResponse

interface SatelliteService {
    fun createSatellite(satelliteRequest: SatelliteRequest): SatelliteResponse
    fun getSatelliteById(id: Long): SatelliteResponse
    fun updateSatellite(id: Long, satelliteRequest: SatelliteRequest): SatelliteResponse
    fun deleteSatellite(id: Long): Boolean
}