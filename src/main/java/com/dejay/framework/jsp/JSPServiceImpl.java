package com.dejay.framework.jsp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class JSPServiceImpl implements JSPService {
    @Override
    public Collection<SampleVO> getSampleList() {
        List<SampleVO> list = Arrays.asList(
                  new SampleVO("1", "ikjoo1", "ijzone@dejay.co.kr")
                , new SampleVO("2", "ikjoo2", "ijzone@dejay.co.kr")
                , new SampleVO("3", "ikjoo3", "ijzone@dejay.co.kr")
                , new SampleVO("4", "ikjoo4", "ijzone@dejay.co.kr")
                , new SampleVO("5", "ikjoo5", "ijzone@dejay.co.kr")
        );

        return list;
    }

    @Override
    public SampleVO addSample(SampleVO sampleVO) {
        return null;
    }
}
