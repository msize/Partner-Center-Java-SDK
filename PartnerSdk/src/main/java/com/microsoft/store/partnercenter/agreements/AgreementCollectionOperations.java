// -----------------------------------------------------------------------
// <copyright file="AgreementCollectionOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.agreements;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.agreements.Agreement;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;
import com.microsoft.store.partnercenter.utils.StringHelper;

import java.text.MessageFormat;
import java.util.Locale;

public class AgreementCollectionOperations
        extends BasePartnerComponentString
        implements IAgreementCollection
{
    /**
     * Initializes a new instance of the {@link #AgreementCollectionOperations} class.
     *
     * @param rootPartnerOperations The root partner operations instance.
     */
    public AgreementCollectionOperations( IPartner rootPartnerOperations, String customerId )
    {
        super( rootPartnerOperations, customerId );
        if ( StringHelper.isNullOrWhiteSpace(customerId) )
        {
            throw new IllegalArgumentException( "customerId must be set" );
        }
    }

    /***
     * Adds accepted agreement.
     *
     * @param newEntity Agreement to add.
     *
     * @return Agreement entity.
     */
    @Override
    public Agreement create( Agreement newEntity )
    {
        if ( newEntity == null )
        {
            throw new IllegalArgumentException( "entity can't be null." );
        }

        return new PartnerServiceProxy<Agreement, Agreement>(new TypeReference<Agreement>() {}, this.getPartner(),
                MessageFormat.format( PartnerService.getInstance().getConfiguration().getApis()
                        .get( "AddAgreement" ).getPath(), this.getContext(), Locale.US ) ).post( newEntity );
    }

    /***
     * Retrieves all agreements.
     *
     * @return The agreements.
     */
    @Override
    public ResourceCollection<Agreement> get()
    {
        return new PartnerServiceProxy<Agreement, ResourceCollection<Agreement>>(
                new TypeReference<ResourceCollection<Agreement>>() {}, this.getPartner(),
                MessageFormat.format( PartnerService.getInstance().getConfiguration().getApis()
                                .get( "GetAgreements" ).getPath(),
                        this.getContext(), Locale.US ) ).get();
    }
}
