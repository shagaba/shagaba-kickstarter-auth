package com.shagaba.kickstarter.common.mapper.converter;

import java.util.Collection;

import org.dozer.DozerConverter;

public class CollectionSizeConverter extends DozerConverter<Collection, Integer> {

    public CollectionSizeConverter() {
        super(Collection.class, Integer.class);
    }

    @Override
    public Integer convertTo(Collection collectionSource, Integer destination) {
        if (collectionSource == null) {
            return 0;
        }
        return collectionSource.size();
    }

    @Override
    public Collection<?> convertFrom(Integer source, Collection destination) {
        throw new IllegalStateException("Unknown value!");
    }

}
