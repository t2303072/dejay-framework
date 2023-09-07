package com.dejay.framework.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;

@Controller
@RequestMapping("/jsp")
public class JSPController {

    private final JSPService JSPService;

    public JSPController(JSPService JSPService) {
        this.JSPService = JSPService;
    }

    @GetMapping({"", "/"})
    public String view(Model model) {
//        Collection<SampleVO> sampleList = JSPService.getSampleList();
        SampleVO sample = JSPService.getSample();
        model.addAttribute("sample", sample);
        return "sample/view-sample";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("sample", new SampleVO("0", "default", "default"));
        return "sample/add-sample";
    }

    @PostMapping("/addRedirect")
    public RedirectView addRedirect(@ModelAttribute("sample") SampleVO sampleVO, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("", true);
        SampleVO savedSampleVO = JSPService.addSample(sampleVO);
        redirectAttributes.addFlashAttribute("savedBook", savedSampleVO);
        redirectAttributes.addFlashAttribute("addBookSuccess", true);
        return redirectView;
    }
}
