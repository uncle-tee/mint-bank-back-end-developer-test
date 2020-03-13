package com.bankwithmint.developertest.dao;

import cloud.dlabs.datautils.OffsetBasedPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class OffsetBasePageRequest {
    public static Pageable of(Long offset, int limit, Sort sort) {
        return new OffsetBasedPageRequest(offset, limit, sort);
    }
}

