package ru.crudio.backend.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import ru.crudio.backend.web.api.SamplesApi;
import ru.crudio.backend.web.dto.SampleDto;
import ru.crudio.backend.web.dto.SamplesSearchBody;

@RestController
public class SamplesBackController implements SamplesApi {

    @Override
    public CreateSampleResponseEntity createSample(Authentication authentication, String context, SampleDto sampleDto) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public GetSampleResponseEntity getSample(Authentication authentication, Long id, String context) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public ListSamplesResponseEntity listSamples(Authentication authentication, String context, SamplesSearchBody samplesSearchBody) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public UpdateSampleResponseEntity updateSample(Authentication authentication, Long id, String context, SampleDto sampleDto) {
        throw new IllegalStateException("Not implemented yet");
    }
}
