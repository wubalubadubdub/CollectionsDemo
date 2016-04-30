import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Created by bearg on 4/28/2016.
 */
public class CategorizedHelpDesk {

    private final Queue<Enquiry> enquiries = new ArrayDeque<>();

    public void processPrinterEnquiry() {

        /*Enquiry enquiry;

        // we need to put enquiry = equiries.poll() in the while loop instead of
        // just enquiries.poll() so that the value of enquiry is changed after
        // each trip through the loop
        while((enquiry = enquiries.poll()) != null) {

            enquiry.getCustomer().reply("Have you tried turning" +
                    " it off and on again?");

        }*/

        // Option 2
        /*while (!enquiries.isEmpty()) { // make sure we don't call remove on empty queue
            final Enquiry enquiry = enquiries.remove();
            enquiry.getCustomer().reply("Have you tried turning" +
                    " it off and on again?");
        }*/

        // look at next enquiry and handle it if we can. otherwise, leave it on the queue
        // for someone else to handle.

        processEnquiry(enq -> enq.getCategory() == Category.PRINTER, // predicate param
                "Is it out of paper?");
    }

    public void processGeneralEnquiry() {

        processEnquiry(enq -> enq.getCategory() != Category.PRINTER, // predicate param
                "Have you tried turning it off and on again?");

    }

    private void processEnquiry(final Predicate<Enquiry> predicate, final String message) {
        final Enquiry enquiry = enquiries.peek();
        if (enquiry != null && predicate.test(enquiry)) {

            enquiries.remove(); // after processing an enquiry, remove it

            enquiry.getCustomer().reply(message);

        } else { // no more enquiries or it is in the printer category
            System.out.println("No work to do. Let's have some coffee.");
        }
    }

    // method to add an Enquiry to the queue of Enquiries
    public boolean enquire(final Customer customer,
                           final Category category) {
        return enquiries.offer(new Enquiry(customer, category));
        // uses CategorizedHelpDesk#.enquiries
    }

    public static void main(String[] args) {

        CategorizedHelpDesk helpDesk = new PriorityHelpDesk();

        helpDesk.enquire(Customer.JACK, Category.PHONE); // 3rd highest
        helpDesk.enquire(Customer.JILL, Category.PRINTER); // highest priority
        helpDesk.enquire(Customer.MARY, Category.COMPUTER); // 2nd highest

        ((PriorityHelpDesk) helpDesk).processAllEnquiries(); // downcast required -- method isn't in superclass

        Calculator calc = new Calculator();
        int answer = calc.evaluate(Calculator.TEST_INPUT);
        System.out.println(answer);

    }


}
