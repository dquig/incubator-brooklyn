/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.brooklyn.camp.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.spi.ApplicationComponent;
import io.brooklyn.camp.spi.Link;
import io.brooklyn.camp.spi.PlatformComponent;

public class ApplicationComponentDto extends ResourceDto {

    // defined as a constant so can be used in Swagger REST API annotations
    public static final String CLASS_NAME = "io.brooklyn.camp.dto.ApplicationComponentDto";
    static { assert CLASS_NAME.equals(ApplicationComponentDto.class.getCanonicalName()); }

    protected ApplicationComponentDto() {}
    protected ApplicationComponentDto(DtoFactory dtoFactory, ApplicationComponent x) {
        super(dtoFactory, x);
        
        platformComponents = new ArrayList<LinkDto>();
        for (Link<PlatformComponent> t: x.getPlatformComponents().links()) {
            platformComponents.add(LinkDto.newInstance(dtoFactory, PlatformComponent.class, t));
        }
        
        applicationComponents = new ArrayList<LinkDto>();
        for (Link<ApplicationComponent> t: x.getApplicationComponents().links()) {
            applicationComponents.add(LinkDto.newInstance(dtoFactory, ApplicationComponent.class, t));
        }
    }
 
    private List<LinkDto> platformComponents;
    private List<LinkDto> applicationComponents;

    @JsonInclude(Include.NON_EMPTY)
    public List<LinkDto> getPlatformComponents() {
        return platformComponents;
    }
    
    @JsonInclude(Include.NON_EMPTY)
    public List<LinkDto> getApplicationComponents() {
        return applicationComponents;
    }
    
    // --- building ---

    public static ApplicationComponentDto newInstance(DtoFactory dtoFactory, ApplicationComponent x) {
        return new ApplicationComponentDto(dtoFactory, x);
    }
    
}
