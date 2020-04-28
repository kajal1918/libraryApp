Library Application:

User can get the Library details and Book details.

Local Start: Open the project in the IDE and Started the application as Spring Boot application. 
It will load the H2 db with some dummy data. User can run it as "mvn spring-boot:run" as a mvn project.

Endpoints:
1. Get Library List: http://localhost:8080/api/library --GET
2. Get Book List: http://localhost:8080/api/book --GET
3. Get Book List by LibraryId: http://localhost:8080/api/book/library/{libraryId} --GET
4. Get Book by Id: http://localhost:8080/api/book/{bookId} --GET
5. Remove Book by id: http://localhost:8080/api/book/{bookId} --DELETE
6. Add Book: http://localhost:8080/api/book --POST --Request {
                                                                          "title": "Core Java III",
                                                                          "price": 0,
                                                                          "volume": 2,
                                                                          "library_id": 2
                                                                      }
7. Update Book: http://localhost:8080/api/book --PUT  --Request {
                                                                            "title": "Core Java II",
                                                                            "price": 600.0,
                                                                            "volume": 5,
                                                                            "book_id": 2,
                                                                            "library_id": 2
                                                                        }

