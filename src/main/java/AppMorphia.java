import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by manuel on 3/15/16.
 */
public class AppMorphia {
    public static void main(String[] args) {
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);

            Morphia morphia = new Morphia();
            morphia.map(Hotel.class).map(Address.class);
            Datastore ds = morphia.createDatastore(mongo, "testdb");

            Hotel hotel = new Hotel();
            hotel.setName("My Hotel");
            hotel.setStars(4);

            Address address = new Address();
            address.setStreet("123 Some street");
            address.setCity("Some city");
            address.setPostCode("123 456");
            address.setCountry("Some country");

            //set address
            hotel.setAddress(address);

            // Save the POJO
            ds.save(hotel);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

}
