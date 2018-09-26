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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

import io.keepnative.soupe.model.AbstractBaseEntityWithDomainNoAuditing;
import io.keepnative.soupe.model.AbstractBaseEntityWithoutDomainNoAuditing;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.kie.internal.task.api.AuditTask;

@Entity
@Table(name = "SOUPE_WF_AUDIT_TASK")
public class AuditTaskImpl extends AbstractBaseEntityWithDomainNoAuditing implements Serializable,
                                      AuditTask {

    private static final long serialVersionUID = 5388016330549830043L;

    @Id
    @GeneratedValue(generator = "S_SOUPE_WF_AUDIT_TASK")
    @GenericGenerator(
            name = "S_SOUPE_WF_AUDIT_TASK",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "S_SOUPE_WF_AUDIT_TASK")
            }
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "TASK_ID")
    private Long taskId;

    @Column(name = "STATUS")
    private String status;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "ACTIVATION_TIME")
    private Date activationTime;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRIORITY")
    private int priority;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "ACTUAL_OWNER")
    private String actualOwner;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON")
    private Date createdOn;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "DUE_DATE")
    private Date dueDate;
    @Column(name = "PROCESS_INSTANCE_ID")
    private long processInstanceId;
    @Column(name = "PROCESS_ID")
    private String processId;
    @Column(name = "PROCESS_SESSION_ID")
    private long processSessionId;
    @Column(name = "PARENT_ID")
    private long parentId;
    @Column(name = "DEPLOYMENT_ID")
    private String deploymentId;
    @Column(name = "WORK_ITEM_ID")
    private Long workItemId;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFICATION_DATE")
    private Date lastModificationDate;

    public AuditTaskImpl() {
    }

    public AuditTaskImpl(long taskId,
                         String name,
                         String status,
                         Date activationTime,
                         String actualOwner,
                         String description,
                         int priority,
                         String createdBy,
                         Date createdOn,
                         Date dueDate,
                         long processInstanceId,
                         String processId,
                         long processSessionId,
                         String deploymentId,
                         long parentId,
                         long workItemId) {
        this.taskId = taskId;
        this.status = status;
        this.activationTime = activationTime;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.actualOwner = actualOwner;
        this.dueDate = dueDate;
        this.processInstanceId = processInstanceId;
        this.processId = processId;
        this.processSessionId = processSessionId;
        this.deploymentId = deploymentId;
        this.parentId = parentId;
        this.workItemId = workItemId;
        this.lastModificationDate = new Date();
    }

    public AuditTaskImpl(long taskId,
                         String name,
                         String status,
                         Date activationTime,
                         String actualOwner,
                         String description,
                         int priority,
                         String createdBy,
                         Date createdOn,
                         Date dueDate,
                         long processInstanceId,
                         String processId,
                         long processSessionId,
                         String deploymentId,
                         long parentId,
                         long workItemId,
                         Date lastModificationDate) {
        this.taskId = taskId;
        this.status = status;
        this.activationTime = activationTime;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.actualOwner = actualOwner;
        this.dueDate = dueDate;
        this.processInstanceId = processInstanceId;
        this.processId = processId;
        this.processSessionId = processSessionId;
        this.deploymentId = deploymentId;
        this.parentId = parentId;
        this.workItemId = workItemId;
        this.lastModificationDate = lastModificationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public long getTaskId() {
        return taskId;
    }

    @Override
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Date getActivationTime() {
        return activationTime;
    }

    @Override
    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public long getProcessInstanceId() {
        return processInstanceId;
    }

    @Override
    public void setProcessInstanceId(long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public String getProcessId() {
        return processId;
    }

    @Override
    public void setProcessId(String processId) {
        this.processId = processId;
    }

    @Override
    public long getProcessSessionId() {
        return processSessionId;
    }

    @Override
    public void setProcessSessionId(long processSessionId) {
        this.processSessionId = processSessionId;
    }

    @Override
    public long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getActualOwner() {
        return actualOwner;
    }

    public void setActualOwner(String actualOwner) {
        this.actualOwner = actualOwner;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    @Override
    public long getWorkItemId() {
        return workItemId;
    }

    @Override
    public void setWorkItemId(long workItemId) {
        this.workItemId = workItemId;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }
}
