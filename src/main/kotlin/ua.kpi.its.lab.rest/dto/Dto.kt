package ua.kpi.its.lab.rest.dto

class ProcessorRequest {
    var name: String? = null
        private set
    var manufactured: String? = null
        private set
    var cores: String? = null
        private set

    constructor()
    constructor(name: String?, manufactured: String?, cores: String?) {
        this.name = name
        this.manufactured = manufactured
        this.cores = cores
    }
}


class ProcessorResponse {
    var id: Long? = null
        private set
    var name: String? = null
        private set
    var manufactured: String? = null
        private set
    var cores: String? = null
        private set

    constructor()
    constructor(id: Long?, name: String?, manufactured: String?, socket: String?) {
        this.id = id
        this.name = name
        this.manufactured = manufactured
        this.cores = socket
    }
}


class SatelliteRequest {
    var name: String? = null
        private set
    var manufactured: String? = null
        private set
    var launchDate: String? = null
        private set

    constructor()
    constructor(name: String?, manufactured: String?, launchDate: String?) {
        this.name = name
        this.manufactured = manufactured
        this.launchDate = launchDate
    }
}


class SatelliteResponse {
    var id: Long? = null
        private set
    var name: String? = null
        private set
    var manufactured: String? = null
        private set
    var launchDate: String? = null
        private set

    constructor()
    constructor(id: Long?, name: String?, manufactured: String?, launchDate: String?) {
        this.id = id
        this.name = name
        this.manufactured = manufactured
        this.launchDate = launchDate
    }
}

