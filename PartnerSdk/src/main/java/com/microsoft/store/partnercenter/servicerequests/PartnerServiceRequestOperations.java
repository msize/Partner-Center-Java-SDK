// -----------------------------------------------------------------------
// <copyright file="PartnerServiceRequestOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.servicerequests;

import java.text.MessageFormat;
import java.util.Locale;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponent;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.servicerequests.ServiceRequest;
import com.microsoft.store.partnercenter.models.utils.Tuple;
import com.microsoft.store.partnercenter.network.IPartnerServiceProxy;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * Implements operations that can be performed on a single partner's service requests.
 */
public class PartnerServiceRequestOperations
    extends BasePartnerComponent<Tuple<String, String>>
    implements IServiceRequest
{
    /**
     * Initializes a new instance of the {@link #PartnerServiceRequestOperations} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param serviceRequestId The service request Id.
     */
    public PartnerServiceRequestOperations( IPartner rootPartnerOperations, String serviceRequestId )
    {
        super( rootPartnerOperations, new Tuple<String, String>( serviceRequestId, "" ) );
        if ( StringHelper.isNullOrWhiteSpace( serviceRequestId ) )
        {
            throw new IllegalArgumentException( "serviceRequestId can't be null" );
        }

    }

    /**
     * Get Service Request By ID
     * 
     * @return Service Request
     */
    @Override
    public ServiceRequest get()
    {
        IPartnerServiceProxy<ServiceRequest, ServiceRequest> partnerServiceProxy =
            new PartnerServiceProxy<ServiceRequest, ServiceRequest>( new TypeReference<ServiceRequest>()
            {
            }, this.getPartner(), MessageFormat.format( PartnerService.getInstance().getConfiguration().getApis().get( "GetServiceRequestPartner" ).getPath(),
                                                        this.getContext().getItem1(), Locale.US ) );

        return partnerServiceProxy.get();
    }

    /**
     * Patches a Service Request
     * 
     * @param updatePayload Payload of the update request
     * @return Updated Service Request
     */
    @Override
    public ServiceRequest patch( ServiceRequest updatePayload )
    {
        IPartnerServiceProxy<ServiceRequest, ServiceRequest> partnerServiceProxy =
            new PartnerServiceProxy<ServiceRequest, ServiceRequest>( new TypeReference<ServiceRequest>()
            {
            }, this.getPartner(), MessageFormat.format( PartnerService.getInstance().getConfiguration().getApis().get( "GetServiceRequestPartner" ).getPath(),
                                                        this.getContext().getItem1(), Locale.US ) );

        return partnerServiceProxy.patch( updatePayload );
    }
}
