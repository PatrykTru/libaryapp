package tru.springframework.com.libaryapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.services.PublisherService;

import javax.validation.Valid;

@Controller
@Slf4j
public class PublisherController {

    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publisher/{id}/show")
    public String getAuthorShowPage(@PathVariable Long id, Model model) {

        model.addAttribute("publisher", publisherService.getPublisherById(id));

        return "publisher/publisherShow";
    }

    @GetMapping("/publisher/{id}/delete")
    public String deleteAuthor(@PathVariable String id){

        publisherService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @GetMapping("publisher/createPublisher")
    public String getPublisherCreationPage(Model model)
    {
        model.addAttribute("publisher", new PublisherCommand());


        return "publisher/newOrEdit";
    }
    @PostMapping("/publisher")
    public String createOrUpdatePublisherHandling(@Valid @ModelAttribute("publisher") PublisherCommand publisherCommand
            , BindingResult bindingResult) {

        PublisherCommand savedCommand = publisherService.savePublisherCommand(publisherCommand);

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError
                    -> log.debug(objectError.toString()));

            publisherService.deleteById(savedCommand.getId());
            return "publisher/newOrEdit";



        }
        return "redirect:/publisher/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/publisher/redirect")
    public String redirectIndexPage() {

        return "redirect:/index";
    }
}
