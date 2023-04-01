package ca.mcgill.ecse.snowshoetours.features;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertNotNull;


import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourCreationController;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ToursStepDefinitions {

    private String error;

    private SnowShoeTour sst;

    /**
     * @author Angela Zhu @angelaxzhu
     */
    @Given("the following SnowShoeTours system exists")
    public void the_following_snow_shoe_tours_system_exists(
            io.cucumber.datatable.DataTable dataTable) {
        sst = SnowShoeToursApplication.getSnowShoeTour();
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
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
        List<Participant> participants = sst.getParticipants();
        for (int p = 0; p < participants.size(); p++) {
            if (participants.get(p).getAccountName() == email) {
                Participant participant = participants.get(p);
                participant.start();
            }
        }
    }

    /**
     * @author Antoine Phan @notkaramel
     * @param string
     */
    @Given("the participant with email {string} has paid for their tour")
    public void the_participant_with_email_has_paid_for_their_tour(String string) {
        // Write code here that turns the phrase above into concrete actions
        for (Participant p : sst.getParticipants()) {
            // Find the participant with the email {string}
            if (p.getAccountName().equals(string)) {
                p.pay();
            }
        }
    }

    /**
     * @author Antoine Phan (@notkaramel)
     * @param dataTable
     */
    @Given("the following guides exist in the system")
    public void the_following_guides_exist_in_the_system(
            io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var r : rows) {
            String email = r.get("email");
            String password = r.get("password");
            String name = r.get("name");
            String emergencyContact = r.get("emergencyContact");

            new Guide(email, password, name, emergencyContact, sst);
        }
    }

    /**
     * @author Sameer Riaz @SRIAZ77
     */
    @Given("the participant with email {string} has cancelled their tour")
    public void the_participant_with_email_has_cancelled_their_tour(String string) {
        Participant p = (Participant) Participant.getWithAccountName(string);
        p.cancel();

    }

    /**
     * @author Sameer Riaz @SRIAZ77
     */
    @Given("the following snowshoe tours exist in the system")
    public void the_following_snowshoe_tours_exist_in_the_system(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
            // Extract data from the given table
            int id = Integer.parseInt(row.get("id"));
            int startWeek = Integer.parseInt(row.get("startWeek"));
            int endWeek = Integer.parseInt(row.get("endWeek"));
            sst.addTour(id, startWeek, endWeek, (Guide) Guide.getWithAccountName(row.get("guide"))); // Add
                                                                                                     // extracted
                                                                                                     // data
        }
    }

    /**
     * @author Angela Zhu @angelaxzhu
     */
    @Given("the following participants exist in the system")
    public void the_following_participants_exist_in_the_system(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
            String email = String.valueOf(row.get("email"));
            String password = String.valueOf(row.get("password"));
            String name = String.valueOf(row.get("name"));
            String emergency_contact = String.valueOf(row.get("emergencyContact"));
            int nr_weeks = Integer.valueOf(row.get("nrWeeks"));
            int week_from = Integer.valueOf(row.get("weeksAvailableFrom"));
            int week_until = Integer.valueOf(row.get("weeksAvailableUntil"));
            boolean lodge_required = Boolean.valueOf(row.get("lodgeRequired"));
            sst.addParticipant(email, password, name, emergency_contact, nr_weeks, week_from,
                    week_until, lodge_required, null, 0);

        }

    }

    /**
     * @author Angela Zhu @angelaxzhu
     */
    @Given("the participant with email {string} has finished their tour")
    public void the_participant_with_email_has_finished_their_tour(String email) {
        List<Participant> participants = sst.getParticipants();
        for (int p = 0; p < participants.size(); p++) {
            if (participants.get(p).getAccountName() == email) {
                Participant participant = participants.get(p);
                participant.finish();
            }
        }
    }

    /*
     * @author Jennifer Tram Su @jennifertramsu
     */
    @When("the manager attempts to cancel the tour for email {string}")
    public void the_manager_attempts_to_cancel_the_tour_for_email(String string) {
        error = SnowShoeTourCreationController.cancelParticipantTrip(string);
    }

    /*
     * @author Jennifer Tram Su @jennifertramsu
     */
    @When("the administrator attempts to initiate the snowshoe tour creation process")
    public void the_administrator_attempts_to_initiate_the_snowshoe_tour_creation_process() {
        error = SnowShoeTourCreationController.initiateSnowToursCreation();
    }

    /*
     * @author Jennifer Tram Su @jennifertramsu
     */
    @When("the manager attempts to finish the tour for the participant with email {string}")
    public void the_manager_attempts_to_finish_the_tour_for_the_participant_with_email(
            String string) {
        error = SnowShoeTourCreationController.finishParticipantTrip(string);
    }

    /**
     * @author Antoine Phan @notkaramel
     * @param string
     */
    @When("the manager attempts to start the tours for week {string}")
    public void the_manager_attempts_to_start_the_tours_for_week(String string) {
        // Write code here that turns the phrase above into concrete actions
        // for (Tour t : sst.getTours()) {
        //     if (t.getStartWeek() == Integer.parseInt(string)) {
        //         for (Participant p : t.getParticipants()) {
        //             SnowShoeTourCreationController.startAllTripsForSpecificWeek(0)
        //         }
        //     }
        // }
        error = SnowShoeTourCreationController.startAllTripsForSpecificWeek(Integer.parseInt(string));
    }

    /**
     * @author Antoine Phan @notkaramel
     * @param string email/account name of participant
     * @param string2 authorization code for the payment
     */
    @When("the manager attempts to confirm payment for email {string} using authorization code {string}")
    public void the_manager_attempts_to_confirm_payment_for_email_using_authorization_code(
            String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        error = SnowShoeTourCreationController.payForTrip(string, string2);
    }

    /*
     * @author Jennifer Tram Su @jennifertramsu
     */
    @Then("the following snowshoe tours shall exist in the system with a guide")
    public void the_following_snowshoe_tours_shall_exist_in_the_system_with_a_guide(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        
        // {id, startWeek, endWeek, participants}
        for (var row : rows) {
            Integer id = Integer.parseInt(row.get("id"));
            Integer startWeek = Integer.parseInt(row.get("startWeek"));
            Integer endWeek = Integer.parseInt(row.get("endWeek"));
            String email = row.get("participants");

            // Grab tour
            List<Tour> tours = sst.getTours();

            // Check if user is participant or guide
            boolean isP = false;
            for (Participant p : sst.getParticipants()) {
                if (p.getAccountName().equals(email)) {
                    isP = true;
                }
            }

            if (isP) {
                Participant p = (Participant) User.getWithAccountName(email);

                for (Tour tour : tours) {
                    assertTrue(id.equals(tour.getId()));
                    assertTrue(startWeek.equals(tour.getStartWeek()));
                    assertTrue(endWeek.equals(tour.getEndWeek()));
                    assertTrue(email.equals(p.getAccountName()));
                }
            } else {
                Guide g = (Guide) User.getWithAccountName(email);

                for (Tour tour : tours) {
                    assertTrue(id.equals(tour.getId()));
                    assertTrue(startWeek.equals(tour.getStartWeek()));
                    assertTrue(endWeek.equals(tour.getEndWeek()));
                    assertTrue(email.equals(g.getAccountName()));
                }
            }
        }
    }

    /**
     * @author Angela Zhu @angelaxzhu
     */
    @Then("the participant with email {string} shall be marked as {string}")
    public void the_participant_with_email_shall_be_marked_as(String email, String mark) {
        List<Participant> participants = sst.getParticipants();
        for (int p = 0; p < participants.size(); p++) {
            if (participants.get(p).getAccountName() == email) {
                assertEquals(mark, participants.get(p).getStatusFullName());
            }
        }
    }

    /**
     * @author Angela Zhu @angelaxzhu
     */
    @Then("the number of snowshoe tours shall be {string}")
    public void the_number_of_snowshoe_tours_shall_be(String number) {
        int num_tours = sst.getTours().size();
        assertEquals(Integer.parseInt(number), num_tours);
    }

    /**
     * @author Sameer Riaz @SRIAZ77
     */
    @Then("the system shall raise the error {string}")
    public void the_system_shall_raise_the_error(String string) {
        assertEquals(string, error);
    }

    /**
     * @author Sameer Riaz @SRIAZ77
     */
    @Then("a participant account shall not exist with email {string}")
    public void a_participant_account_shall_not_exist_with_email(String string) {
        assert((Participant) Participant.getWithAccountName(string) == null);
    }

    /**
     * @author Sameer Riaz @SRIAZ77
     */
    @Then("the number of participants shall be {string}")
    public void the_number_of_participants_shall_be(String string) {
        assertEquals(Integer.parseInt(string), sst.getParticipants().size());
    }

    /**
     * @author Sameer Riaz @SRIAZ77
     */
    @Then("a participant account shall exist with email {string} and a refund of {string} percent")
    public void a_participant_account_shall_exist_with_email_and_a_refund_of_percent(String string,
            String string2) {
        assertNotNull((Participant) Participant.getWithAccountName(string));
        Participant p = (Participant) Participant.getWithAccountName(string);
        assertEquals(Integer.parseInt(string2), p.getRefundedPercentageAmount());
    }

    /**
     * @author Sameer Riaz @SRIAZ77
     */
    @Then("a participant account shall exist with email {string} and authorization code {string}")
    public void a_participant_account_shall_exist_with_email_and_authorization_code(String string,
            String string2) {
        assertNotNull((Participant) Participant.getWithAccountName(string));
        Participant p = (Participant) Participant.getWithAccountName(string);
        assertEquals(string2, p.getAuthorizationCode());
    }
}
