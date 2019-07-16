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

package org.jbpm.services.task.audit.impl.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.kie.internal.task.api.TaskVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "SOUPE_WF_TASK_VARIABLE")
public class TaskVariableImpl implements TaskVariable, Serializable {

    private static final long serialVersionUID = 5388016330549830048L;
    private static final Logger logger = LoggerFactory.getLogger(TaskVariableImpl.class);
    
    @Transient
    private final int VARIABLE_LOG_LENGTH = Integer.parseInt(System.getProperty("org.jbpm.task.var.log.length", "4000"));


    @Id
    @GeneratedValue(generator = "S_SOUPE_WF_TASK_VARIABLE")
    @GenericGenerator(
            name = "S_SOUPE_WF_TASK_VARIABLE",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "S_SOUPE_WF_TASK_VARIABLE")
            }
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "TASK_ID")
    private Long taskId;

    @Column(name = "PROCESS_INSTANCE_ID")
    private Long processInstanceId;

    @Column(name = "PROCESS_ID")
    private String processId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE", length = 4000)
    private String value;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TYPE")
    private VariableType type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFICATION_DATE")
    private Date modificationDate;

    public Long getId() {
        return id;
    }

    @Override
    public Long getTaskId() {
        return taskId;
    }

    @Override
    public Long getProcessInstanceId() {
        return processInstanceId;
    }

    @Override
    public String getProcessId() {
        return processId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Date getModificationDate() {
        return modificationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setProcessInstanceId(Long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        if (value != null && value.length() > VARIABLE_LOG_LENGTH) {
            value = value.substring(0, VARIABLE_LOG_LENGTH);
            logger.warn("Task variable '{}' content was trimmed as it was too long (more than {} characters)", name, VARIABLE_LOG_LENGTH);
        }
        this.value = value;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public VariableType getType() {
        return type;
    }

    public void setType(VariableType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TaskVariableImpl [taskId=" + taskId + ", name='" + name + "', value='" + value + "', type=" + type
                + " (processInstanceId=" + processInstanceId + ", processId=" + processId + ")]";
    }

}
