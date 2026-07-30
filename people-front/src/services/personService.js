import api from "./api";

export function getPeople() {
    return api.get('/people')
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