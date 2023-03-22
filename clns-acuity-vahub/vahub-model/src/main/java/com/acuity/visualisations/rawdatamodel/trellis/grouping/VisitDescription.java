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

package com.acuity.visualisations.rawdatamodel.trellis.grouping;

import com.acuity.visualisations.rawdatamodel.trellis.grouping.extractor.VisitDescriptionValueExtractor;
import com.acuity.visualisations.rawdatamodel.util.Attributes;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Comparator;
import java.util.Objects;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class VisitDescription extends VisitDescriptionValueExtractor implements Comparable<VisitDescription> {
    private String visitDescription;

    @Override
    public int compareTo(VisitDescription o) {
        int v1 = extractFrom(this);
        int v2 = extractFrom(o);
        if (v1 == v2) {
            return Objects.compare(this.getVisitDescription(), o.getVisitDescription(), Comparator.naturalOrder());
        } else {
            return Integer.compare(v1, v2);
        }
    }

    @Override
    public String toString() {
        return visitDescription == null ? Attributes.DEFAULT_EMPTY_VALUE : visitDescription;
    }
}
