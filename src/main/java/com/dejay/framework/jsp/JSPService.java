package com.dejay.framework.jsp;

import java.util.Collection;

public interface JSPService {
    Collection<SampleVO> getSampleList();

    SampleVO addSample(SampleVO sampleVO);
}
