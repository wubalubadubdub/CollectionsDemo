import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Created by bearg on 4/29/2016.
 */
public class PriorityHelpDesk extends CategorizedHelpDesk {

    private static final Comparator<Enquiry> BY_CATEGORY =
            new Comparator<Enquiry>() {
                @Override
                public int compare(final Enquiry e1, final Enquiry e2) {
                    // use enum's compareTo method
                    return e1.getCategory().compareTo(e2.getCategory());
                }
            };

    private Queue<Enquiry> enquiries = new PriorityQueue<>(BY_CATEGORY);

    public void processAllEnquiries() {

        Enquiry enquiry;
        while ((enquiry = enquiries.poll()) != null) {
            enquiry.getCustomer().reply("Have you tried turned it off and " +
                    "back on again?");
        }
    }

    @Override
    public boolean enquire(final Customer customer,
                           final Category category) {

        return enquiries.offer(new Enquiry(customer, category));
        // uses PriorityHelpDesk#.enquiries
    }


}
