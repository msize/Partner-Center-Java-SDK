// -----------------------------------------------------------------------
// <copyright file="GetAgreementMetadata.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.agreements;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.agreements.AgreementMetaData;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;

/**
 * A scenario that retrieves agreement metadata.
 */
public class GetAgreementMetadata
        extends BasePartnerScenario
{
    /**
     * Initializes a new instance of the {@link #GetAgreementMetadata} class.
     *
     * @param context The scenario context.
     */
    public GetAgreementMetadata( IScenarioContext context )
    {
        super( "Get agreement metadata", context );
    }

    /**
     * Executes the scenario.
     */
    @Override
    protected void runScenario()
    {
        IPartner partnerOperations = this.getContext().getUserPartnerOperations();
        this.getContext().getConsoleHelper().startProgress( "Retrieving agreement metadata" );
        ResourceCollection<AgreementMetaData> agreementMetaData =
                partnerOperations.getAgreements().get();
        this.getContext().getConsoleHelper().stopProgress();
        if (agreementMetaData.getItems().iterator().hasNext())
        {
            this.getContext().getConsoleHelper().writeObject( agreementMetaData, "Agreement metadata" );
        }
        else
        {
            this.getContext().getConsoleHelper().warning( "No agreement metadata found" );
        }
    }
}
