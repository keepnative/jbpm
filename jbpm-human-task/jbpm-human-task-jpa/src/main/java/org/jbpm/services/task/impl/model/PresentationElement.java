/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbpm.services.task.impl.model;

import com.bmit.platform.soupe.data.core.model.AbstractBaseEntityWithDomainNoAuditing;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SOUPE_WF_PRESENT_ELEMENT")
public class PresentationElement extends AbstractBaseEntityWithDomainNoAuditing {

    @Id
    @GeneratedValue(generator = "sequenceStyleGenerator")
    @GenericGenerator(
            name = "sequenceStyleGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "S_SOUPE_WF_PRESENT_ELEMENT")
            }
    )
    @Column(name = "ID")
    private Long id;
}
