// -----------------------------------------------------------------------
// <copyright file="GetAgreements.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.agreements;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.agreements.Agreement;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;

/**
 * A scenario that retrieves a confirmation of customer acceptance of Microsoft Cloud agreement.
 */
public class GetAgreements
        extends BasePartnerScenario
{
    /**
     * Initializes a new instance of the {@link #GetAgreements} class.
     *
     * @param context The scenario context.
     */
    public GetAgreements( IScenarioContext context )
    {
        super( "Get confirmation of customer acceptance of Microsoft Cloud agreement", context );
    }

    /**
     * Executes the scenario.
     */
    @Override
    protected void runScenario()
    {
        IPartner partnerOperations = this.getContext().getUserPartnerOperations();
        String customerId = this.obtainCustomerId( "Enter the ID of the customer whom to retrieve their confirmation of acceptance of Microsoft Cloud agreement" );
        this.getContext().getConsoleHelper().startProgress( "Retrieving a confirmation of customer acceptance of Microsoft Cloud agreement" );
        ResourceCollection<Agreement> agreements =
                partnerOperations.getCustomers().byId( customerId ).getAgreements().get();
        this.getContext().getConsoleHelper().stopProgress();
        this.getContext().getConsoleHelper().writeObject( agreements, "Accepted agreements" );
    }
}
