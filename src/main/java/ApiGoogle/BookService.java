
package ApiGoogle;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.model.Volumes;

public class BookService {

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final Books books;

    public BookService(String apiKey) throws Exception {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        books = new Books.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName("Repaginando")
                .setGoogleClientRequestInitializer(request -> request.put("key", apiKey))
                .build();
    }

    public Volumes search(String query) throws Exception {
    return books.volumes().list(query).setMaxResults(5L).execute();
}
}
