package ua.kpi.its.lab.rest.svc.impl

import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.dto.ProcessorRequest
import ua.kpi.its.lab.rest.dto.ProcessorResponse
import ua.kpi.its.lab.rest.entity.Processor
import ua.kpi.its.lab.rest.repository.ProcessorRepository
import ua.kpi.its.lab.rest.svc.ProcessorService
import org.springframework.security.access.prepost.PreAuthorize

@Service
class ProcessorServiceImpl(private val processorRepository: ProcessorRepository) : ProcessorService {
    @PreAuthorize("hasAuthority('EDITOR')")
    override fun createProcessor(processorRequest: ProcessorRequest): ProcessorResponse {
        val way = Processor(name = processorRequest.name, manufactured = processorRequest.manufactured)
        val savedWay = processorRepository.save(way)
        return ProcessorResponse.fromEntity(savedWay)
    }

    @PreAuthorize("permitAll()")
    override fun getProcessorById(id: Long): ProcessorResponse {
        val drugs = processorRepository.findById(id).orElseThrow()
        return ProcessorResponse.fromEntity(drugs)
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    override fun updateProcessor(id: Long, drugsRequest: ProcessorRequest): ProcessorResponse {
        val way = processorRepository.findById(id).orElseThrow()
        way.startPoint = drugsRequest.name.toString()
        way.destination = drugsRequest.manufactured.toString()
        val updatedWay = processorRepository.save(way)
        return ProcessorResponse.fromEntity(updatedWay)
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    override fun deleteProcessor(id: Long): Boolean {
        processorRepository.deleteById(id)
        return true
    }
}