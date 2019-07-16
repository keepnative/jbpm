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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbpm.services.task.impl.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name="SOUPE_WF_TASK_DEFINITION")
public class TaskDefImpl implements org.kie.internal.task.api.model.TaskDef {
    
    @Id
    @GeneratedValue(generator = "S_SOUPE_WF_TASK_DEFINITION")
    @GenericGenerator(
            name = "S_SOUPE_WF_TASK_DEFINITION",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "S_SOUPE_WF_TASK_DEFINITION")
            }
    )
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRIORITY")
    private int priority;


    public TaskDefImpl() {
        
    }

    
    public TaskDefImpl(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong( id );
        if( name == null ) { 
            name = "";
        }
        out.writeUTF( name );
        
        out.writeInt( priority );        
    }
    
    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        id = in.readLong();
        name = in.readUTF();
        priority = in.readInt();       
    }
    
}
