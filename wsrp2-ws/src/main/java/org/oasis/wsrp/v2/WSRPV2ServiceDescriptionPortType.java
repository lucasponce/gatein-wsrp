
package org.oasis.wsrp.v2;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "WSRP_v2_ServiceDescription_PortType", targetNamespace = "urn:oasis:names:tc:wsrp:v2:intf")
public interface WSRPV2ServiceDescriptionPortType {


    /**
     * 
     * @param requiresInitCookie
     * @param schemaType
     * @param portletHandles
     * @param customModeDescriptions
     * @param offeredPortlets
     * @param eventDescriptions
     * @param userContext
     * @param userCategoryDescriptions
     * @param extensions
     * @param customWindowStateDescriptions
     * @param registrationPropertyDescription
     * @param supportedOptions
     * @param requiresRegistration
     * @param resourceList
     * @param registrationContext
     * @param mayReturnRegistrationState
     * @param locales
     * @param desiredLocales
     * @param extensionDescriptions
     * @param exportDescription
     * @throws OperationFailed
     * @throws ModifyRegistrationRequired
     * @throws ResourceSuspended
     * @throws InvalidRegistration
     */
    @WebMethod(action = "urn:oasis:names:tc:wsrp:v2:getServiceDescription")
    @RequestWrapper(localName = "getServiceDescription", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", className = "org.oasis.wsrp.v2.GetServiceDescription")
    @ResponseWrapper(localName = "getServiceDescriptionResponse", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", className = "org.oasis.wsrp.v2.ServiceDescription")
    public void getServiceDescription(
        @WebParam(name = "registrationContext", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types")
        RegistrationContext registrationContext,
        @WebParam(name = "desiredLocales", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types")
        List<String> desiredLocales,
        @WebParam(name = "portletHandles", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types")
        List<String> portletHandles,
        @WebParam(name = "userContext", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types")
        UserContext userContext,
        @WebParam(name = "requiresRegistration", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<Boolean> requiresRegistration,
        @WebParam(name = "offeredPortlets", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<List<PortletDescription>> offeredPortlets,
        @WebParam(name = "userCategoryDescriptions", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<List<ItemDescription>> userCategoryDescriptions,
        @WebParam(name = "extensionDescriptions", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<List<ExtensionDescription>> extensionDescriptions,
        @WebParam(name = "customWindowStateDescriptions", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<List<ItemDescription>> customWindowStateDescriptions,
        @WebParam(name = "customModeDescriptions", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<List<ItemDescription>> customModeDescriptions,
        @WebParam(name = "requiresInitCookie", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<CookieProtocol> requiresInitCookie,
        @WebParam(name = "registrationPropertyDescription", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<ModelDescription> registrationPropertyDescription,
        @WebParam(name = "locales", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<List<String>> locales,
        @WebParam(name = "resourceList", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<ResourceList> resourceList,
        @WebParam(name = "eventDescriptions", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<List<EventDescription>> eventDescriptions,
        @WebParam(name = "schemaType", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<ModelTypes> schemaType,
        @WebParam(name = "supportedOptions", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<List<String>> supportedOptions,
        @WebParam(name = "exportDescription", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<ExportDescription> exportDescription,
        @WebParam(name = "mayReturnRegistrationState", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<Boolean> mayReturnRegistrationState,
        @WebParam(name = "extensions", targetNamespace = "urn:oasis:names:tc:wsrp:v2:types", mode = WebParam.Mode.OUT)
        Holder<List<Extension>> extensions)
        throws InvalidRegistration, ModifyRegistrationRequired, OperationFailed, ResourceSuspended
    ;

}
