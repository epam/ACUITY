/*
 * Copyright 2021 The University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.acuity.va.security.acl.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class UsernameFullNameAndLinkedAccount {

    private String username;
    private String fullname;
    private String linkedUsername;
    
    public boolean hasLinkedAccount() {
        return !StringUtils.isEmpty(linkedUsername);
    }
}
