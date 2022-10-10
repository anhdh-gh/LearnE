package source.third_party.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuestionBankThirdPartyServiceImpl implements QuestionBankThirdPartyService {

    @Value("${service.question-bank.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

}
