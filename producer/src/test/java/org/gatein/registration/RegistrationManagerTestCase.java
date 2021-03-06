/*
 * JBoss, a division of Red Hat
 * Copyright 2010, Red Hat Middleware, LLC, and individual
 * contributors as indicated by the @authors tag. See the
 * copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.gatein.registration;

import junit.framework.TestCase;
import org.gatein.registration.impl.RegistrationManagerImpl;
import org.gatein.registration.impl.RegistrationPersistenceManagerImpl;
import org.gatein.registration.policies.DefaultRegistrationPolicy;
import org.gatein.registration.policies.DefaultRegistrationPropertyValidator;
import org.gatein.wsrp.WSRPConstants;
import org.gatein.wsrp.registration.RegistrationPropertyDescription;

import javax.xml.namespace.QName;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a>
 * @version $Revision: 8784 $
 * @since 2.6
 */
public class RegistrationManagerTestCase extends TestCase
{

   private RegistrationManager manager;
   private Map<QName, Object> registrationProperties;
   private Map<QName, RegistrationPropertyDescription> expectations;

   protected void setUp() throws Exception
   {
      manager = new RegistrationManagerImpl();

      DefaultRegistrationPolicy policy = new DefaultRegistrationPolicy();
      policy.setValidator(new DefaultRegistrationPropertyValidator());
      manager.setPolicy(policy);
      manager.setPersistenceManager(new RegistrationPersistenceManagerImpl());

      //todo: registration properties setup will need to be updated when property validation is implemented
      QName prop1Name = new QName("prop1");
      QName prop2Name = new QName("prop2");
      registrationProperties = new HashMap<QName, Object>();
      registrationProperties.put(prop1Name, "value1");
      registrationProperties.put(prop2Name, "value2");

      expectations = new HashMap<QName, RegistrationPropertyDescription>();
      expectations.put(prop1Name, new RegistrationPropertyDescription(prop1Name, WSRPConstants.XSD_STRING));
      expectations.put(prop2Name, new RegistrationPropertyDescription(prop2Name, WSRPConstants.XSD_STRING));
   }

   public void testPolicy()
   {
      RegistrationPolicy policy = manager.getPolicy();
      assertNotNull(policy);
   }

   public void testAddRegistrationTo() throws Exception
   {
      Registration registration = manager.addRegistrationTo("consumerName", registrationProperties, expectations, true);
      assertNotNull(registration);
      assertNotNull(registration.getPersistentKey());

      Consumer consumer = manager.getConsumerByIdentity("consumerName");
      assertNotNull(consumer); // default policy: name == identity
      assertEquals(consumer, registration.getConsumer());

      String registrationHandle = registration.getRegistrationHandle();
      assertNotNull(registrationHandle);
      assertEquals(consumer, manager.getConsumerFor(registrationHandle));
   }

   public void testAddRegistrationToInexistentConsumer() throws RegistrationException
   {
      try
      {
         manager.addRegistrationTo("consumerName", registrationProperties, expectations, false);
         fail("Should have failed: consumer does not exist");
      }
      catch (NoSuchRegistrationException expected)
      {
      }

      assertNull(manager.getConsumerByIdentity("consumerName")); // default policy: name == identity
   }

   public void testGetConsumerForNullRegistrationHandle() throws Exception
   {
      try
      {
         manager.getConsumerFor(null);
         fail("Should have failed: cannot find a consumer for a null registration handle");
      }
      catch (IllegalArgumentException e)
      {
      }
   }

   public void testCreateConsumer() throws Exception
   {
      String name = "consumerName";
      Consumer consumer = manager.createConsumer(name);
      assertNotNull(consumer);
      assertEquals(name, consumer.getName());
      assertNotNull(consumer.getId());
      assertNull(consumer.getGroup());

      Collection consumers = manager.getConsumers();
      assertEquals(1, consumers.size());
      assertTrue(consumers.contains(consumer));
      assertEquals(consumer, manager.getConsumerByIdentity(name)); // default policy: name == identity

      try
      {
         consumers.add(consumer);
         fail("Shouldn't be possible to directly modify consumer collection");
      }
      catch (UnsupportedOperationException expected)
      {
      }
   }

   public void testCreateConsumerWithGroupFromPolicy() throws RegistrationException
   {
      // use a different policy: now specifies that when creating a consumer, it should be added to a group with the same name
      DefaultRegistrationPolicy policy = new DefaultRegistrationPolicy()
      {
         public String getAutomaticGroupNameFor(String consumerName)
         {
            return "group_" + consumerName;
         }
      };
      manager.setPolicy(policy);

      String name = "name";
      Consumer consumer = manager.createConsumer(name);
      assertNotNull(consumer);

      ConsumerGroup group = manager.getConsumerGroup("group_" + name);
      assertNotNull(group);
      assertEquals(group, consumer.getGroup());
      assertTrue(group.getConsumers().contains(consumer));
   }

   public void testCreateDuplicateConsumer() throws RegistrationException
   {
      String name = "name";
      assertNotNull(manager.createConsumer(name));

      try
      {
         manager.createConsumer(name);
         fail("Should have failed when trying to create a consumer with an existing name");
      }
      catch (DuplicateRegistrationException expected)
      {
      }
   }

   public void testAddAutomaticallyCreatedConsumerToInexistentGroup() throws RegistrationException
   {
      try
      {
         manager.addConsumerToGroupNamed("foo", "bar", false, true);
         fail("Shouldn't be possible to add a consumer to an inexistent group without first creating it");
      }
      catch (NoSuchRegistrationException expected)
      {
      }

      assertNull(manager.getConsumerByIdentity("foo"));
      assertNull(manager.getConsumerGroup("bar"));
   }

   public void testAddInexistentConsumerToAutomaticallyCreatedGroup() throws RegistrationException
   {
      try
      {
         manager.addConsumerToGroupNamed("foo", "bar", true, false);
         fail("Shouldn't be possible to add an inexistent consumer to a group without first creating it");
      }
      catch (NoSuchRegistrationException expected)
      {
      }

      assertNull(manager.getConsumerByIdentity("foo"));
      assertNull(manager.getConsumerGroup("bar"));
   }

   public void testAddInexistentConsumerToGroup() throws RegistrationException
   {
      manager.createConsumerGroup("bar");
      try
      {
         manager.addConsumerToGroupNamed("foo", "bar", false, false);
         fail("Shouldn't be possible to add an inexistent consumer to a group without first creating it");
      }
      catch (NoSuchRegistrationException expected)
      {
      }

      assertNull(manager.getConsumerByIdentity("foo"));
      assertNotNull(manager.getConsumerGroup("bar"));
   }

   public void testAddInexistentConsumerToInexistentGroup() throws RegistrationException
   {
      try
      {
         manager.addConsumerToGroupNamed("foo", "bar", false, false);
         fail("Shouldn't be possible to add a consumer to an inexistent group without first creating it");
      }
      catch (NoSuchRegistrationException expected)
      {
      }

      assertNull(manager.getConsumerByIdentity("foo"));
      assertNull(manager.getConsumerGroup("bar"));
   }

   public void testAddConsumerToGroup() throws Exception
   {
      String groupName = "group";
      String consumerName = "consumer";
      Consumer consumer = manager.addConsumerToGroupNamed(consumerName, groupName, true, true);

      Consumer consumer1 = manager.getConsumerByIdentity(consumerName);
      assertNotNull(consumer1);
      assertEquals(consumer1, consumer); // default policy: identity == name

      ConsumerGroup group = manager.getConsumerGroup(groupName);
      assertNotNull(group);
      assertEquals(group, consumer.getGroup());
   }

   public void testCreateConsumerGroup() throws Exception
   {
      String groupName = "name";
      ConsumerGroup group = manager.createConsumerGroup(groupName);
      assertNotNull(group);
      assertEquals(groupName, group.getName());

      Collection groups = manager.getConsumerGroups();
      assertEquals(1, groups.size());
      assertTrue(groups.contains(group));
      assertEquals(group, manager.getConsumerGroup(groupName));

      try
      {
         groups.add(group);
         fail("Shouldn't be possible to directly modify group collection");
      }
      catch (UnsupportedOperationException expected)
      {
      }
   }

   public void testRemoveConsumerGroup() throws RegistrationException
   {
      String groupName = "name";
      ConsumerGroup group = manager.createConsumerGroup(groupName);
      manager.removeConsumerGroup(group);
      assertNull(manager.getConsumerGroup(groupName));

      manager.createConsumerGroup(groupName);
      manager.removeConsumerGroup(groupName);
      assertNull(manager.getConsumerGroup(groupName));
   }

   public void testCascadeRemovalOnConsumerGroupRemoval() throws Exception
   {
      String groupName = "group";
      String consumerName = "consumer";
      Consumer consumer = manager.addConsumerToGroupNamed(consumerName, groupName, true, true);
      String consumerIdentity = consumer.getId();

      Registration reg = manager.addRegistrationTo(consumerName, registrationProperties, expectations, false);
      String handle = reg.getRegistrationHandle();

      ConsumerGroup group = manager.getConsumerGroup(groupName);

      manager.removeConsumerGroup(group);
      assertNull(manager.getConsumerByIdentity(consumerIdentity));
      assertNull(manager.getRegistration(handle));
   }

   public void testCascadeRemovalOnConsumerRemoval() throws Exception
   {
      String consumerName = "consumer";
      Consumer consumer = manager.createConsumer(consumerName);
      String consumerIdentity = consumer.getId();

      Registration reg = manager.addRegistrationTo(consumerName, registrationProperties, expectations, false);
      String handle = reg.getRegistrationHandle();

      manager.removeConsumer(consumer);
      assertNull(manager.getConsumerByIdentity(consumerIdentity));
      assertNull(manager.getRegistration(handle));
   }

   public void testRemoveSingleRegistration() throws Exception
   {
      String consumerName = "consumer";
      Consumer consumer = manager.createConsumer(consumerName);

      Registration reg = manager.addRegistrationTo(consumerName, registrationProperties, expectations, false);
      String handle = reg.getRegistrationHandle();

      assertTrue(consumer.getRegistrations().contains(reg));

      manager.removeRegistration(handle);
      assertTrue(!consumer.getRegistrations().contains(reg));
      assertNull(manager.getRegistration(handle));

      // since the consumer doesn't have any registration anymore, its status should become pending
      assertEquals(RegistrationStatus.PENDING, consumer.getStatus());

      // shouldn't be possible anymore to retrieve the consumer from the registration handle
      assertNull(manager.getConsumerFor(handle));
   }

   public void testRemoveRegistrationOnConsumerWithOtherRegistrations() throws Exception
   {
      String consumerName = "consumer";
      Consumer consumer = manager.createConsumer(consumerName);

      Registration reg = manager.addRegistrationTo(consumerName, registrationProperties, expectations, false);
      String handle = reg.getRegistrationHandle();

      RegistrationStatus status = consumer.getStatus();
      manager.removeRegistration(handle);

      // consumer status shouldn't have changed
      assertEquals(status, consumer.getStatus());

      // Shouldn't be possible to retrieve consumer from handle anymore
      assertNull(manager.getConsumerFor(handle));
   }

   public void testAddRegistrationWithInvalidRegistrationProperties() throws Exception
   {
      String consumerName = "consumer";
      Consumer consumer = manager.createConsumer(consumerName);

      registrationProperties.put(new QName("prop3"), "value3");
      try
      {
         manager.addRegistrationTo(consumerName, registrationProperties, expectations, false);
         fail("Expectations does not contain prop3, so trying to register with a value for it should fail");
      }
      catch (RegistrationException e)
      {
         assertTrue(e.getMessage().contains("prop3"));
         assertEquals(0, consumer.getRegistrations().size());
      }
   }

   public void testRemoveInexistentRegistration() throws RegistrationException
   {
      try
      {
         manager.removeRegistration((Registration)null);
         fail("Should be possible to remove a null registration");
      }
      catch (IllegalArgumentException expected)
      {
      }

      try
      {
         manager.removeRegistration((String)null);
         fail("Should be possible to remove a registration with a null handle");
      }
      catch (IllegalArgumentException expected)
      {
      }

      try
      {
         manager.removeRegistration("");
         fail("Should be possible to remove a registration with an empty handle");
      }
      catch (IllegalArgumentException expected)
      {
      }

      try
      {
         manager.removeRegistration("doesn't exist");
         fail("Should be possible to remove a registration with an invalid handle");
      }
      catch (NoSuchRegistrationException expected)
      {
      }
   }

   public void testClear() throws Exception
   {
      manager.createConsumer("c1");
      manager.createConsumer("c2");
      manager.addConsumerToGroupNamed("c1g1", "g1", true, true);
      manager.createConsumerGroup("g2");
      Registration r1 = manager.addRegistrationTo("c1", registrationProperties, expectations, false);
      Registration r2 = manager.addRegistrationTo("c3", registrationProperties, expectations, true);

      manager.clear();
      assertTrue(manager.getConsumerGroups().isEmpty());
      assertTrue(manager.getConsumers().isEmpty());
      assertNull(manager.getRegistration(r1.getRegistrationHandle()));
      assertNull(manager.getRegistration(r2.getRegistrationHandle()));
   }
}
