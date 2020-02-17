package RestAssured;

import RestAssured.my.reqrest.UserPage;
import RestAssured.resource.request.ResourcePage;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests {

    private static RequestSpecification spec;
    private static RequestSpecification spec2;
    @BeforeAll
    public static void init() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setProxy("gate.swissre.com", 9443)
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
        spec2 = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setProxy("gate.swissre.com", 9443)
                .setBaseUri("https://reqres.in/api")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void someTest() {
        User[] users = given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .extract().as(User[].class);
        assertEquals(users[0].getId(), 1);
        assertEquals(users[0].getUsername(), "Bret");
    }

    @Test
    public void getPostsTest() {
        Posts[] posts = given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .extract().as(Posts[].class);
        System.out.println(posts[0].getBody());
        assertEquals(1, posts[0].getId());
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", posts[0].getTitle());
    }

    @Test
    public void getPostsOneTest() {
        Posts posts = given()
                .spec(spec)
                .when()
                .get("posts/1")
                .then()
                .statusCode(200)
                .extract().as(Posts.class);
        assertEquals(1, posts.getId());
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", posts.getTitle());
    }

    @Test
    public void getTodosTest() {
        Todos[] todos = given()
                .spec(spec)
                .when()
                .get("todos")
                .then()
                .statusCode(200)
                .extract().as(Todos[].class);
        assertEquals(1, todos[0].getId());
        assertEquals(false, todos[0].getCompleted());
    }

    @Test
    public void getTodosOneTest() {
        Todos todos = given()
                .spec(spec)
                .when()
                .get("todos/1")
                .then()
                .statusCode(200)
                .extract().as(Todos.class);
        assertEquals(1, todos.getUserId());
        assertEquals(false, todos.getCompleted());
    }

    @Test
    public void setPostsOneTest() {

        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        UserResponse userResponse = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("users")
                .then().statusCode(201)
                .and()
                .extract().as(UserResponse.class);

        System.out.println(userResponse.getCreatedAt());

     //   assertEquals(1, posts.getId());
     //   assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", posts.getTitle());
    }

    @Test
    public void postSuccessfulLogin() {

        String payload = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        Login login = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("login")
                .then().statusCode(200)
                .and()
                .extract().as(Login.class);

        String neededToken = "QpwL5tke4Pnpja7X4";
        assertEquals(neededToken, login.getToken());
    }

    @Test
    public void getUserList() {
        UserPage userPage = given()
                .spec(spec2)
                .when()
                .get("users?page=2")
                .then()
                .statusCode(200)
                .extract().as(UserPage.class);
        assertEquals(7, userPage.getData()[0].getId());
        assertEquals("Michael", userPage.getData()[0].getFirst_name());
    }

    @Test
    public void getResourceList() {
        ResourcePage resourcePage = given()
                .spec(spec2)
                .when()
                .get("unknown")
                .then()
                .statusCode(200)
                .extract().as(ResourcePage.class);

      //  int elementsNumber = resourcePage.getPer_page();
        int numberOfRed = 0;
        for (int i = 0; i<resourcePage.getData().length; i++){
            if (resourcePage.getData()[i].getName().equals("true red"))
                { numberOfRed++;}
        }

        assertEquals(2, resourcePage.getTotal_pages());
        assertEquals(1, numberOfRed, "Wrong number of red occurencies");
    }

    @Test
    public void postUnsuccessfulLogin() {

        String payload = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +"}";
        Login login = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("login")
                .then().statusCode(400)
                .and()
                .extract().as(Login.class);

        String response = "Missing password";
        assertEquals(response, login.getError());
    }


}
