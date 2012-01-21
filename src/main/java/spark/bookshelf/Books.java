package spark.bookshelf;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.routes.ViewRoute;

/**
 * A simple book shelf example showing howto create, get, update and delete book
 * resources.
 */
public class Books {

	private final static Logger logger = LoggerFactory.getLogger(Books.class);

	/**
	 * Map holding the books
	 */
	private static Map<String, Book> books = new HashMap<String, Book>();

	public static void main(String[] args) {

		// Creates a new book resource, will return the ID to the created
		// resource
		// author and title are sent as query parameters e.g.
		// /books?author=Foo&title=Bar
		post(new Route("/books") {
			Random random = new Random();

			@Override
			public Object handle(Request request, Response response) {
				logger.debug("Handle post request");
				String author = request.queryParams("author");
				String title = request.queryParams("title");

				int id = random.nextInt(Integer.MAX_VALUE);
				Book book = new Book(String.valueOf(id), author, title);

				books.put(String.valueOf(id), book);

				response.status(201); // 201 Created
				response.redirect("/books");
				return id;
			}

		});

		// Gets the book resource for the provided id
		get(new ViewRoute("/books/:id", "book.ftl") {
			@Override
			protected Object handle(Request request, Response response, Map<String, Object> model) {
				logger.debug("Handle get request");
				Book book = books.get(request.params(":id"));
				if (book != null) {
					model.put("book", book);
					return "";
				} else {
					response.status(404); // 404 Not found
					return "Book not found";
				}
			}
		});

		// Gets all available book resources (id's)
		get(new ViewRoute("/books", "books.ftl") {
			@Override
			protected Object handle(Request request, Response response, Map<String, Object> model) {
				logger.debug("Handle get request");
				model.put("books", books.values());
				return "";
			}
		});

		// Updates the book resource for the provided id with new information
		// author and title are sent as query parameters e.g.
		// /books/<id>?author=Foo&title=Bar
		put(new Route("/books/:id") {
			@Override
			public Object handle(Request request, Response response) {
				logger.debug("Handle put request");
				String id = request.params(":id");
				Book book = books.get(id);
				if (book != null) {
					String newAuthor = request.queryParams("author");
					String newTitle = request.queryParams("title");
					if (newAuthor != null) {
						book.setAuthor(newAuthor);
					}
					if (newTitle != null) {
						book.setTitle(newTitle);
					}
					response.status(206); // 201 Created
					return "Book with id '" + id + "' changed!";
				} else {
					response.status(404); // 404 Not found
					return "Book not found";
				}
			}
		});

		// Deletes the book resource for the provided id
		delete(new Route("/books/:id") {
			@Override
			public Object handle(Request request, Response response) {
				logger.debug("Handle delete request");
				String id = request.params(":id");
				Book book = books.remove(id);
				if (book != null) {
					response.status(200); // 200 OK
					return "Book with id '" + id + "' deleted!";
				} else {
					response.status(404); // 404 Not found
					return "Book not found";
				}
			}
		});

	}

}