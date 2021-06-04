
package net.codejava.calendario.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import net.codejava.evento.controller.Event;
import net.codejava.evento.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
public class CalendarioController {

    @Autowired
    EventRepository er;

    @RequestMapping("/api")
    @ResponseBody
    String home() {
        return "Bem Vindo!";
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        return er.findBetween(start, end);
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {

        Event e = new Event();
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setText(params.text);

        er.save(e);

        return e;
    }

    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {

        Event e = er.findById(params.id).get();

        e.setStart(params.start);
        e.setEnd(params.end);

        er.save(e);

        return e;
    }

    @PostMapping("/api/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event setColor(@RequestBody SetColorParams params) {

        Event e = er.findById(params.id).get();
        e.setColor(params.color);
        er.save(e);

        return e;
    }

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }


}