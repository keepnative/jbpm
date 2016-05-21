package org.jbpm.persistence.processinstance;

import com.bmit.platform.soupe.data.core.model.AbstractBaseEntityWithDomainNoAuditing;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "SOUPE_WF_PROC_INST_EVENT")
public class ProcessInstanceEventInfo extends AbstractBaseEntityWithDomainNoAuditing {

    @Id
    @GeneratedValue(generator = "sequenceStyleGenerator")
    @GenericGenerator(
            name = "sequenceStyleGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "S_SOUPE_WF_PROC_INST_EVENT")
            }
    )
    @Column(name = "ID")
    private long   id;

    @Version
    @Column(name = "VERSION")
    private int    version;

    @Column(name = "EVENT_TYPE")
    private String eventType;

    @Column(name = "PROCESS_INSTANCE_ID")
    private long   processInstanceId;

    protected ProcessInstanceEventInfo() {
    }
    
    public long getId() {
        return this.id;
    }
    
    public int getVersion() {
        return this.version;
    }    

    public ProcessInstanceEventInfo(long processInstanceId,
                                    String eventType) {
        this.processInstanceId = processInstanceId;
        this.eventType = eventType;
    }

    public long getProcessInstanceId() {
        return processInstanceId;
    }

    public String getEventType() {
        return eventType;
    }

}
