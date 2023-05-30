package ua.kpi.its.lab.rest.repository

import ua.kpi.its.lab.rest.entity.Train
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.kpi.its.lab.rest.entity.Satellite

@Repository
interface SatelliteRepository : JpaRepository<Satellite, Long>