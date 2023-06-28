package com.shvk.springbootmongodb.controller;

import com.shvk.springbootmongodb.collection.Person;
import com.shvk.springbootmongodb.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(
            summary = "Create person detail"
    )
    @PostMapping
    public String save(@RequestBody Person person) {
        return personService.save(person);
    }

    @Operation(
            summary = "Update person details"
    )
    @PutMapping("/{id}")
    public String update(@RequestBody Person person,
                         @PathVariable(name = "id") String personId) {
        return personService.update(person, personId);
    }

    @Operation(
            summary = "Get person details starts with given parameter"
    )
    @GetMapping
    public List<Person> getPersonStartWith(@RequestParam("name") String name) {
        return personService.getPersonStartWith(name);
    }

    @Operation(
            summary = "Get person detail by id"
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        personService.delete(id);
    }

    @Operation(
            summary = "Get person detail by age"
    )
    @GetMapping("/age")
    public List<Person> getByPersonAge(@RequestParam Integer minAge,
                                       @RequestParam Integer maxAge) {
        return personService.getByPersonAge(minAge, maxAge);
    }

    @Operation(
            summary = "Get person detail by search criteria"
    )
    @GetMapping("/search")
    public Page<Person> searchPerson(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        Pageable pageable
                = PageRequest.of(page, size);
        return personService.search(name, minAge, maxAge, city, pageable);
    }

    @Operation(
            summary = "Get oldest person per city"
    )
    @GetMapping("/oldestPerson")
    public List<Document> getOldestPerson() {
        return personService.getOldestPersonByCity();
    }

    @Operation(
            summary = "Get population per city"
    )
    @GetMapping("/populationByCity")
    public List<Document> getPopulationByCity() {
        return personService.getPopulationByCity();
    }
}
