package ca.mcgill.ecse.snowshoetours.features;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ToursStepDefinitions {
	private SnowShoeTour sst;
	/**
	 * @author Angela Zhu @angelaxzhu
	 */
    @Given("the following SnowShoeTours system exists")
    public void the_following_snow_shoe_tours_system_exists(io.cucumber.datatable.DataTable dataTable) {    	
    	sst= SnowShoeToursApplication.getSnowShoeTour();
    	List<Map<String,String>> rows = dataTable.asMaps();
    	for (var row:rows) {
    		Date startDate = Date.valueOf(row.get("startDate"));
    		int nrWeeks = Integer.valueOf(row.get("nrWeeks"));
    		int weeklyGuidePrice = Integer.valueOf(row.get("priceOfGuidePerWeek"));
    		
    		sst.setStartDate(startDate);
    		sst.setNrWeeks(nrWeeks);
    		sst.setPriceOfGuidePerWeek(weeklyGuidePrice);
    	}
    }
    
    /**
	 * @author Angela Zhu @angelaxzhu
	 */
    @Given("the participant with email {string} has started their tour")
    public void the_participant_with_email_has_started_their_tour(String email) {
    	List <Participant> participants = sst.getParticipants();
    	for (int p = 0; p<participants.size();p++) {
    		if(participants.get(p).getAccountName() == email) {
    			Participant participant = participants.get(p);
    			participant.startTour();
    		}
    	}
    }
    
    // Antoine
    @Given("the participant with email {string} has paid for their tour")
    public void the_participant_with_email_has_paid_for_their_tour(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    // Antoine
    @Given("the following guides exist in the system")
    public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    // Sameer
    @Given("the participant with email {string} has cancelled their tour")
    public void the_participant_with_email_has_cancelled_their_tour(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Sameer
    @Given("the following snowshoe tours exist in the system")
    public void the_following_snowshoe_tours_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    /**
	 * @author Angela Zhu @angelaxzhu
	 */
    @Given("the following participants exist in the system")
    public void the_following_participants_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {  
    	List<Map<String,String>> rows = dataTable.asMaps();
    	for (var row:rows) {
    		String email = String.valueOf(row.get("email"));
    		String password = String.valueOf(row.get("password"));
    		String name = String.valueOf(row.get("name"));
    		String emergency_contact = String.valueOf(row.get("emergencyContact"));
    		int nr_weeks=Integer.valueOf(row.get("nrWeeks"));
    		int week_from = Integer.valueOf(row.get("weeksAvailableFrom"));
    		int week_until = Integer.valueOf(row.get("weeksAvailableUntil"));
    		boolean lodge_required = Boolean.valueOf(row.get("lodgeRequired"));
    		sst.addParticipant(email, password, name, emergency_contact, nr_weeks, week_from, week_until, lodge_required,null,0);
    		
    	}
        
    }

    /**
	 * @author Angela Zhu @angelaxzhu
	 */
    @Given("the participant with email {string} has finished their tour")
    public void the_participant_with_email_has_finished_their_tour(String email) {
    	List <Participant> participants = sst.getParticipants();
    	for (int p = 0; p<participants.size();p++) {
    		if(participants.get(p).getAccountName() == email) {
    			Participant participant = participants.get(p);
    			participant.finish();
    		}
    	}
    }

    // Jen
    @When("the manager attempts to cancel the tour for email {string}")
    public void the_manager_attempts_to_cancel_the_tour_for_email(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Jen
    @When("the administrator attempts to initiate the snowshoe tour creation process")
    public void the_administrator_attempts_to_initiate_the_snowshoe_tour_creation_process() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Jen
    @When("the manager attempts to finish the tour for the participant with email {string}")
    public void the_manager_attempts_to_finish_the_tour_for_the_participant_with_email(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Antoine
    @When("the manager attempts to start the tours for week {string}")
    public void the_manager_attempts_to_start_the_tours_for_week(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Antoine    
    @When("the manager attempts to confirm payment for email {string} using authorization code {string}")
    public void the_manager_attempts_to_confirm_payment_for_email_using_authorization_code(String string,
            String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Jen
    @Then("the following snowshoe tours shall exist in the system with a guide")
    public void the_following_snowshoe_tours_shall_exist_in_the_system_with_a_guide(
            io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    /**
	 * @author Angela Zhu @angelaxzhu
	 */
    @Then("the participant with email {string} shall be marked as {string}")
    public void the_participant_with_email_shall_be_marked_as(String email, String mark) {
    	List <Participant> participants = sst.getParticipants();
    	boolean participant_exists = false;
    	for (int p = 0; p<participants.size();p++) {
    		if(participants.get(p).getAccountName() == email) {
    			assertEquals(mark,participants.get(p).getStatusFullName());
    			participant_exists = true;
    		}
    	}
    	assertTrue(participant_exists);
    }

    /**
	 * @author Angela Zhu @angelaxzhu
	 */
    @Then("the number of snowshoe tours shall be {string}")
    public void the_number_of_snowshoe_tours_shall_be(String number) {
    	int num_tours = sst.getTours().size();
    	assertEquals(Integer.parseInt(number),num_tours);
    }

    // Jen
    @Then("the system shall raise the error {string}")
    public void the_system_shall_raise_the_error(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Jen
    @Then("a participant account shall not exist with email {string}")
    public void a_participant_account_shall_not_exist_with_email(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Sameer
    @Then("the number of participants shall be {string}")
    public void the_number_of_participants_shall_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Sameer
    @Then("a participant account shall exist with email {string} and a refund of {string} percent")
    public void a_participant_account_shall_exist_with_email_and_a_refund_of_percent(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Sameer
    @Then("a participant account shall exist with email {string} and authorization code {string}")
    public void a_participant_account_shall_exist_with_email_and_authorization_code(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
