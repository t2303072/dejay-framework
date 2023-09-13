package com.dejay.framework.controller;

import java.util.Collection;

public interface SampleService {

    SampleVO getSample();
    Collection<SampleVO> getSampleList();

    SampleVO addSample(SampleVO sampleVO);
}
