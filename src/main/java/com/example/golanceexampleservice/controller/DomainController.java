package com.example.golanceexampleservice.controller;

import com.example.golanceexampleservice.dto.DomainRequest;
import com.example.golanceexampleservice.model.Domain;
import com.example.golanceexampleservice.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DomainController {

    private final DomainService domainService;

    @PostMapping("/domain")
    public ModelAndView saveDomain(Map<String, Object> model, @ModelAttribute("domainRequest") DomainRequest domainRequest) {
        domainService.saveDomain(domainRequest);
        return getDomains(model);
    }

    @GetMapping(value = {"/domain","/"})
    public ModelAndView getDomains(Map<String, Object> model) {
        List<Domain> domains = domainService.findAll();
        model.put("domains", domains);
        return new ModelAndView("index", model);
    }
}
