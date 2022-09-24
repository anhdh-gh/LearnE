package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import source.service.QuestionBankService;

@RestController
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;
}