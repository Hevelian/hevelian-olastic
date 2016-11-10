package com.hevelian.olastic.core.edm.provider;

import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;

/**
 * Elasticsearch Entity Set implementation.
 * 
 * @author rdidyk
 */
public class ElasticCsdlEntitySet extends CsdlEntitySet
        implements IElasticCsdlEdmItem<ElasticCsdlEntitySet> {

    private String eIndex;
    private String eType;

    @Override
    public String getEType() {
        return eType;
    }

    @Override
    public String getEIndex() {
        return eIndex;
    }

    @Override
    public ElasticCsdlEntitySet setEIndex(String eIndex) {
        this.eIndex = eIndex;
        return this;
    }

    @Override
    public ElasticCsdlEntitySet setEType(String eType) {
        this.eType = eType;
        return this;
    }
}