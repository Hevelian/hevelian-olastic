package com.hevelian.olastic.core.api.edm.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.apache.olingo.commons.api.ex.ODataException;
import org.junit.Test;
import org.mockito.Mock;

import com.hevelian.olastic.core.api.edm.provider.SingleElasticIndexCsdlEdmProvider;
import com.hevelian.olastic.core.common.NestedMappingStrategy;
import com.hevelian.olastic.core.elastic.mappings.ElasticToCsdlMapper;
import com.hevelian.olastic.core.elastic.mappings.IElasticToCsdlMapper;
import com.hevelian.olastic.core.elastic.mappings.IMappingMetaDataProvider;
import com.hevelian.olastic.core.utils.MetaDataUtils;

/**
 * JUnit tests for {@link SingleElasticIndexCsdlEdmProvider} class.
 * 
 * @author rdidyk
 */
public class SingleElasticIndexCsdlEdmProviderTest {

    private static final String AUTHORS_INDEX = "authors";

    private static final String AUTHORS_NAMESPACE = ElasticToCsdlMapper.DEFAULT_NAMESPACE
            + MetaDataUtils.NEMESPACE_SEPARATOR + AUTHORS_INDEX;

    @Mock
    private IMappingMetaDataProvider metaDataProvider;

    @Test
    public void constructor_MappingMetadataProvider_Setted() {
        SingleElasticIndexCsdlEdmProvider edmProvider = new SingleElasticIndexCsdlEdmProvider(
                metaDataProvider, AUTHORS_INDEX);
        assertEquals(metaDataProvider, edmProvider.getMappingMetaDataProvider());
        assertNotNull(edmProvider.getCsdlMapper());
        assertNotNull(edmProvider.getNestedTypeMapper());
    }

    @Test
    public void constructor_MappingMetadataProviderAndCsdlMapper_Setted() {
        IElasticToCsdlMapper csdlMapper = mock(IElasticToCsdlMapper.class);
        SingleElasticIndexCsdlEdmProvider edmProvider = new SingleElasticIndexCsdlEdmProvider(
                metaDataProvider, AUTHORS_INDEX, csdlMapper);
        assertEquals(metaDataProvider, edmProvider.getMappingMetaDataProvider());
        assertEquals(csdlMapper, edmProvider.getCsdlMapper());
        assertNotNull(edmProvider.getNestedTypeMapper());
    }

    @Test
    public void constructor_MappingMetadataProviderAndNestedMappingStrategy_Setted() {
        NestedMappingStrategy nestedMappingStrategy = mock(NestedMappingStrategy.class);
        SingleElasticIndexCsdlEdmProvider edmProvider = new SingleElasticIndexCsdlEdmProvider(
                metaDataProvider, AUTHORS_INDEX, nestedMappingStrategy);
        assertEquals(metaDataProvider, edmProvider.getMappingMetaDataProvider());
        assertNotNull(edmProvider.getCsdlMapper());
        assertNotNull(edmProvider.getNestedTypeMapper());
        assertEquals(nestedMappingStrategy,
                edmProvider.getNestedTypeMapper().getNestedMappingStrategy());
    }

    @Test
    public void constructor_MappingMetadataProviderAndCsdlMapperAndNestedMappingStrategy_Setted() {
        IElasticToCsdlMapper csdlMapper = mock(IElasticToCsdlMapper.class);
        NestedMappingStrategy nestedMappingStrategy = mock(NestedMappingStrategy.class);
        SingleElasticIndexCsdlEdmProvider edmProvider = new SingleElasticIndexCsdlEdmProvider(
                metaDataProvider, AUTHORS_INDEX, csdlMapper, nestedMappingStrategy);
        assertEquals(metaDataProvider, edmProvider.getMappingMetaDataProvider());
        assertEquals(csdlMapper, edmProvider.getCsdlMapper());
        assertNotNull(edmProvider.getNestedTypeMapper());
        assertEquals(nestedMappingStrategy,
                edmProvider.getNestedTypeMapper().getNestedMappingStrategy());
    }

    @Test
    public void getSchemaNamespaces_Index_ShemaNamespacesRetrieved() throws ODataException {
        SingleElasticIndexCsdlEdmProvider edmProvider = new SingleElasticIndexCsdlEdmProvider(
                metaDataProvider, AUTHORS_INDEX);
        List<String> schemaNamespaces = edmProvider.getSchemaNamespaces();
        assertEquals(1, schemaNamespaces.size());
        assertTrue(schemaNamespaces.contains(AUTHORS_NAMESPACE));
        assertFalse(schemaNamespaces.contains("Olingo.Test.authors"));
    }

    @Test
    public void namespaceToIndex_DifferentNamespaces_ExpectedValuesRetrieved()
            throws ODataException {
        SingleElasticIndexCsdlEdmProvider edmProvider = new SingleElasticIndexCsdlEdmProvider(
                metaDataProvider, AUTHORS_INDEX);
        assertEquals(AUTHORS_INDEX, edmProvider.namespaceToIndex(AUTHORS_NAMESPACE));
        assertNull(edmProvider.namespaceToIndex("Olingo.Test.authors"));
    }

}
