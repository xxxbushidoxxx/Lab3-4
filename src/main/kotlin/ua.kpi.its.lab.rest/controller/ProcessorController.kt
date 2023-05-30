package ua.kpi.its.lab.rest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.ProcessorRequest
import ua.kpi.its.lab.rest.dto.ProcessorResponse
import ua.kpi.its.lab.rest.svc.impl.ProcessorServiceImpl

@RestController
@RequestMapping("/processor")
class ProcessorController(private val ProcessorService: ProcessorServiceImpl) {
    @PostMapping
    fun createProcessor(@RequestBody req: ProcessorRequest): ResponseEntity<ProcessorResponse> {
        val resp = ProcessorService.createProcessor(req)
        return ResponseEntity(resp, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getProcessorById(@PathVariable id: Long): ResponseEntity<ProcessorResponse> {
        val resp = ProcessorService.getProcessorById(id)
        return ResponseEntity(resp, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateProcessor(@PathVariable id: Long, @RequestBody req: ProcessorRequest): ResponseEntity<ProcessorResponse> {
        val reps = ProcessorService.updateProcessor(id, req)
        return ResponseEntity(reps, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteProcessor(@PathVariable id: Long): ResponseEntity<Void> {
        ProcessorService.deleteProcessor(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}