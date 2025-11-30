package ru.crudio.api.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.crudio.api.api.v1.SamplesApi;
import ru.crudio.api.dto.PaginationResponse;
import ru.crudio.api.dto.SampleExtDto;
import ru.crudio.api.dto.SamplesRequestBody;
import ru.crudio.api.dto.SamplesResponseBody;

import java.util.List;

@RestController
@Slf4j
public class SamplesApiController implements SamplesApi {

    @Override
    public ForTestPurposesSamplesResponseEntity forTestPurposesSamples(String context) {
        return ForTestPurposesSamplesResponseEntity.response200(
                toSamplesResponseBody(
                        sampleResult()));
    }

    @Override
    public SupportSamplesResponseEntity supportSamples(String context, SamplesRequestBody requestBody) {
        return SupportSamplesResponseEntity.response200(
                toSamplesResponseBody(
                        sampleResult()));
    }


    private SamplesResponseBody toSamplesResponseBody(List<SampleExtDto> dtoList) {
        return new SamplesResponseBody()
                .content(dtoList)
                .pagination(new PaginationResponse()
                        .offset(0L)
                        .limit(10L)
                        .totalCount(1L));
    }

    private List<SampleExtDto> sampleResult() {
        return List.of(
                new SampleExtDto()
                        .id("1")
                        .name("some-name"));
    }
}
