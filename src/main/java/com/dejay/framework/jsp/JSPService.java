package com.dejay.framework.jsp;

import java.util.Collection;

public interface JSPService {

    SampleVO getSample();
    Collection<SampleVO> getSampleList();

    SampleVO addSample(SampleVO sampleVO);
}
