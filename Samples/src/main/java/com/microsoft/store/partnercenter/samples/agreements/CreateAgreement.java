// -----------------------------------------------------------------------
// <copyright file="CreateAgreement.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.agreements;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.Contact;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.agreements.Agreement;
import com.microsoft.store.partnercenter.models.agreements.AgreementMetaData;
import com.microsoft.store.partnercenter.models.agreements.AgreementType;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;
import org.joda.time.DateTime;

/**
 * A scenario that confirm customer acceptance of Microsoft Cloud agreement.
 */
public class CreateAgreement
        extends BasePartnerScenario
{
    /**
     * Initializes a new instance of the {@link #CreateAgreement} class.
     *
     * @param context The scenario context.
     */
    public CreateAgreement( IScenarioContext context )
    {
        super( "Confirm customer acceptance of Microsoft Cloud agreement", context );
    }

    /**
     * Executes the scenario.
     */
    @Override
    protected void runScenario()
    {
        IPartner partnerOperations = this.getContext().getUserPartnerOperations();
        String customerId = this.obtainCustomerId( "Enter the ID of the customer who confirms acceptance of Microsoft Cloud agreement" );
        String userId = this.obtainCustomerUserId( "Enter the ID of the logged in user in the partner tenant who is providing confirmation on behalf of the partner organization" );

        this.getContext().getConsoleHelper().startProgress( "Retrieving agreement metadata for Microsoft Cloud Agreement" );
        ResourceCollection<AgreementMetaData> metaDataCollection =
                partnerOperations.getAgreements().get();
        this.getContext().getConsoleHelper().stopProgress();

        AgreementMetaData metaData = null;

        for ( AgreementMetaData item : metaDataCollection.getItems() )
        {
            if ( item.getAgreementType().equals( AgreementType.MicrosoftCloudAgreement ) )
            {
                metaData = item;
                break;
            }
        }

        if ( metaData == null )
        {
            this.getContext().getConsoleHelper().error( "No agreement metadata for Microsoft Cloud Agreement found" );
        }
        else
        {
            Contact primaryContact = new Contact();
            primaryContact.setFirstName( "Tania" );
            primaryContact.setLastName( "Carr" );
            primaryContact.setEmail( "SomeEmail@Outlook.com" );
            primaryContact.setPhoneNumber( "1234567890" );

            Agreement agreementToCreate = new Agreement();
            agreementToCreate.setTemplateId( metaData.getTemplateId() );
            agreementToCreate.setType( metaData.getAgreementType() );
            agreementToCreate.setUserId( userId );
            agreementToCreate.setDateAgreed( DateTime.now() );
            agreementToCreate.setPrimaryContact( primaryContact );

            this.getContext().getConsoleHelper().startProgress( "Confirmation customer acceptance of Microsoft Cloud agreement" );
            Agreement agreement =
                    partnerOperations.getCustomers().byId( customerId ).getAgreements().create( agreementToCreate );
            this.getContext().getConsoleHelper().stopProgress();
            this.getContext().getConsoleHelper().writeObject( agreement, "Accepted agreement" );
        }
    }
}
