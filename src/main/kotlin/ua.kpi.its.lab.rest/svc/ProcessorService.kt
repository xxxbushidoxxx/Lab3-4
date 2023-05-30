package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.ProcessorRequest
import ua.kpi.its.lab.rest.dto.ProcessorResponse
interface ProcessorService {
    fun createProcessor(drugsRequest: ProcessorRequest): ProcessorResponse
    fun getProcessorById(id: Long): ProcessorResponse
    fun updateProcessor(id: Long, drugsRequest: ProcessorRequest): ProcessorResponse
    fun deleteProcessor(id: Long): Boolean
}