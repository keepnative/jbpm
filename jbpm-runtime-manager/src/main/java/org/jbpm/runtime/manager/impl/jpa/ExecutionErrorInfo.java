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

package org.jbpm.runtime.manager.impl.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

import io.keepnative.soupe.model.AbstractBaseEntityWithDomainNoAuditing;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.kie.internal.runtime.error.ExecutionError;


@Entity
@Table(name = "SOUPE_WF_EXEC_ERROR_INFO")
public class ExecutionErrorInfo extends AbstractBaseEntityWithDomainNoAuditing implements Serializable {

	private static final long serialVersionUID = 6669858787722894023L;

    @Id
    @GeneratedValue(generator = "S_SOUPE_WF_EXEC_ERROR_INFO")
    @GenericGenerator(
            name = "S_SOUPE_WF_EXEC_ERROR_INFO",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "S_SOUPE_WF_EXEC_ERROR_INFO")
            }
    )
    @Column(name = "ID")
	private Long id;

    @Column(name="ERROR_ID")
    private String errorId;

    @Column(name="TYPE")
    private String type;

    @Column(name="DEPLOYMENT_ID")
    private String deploymentId;

    @Column(name="PROCESS_INSTANCE_ID")
    private Long processInstanceId;
    @Column(name="PROCESS_ID")
    private String processId;
    @Column(name="ACTIVITY_ID")
    private Long activityId;
    @Column(name="ACTIVITY_NAME")
    private String activityName;
    @Column(name="JOB_ID")
    private Long jobId;

    @Column(name="ERROR_MESSAGE")
    private String errorMessage;

    @Lob
    @Column(name="ERROR", length=65535)
    private String error;

    @Column(name="ACKNOWLEDGED")
    private Short acknowledged = 0;

    @Column(name="ACKNOWLEDGED_BY")
    private String acknowledgedBy;

    @Column(name="ACKNOWLEDGED_AT")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date acknowledgedAt;

    @Column(name="ERROR_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date errorDate;

    @Column(name="INIT_ACTIVITY_ID")
    private Long initActivityId;

    public ExecutionErrorInfo() {
        errorId = UUID.randomUUID().toString();
    }

    public ExecutionErrorInfo(String errorId, String type, String deploymentId, Long processInstanceId, String processId, Long activityId, String activityName, Long jobId, String errorMessage,
                          short acknowledged, String acknowledgedBy, Date acknowledgedAt,
                          Date errorDate) {
        this(errorId, type, deploymentId, processInstanceId, processId, activityId, activityName, jobId, errorMessage, null, acknowledged, acknowledgedBy, acknowledgedAt, errorDate);
    }

    public ExecutionErrorInfo(String errorId, String type, String deploymentId, Long processInstanceId, String processId, Long activityId, String activityName, Long jobId, String errorMessage, String error,
                          short acknowledged, String acknowledgedBy, Date acknowledgedAt,
                          Date errorDate) {
        this.errorId = errorId;
        this.type = type;
        this.deploymentId = deploymentId;
        this.processInstanceId = processInstanceId;
        this.processId = processId;
        this.activityId = activityId;
        this.activityName = activityName;
        this.jobId = jobId;
        this.errorMessage = errorMessage;
        this.error = error;
        this.acknowledged = acknowledged;
        this.acknowledgedBy = acknowledgedBy;
        this.acknowledgedAt = acknowledgedAt;
        this.errorDate = errorDate;
    }

    public ExecutionErrorInfo(String errorId, String type, String deploymentId, Long processInstanceId, String processId, Long activityId, String activityName, Long jobId, String errorMessage, String error, Date errorDate, Long initActivityId) {
        this.errorId = errorId;
        this.type = type;
        this.deploymentId = deploymentId;
        this.processInstanceId = processInstanceId;
        this.processId = processId;
        this.activityId = activityId;
        this.activityName = activityName;
        this.jobId = jobId;
        this.errorMessage = errorMessage;
        this.error = error;
        this.errorDate = errorDate;
        this.acknowledged = new Short("0");
        this.initActivityId = initActivityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Long getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(Long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    protected Short getAcknowledged() {
        return acknowledged;
    }

    protected void setAcknowledged(Short acknowledged) {
        this.acknowledged = acknowledged;
    }

    public boolean isAcknowledged() {
        if (acknowledged == null) {
            return false;
        }
        return (acknowledged == 1) ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setAcknowledged(boolean acknowledged) {
        setAcknowledged(acknowledged ? new Short("1") : new Short("0"));
    }

    public String getAcknowledgedBy() {
        return acknowledgedBy;
    }

    public void setAcknowledgedBy(String acknowledgedBy) {
        this.acknowledgedBy = acknowledgedBy;
    }

    public Date getAcknowledgedAt() {
        return acknowledgedAt;
    }

    public void setAcknowledgedAt(Date acknowledgedAt) {
        this.acknowledgedAt = acknowledgedAt;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getInitActivityId() {
        return initActivityId;
    }


    public void setInitActivityId(Long initActivityId) {
        this.initActivityId = initActivityId;
    }

    @Override
    public String toString() {
        return "ExecutionErrorInfo [errorId=" + errorId + ", type=" + type + ", deploymentId=" + deploymentId + ", processInstanceId=" + processInstanceId + ", initActivityId=" + initActivityId +
                ", processId=" + processId + ", activityId=" + activityId + ", activityName=" + activityName + ", errorMessage=" + errorMessage +
                ", acknowledged=" + acknowledged + "]";
    }

	
}
