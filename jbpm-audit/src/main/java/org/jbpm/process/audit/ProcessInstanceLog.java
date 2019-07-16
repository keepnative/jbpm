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

package org.jbpm.process.audit;

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
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.jbpm.process.audit.event.AuditEvent;
import org.jbpm.process.audit.event.AuditEventBuilder;
import org.kie.api.runtime.KieRuntime;

@Entity
@Table(name="SOUPE_WF_PROC_INST_LOG")
public class ProcessInstanceLog implements Serializable, AuditEvent, org.kie.api.runtime.manager.audit.ProcessInstanceLog {
    
	private static final long serialVersionUID = 510l;

    @Id
    @GeneratedValue(generator = "S_SOUPE_WF_PROC_INST_LOG")
    @GenericGenerator(
            name = "S_SOUPE_WF_PROC_INST_LOG",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "S_SOUPE_WF_PROC_INST_LOG")
            }
    )
    @Column(name = "ID")
	private long id;

    @Column(name = "PROCESS_INSTANCE_ID")
    private long processInstanceId;

    @Column(name = "PROCESS_ID")
    private String processId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date start;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date end;
    
    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "PARENT_PROCESS_INSTANCE_ID")
    private Long parentProcessInstanceId;

    @Column(name = "OUTCOME")
    private String outcome;

    @Column(name = "DURATION")
    private Long duration;

    @Column(name="IDENTIFIER")
    private String identity;

    @Column(name = "PROCESS_VERSION")
    private String processVersion;

    @Column(name = "PROCESS_NAME")
    private String processName;

    @Column(name = "CORRELATION_KEY")
    private String correlationKey;
    
    @Column(name = "PROCESS_TYPE")
    private Integer processType;
      
    /**
     * Dependening on the {@link AuditEventBuilder} implementation, 
     * this can be<ul>
     * <li>The {@link KieRuntime} id</li>
     * <li>The deployment unit Id</li>
     * 
     */
    @Column(name = "EXTERNAL_ID")
    private String externalId;

    @Column(name = "PROCESS_INSTANCE_DESC")
    private String processInstanceDescription;
        
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SLA_DUE_DATE")
    private Date slaDueDate;
    
    @Column(name = "SLA_COMPLIANCE")
    private Integer slaCompliance;
    
    public ProcessInstanceLog() {
    }
    
    public ProcessInstanceLog(long processInstanceId, String processId) {
        setProcessInstanceId(processInstanceId);
        setProcessId(processId);
        setStart(new Date());
    }
    
    public long getId() {
    	return id;
    }
    
    void setId(long id) {
		this.id = id;
	}

    public Long getProcessInstanceId() {
        return processInstanceId;
    }
    
    public void setProcessInstanceId(long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    
    public String getProcessId() {
        return processId;
    }
    
    public void setProcessId(String processId) {
        this.processId = processId;
    }
    
    public Date getStart() {
        return start;
    }
    
    public void setStart(Date start) {
        this.start = start;
    }
    
    public Date getEnd() {
        return end;
    }
    
    public void setEnd(Date end) {
        this.end = end;
    }
    
    public String toString() {
        return "Process '" + processId + "' [" + processInstanceId + "]";
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + (int) id;
		result = prime * result
				+ ((processId == null) ? 0 : processId.hashCode());
		result = prime * result	+ (int) processInstanceId;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result   + ((parentProcessInstanceId == null) ? 0 : parentProcessInstanceId.hashCode());
		result = prime * result +  ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((outcome == null) ? 0 : outcome.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		result = prime * result + ((externalId == null) ? 0 : externalId.hashCode());
		result = prime * result + ((processVersion == null) ? 0 : processVersion.hashCode());
		result = prime * result + ((processName == null) ? 0 : processName.hashCode());
		result = prime * result + ((processInstanceDescription == null) ? 0 : processInstanceDescription.hashCode());
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
		ProcessInstanceLog other = (ProcessInstanceLog) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (id != other.id)
			return false;
		if (processId == null) {
			if (other.processId != null)
				return false;
		} else if (!processId.equals(other.processId))
			return false;
		if (processInstanceId != other.processInstanceId)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		
		if (parentProcessInstanceId == null) {
            if (other.parentProcessInstanceId != null)
                return false;
        } else if (!parentProcessInstanceId.equals(other.parentProcessInstanceId))
            return false;
		
		if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
		
		if (outcome == null) {
            if (other.outcome != null)
                return false;
        } else if (!outcome.equals(other.outcome))
            return false;
		
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;	
        
        if (identity == null) {
            if (other.identity != null)
                return false;
        } else if (!identity.equals(other.identity))
            return false;  
        
        if (externalId == null) {
            if (other.externalId != null)
                return false;
        } else if (!externalId.equals(other.externalId))
            return false;
        
        if (processVersion == null) {
            if (other.processVersion != null)
                return false;
        } else if (!processVersion.equals(other.processVersion))
            return false;
        
        if (processName == null) {
            if (other.processName != null)
                return false;
        } else if (!processName.equals(other.processName))
            return false; 
        if (processInstanceDescription == null) {
            if (other.processInstanceDescription != null)
                return false;
        } else if (!processInstanceDescription.equals(other.processInstanceDescription))
            return false; 
		return true; 
	}

	public Integer getStatus() {
        return status;
    }

	public void setStatus(int status) {
        this.status = status;
    }

	public Long getParentProcessInstanceId() {
        return parentProcessInstanceId;
    }

	public void setParentProcessInstanceId(long parentProcessInstanceId) {
        this.parentProcessInstanceId = parentProcessInstanceId;
    }

	public String getOutcome() {
        return outcome;
    }

	public void setOutcome(String errorCode) {
        this.outcome = errorCode;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String domainId) {
        this.externalId = domainId;
    }

    public String getProcessVersion() {
        return processVersion;
    }

    public void setProcessVersion(String version) {
        this.processVersion = version;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
    
    public String getProcessInstanceDescription() {
        return processInstanceDescription;
    }

    public void setProcessInstanceDescription(String processInstanceDescription) {
        this.processInstanceDescription = processInstanceDescription;
    }

	public String getCorrelationKey() {
		return correlationKey;
	}

	public void setCorrelationKey(String correlationKey) {
		this.correlationKey = correlationKey;
	}

    public Integer getProcessType() {
        return processType;
    }
    
    public void setProcessType(Integer processType) {
        this.processType = processType;
    }
    
    public Date getSlaDueDate() {
        return slaDueDate;
    }
    
    public void setSlaDueDate(Date slaDueDate) {
        this.slaDueDate = slaDueDate;
    }
    
    public Integer getSlaCompliance() {
        return slaCompliance;
    }
    
    public void setSlaCompliance(Integer slaCompliance) {
        this.slaCompliance = slaCompliance;
    }

}
