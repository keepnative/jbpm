/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.persistence.correlation;

import com.bmit.platform.soupe.model.AbstractBaseEntityWithDomainNoAuditing;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.kie.internal.process.CorrelationProperty;

import javax.persistence.*;

@Entity
@Table(name = "SOUPE_WF_CORRELATION_PROP")
public class CorrelationPropertyInfo extends AbstractBaseEntityWithDomainNoAuditing implements CorrelationProperty<String> {

	public CorrelationPropertyInfo() {
        
    }
    
    public CorrelationPropertyInfo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Id
    @GeneratedValue(generator = "sequenceStyleGenerator")
    @GenericGenerator(
            name = "sequenceStyleGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "S_SOUPE_WF_CORRELATION_PROP")
            }
    )
    @Column(name = "ID")
    private long id;
    
    @Version
    @Column(name = "VERSION")
    private int version;
    
    @ManyToOne
    @JoinColumn(name = "CORRELATION_KEY_ID")
    private CorrelationKeyInfo correlationKey;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private String value;
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {        
        return String.class.getName();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public CorrelationKeyInfo getCorrelationKey() {
        return correlationKey;
    }

    public void setCorrelationKey(CorrelationKeyInfo correlationKey) {
        this.correlationKey = correlationKey;
    }

    @Override
    public String toString() {
        return "CorrelationPropertyInfo [name=" + name + ", value=" + value
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((correlationKey == null) ? 0 : (int) (correlationKey.getId() ^ (correlationKey.getId() >>> 32)));
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + version;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CorrelationPropertyInfo other = (CorrelationPropertyInfo) obj;
        if (correlationKey == null) {
            if (other.correlationKey != null)
                return false;
        } else if (correlationKey.getId() != other.correlationKey.getId())
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        if (version != other.version)
            return false;
        return true;
    }
    

}
