package ru.sfu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sfu.entity.CMValidator;
import ru.sfu.entity.CoffeeMachine;

/** Controller for routing */
@Controller
public class RoutingController {

    @Autowired
    CoffeeDao dao;

    /** Routing the main page */
    @GetMapping(value="/")
    public String defaultPage(
        ModelMap model
    ) {
        model.addAttribute("machines", dao.findAll());
        return "index";
    }

    /** Routing the page for adding item */
    @GetMapping("/add")
    public String addGet(ModelMap model) {
        model.addAttribute("m", new CoffeeMachine());
        return "add";
    }
    /** Routing 'POST' query for adding item */
    @PostMapping("/add")
    public String addPost(
        @ModelAttribute("m") CoffeeMachine coffeeMachine,
        BindingResult result,
        ModelMap model
    ) {
        CMValidator validator = new CMValidator(coffeeMachine);
        if (validator.invalid()) {
            model.addAttribute("v", validator);
            model.addAttribute("m", coffeeMachine);
            return "add";
        }
        dao.add(coffeeMachine);
        return "redirect:/";
    }

    /** Routing the page for editing item */
    @GetMapping("/edit/{id:\\d+}")
    public String editGet(@PathVariable("id") String id, ModelMap model) {
        CoffeeMachine coffeeMachine;
        try {
            coffeeMachine = dao.findById(Integer.parseInt(id));
        } catch (Exception e) {
            return "redirect:/error";
        }
        model.addAttribute("m", coffeeMachine);
        return "edit";
    }
    /** Routing 'POST' query for editing item */
    @PostMapping("/edit/{id:\\d+}")
    public String editPost(
        @PathVariable("id") String id,
        @ModelAttribute("m") CoffeeMachine coffeeMachine,
        BindingResult result,
        ModelMap model
    ) {
        CMValidator validator = new CMValidator(coffeeMachine);
        try {
            if (Integer.parseInt(id) != coffeeMachine.getId())
                return "redirect:/error";
        } catch (Exception e) {
            return "redirect:/error";
        }
        if (validator.invalid()) {
            model.addAttribute("v", validator);
            model.addAttribute("m", coffeeMachine);
            return "edit";
        }
        dao.update(coffeeMachine);
        return "redirect:/";
    }

    /** Routing the page for deleting item */
    @RequestMapping(value = "/del/{id:\\d+}")
    public String del(@PathVariable("id") String id, ModelMap model) {
        try {
            dao.delete(Integer.parseInt(id));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "redirect:/";
    }

    /** Routing the page for buying item */
    @RequestMapping(value = "/buy/{id:\\d+}")
    public String buy(@PathVariable("id") String id, ModelMap model) {
        try {
            dao.buy(Integer.parseInt(id));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "redirect:/";
    }

    /** Routing the page for search items */
    @RequestMapping("/search")
    public String search(
        @RequestParam(required = false) String year,
        ModelMap model
    ) {
        if (year == null)
            return "search";
        try {
            model.addAttribute("machines",
                dao.findByYear(Integer.parseInt(year)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "search";
    }

    // REST

    /** Routing 'GET' query for all items */
    @ResponseBody
    @GetMapping(value="/coffee-machine", headers={"Accept=application/json"})
    public List<CoffeeMachine> getAllCoffeeMachines() {
        return dao.findAll();
    }

    /** Routing 'GET' query for one item */
    @ResponseBody
    @GetMapping(value="/coffee-machine/{id:\\d+}", headers={"Accept=application/json"})
    public CoffeeMachine getCoffeeMachine(@PathVariable String id) {
        return dao.findById(Integer.parseInt(id));
    }

    /** Routing 'POST' query */
    @ResponseBody
    @PostMapping(value="/coffee-machine", headers={"Accept=application/json"})
    public String addCoffeeMachine(
        @RequestBody CoffeeMachine coffeeMachine,
        BindingResult result
    ) {
        CMValidator validator = new CMValidator(coffeeMachine);
        System.out.println(coffeeMachine);
        if (validator.invalid())
            return "Coffee-machine is not added";
        dao.add(coffeeMachine);
        return "Coffee-machine is added";
    }

    /** Routing 'PUT' query */
    @ResponseBody
    @PutMapping(value="/coffee-machine/{id:\\d+}", headers={"Accept=application/json"})
    public String updateCoffeeMachine(
        @PathVariable String id,
        @RequestBody CoffeeMachine coffeeMachine,
        BindingResult result
    ) {
        CMValidator validator = new CMValidator(coffeeMachine);
        if (validator.invalid())
            return "Coffee-machine is not updated";
        dao.update(coffeeMachine);
        return "Coffee-machine is updated";
    }

    /** Routing 'DELETE' query */
    @ResponseBody
    @DeleteMapping(value="/coffee-machine/{id:\\d+}")
    public String deleteCoffeeMachine(
        @PathVariable("id") String id
    ) {
        try {
            dao.delete(Integer.parseInt(id));
        } catch (Exception ignored) {
            return "Coffee-machine is not deleted";
        }
        return "Coffee-machine is deleted";
    }
}
