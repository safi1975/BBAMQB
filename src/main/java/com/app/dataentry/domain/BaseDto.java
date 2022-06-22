package com.app.dataentry.domain;

import com.app.dataentry.model.BaseEntity;

public interface BaseDto<E extends BaseEntity> {

     BaseDto<?> mapFromEntity(E entity);

     E mapToEntity();
}
