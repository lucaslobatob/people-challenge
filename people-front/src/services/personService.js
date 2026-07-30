import api from "./api";

export function getPeople(params) {
    return api.get('/people', {
        params
    })
}

export function createPerson(person) {
    return api.post('/people', person)
}

export function getPersonById(id) {
    return api.get(`/people/${id}`)
}

export function updatePerson(id, person) {
    return api.patch(`/people/${id}`, person)
}

export function deletePerson(id) {
    return api.delete(`/people/${id}`)
}