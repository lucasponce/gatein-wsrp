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

package org.gatein.wsrp.producer;

import org.oasis.wsrp.v2.AccessDenied;
import org.oasis.wsrp.v2.BlockingInteractionResponse;
import org.oasis.wsrp.v2.GetMarkup;
import org.oasis.wsrp.v2.InconsistentParameters;
import org.oasis.wsrp.v2.InitCookie;
import org.oasis.wsrp.v2.InvalidCookie;
import org.oasis.wsrp.v2.InvalidHandle;
import org.oasis.wsrp.v2.InvalidRegistration;
import org.oasis.wsrp.v2.InvalidSession;
import org.oasis.wsrp.v2.InvalidUserCategory;
import org.oasis.wsrp.v2.MarkupResponse;
import org.oasis.wsrp.v2.MissingParameters;
import org.oasis.wsrp.v2.OperationFailed;
import org.oasis.wsrp.v2.PerformBlockingInteraction;
import org.oasis.wsrp.v2.PortletStateChangeRequired;
import org.oasis.wsrp.v2.ReleaseSessions;
import org.oasis.wsrp.v2.ReturnAny;
import org.oasis.wsrp.v2.UnsupportedLocale;
import org.oasis.wsrp.v2.UnsupportedMimeType;
import org.oasis.wsrp.v2.UnsupportedMode;
import org.oasis.wsrp.v2.UnsupportedWindowState;

/**
 * @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a>
 * @version $Revision$
 */
public interface MarkupInterface
{
   MarkupResponse getMarkup(GetMarkup getMarkup)
      throws UnsupportedWindowState, InvalidCookie, InvalidSession, AccessDenied,
      InconsistentParameters, InvalidHandle, UnsupportedLocale, UnsupportedMode,
      OperationFailed, MissingParameters, InvalidUserCategory, InvalidRegistration,
      UnsupportedMimeType;

   BlockingInteractionResponse performBlockingInteraction(PerformBlockingInteraction performBlockingInteraction)
      throws InvalidSession, UnsupportedMode, UnsupportedMimeType, OperationFailed,
      UnsupportedWindowState, UnsupportedLocale, AccessDenied, PortletStateChangeRequired,
      InvalidRegistration, MissingParameters, InvalidUserCategory, InconsistentParameters,
      InvalidHandle, InvalidCookie;

   ReturnAny releaseSessions(ReleaseSessions releaseSessions)
      throws InvalidRegistration, OperationFailed, MissingParameters, AccessDenied;

   ReturnAny initCookie(InitCookie initCookie)
      throws AccessDenied, OperationFailed, InvalidRegistration;
}
