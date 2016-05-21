package org.jbpm.process.audit.strategy;

import javax.persistence.EntityManager;

public interface PersistenceStrategy {

   String PERSISTENCE_STRATEGY_TYPE_NAME = "org.jbpm.process.audit.strategy.PersistenceStrategy";

   public EntityManager getEntityManager();
   
   public Object joinTransaction(EntityManager em);
  
   public void leaveTransaction(EntityManager em, Object transaction);
 
   public void dispose();
}
