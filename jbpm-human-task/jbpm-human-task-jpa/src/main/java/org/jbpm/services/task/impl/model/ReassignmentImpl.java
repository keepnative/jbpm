/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package org.jbpm.services.task.impl.model;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.keepnative.soupe.model.AbstractBaseEntityWithDomainNoAuditing;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.jbpm.services.task.utils.CollectionUtils;
import org.kie.api.task.model.I18NText;
import org.kie.api.task.model.OrganizationalEntity;

@Entity
@Table(name="SOUPE_WF_REASSIGNMENT")
public class ReassignmentImpl extends AbstractBaseEntityWithDomainNoAuditing implements org.kie.internal.task.api.model.Reassignment {
    
    @Id
    @GeneratedValue(generator = "S_SOUPE_WF_REASSIGNMENT")
    @GenericGenerator(
            name = "S_SOUPE_WF_REASSIGNMENT",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "S_SOUPE_WF_REASSIGNMENT")
            }
    )
    @Column(name = "ID")
    private Long                       id;

    @OneToMany(cascade = CascadeType.ALL, targetEntity=I18NTextImpl.class)
    @JoinColumn(name = "REASSIGNMENT_DOCUMENTATION_ID", nullable = true)
    private List<I18NText>             documentation = Collections.emptyList();
    
    @ManyToMany(targetEntity=OrganizationalEntityImpl.class)
    @JoinTable(name = "SOUPE_WF_RASIN_OWNER", joinColumns = @JoinColumn(name = "TASK_ID"), inverseJoinColumns = @JoinColumn(name = "ENTITY_ID"))
    private List<OrganizationalEntity> potentialOwners = Collections.emptyList();

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong( id );
        CollectionUtils.writeI18NTextList( documentation, out );
        CollectionUtils.writeOrganizationalEntityList( potentialOwners, out );
    }
    
    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        id = in.readLong();
        documentation = CollectionUtils.readI18NTextList( in );
        potentialOwners = CollectionUtils.readOrganizationalEntityList( in );        
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<I18NText> getDocumentation() {
        return documentation;
    }

    public void setDocumentation(List<I18NText> documentation) {
        this.documentation = documentation;
    }

    public List<OrganizationalEntity> getPotentialOwners() {
        return potentialOwners;
    }

    public void setPotentialOwners(List<OrganizationalEntity> potentialOwners) {
        this.potentialOwners = potentialOwners;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + CollectionUtils.hashCode( documentation );
        result = prime * result + CollectionUtils.hashCode( potentialOwners );
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( !(obj instanceof ReassignmentImpl) ) return false;
        ReassignmentImpl other = (ReassignmentImpl) obj;
        return CollectionUtils.equals( documentation, other.documentation ) && CollectionUtils.equals( potentialOwners, other.potentialOwners );
    }
    
    

}
