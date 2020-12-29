package com.sigsauer.promotor.controller;

import com.sigsauer.promotor.entity.Month;

import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class Controller {
    List<Month> months = new ArrayList<>( List.of(
            new Month(1, "JANUARY"),
            new Month(2, "FEBRUARY"),
            new Month(3, "MARCH"),
            new Month(4, "APRIL"),
            new Month(5, "MAY"),
            new Month(6, "JUNE"),
            new Month(7, "JULY"),
            new Month(8, "AUGUST"),
            new Month(9, "SEPTEMBER"),
            new Month(10, "OCTOBER"),
            new Month(11, "NOVEMBER"),
            new Month(12, "DECEMBER")));

    @GetMapping
    public ResponseEntity get(@RequestParam(required = false) String q) {
        return ResponseEntity.ok(q == null || q.isEmpty() ? months :
                months.stream().filter(m -> m.getName().contains(q.toUpperCase())).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Month month) {
        month.setId(months.size()+1);
        month.setName(month.getName().toUpperCase());
        months.add(month);
        return get(month.getName());
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Month month) {
        months.stream().filter(m -> m.getId() == month.getId()).
                findFirst().get().setName(month.getName().toUpperCase());
        return get(month.getName());
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam int id) {
        months.remove(id-1);
        return get(null);
    }


}


